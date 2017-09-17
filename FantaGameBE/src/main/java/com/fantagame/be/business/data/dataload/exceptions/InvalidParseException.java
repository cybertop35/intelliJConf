package com.fantagame.be.business.data.dataload.exceptions;

import org.apache.log4j.Logger;

import com.fantagame.be.business.data.DataConstants;
import com.fantagame.be.business.data.dataload.parse.IFace.IParse;

public class InvalidParseException extends Throwable {
    
	/**
	 * 
   */
	private static final long serialVersionUID = -759480545618741157L;

	private static org.apache.log4j.Logger log = Logger.getLogger(InvalidParseException.class);
	
 
 

    public InvalidParseException(Throwable cause) {
        super(cause);
    }

    
    @SuppressWarnings({ "unused", "rawtypes" } )
	public InvalidParseException(String message, Throwable cause,boolean logged, Class<? extends IParse > c) {
    	super(message, cause);
    	if(logged && !DataConstants.LOGGER_GLOBAL)
    		Logger.getLogger(c).error(message, cause);
    	else if(logged && DataConstants.LOGGER_GLOBAL)
    		log.error(message, cause);
        
    	
        
    }

	@SuppressWarnings({ "unused", "rawtypes" })
	public InvalidParseException(String message, boolean logged, Class<? extends IParse > c) {
		super(message);
		if(logged && !DataConstants.LOGGER_GLOBAL)
			Logger.getLogger(c).error(message);
		else if(logged && DataConstants.LOGGER_GLOBAL)
			log.error(message);
	}

}

