package com.fantagame.be.application;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext ctx;  
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		 ctx = applicationContext;
		

	}

	public static ApplicationContext getApplicationContext() { 
        return ctx; 
    }  
}
