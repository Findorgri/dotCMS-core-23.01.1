package com.dotcms.util.marshal;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Type;

/**
 * Encapsulates the logic to marshal an object to JSON and viceversa.
 *
 * @author jsanca
 * @version 3.7
 * @since Jun 14, 2016
 */
public interface MarshalUtils extends Serializable {

    /**
     * Marshal an object to json
     *
     * @param object Object
     */
    public String marshal(final Object object);

    /**
     * Marshal an object to json using the writer
     *
     * @param writer Writer
     * @param object Object
     */
    //TODO: And this isn't used at all
    public void marshal(final Writer writer, final Object object);

    /**
     * Un marshal from a reader to a object with the clazz type
     *
     * @param s String
     * @param clazz Class
     * @return T
     */
    public <T> T unmarshal(final String s, Class<? extends T> clazz);

    /**
     * Un marshal from a String into a Type
     */
    public <T> T unmarshal(String s, TypeReference<T> typeOfT);

    /**
     * Un marshal from a reader to a object with the clazz type
     *
     * @param reader Reader
     * @param clazz Class
     * @return T
     */
    //TODO: This is used only on test cases
    public <T> T unmarshal(final Reader reader, Class<? extends T> clazz);

    /**
     * Un marshal from an input stream to a object with the clazz type
     *
     * @param inputStream InputStream
     * @param clazz Class
     * @return T
     */
    //TODO: This is used only on test cases
    public <T> T unmarshal(final InputStream inputStream, final Class<T> clazz);


} // E:O:F:MarshalUtils.
