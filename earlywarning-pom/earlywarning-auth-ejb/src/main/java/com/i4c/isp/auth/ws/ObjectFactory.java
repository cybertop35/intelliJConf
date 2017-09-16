
package com.i4c.isp.auth.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.i4c.isp.auth.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _String_QNAME = new QName("http://sanpaoloimi.com/SSO", "string");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.i4c.isp.auth.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetProfile2 }
     * 
     */
    public GetProfile2 createGetProfile2() {
        return new GetProfile2();
    }

    /**
     * Create an instance of {@link GetProfile2Response }
     * 
     */
    public GetProfile2Response createGetProfile2Response() {
        return new GetProfile2Response();
    }

    /**
     * Create an instance of {@link GetCache }
     * 
     */
    public GetCache createGetCache() {
        return new GetCache();
    }

    /**
     * Create an instance of {@link GetCacheResponse }
     * 
     */
    public GetCacheResponse createGetCacheResponse() {
        return new GetCacheResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sanpaoloimi.com/SSO", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

}
