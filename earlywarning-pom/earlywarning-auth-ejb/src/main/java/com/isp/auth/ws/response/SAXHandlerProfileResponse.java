package com.isp.auth.ws.response;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandlerProfileResponse extends DefaultHandler {

	protected String content = null;
	protected List<String> profiles;
	
	public SAXHandlerProfileResponse(){
		profiles = new ArrayList<String>();
	}
	@Override
	public void startElement(String uri, String localName,String qName, Attributes attributes)throws SAXException {
		switch(qName){
			case "privilege":
				String prof = attributes.getValue("name");
				profiles.add(prof);
				break;
		}
	}
	
	public List<String> getProfile(){
		return profiles;
	}

}
