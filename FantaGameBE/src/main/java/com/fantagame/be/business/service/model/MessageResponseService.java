package com.fantagame.be.business.service.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fantagame.be.business.service.Iface.IMessageBean;

@XmlRootElement(name="messageService-Response")
public class MessageResponseService implements IMessageBean {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -255681134557670380L;
	private int code = 0;
 	private String message;
	private Object result;
	private String clazzToCast;
	
	public MessageResponseService() {
		super();
	}
	
	public MessageResponseService(int code, String message,Object result) {
		super();
		this.code = code;
		this.message = message;
		this.result = result;
		
	}
	@Override
	public void setCode(int code) {
		this.code = code;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	@XmlElement
	@Override
	public int getCode() {
		return code;
	}
	
	@XmlElement
	@Override
	public String getMessage() {
		return message;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getResult() {
		return result;
	}

	@Override
	public void setResult(Object t) {
		this.result = t;		
	}

	@Override
	public String getClazzToCast() {
		
		return clazzToCast;
	}

	@Override
	public void setClazzToCast(String clazzToCast) {
		this.clazzToCast = clazzToCast;
		
	}

	
	
	
}
