package com.fantagame.be.application;


import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextListener;;



/**
 * @author marco.calabretta 
 *
 */
public class RequestCTX extends RequestContextListener  {
	
	private static String applicationBase;
	
	@Override
	public void requestInitialized(ServletRequestEvent requestEvent) {
		super.requestInitialized(requestEvent);
		HttpServletRequest curRequest = (HttpServletRequest) requestEvent.getServletRequest();
		setApplicationBase(curRequest);
	}
	
	public static String getContextPath(){
		
		return applicationBase;
	}

/******Private method****/
	
	private static void setApplicationBase(HttpServletRequest req) {
	    if (applicationBase == null) {
	        applicationBase = req.getScheme() + "://" + req.getServerName() +
	                getPort(req) + req.getContextPath()+req.getServletPath();
	    }
	}

	private static String getPort(HttpServletRequest req) {
	    if ("http".equalsIgnoreCase(req.getScheme()) && req.getServerPort() != 80 ||
	            "https".equalsIgnoreCase(req.getScheme()) && req.getServerPort() != 443 ) {
	        return (":" + req.getServerPort());
	    } else {
	        return "";
	    }
	}
}
