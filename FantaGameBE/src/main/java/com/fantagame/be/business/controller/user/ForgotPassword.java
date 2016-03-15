package com.fantagame.be.business.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fantagame.be.application.MessagePropertyUtil;
import com.fantagame.be.business.controller.ControllerMapping;
import com.fantagame.be.business.controller.validator.RegexlValidator;
import com.fantagame.be.business.data.bean.Persona;
import com.fantagame.be.business.service.ServiceConstants;
import com.fantagame.be.business.service.Iface.IMessageBean;
import com.fantagame.be.business.service.Iface.IPersonaService;

@Controller
@RequestMapping(ControllerMapping.DEFAULT_PUB_APP + ControllerMapping.PERSONA_SERVICE)
public class ForgotPassword {

	@Autowired
	private IPersonaService personaService;
	
	public ForgotPassword(){}
	
	public void simpleForm(Model model){
		model.addAttribute(new Persona());
	}
	@ModelAttribute
    public Persona newRequest() {
        return   new Persona();
    }
    @RequestMapping(value=ControllerMapping.FORGOT_PASSWORD, method=RequestMethod.GET)
    public void form() {}
    
    @RequestMapping(value=ControllerMapping.FORGOT_PASSWORD, method=RequestMethod.POST)
    public void form(Persona persona,BindingResult bindingResult,Model model) {
    	RegexlValidator validator = new RegexlValidator();
    	String mail = persona.getEmail();

    	if(mail == null || !validator.validate(mail, RegexlValidator.EMAIL_PATTERN)){
    		model.addAttribute("message", MessagePropertyUtil.getMessage("forgetPassword.input.error"));
    		model.addAttribute("statusMessageCode",ServiceConstants.NULL_CODE);
    		model.addAttribute("classs","error");
    		
    	}
    	else{
    		
    		IMessageBean messageBean = personaService.forgetPassword(persona);
    		if(messageBean.getCode() != ServiceConstants.SUCCESS_CODE)
    			
    		model.addAttribute("statusMessageCode",messageBean.getCode());
    		model.addAttribute("message",messageBean.getMessage());
    		
    	}
    		
    }
   
}
