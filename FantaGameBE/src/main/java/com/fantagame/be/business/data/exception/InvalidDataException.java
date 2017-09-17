package com.fantagame.be.business.data.exception;

import org.apache.log4j.Logger;

import com.fantagame.be.business.data.DataConstants;
import com.fantagame.be.business.data.dao.Iface.GenericDAO;

public class InvalidDataException extends RuntimeException {
    
	/**
	 * 
   */
	private static final long serialVersionUID = -759480545618741157L;

	private static org.apache.log4j.Logger log = Logger.getLogger(InvalidDataException.class);
	
 
 

    public InvalidDataException(Throwable cause) {
        super(cause);
    }

    
    @SuppressWarnings({ "unused", "rawtypes" } )
	public InvalidDataException(String message, Throwable cause,boolean logged, Class<? extends GenericDAO > c) {
    	super(message, cause);
    	if(logged && !DataConstants.LOGGER_GLOBAL)
    		Logger.getLogger(c).error(message, cause);
    	else if(logged && DataConstants.LOGGER_GLOBAL)
    		log.error(message, cause);
        
    	
        
    }

	@SuppressWarnings({ "unused", "rawtypes" })
	public InvalidDataException(String message, boolean logged,Class<? extends GenericDAO > c) {
		super(message);
		if(logged && !DataConstants.LOGGER_GLOBAL)
			Logger.getLogger(c).error(message);
		else if(logged && DataConstants.LOGGER_GLOBAL)
			log.error(message);
	}
}
