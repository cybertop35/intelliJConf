package org.cybertop.back.config.exception;

import org.apache.log4j.Logger;

public class DatabaseConfigurationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8632486681682792353L;
	private static final Logger log = Logger.getLogger(DatabaseConfigurationException.class);
	
	public DatabaseConfigurationException (Throwable t){
		super(t);
		log.error(t.getMessage(),t);
	}
	
	public DatabaseConfigurationException (Throwable t,String meString){
		super(t);
		log.error(meString,t);
	}
}
