package com.fantagame.be.business.controller.json.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fantagame.be.business.controller.ControllerMapping;
import com.fantagame.be.business.data.bean.Persona;
import com.fantagame.be.business.data.bean.UserLogin;
import com.fantagame.be.business.service.Iface.IMessageBean;
import com.fantagame.be.business.service.Iface.IPersonaService;


@Controller
public class UserControllerJSON {
	 
	@Autowired
	private IPersonaService personaService;
	
	/**
     * For every request for this controller, this will 
     * create a person instance for the form.
	 * 
	 */	
	@RequestMapping(value=ControllerMapping.DEFAULT_PRIVATE_APP+
						  ControllerMapping.JSON + 
						  ControllerMapping.PERSONA_SERVICE + 
						  ControllerMapping.PROFILE_USER_JSON, method=RequestMethod.GET)
	public @ResponseBody UserLogin getProfile(@RequestBody String user)  throws Exception {
		return personaService.getUserByUserName(user).getResult();
	}

	@RequestMapping(value=ControllerMapping.DEFAULT_PUB_APP+
						  ControllerMapping.JSON + 
						  ControllerMapping.PERSONA_SERVICE + 
						  ControllerMapping.REGISTER_USER_JSON, method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody IMessageBean activationUser(@RequestBody Persona persona)  throws Exception {		
		return personaService.registerUser(persona, persona.getUserLogin());
	}

}
