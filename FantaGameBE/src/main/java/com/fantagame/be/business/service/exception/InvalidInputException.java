package com.fantagame.be.business.service.exception;

import org.apache.log4j.Logger;

public class InvalidInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4906867864069081679L;
	
	public InvalidInputException() {
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(Throwable cause) {
        super(cause);
    }    
    
    public InvalidInputException(String message, Throwable cause,boolean logged,Class<?> c) {
    	super(message, cause);
    	
    	if(logged)
    		Logger.getLogger(c).error(message);
    	
    }

}
