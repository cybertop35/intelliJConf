
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.i4c.isp.client;

import java.util.logging.Logger;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2015-11-10T10:37:30.853+01:00
 * Generated source version: 3.0.4
 * 
 */

@javax.jws.WebService(
                      serviceName = "executeDailyBatchWS",
                      portName = "executeDailyBatchPort",
                      targetNamespace = "http://service.isp.i4c.com/",
                      wsdlLocation = "file:/c:/Dati/DEV/Doc/Intesa/WSDL_BATCH.wsdl",
                      endpointInterface = "com.i4c.isp.client.ExecuteDailyBatch")
                      
public class ExecuteDailyBatchPortImpl implements ExecuteDailyBatch {

    private static final Logger LOG = Logger.getLogger(ExecuteDailyBatchPortImpl.class.getName());

    /* (non-Javadoc)
     * @see com.i4c.isp.client.ExecuteDailyBatch#executeWorkflow(java.lang.String  arg0 )*
     */
    public java.lang.String executeWorkflow(java.lang.String arg0) { 
        LOG.info("Executing operation executeWorkflow");
        System.out.println(arg0);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
