package com.fantagame.be.business.controller.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fantagame.be.business.controller.ControllerMapping;
import com.fantagame.be.business.controller.validator.UserValidator;
import com.fantagame.be.business.data.bean.Persona;
import com.fantagame.be.business.data.bean.UserLogin;
import com.fantagame.be.business.service.Iface.IMessageBean;
import com.fantagame.be.business.service.Iface.IPersonaService;

@Controller
@RequestMapping(ControllerMapping.DEFAULT_PRIVATE_APP)
public class ModifyProfile {

	@Autowired
	private IPersonaService personaService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserValidator());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	public void simpleForm(Model model){
		model.addAttribute(new Persona());
	}
	
	@ModelAttribute
    public Persona newRequest(){
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 Persona persona = null;
		 if (auth != null) {
			UserLogin userLogin = (UserLogin) auth.getPrincipal();
			IMessageBean messageBean = personaService.getUserByUserName(userLogin.getUsername());
			persona = messageBean.getResult();
		 }
		
		return  persona;
	}
	
    @RequestMapping(value=ControllerMapping.MY_PROFILE, method=RequestMethod.GET)
    public void form(){ 
    	
    }
	
    @RequestMapping(value=ControllerMapping.MY_PROFILE, method=RequestMethod.POST)
    public void form(@Valid Persona person,BindingResult bindingResult, Model model){ 
    	
    }
}
