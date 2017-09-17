package com.fantagame.be.business.service.exception;

import java.security.cert.CertificateException;

import org.apache.log4j.Logger;


public class CryptoException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4906867864069081679L;
	
	public CryptoException() {
    }
 
    public CryptoException(String message) {
        super(message);
    }

    public CryptoException(Throwable cause) {
        super(cause);
    }    

	public CryptoException(String message, Throwable cause,boolean logged,Class<?> c) {
    	super(message, cause);
    	if(logged)
    		Logger.getLogger(c).error(message,cause);
    	
    }

	public CryptoException(String string, CertificateException e) {
		super(string, e);
	}
}
