package com.fantagame.be.mail;

import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.fantagame.be.mail.Iface.CustomMailMessage;

public class MessagePreparator implements MimeMessagePreparator {

	private CustomMailMessage msg;
	
	public MessagePreparator() {
		super();
	}
	
	public MessagePreparator(CustomMailMessage email) {
		super();
		this.msg = email;
	}
	
	public CustomMailMessage getEmail() {
		return msg;
	}

	public void setEmail(CustomMailMessage email) {
		this.msg = email;
	}



	@Override
	public void prepare(MimeMessage mimeMessage) throws Exception {
		
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		message.setTo(msg.getTo());
        message.setFrom(msg.getFrom());
        message.setSubject(msg.getSubject());
        message.setText(msg.getText(), true);
        
        if(msg.getBcc() != null)
        	message.setBcc(msg.getBcc());
        if(msg.getCc() != null)
        	message.setCc(msg.getCc());
        if(msg.getReplyTo() != null)
        	message.setReplyTo(msg.getReplyTo());
        if(msg.getSentDate() != null)
        	message.setSentDate(msg.getSentDate());
        else
        	message.setSentDate(new Date());
              
	}

}
