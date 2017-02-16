package com.fantagame.be.business.service.exception;

import org.apache.log4j.Logger;

public class MailException extends Exception {

private static final long serialVersionUID = 4906867864069081679L;
	
	public MailException() {
    }

    public MailException(String message) {
        super(message);
    }

    public MailException(Throwable cause) {
        super(cause);
    }    
    
    public MailException(String message, Throwable cause,boolean logged,Class<?> c) {
    	super(message, cause);
    	
    	if(logged)
    		Logger.getLogger(c).error(message,cause);
    	
    }
}
