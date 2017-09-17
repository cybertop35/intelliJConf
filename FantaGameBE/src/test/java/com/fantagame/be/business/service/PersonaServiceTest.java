package com.fantagame.be.business.service;

import static org.junit.Assert.*;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fantagame.be.business.data.bean.Authorities;
import com.fantagame.be.business.data.bean.Persona;
import com.fantagame.be.business.data.bean.UserLogin;
import com.fantagame.be.business.data.dao.Iface.GenericDAO;
import com.fantagame.be.business.service.Iface.IMessageBean;
import com.fantagame.be.business.service.Iface.IPersonaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-application-context.xml"})
public class PersonaServiceTest {

	@Resource(name="personaService")
	private IPersonaService personaService;
	
	@Resource(name="personaDao")
	private GenericDAO<Persona, Integer> daoPersona;
	
	@Resource(name="userLoginDao")
	private GenericDAO<UserLogin, String> daoLogin;
	
	@Resource(name="authoritiesDao")
	private GenericDAO<Authorities, Integer> daoA;
	
	private final String cognome 	= "Calabretta";
	private final Date dataNascita 	= new Date();
	private final String email 		= "marco.cal@hotmail.it";
	private final String nome 		= "Marco";
	private final String sesso 		= "M";
	private final String telefono 	= "330-2066776";

	private final String nick 	= "CyberTop";
	private final String password = "password";

	private final String password_new = "password_new";
	private final String authority = "ADMIN";

	private String cognome_new;

	private Date dataNascita_new;

	private String email_new;

	private String nome_new;
	
	@SuppressWarnings("unused")
	@Test
	@Order(value=1)
	public void addNewUser(){
		Persona persona = new Persona();
		
		persona.setAccCond(true);
		persona.setCognome(cognome);
		persona.setDataNascita(dataNascita);
		persona.setEmail(email);
		persona.setNome(nome);
		persona.setSesso(sesso);
		persona.setTelefono(telefono);
		
		UserLogin userLogin = new UserLogin();
		userLogin.setAttivo(false);
		userLogin.setNick(nick);
		userLogin.setPassword(password);
		
		Authorities authorities = new Authorities();
		authorities.setAuthority(authority );
		
		IMessageBean responce = personaService.registerUser(persona, userLogin, authorities);
		Persona p = responce.getResult();
		
		Persona savePersona = daoPersona.findAll().get(0);
		assertEquals(persona.getCognome(), savePersona.getCognome());
		assertEquals(persona.getNome(), savePersona.getNome());
		assertEquals(persona.getEmail(), savePersona.getEmail());
		
		UserLogin userLogin2 = savePersona.getUserLogin();
		assertEquals(userLogin.getUsername(), userLogin2.getUsername());
		assertEquals(userLogin.getPassword(), userLogin2.getPassword());
		
		GrantedAuthority authorities2 = (GrantedAuthority) userLogin2.getAuthorities_().get(0);
		assertEquals(authorities.getAuthority(), authorities2.getAuthority());
		
		
	}
	
	@Test
	public void forgetPassword(){
		UserLogin userLogin = new UserLogin();
		userLogin.setAttivo(false);
		userLogin.setNick(nick);
		
		//IMessageBean responce = personaService.forgetPassword(userLogin);
		
	}
	
	@Test
	public void changeUserLoginValues(){
		UserLogin userLogin = new UserLogin();
		userLogin.setAttivo(true);
		userLogin.setNick(nick);
		userLogin.setPassword(password_new);
		
		IMessageBean responce = personaService.changeUserLoginValues(nick, userLogin);
		
		UserLogin saveLogin =  responce.getResult();
		
		assertNotNull(saveLogin);
		assertEquals(password_new, saveLogin.getPassword());
	}
	
	@Test
	public void changePersonValues(){
		Persona persona = new Persona();
		
		persona.setAccCond(true);
		persona.setCognome(cognome);
		persona.setDataNascita(dataNascita);
		persona.setEmail(email);
		persona.setNome(nome);
		persona.setSesso(sesso);
		persona.setTelefono(telefono);
		
		Integer id =daoPersona.findByProperties(persona).get(0).getId();
		
		persona.setCognome(cognome_new);
		persona.setDataNascita(dataNascita_new);
		persona.setEmail(email_new);
		persona.setNome(nome_new);
		
		IMessageBean responce = personaService.changePersonValues(id, persona);
		
		Persona persona2 = responce.getResult();
		
		assertNotNull(persona2);
		assertEquals(cognome_new, persona2.getCognome());
		assertEquals(dataNascita_new, persona2.getDataNascita());
		assertEquals(email_new, persona2.getEmail());
		assertEquals(nome_new, persona2.getNome());
	}
	
	@Test
	public void checkUserNameExist(){
		IMessageBean responce = personaService.checkUserNameExist(nick);
		assertNotNull(responce.getResult());
	}
	
	@After
	public void cleanDataBase(){
		daoA.sqlExecution("delete FROM authorities");
		daoLogin.sqlExecution("delete FROM userlogin");
		daoPersona.sqlExecution("delete FROM persona");
	}
}
