package com.fantagame.be.mail;

public interface MailConstant {

	public final static String FROM 				 	= "regitration@fantagame.it";
	
	public final static String TEMPLATE_REGISTRATION 	= "emailRegistrationBody.vm";
	public final static String TEMPLATE_GROUP_MESSAGE 	= "emailGruppoBody.vm";
	public static final String FORGET_PASSWORD_SUBJECT 	= "forgetPassordTemplate.vm";
	public static final String TEMPLATE_INVITE 			= "InviteTemplate.vm";
	public static final Object TEMPLATE_FIELD_MESSAGE 	= "MessageTemplate.vm";
	public static final String TEMPLATE_FORGET_PASSWORD = "emailForgetPasswordBody.vm";
	
	public final static String REGISTRATION_SUBJECT   = "Welcome to FantaGame.it";
	public final static String INVITATION_SUBJECT   =	"has invited you to register on FantaGame.it";
	
	public final static String TEMPLATE_FIELD_NAME 			= "name";
	public final static String TEMPLATE_FIELD_LINK 			= "link";
	public final static String TEMPLATE_FIELD_USERNAME 		= "username";
	public final static String TEMPLATE_FIELD_MAIL 			= "mail";
	public static final String TEMPLATE_FIELD_PASSWORD 		= "password";
	public static final Object TEMPLATE_FIELD_INVITE_CODE 	= "inviteCode";
	public static final Object TEMPLATE_FIELD_GROUP_NAME 	= "groupName";
	public static final String TEMPLATE_SEND_MESSAGE 		= "message";

	

	


	
}
