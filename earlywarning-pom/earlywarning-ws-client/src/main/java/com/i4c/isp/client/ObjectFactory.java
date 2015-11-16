
package com.i4c.isp.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.i4c.isp.client package. 
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

    private final static QName _ExecuteWorkflow_QNAME = new QName("http://service.isp.i4c.com/", "executeWorkflow");
    private final static QName _ExecuteWorkflowResponse_QNAME = new QName("http://service.isp.i4c.com/", "executeWorkflowResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.i4c.isp.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExecuteWorkflow }
     * 
     */
    public ExecuteWorkflow createExecuteWorkflow() {
        return new ExecuteWorkflow();
    }

    /**
     * Create an instance of {@link ExecuteWorkflowResponse }
     * 
     */
    public ExecuteWorkflowResponse createExecuteWorkflowResponse() {
        return new ExecuteWorkflowResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteWorkflow }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.isp.i4c.com/", name = "executeWorkflow")
    public JAXBElement<ExecuteWorkflow> createExecuteWorkflow(ExecuteWorkflow value) {
        return new JAXBElement<ExecuteWorkflow>(_ExecuteWorkflow_QNAME, ExecuteWorkflow.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteWorkflowResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.isp.i4c.com/", name = "executeWorkflowResponse")
    public JAXBElement<ExecuteWorkflowResponse> createExecuteWorkflowResponse(ExecuteWorkflowResponse value) {
        return new JAXBElement<ExecuteWorkflowResponse>(_ExecuteWorkflowResponse_QNAME, ExecuteWorkflowResponse.class, null, value);
    }

}
