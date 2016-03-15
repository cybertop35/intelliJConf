package com.fantagame.be.application;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;
 
public class ServletContextProvider   implements ServletContextAware {

	private static ServletContext ctx;  	
	
	
	public ServletContextProvider(){
	
		
		//HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		//setApplicationBase(curRequest);
	}
	
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		ctx = servletContext;
		
	}

	public static ServletContext getServletContext(){
		return ctx;
	}
	
	
	
	

}
