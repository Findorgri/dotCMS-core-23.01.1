package com.dotcms.contenttype.model.type;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableSimpleContentType.class)
@JsonDeserialize(as = ImmutableSimpleContentType.class)
@Value.Immutable
public abstract class SimpleContentType extends ContentType
        implements UrlMapable, Serializable, Expireable {

    private static final long serialVersionUID = 1L;

    @Override
    public BaseContentType baseType() {
        return BaseContentType.CONTENT;
    }

    public abstract static class Builder implements ContentTypeBuilder {
    }

}
