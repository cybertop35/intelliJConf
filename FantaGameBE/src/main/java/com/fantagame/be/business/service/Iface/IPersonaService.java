package com.fantagame.be.business.service.Iface;

import com.fantagame.be.business.data.bean.Authorities;
import com.fantagame.be.business.data.bean.Persona;
import com.fantagame.be.business.data.bean.UserLogin;

public interface IPersonaService {

	public IMessageBean registerUser(Persona persona, UserLogin userLogin,Authorities authorities);
	public IMessageBean registerUser(Persona persona, UserLogin userLogin);
	public IMessageBean forgetPassword (Persona persona);
	public IMessageBean changeUserLoginValues (String userLogin,UserLogin userLoginNew);
	public IMessageBean changePersonValues (Integer persona,Persona personaNew);
	public IMessageBean checkUserNameExist(String login);
	public IMessageBean activeUser(String code);
	public IMessageBean getUserByID(Integer code);
	public IMessageBean getUserByUserName(String code);
}
