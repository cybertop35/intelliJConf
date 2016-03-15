package com.fantagame.be.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;

import com.fantagame.be.application.MessagePropertyUtil;
import com.fantagame.be.application.RequestCTX;
import com.fantagame.be.application.security.IFcae.PlainText;
import com.fantagame.be.business.controller.ControllerMapping;
import com.fantagame.be.business.data.bean.Authorities;
import com.fantagame.be.business.data.bean.Persona;
import com.fantagame.be.business.data.bean.UserLogin;
import com.fantagame.be.business.data.dao.Iface.GenericDAO;
import com.fantagame.be.business.service.Iface.IMessageBean;
import com.fantagame.be.business.service.Iface.IPersonaService;
import com.fantagame.be.business.service.exception.DataBaseException;
import com.fantagame.be.business.service.exception.MailException;
import com.fantagame.be.business.service.model.MessageResponseService;
import com.fantagame.be.mail.EmailSenderService;
import com.fantagame.be.mail.MailConstant;
import com.fantagame.be.util.StringUtils;

public class PersonaService implements IPersonaService  {

	@Resource(name="personaDao")
	private GenericDAO<Persona, Integer> daoPersona;
	
	@Resource(name="userLoginDao")
	private GenericDAO<UserLogin, String> daoUserLogin;
	 
	@Resource(name="authoritiesDao")
	private GenericDAO<Authorities, Integer> daoAuthority;
	
	@Resource(name="emailSenderService")
	private EmailSenderService emailSenderService;
	
	private ShaPasswordEncoder encoder;
	
	@Autowired
	private PlainText rsaPrivateKey;
	
	public PersonaService(){ 
		encoder = new ShaPasswordEncoder(256);		
	}
	
	public IMessageBean registerUser(Persona persona, UserLogin userLogin){
		return registerUser(persona, userLogin, null);
	}
	
	public IMessageBean registerUser(Persona persona, UserLogin userLogin,Authorities authorities){
		IMessageBean messageBean = new MessageResponseService();
		String activationCode = "";
		try{
			 persona.setDataCreation(new Date());
			 persona.setUserLogin(null);
			 
			 daoPersona.save(persona);
			try{
				activationCode = UUID.nameUUIDFromBytes((userLogin.getName()+persona.getDataCreation()).getBytes()).toString();
				byte arr [] = Base64.decode(userLogin.getPassword().getBytes());
				String tmp = new String(rsaPrivateKey.getPlainText(arr));
				
				userLogin.setPassword(encoder.encodePassword(tmp, userLogin.getNick()));
				userLogin.setActivationCode(activationCode.replaceAll("-", ""));
				userLogin.setPersona(persona);
				userLogin.setAttivo(false);
				userLogin.setAccountNonExpired(true);
				userLogin.setAccountNonLocked(true);
				userLogin.setCredentialsNonExpired(true);
				daoUserLogin.save(userLogin);				
				
				try{
					if(authorities == null){
						authorities = new Authorities();
						authorities.setAuthority("ROLE_USER");
					}
					authorities.setUserLogin(userLogin);
					daoAuthority.save(authorities);
			
					messageBean.setCode(ServiceConstants.SUCCESS_CODE);
					messageBean.setMessage(MessagePropertyUtil.getMessage("service.person.registration"));
					List<Authorities> list = new ArrayList<Authorities>();
					list.add(authorities);
					userLogin.setAuthorities(list);
					persona.setUserLogin(userLogin);
					messageBean.setResult(persona);
					
					
					String url =(RequestCTX.getContextPath()+ControllerMapping.DEFAULT_PUB_APP+ ControllerMapping.PERSONA_SERVICE+ControllerMapping.ACTIVE_USER+"?command=active&code="+userLogin.getActivationCode()); 
					try{
					emailSenderService.send(emailSenderService.createMessage(persona, MailConstant.REGISTRATION_SUBJECT), 
										MailConstant.TEMPLATE_REGISTRATION, 
									registrationTemplateVariables(persona,url));
					}catch(Exception e){
						new MailException("Email Error",e.getCause(),ServiceConstants.LOGGED,this.getClass());
						messageBean.setCode(ServiceConstants.DAO_AUTHORITIES_ERROR_CODE);
						daoAuthority.remove(authorities);
						daoUserLogin.remove(userLogin);
						daoPersona.remove(persona);		
						messageBean.setCode(ServiceConstants.NULL_CODE);
					}	
					
				}catch(Exception e){
					new DataBaseException("authorities Error",e.getCause(),ServiceConstants.LOGGED,this.getClass());
					messageBean.setCode(ServiceConstants.DAO_AUTHORITIES_ERROR_CODE);
					daoUserLogin.remove(userLogin);
					daoPersona.remove(persona);	
					messageBean.setCode(ServiceConstants.DAO_AUTHORITIES_ERROR_CODE);
				}				
				
			}catch(Exception e){
				new DataBaseException("UserLogin code Error",e.getCause(),ServiceConstants.LOGGED,this.getClass());
				messageBean.setCode(ServiceConstants.DAO_USERLOGIN_ERROR_CODE);
				daoPersona.remove(persona);
				messageBean.setCode(ServiceConstants.DAO_USERLOGIN_ERROR_CODE);
			}	
			
		}catch(Exception e){
			new DataBaseException("Person code Error",e.getCause(),ServiceConstants.LOGGED,this.getClass());
			messageBean.setCode(ServiceConstants.DAO_PERSON_ERROR_CODE);
			messageBean.setMessage("Creare il Poperies...");
			messageBean.setCode(ServiceConstants.DAO_PERSON_ERROR_CODE);
			
		}		
		
		return messageBean;
	}
	
	public IMessageBean forgetPassword (Persona persona){
		IMessageBean messageBean = new MessageResponseService();
		Persona personaLoaded = null;
		UserLogin userLogin = null;
		try{
			List<Persona> personeLoaded = daoPersona.findByProperties(persona);
			
			if(personeLoaded != null && personeLoaded.size() == 1){
				personaLoaded = personeLoaded.get(0) ;
				userLogin = personaLoaded.getUserLogin();
				String newPws =  StringUtils.generateUniqueToken(6) ;
				userLogin.setPassword(encoder.encodePassword(newPws, userLogin.getNick()));
				try{
					daoUserLogin.merge(userLogin);
				}catch(Exception e){
					messageBean.setCode(ServiceConstants.NULL_CODE);
					messageBean.setMessage(MessagePropertyUtil.getMessage("forgetPassword.send.mail.error"));
					new DataBaseException("UserLogin ForgetPassword Error",e.getCause(),ServiceConstants.LOGGED,this.getClass());
				}
				
				emailSenderService.send(emailSenderService.createMessage(personaLoaded, MailConstant.TEMPLATE_REGISTRATION), 
										MailConstant.TEMPLATE_FORGET_PASSWORD, 
										forgetPasswordTemplateVariables(userLogin.getUsername(),newPws));
				
				messageBean.setCode(ServiceConstants.SUCCESS_CODE);
				messageBean.setMessage(MessagePropertyUtil.getMessage("forgetPassword.send.mail"));
			
			}
			else {
				messageBean.setCode(ServiceConstants.NULL_CODE);
				messageBean.setMessage(MessagePropertyUtil.getMessage("forgetPassword.person.error"));
			}
			
			
		}catch(Exception e){
			new DataBaseException("Person ForgetPassword Error",e.getCause(),ServiceConstants.LOGGED,this.getClass());
			messageBean.setCode(ServiceConstants.NULL_CODE);
			messageBean.setMessage(MessagePropertyUtil.getMessage("forgetPassword.person.error"));
		}
		return messageBean;
	}
	
	public IMessageBean changeUserLoginValues (String user,UserLogin userLoginNew){
		IMessageBean messageBean = new MessageResponseService();
		
		//userLoginNew.setNick(user);
		try{
			messageBean.setResult(daoUserLogin.merge(userLoginNew));
			messageBean.setCode(ServiceConstants.SUCCESS_CODE);
			messageBean.setMessage("Creare il Poperies...");
		}catch (Exception e){
			new DataBaseException("changeUserLoginValues Error",e.getCause(),ServiceConstants.LOGGED,this.getClass());
			messageBean.setCode(ServiceConstants.DAO_USERLOGIN_ERROR_CODE);
			messageBean.setMessage("Creare il Poperies...");
		}
		
		return messageBean;
	}
	
	public IMessageBean changePersonValues (Integer persona,Persona personaNew){
		IMessageBean messageBean = new MessageResponseService();
		
		personaNew.setIdUser(persona);
		try{
			messageBean.setResult(daoPersona.merge(personaNew));
			messageBean.setCode(ServiceConstants.SUCCESS_CODE);
			messageBean.setMessage("Creare il Poperies...");
		}catch (Exception e){
			new DataBaseException("changePersonValues Error",e.getCause(),ServiceConstants.LOGGED,this.getClass());
			messageBean.setCode(ServiceConstants.DAO_PERSON_ERROR_CODE);
			messageBean.setMessage("Creare il Poperies...");
		}
		
		return messageBean;
 
	}
	
	public IMessageBean checkUserNameExist(String login){
		
		IMessageBean messageBean = new MessageResponseService();
		
		 try{
			 UserLogin loginFound = daoUserLogin.findById(login);
			 
			 if(loginFound != null){
				messageBean.setCode(ServiceConstants.DUPLICATE_CODE);
				messageBean.setMessage(MessagePropertyUtil.getMessage("username.notValid"));
			 }
			 else{
				 messageBean.setCode(ServiceConstants.SUCCESS_CODE);
				 messageBean.setMessage(MessagePropertyUtil.getMessage("username.valid"));
				 
			 }
				 
		 }catch (Exception e){
			new DataBaseException("checkUserNameExist Error",e.getCause(),ServiceConstants.LOGGED,this.getClass());
			messageBean.setCode(ServiceConstants.DAO_USERLOGIN_ERROR_CODE);
			messageBean.setMessage(MessagePropertyUtil.getMessage("username.error"));
		 }
		
		return messageBean;
	}
	
	@Override
	public IMessageBean activeUser(String code) {
		IMessageBean messageBean = new MessageResponseService();
		
		if(code != null && code.length() > 0){
			UserLogin userLogin = new UserLogin();
			userLogin.setActivationCode(code);
			List<UserLogin> list = null;
			try{
				 list = daoUserLogin.findByProperties(userLogin);
				
				 if(list != null && list.size() > 0 ){
						userLogin = list.get(0);
						userLogin.setActivationCode("");
						userLogin.setAttivo(true);
						daoUserLogin.merge(userLogin);
						messageBean.setMessage( "Account active");
						messageBean.setCode(ServiceConstants.SUCCESS_CODE);
						
					}
					else{
						messageBean.setCode(ServiceConstants.NULL_CODE);
						messageBean.setMessage( "User not valid");
					}
			}catch (Exception e ){
				messageBean.setCode(ServiceConstants.DAO_USERLOGIN_ERROR_CODE);
				messageBean.setMessage(e.getMessage());
			}			
		}
		
		
		return messageBean;
	}
		
	public IMessageBean getUserByID(Integer code){
		IMessageBean messageBean = new MessageResponseService();
		
		if(code != null){
			try{
				Persona persona = daoPersona.findById(code);
				messageBean.setResult(persona);
				messageBean.setCode(ServiceConstants.SUCCESS_CODE);
			}catch(Exception e){
				messageBean.setCode(ServiceConstants.DAO_PERSON_ERROR_CODE);
			}
		}
		else
			messageBean.setCode(ServiceConstants.NULL_CODE);
		
		return messageBean;
	}
	
	public IMessageBean getUserByUserName(String code){
		IMessageBean messageBean = new MessageResponseService();
		
		if(code != null){
			try{
				UserLogin userLogin = daoUserLogin.findById(code);
				messageBean.setResult(userLogin);
				messageBean.setCode(ServiceConstants.SUCCESS_CODE);
			}catch(Exception e){
				messageBean.setCode(ServiceConstants.DAO_USERLOGIN_ERROR_CODE);
			}
		}
		else
			messageBean.setCode(ServiceConstants.NULL_CODE);
		
		return messageBean;
	}
	
	private Map<Object, Object> registrationTemplateVariables(Persona persona,String url) {
		Map<Object, Object> variables = new HashMap<Object, Object>();
		variables.put(MailConstant.TEMPLATE_FIELD_NAME, persona.getNome());
		variables.put(MailConstant.TEMPLATE_FIELD_LINK,url);
		variables.put(MailConstant.TEMPLATE_FIELD_MAIL,persona.getEmail());
		variables.put(MailConstant.TEMPLATE_FIELD_USERNAME,persona.getUserLogin().getUsername());
		
		return variables;
	}
	private Map<Object, Object> forgetPasswordTemplateVariables(
			String username,String pws) {
		
		Map<Object, Object> variables = new HashMap<Object, Object>();
		variables.put(MailConstant.TEMPLATE_FIELD_USERNAME,username);
		variables.put(MailConstant.TEMPLATE_FIELD_PASSWORD,pws);
		
		return variables;
		

	}

	
	
}