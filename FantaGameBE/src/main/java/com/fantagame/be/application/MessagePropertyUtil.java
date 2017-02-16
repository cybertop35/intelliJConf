package com.fantagame.be.application;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.NoSuchMessageException;

import com.fantagame.be.business.data.DataConstants;

public class MessagePropertyUtil implements MessageSourceAware {

	private static  MessageSource ms;
	
	private static Logger log = Logger.getLogger(MessagePropertyUtil.class);
	
	@SuppressWarnings("static-access")
	@Override
	public void setMessageSource(MessageSource ms) {	
		this.ms = ms;		
	} 
	
	public static String getMessage(String key){
		try{
			return ms.getMessage(key, null, Locale.getDefault());
		}catch(NoSuchMessageException e){
			log.warn("No message found Key: "+ key +" Locale: "+Locale.getDefault().getDisplayName());
		}
		
		return DataConstants.NO_MESSAGE;
	}
	
	public static String getMessageLocale(String key,Locale l){
		try{
			return ms.getMessage(key, null, l);
		}catch(NoSuchMessageException e){
			log.warn("No message found Key: "+ key +" Locale: "+l.getDisplayName());
		}
		return DataConstants.NO_MESSAGE;
	}

}
