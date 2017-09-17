package com.fantagame.be.mail.model;

import java.util.Date;

import org.springframework.mail.MailParseException;
import org.springframework.util.StringUtils;

import com.fantagame.be.mail.MailConstant;
import com.fantagame.be.mail.Iface.CustomMailMessage;

public class EmailMessage implements CustomMailMessage{

	private String from = MailConstant.FROM;
	private String replyTo;
	private String[] to;
	private String[] cc;
	private String[] bcc;
	private Date sentDate;
	private String subject;
	private String text;

	@Override
	public void setFrom(String from) throws MailParseException {
		this.from = from;		
	}

	@Override
	public void setReplyTo(String replyTo) throws MailParseException {
		this.replyTo = replyTo;		
	}

	@Override
	public void setTo(String to) throws MailParseException {
		this.to = new String[] {to};	
	}

	@Override
	public void setTo(String[] to) throws MailParseException {
		this.to = to;		
	}

	@Override
	public void setCc(String cc) throws MailParseException {
		this.cc = new String[] {cc};		
	}

	@Override
	public void setCc(String[] cc) throws MailParseException {
		this.cc = cc;		
	}

	@Override
	public void setBcc(String bcc) throws MailParseException {
		this.bcc = new String[] {bcc};		
	}

	@Override
	public void setBcc(String[] bcc) throws MailParseException {
		this.bcc = bcc;
	}

	@Override
	public void setSentDate(Date sentDate) throws MailParseException {
		this.sentDate = sentDate;
	}

	@Override
	public void setSubject(String subject) throws MailParseException {
		this.subject = subject;	
	}

	@Override
	public void setText(String text) throws MailParseException {
		this.text = text;		
	}

	public String getFrom() {
		return from;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public String[] getTo() {
		return to;
	}

	public String[] getCc() {
		return cc;
	}

	public String[] getBcc() {
		return bcc;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public String getSubject() {
		return subject;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("EmailMessage: ");
		sb.append("from=").append(this.from).append("; ");
		sb.append("replyTo=").append(this.replyTo).append("; ");
		sb.append("to=").append(StringUtils.arrayToCommaDelimitedString(this.to)).append("; ");
		sb.append("cc=").append(StringUtils.arrayToCommaDelimitedString(this.cc)).append("; ");
		sb.append("bcc=").append(StringUtils.arrayToCommaDelimitedString(this.bcc)).append("; ");
		sb.append("sentDate=").append(this.sentDate).append("; ");
		sb.append("subject=").append(this.subject).append("; ");
		sb.append("text=").append(this.text);
		return sb.toString();
	}
}
