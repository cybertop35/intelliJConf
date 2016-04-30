package com.fantagame.be.business.controller.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class RegexlValidator{
 
	  private Pattern pattern;
	  private Matcher matcher;
 
	  public static final String EMAIL_PATTERN ="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	                                           //"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+(?:[A-Z]{2}|com|org|net|edu|gov|mil|biz|info|mobi|name|aero|asia|jobs|museum)\b";
	  public static final String PHONE_PATTERN = "^([0-9]{2,4})+(\\-[0-9]+)$";
 
	  public RegexlValidator(){
		  
	  }
 
	  /**
	   * Validate hex with regular expression
	   * @param hex hex for validation
	   * @return true valid hex, false invalid hex
	   */
	  public boolean validate(final String hex,String patRex){
		  pattern = Pattern.compile(patRex,Pattern.CASE_INSENSITIVE);
		  matcher = pattern.matcher(hex);
		  
		  return matcher.matches();
 
	  }
	  
	  public static void main (String args[]){
		  RegexlValidator a = new RegexlValidator();
		  System.out.println(a.validate("fdgd@fgdfgdg.it", EMAIL_PATTERN));
	  }
	  
	  
}