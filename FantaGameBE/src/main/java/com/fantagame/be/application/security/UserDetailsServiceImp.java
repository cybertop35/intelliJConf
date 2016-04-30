package com.fantagame.be.application.security;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fantagame.be.business.data.bean.UserLogin;
import com.fantagame.be.business.data.dao.Iface.GenericDAO;

public class UserDetailsServiceImp implements UserDetailsService {

	@Resource(name="userLoginDao")
	private GenericDAO<UserLogin, String> daoUserLogin;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		UserLogin user = null;
		if(username != null){
			try{
				user = daoUserLogin.findById(username);
			}catch(Exception e){
				return null;
			}
		}
		return user;
	}

}
