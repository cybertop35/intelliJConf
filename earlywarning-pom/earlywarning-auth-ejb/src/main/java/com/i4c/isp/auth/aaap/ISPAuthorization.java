package com.i4c.isp.auth.aaap;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.i4c.antares.authentication.AuthorizationProvider;
import com.i4c.antares.authentication.UserAuthorizations;
import com.i4c.antares.common.utils.ServiceLocator;
import com.i4c.isp.auth.ws.Service;
import com.i4c.isp.auth.ws.ServiceSoap;
import com.i4c.isp.auth.ws.response.SAXHandlerProfileResponse;

public class ISPAuthorization implements AuthorizationProvider {

	private static final Logger logger = Logger
			.getLogger(ISPAuthorization.class);

	
	@SuppressWarnings("unused")
	@Override
	public UserAuthorizations authorizeRequest(String username,	HttpServletRequest request) {

		logger.info("Profile Service ");
		
		UserAuthorizations authorizations = null;

		String timeStamp = null;
		String encodedPwd = null;
		String encodedType = null;
		String filter = null;
		String siteName = null;
		
		ManagerProfileLocal managerProfiles = ServiceLocator.getInstance().getEJB(ManagerProfileLocal.class, ManagerProfileSession.class, "earlywarning");

		try {
			siteName = request.getHeader("svcUrl");

			logger.info("Logged User: " + username);

			if (siteName != null && siteName.length() > 0) {
				
				if (!siteName.contains("?wsdl"))
					siteName = siteName + "?wsdl";

				Service ss = new Service(new URL(siteName));

				if (ss != null) {
					ServiceSoap port = ss.getServiceSoap12();
					
					String profiles = port.getProfile2(username, siteName,timeStamp, encodedPwd, encodedType, filter);
					
					List<String> systemGroups = getGroup(profiles);
					
					if(systemGroups == null || systemGroups.size() == 0){
						logger.warn("No group found: "+profiles);
					}
					else
						logger.info("Groups:" + Arrays.toString(systemGroups.toArray()));
					
					
					List<String> aaapProfiles = new ArrayList<String>();

					if(managerProfiles != null)					
						for(String profile: systemGroups){
							aaapProfiles.addAll(managerProfiles.getAAAPProfile(profile));
						}
					else
						logger.error("No manager profile found");
					
					if(aaapProfiles.size() == 0)
						logger.error("No profile mapping");
					
					authorizations = new UserAuthorizations(username, aaapProfiles);	
					
					
				} else
					logger.error("Service is null. Check " + siteName);

			} else {
				logger.error("ISPAuthorization URL not found " + siteName);
			}

		} catch (MalformedURLException e) {
			logger.error("ISPAuthorization Malformed URL " + siteName);
		} catch (Exception e) {
			logger.error("ISPAuthorization Generic error ", e);
		}

		return authorizations;
	}

	private static List<String> getGroup(String response) {

		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
		SAXParser parser;
		
		try {
			parser = parserFactor.newSAXParser();
			SAXHandlerProfileResponse handler = new SAXHandlerProfileResponse();
			InputSource inputSource = new InputSource(new StringReader(response));
			parser.parse(inputSource, handler);
			
			return  handler.getProfile();
			
		} catch (ParserConfigurationException e) {
			logger.error("ParserConfigurationException Error "+e.getMessage());
		} catch (SAXException e) {
			logger.error("SAXException Error "+e.getMessage());
		} catch (IOException e) {
			logger.error("IOException Error "+e.getMessage());
		} 

		return null;
	}
	
	public static void main(String args[]) {
		try {
			// String content =new
			// String(Files.readAllBytes(Paths.get("C:/Dati/DEV/Doc/Intesa/sicurezza/profiles.xml")));
			String content = "<SpiProfilingWS>	<userinfo name=\"U0I2197\">		<userprofile bank=\"01\" branch=\"02955\">		</userprofile>	</userinfo></SpiProfilingWS>";

			System.out.println(content);
			List<String> groups = getGroup(content);
			System.out.println(Arrays.toString(groups.toArray()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
