package com.fantagame.be.business.controller;

public interface ControllerMapping {
	
	public static final String DEFAULT_PUB_APP 		="/pub";
	public static final String DEFAULT_PRIVATE_APP 	="/secure";
	
	/*User Service Path */
	public static final String PERSONA_SERVICE 	= "/personServices";
	public static final String GROUP_SERVICE 	= "/groupServices";
	
	public static final String ACTIVE_USER 		= "/activation.htm";
	public static final String REGISTER_USER 	= "/registration.htm";
	public static final String CHECK_USERNAME 	= "/checkUserName.htm";
	public static final String PREVIEW_IMG 		= "/preview.htm";
	public static final String FORGOT_PASSWORD	= "/forgotPassword.htm";
	public static final String MY_PROFILE	= "/myProfile.htm";
	
	public static final String JSON = "/JSON";
	public static final String ACTIVE_USER_JSON			= "activation.json";
	public static final String PROFILE_USER_JSON 		= "/profile.json";
	public static final String REGISTER_USER_JSON		= "/register.json";
	public static final String GET_GRUPPI_JSON			= "/groups.json";
	

}
 