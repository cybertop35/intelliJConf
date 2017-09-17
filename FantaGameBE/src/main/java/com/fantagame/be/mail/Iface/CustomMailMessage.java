package com.fantagame.be.mail.Iface;

import java.util.Date;

import org.springframework.mail.MailMessage;

public interface CustomMailMessage extends MailMessage {
	
	public String getFrom();

	public String getReplyTo();

	public String[] getTo() ;

	public String[] getCc();

	public String[] getBcc();

	public Date getSentDate();

	public String getSubject();

	public String getText();
	
}
