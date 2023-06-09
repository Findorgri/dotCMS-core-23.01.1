package com.dotmarketing.portlets.htmlpageasset.business.render;


import com.dotcms.api.vtl.model.DotJSON;
import com.dotcms.rendering.velocity.services.PageRenderUtil;
import com.dotcms.rendering.velocity.services.VelocityResourceKey;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.exception.DotDataException;
import com.dotmarketing.exception.DotSecurityException;
import com.dotmarketing.portlets.htmlpageasset.model.HTMLPageAsset;
import com.dotmarketing.util.Logger;
import com.dotmarketing.util.PageMode;
import com.dotmarketing.util.VelocityUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import io.vavr.control.Try;
import org.apache.velocity.context.Context;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Builder of {@link ContainerRendered}
 */
public class ContainerRenderedBuilder {


    final PageMode mode;
    final Context velocityContext;
    final List<ContainerRaw> containerRaws;


    public ContainerRenderedBuilder(final HTMLPageAsset page, final PageMode mode) throws DotDataException, DotSecurityException {
        this(new PageRenderUtil(page, APILocator.systemUser(), mode).getContainersRaw(), null, mode);


    }

    public ContainerRenderedBuilder(List<ContainerRaw> raws, final Context velocityContext, final PageMode mode) {
        this.containerRaws = raws;
        this.velocityContext = velocityContext;
        this.mode = mode;

    }

    public Collection<? extends ContainerRaw> build() {
        if (velocityContext == null) {
            return this.containerRaws;
        }

        return this.containerRaws.stream().map(containerRaw -> {
            try {
                final Map<String, Object> uuidsRendered = render(velocityContext, mode, containerRaw);
                return new ContainerRendered(containerRaw, uuidsRendered);
            } catch (Exception e) {
                // if the container does not exists or is not valid for the mode, returns null to be filtrated
                return null;

            }
        }).filter(Objects::nonNull).collect(Collectors.toList());


    }


    public static Map<String, Object> render(final Context velocityContext, final PageMode mode, final ContainerRaw containerRaw) {

        final Map<String, Object> rendered = Maps.newHashMap();
        for (final String uuid : containerRaw.getContentlets().keySet()) {
            final VelocityResourceKey key = new VelocityResourceKey(containerRaw.getContainer(),
                    uuid.replace("uuid-", ""), mode);
            try {
                velocityContext.put("dotJSON", new DotJSON());
                final String renderedContainer = VelocityUtil.getInstance()
                        .mergeTemplate(key.path, velocityContext);
                final DotJSON dotJSON = (DotJSON) velocityContext.get("dotJSON");
                final boolean parseJSON = Try.of(()->(boolean) velocityContext.get("parseJSON"))
                        .getOrElse(false);

                if(dotJSON.size()>0 && parseJSON) {
                    rendered.put(uuid, dotJSON.getMap());
                } else {
                    rendered.put(uuid, renderedContainer);
                }

            } catch (Exception e) {
                Logger.warn(ContainerRenderedBuilder.class, e.getMessage());
            }
        }
        return rendered;
    }
}
