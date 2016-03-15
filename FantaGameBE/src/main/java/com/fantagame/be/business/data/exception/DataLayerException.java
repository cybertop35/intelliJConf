package com.fantagame.be.business.data.exception;

import org.apache.log4j.Logger;

import com.fantagame.be.business.data.DataConstants;
import com.fantagame.be.business.data.dao.Iface.GenericDAO;

public class DataLayerException extends RuntimeException {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -759480545615741157L;

	private static org.apache.log4j.Logger log = Logger.getLogger(DataLayerException.class);
	
	public DataLayerException() {
    }

    public DataLayerException(String message) {
        super(message);
    }

    public DataLayerException(Throwable cause) {
        super(cause);
    }    
    

    @SuppressWarnings({ "unused", "rawtypes" })
	public DataLayerException(String message, Throwable cause,boolean logged,Class<? extends GenericDAO > c) {
    	super(message, cause);
    	if(logged && !DataConstants.LOGGER_GLOBAL)
    		 Logger.getLogger(c).error(message, cause);
    	else if(logged && DataConstants.LOGGER_GLOBAL)
    		log.error(message, cause);
    	
        
    }
}
