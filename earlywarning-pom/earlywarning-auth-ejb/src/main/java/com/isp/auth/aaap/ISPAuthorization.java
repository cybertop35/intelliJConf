package com.isp.auth.aaap;


import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.i4c.antares.authentication.AuthorizationProvider;
import com.i4c.antares.authentication.UserAuthorizations;
import com.i4c.antares.common.core.PropertiesUtil;
import com.isp.auth.ws.Service;
import com.isp.auth.ws.ServiceSoap;
import com.isp.auth.ws.response.ProfileResponse;
import com.isp.auth.ws.response.ProfileResponse.SpiProfilingWS.Userinfo.Userprofile.Privileges;
import com.isp.auth.ws.response.ProfileResponse.SpiProfilingWS.Userinfo.Userprofile.Privileges.Privilege;



public class ISPAuthorization implements AuthorizationProvider {

	private static final QName SERVICE_NAME = new QName("http://sanpaoloimi.com/SSO", "Service");
	private static final Logger logger = Logger.getLogger(ISPAuthorization.class);
	
	private static final String PROPERTY_NAME = "auth.profile.url"; 
	
	@Override
	public UserAuthorizations authorizeRequest(String username,
			HttpServletRequest request) {
		
		logger.info("Profile Service ");
		
		UserAuthorizations authorizations = null;
		URL profileServiceUrl = getProfileServiceURL();


		System.out.println("*************************************************");
		
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
		  String headerName = (String)headerNames.nextElement();
		  System.out.println("" + headerName+"="+request.getHeader(headerName));
		}
		
		System.out.println("*************************************************");
		
		for (Cookie c :request.getCookies()){
			System.out.println("Cookie: "+c.getName()+ " Value:"+c.getValue());
		}
		System.out.println("*************************************************");
		
		if(profileServiceUrl != null){
			String token = request.getHeader("userId");
			String siteName = null;
			String timeStamp = null;
			String encodedPwd = null;
			String encodedType = null;
			String filter = null;
			
			logger.info("Logged User: "+token);
			
			Service ss = new Service(profileServiceUrl, SERVICE_NAME);
			ServiceSoap port = ss.getServiceSoap12();
			
			String profiles = port.getProfile2(token, siteName, timeStamp,encodedPwd, encodedType, filter);
	
			List<String> groups = getGroup(profiles);
			System.out.println("Groups:"+Arrays.toString(groups.toArray()));
			
			authorizations = new UserAuthorizations(username, groups);
		}
		
		return authorizations;
	}

	private URL getProfileServiceURL() {
		String url = PropertiesUtil.getPropertyOrDefault(PROPERTY_NAME, null);
		try{
			
			return new URL(url);
			
		}catch(Exception e){
			logger.error("ISPAuthorization: "+e.getMessage());
			logger.error("Check property configuration: "+PROPERTY_NAME);
		}
		return null;
	}

	private static List<String> getGroup(String response){
		List<String> groups = new ArrayList<String>();
		try {
			
			JAXBContext jc = JAXBContext.newInstance(ProfileResponse.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			
			SAXParserFactory sax = SAXParserFactory.newInstance();
			sax.setNamespaceAware(false);
			XMLReader reader = sax.newSAXParser().getXMLReader();
			InputSource inputSource = new InputSource( new StringReader( response ) );
			Source source = new SAXSource(reader, inputSource );

			ProfileResponse profileResponse = (ProfileResponse) unmarshaller.unmarshal(source);
			Privileges privileges = profileResponse.getSpiProfilingWS().getUserinfo().getUserprofile().getPrivileges();
			
			for(Privilege privilege:privileges.getPrivilege()){
				groups.add(privilege.getName());
			}
			
			
		} catch (Exception e) {
			logger.error("ISPAuthorization getGroup: "+e.getMessage());
		} 
		
		
		return groups;
	}
	
	public static void main(String args[]){
		try {
			String content = new String(Files.readAllBytes(Paths.get("C:/Dati/DEV/Doc/Intesa/sicurezza/profiles.xml")));
			System.out.println(content);
			List<String> groups = getGroup(content);
			System.out.println(Arrays.toString(groups.toArray()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
