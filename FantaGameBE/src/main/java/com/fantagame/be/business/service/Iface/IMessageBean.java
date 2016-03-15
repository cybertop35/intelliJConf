package com.fantagame.be.business.service.Iface;

import java.io.Serializable;

public interface IMessageBean extends Serializable {

	public int getCode();
	public void setCode(int code);
	public String getMessage();
	public void setMessage(String message);
	public <T> T  getResult();
	public <T> void setResult(T t);
	
	public String getClazzToCast();
	public void setClazzToCast(String clazz);
} 
