package it.Marco.Controller;

import java.util.Properties;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	private Session session = null;
	static final String protocollo = "smtp";
	private String smtp = null;
	private String pop3 = null;
	private String userName = null;
	private String pws = null;
	private String mailTo = null;
	private String object = null;
	private String body = null;
	private URLName url = null;
	private Properties props = null;
	private String cc = null;
	
	public boolean  send() {
		
		try {
			
		 
			props = System.getProperties( );
			props.put("mail.pop3.host", pop3);
			props.put("mail.smtp.host", smtp);
			props.put("mail.pop3.auth","true");

			Authenticator auth = new Autenticazione(userName,pws);

			session = Session.getDefaultInstance(props,auth);
			session.setDebug(true);
			
			
			//Principale.setText((String).println());
			
			url = new URLName(protocollo,smtp,-1,null,userName,pws);
		
			Store s = session.getStore("pop3");
			s.connect();
			
			if(s.isConnected()){
				Transport t=session.getTransport("smtp");
				t.connect();
					
				Message msg = new MimeMessage(session);
				InternetAddress[] toAddrs = null, ccAddrs = null;
		  
		    
		    	if (mailTo != null){ 
					toAddrs = InternetAddress.parse(mailTo, false);
					msg.setRecipients(Message.RecipientType.TO, toAddrs);
		    	}	

		    	if (cc != null) {
		    		ccAddrs = InternetAddress.parse(cc, false);
		    		msg.setRecipients(Message.RecipientType.CC, ccAddrs);
		    	}

		    	if (object != null)
		    		msg.setSubject(object);
		    
		    	msg.setFrom(new InternetAddress(userName));

		    	if (body != null)
		    		msg.setText(body);

		    	t.sendMessage(msg, msg.getAllRecipients());
		    	t.close();
		    	s.close();
		    	return true;
			}
			
			
		} catch(AuthenticationFailedException afe){
				System.out.println(afe.getMessage()
						+"<>"+afe.getLocalizedMessage()+"<>"+afe.getCause());
				return false;
		} catch (AddressException ae) {
				// TODO Auto-generated catch block
		    	System.out.println(ae.toString());
		    	return false;
		} catch (MessagingException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
				return false;
			}
		return false;
	
		
	}

	
	
	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String host) {
		this.smtp = host;
	}
	public String getPop3() {
		return pop3;
	}

	public void setPop3(String pop3) {
		this.pop3 = pop3;
	}

	public String getProtocollo() {
		return protocollo;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPws() {
		return pws;
	}

	public void setPws(String pws) {
		this.pws = pws;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
