package com.fantagame.be.mail;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.fantagame.be.business.data.bean.Persona;
import com.fantagame.be.mail.Iface.CustomMailMessage;
import com.fantagame.be.mail.Iface.Sender;
import com.fantagame.be.mail.model.EmailMessage;


public class EmailSenderService implements Sender {

	private static final Logger logger = Logger.getLogger(EmailSenderService.class);

	@Autowired
    private VelocityEngine velocityEngine;
    @Autowired
	private JavaMailSender mailSender;

    
    public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	@Override
	public void send(final CustomMailMessage msg,final String template,
			final Map<Object, Object> hTemplateVariables) {
		
		String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
																 template,
																  hTemplateVariables);
		msg.setText(body);
		MessagePreparator preparator = new MessagePreparator(msg);
		mailSender.send(preparator);
		
		logger.info("Sent e-mail to '{}'."+ msg.getTo());
	}
    
	public CustomMailMessage createMessage(Persona persona,String subject){
		CustomMailMessage message = new EmailMessage();
		message.setTo(persona.getEmail());		
		message.setSubject(subject);
		return message;
	}
	
	public CustomMailMessage createMessage(String email,String subject){
		CustomMailMessage message = new EmailMessage();
		message.setTo(email);		
		message.setSubject(subject);
		return message;
	}
    

}
