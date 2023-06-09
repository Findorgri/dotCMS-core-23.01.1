package com.ettrema.httpclient;


import com.dotcms.repackage.org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.io.IOUtils;
import com.dotcms.repackage.org.slf4j.Logger;
import com.dotcms.repackage.org.slf4j.LoggerFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.jdom.Document;
import org.jdom.JDOMException;

/**
 *
 * @author brad
 */
public class ReportMethod extends EntityEnclosingMethod {

    private static final Logger log = LoggerFactory.getLogger( PropFindMethod.class );

    public ReportMethod( String uri ) {
        super( uri );
    }

    @Override
    public String getName() {
        return "REPORT";
    }

    public Document getResponseAsDocument() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = getResponseBodyAsStream();
        IOUtils.copy( in, out );
        String xml = out.toString();
        try {

            Document document = RespUtils.getJDomDocument( new ByteArrayInputStream( xml.getBytes() ) );
            return document;
        } catch( JDOMException ex ) {
            throw new RuntimeException(xml, ex );
        }
    }
}
