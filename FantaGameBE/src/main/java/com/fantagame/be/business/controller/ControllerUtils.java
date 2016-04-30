package com.fantagame.be.business.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public class ControllerUtils {

	public static final String MESSAGE 	= "message";
	public static final String CAUSE 	= "cause";
	private static final String url 	= "pub/error";
	
	public static ModelAndView getErrorMOdel(String mesString,String causeString){
		ModelAndView model = new ModelAndView(url);
		Map<String,String> obj = new HashMap<String, String>();
		
		obj.put(MESSAGE,mesString);
		obj.put(CAUSE,causeString);
		
		model.addAllObjects(obj);
		
		return model;
	}

	public static ModelAndView getErrorMOdel(int code, String message) {
		ModelAndView model = new ModelAndView(url);
		Map<String,Object> obj = new HashMap<String, Object>();
		
		obj.put(MESSAGE,code);
		obj.put(CAUSE,message);
		
		model.addAllObjects(obj);
		
		return model;
	}
}
