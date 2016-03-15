package com.fantagame.be.ws;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/*
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
*/


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:jax-ws.xml"})
public class ClientWS {

	
	@Resource(name="client")
	//private WebServiceTemplate client;
	
	@Test
	public void callService(){
//		PersonaRequest request =  new PersonaRequest();
//		request.setName("Marco");
//		 
//		
//		PersonaServiceResponse response = (PersonaServiceResponse) client.marshalSendAndReceive(request,new WebServiceMessageCallback() {
//			
//			@Override
//			public void doWithMessage(WebServiceMessage message) throws IOException,
//					TransformerException {
//				// TODO Auto-generated method stub
//				//((SoapMessage)message).getEnvelope().addNamespaceDeclaration("tns","http://www.luigibennardis.it/spring-ws/Ecomm/Acquisto/types");
//			}
//		});
		assertNotNull(null);
		
	}
}
 