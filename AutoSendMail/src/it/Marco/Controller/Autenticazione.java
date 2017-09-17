package it.Marco.Controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class Autenticazione extends Authenticator {

	 String username, password;
	 public Autenticazione(String u, String p){
		 username =u;
		 password = p;
	 }
	 
  public PasswordAuthentication getPasswordAuthentication() {
   
	  return new PasswordAuthentication(username, password);
  }

}
