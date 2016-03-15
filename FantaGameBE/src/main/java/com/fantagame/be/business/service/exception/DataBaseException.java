package com.fantagame.be.business.service.exception;

import org.apache.log4j.Logger;

public class DataBaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4906867864069081679L;
	
	public DataBaseException() {
    }

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(Throwable cause) {
        super(cause);
    }    
    
    public DataBaseException(String message, Throwable cause,boolean logged,Class<?> c) {
    	super(message, cause);
    	
    	if(logged)
    		Logger.getLogger(c).error(message,cause);
    	
    }
}
