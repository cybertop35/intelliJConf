
package com.isp.auth.ws;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2015-09-28T12:48:06.551+02:00
 * Generated source version: 3.0.4
 * 
 */
public final class ServiceHttpGet_ServiceHttpGet_Client {

    private static final QName SERVICE_NAME = new QName("http://sanpaoloimi.com/SSO", "Service");

    private ServiceHttpGet_ServiceHttpGet_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = Service.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        Service ss = new Service(wsdlURL, SERVICE_NAME);
        ServiceHttpGet port = ss.getServiceHttpGet();  
        
        {
        System.out.println("Invoking getCache...");
        java.lang.String _getCache__return = port.getCache();
        System.out.println("getCache.result=" + _getCache__return);


        }
        {
        System.out.println("Invoking getProfile2...");
        java.lang.String _getProfile2_token = "";
        java.lang.String _getProfile2_siteName = "";
        java.lang.String _getProfile2_timeStamp = "";
        java.lang.String _getProfile2_encodedPwd = "";
        java.lang.String _getProfile2_encodedType = "";
        java.lang.String _getProfile2_filter = "";
        java.lang.String _getProfile2__return = port.getProfile2(_getProfile2_token, _getProfile2_siteName, _getProfile2_timeStamp, _getProfile2_encodedPwd, _getProfile2_encodedType, _getProfile2_filter);
        System.out.println("getProfile2.result=" + _getProfile2__return);


        }

        System.exit(0);
    }

}
