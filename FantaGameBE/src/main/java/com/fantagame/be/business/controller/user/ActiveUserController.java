package com.fantagame.be.business.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fantagame.be.application.MessagePropertyUtil;
import com.fantagame.be.business.controller.ControllerMapping;
import com.fantagame.be.business.service.ServiceConstants;
import com.fantagame.be.business.service.Iface.IMessageBean;
import com.fantagame.be.business.service.Iface.IPersonaService;



@Controller
@RequestMapping(ControllerMapping.DEFAULT_PUB_APP + ControllerMapping.PERSONA_SERVICE)
public class ActiveUserController {

	@Autowired
	private IPersonaService personaService;
	
	@RequestMapping(value=ControllerMapping.ACTIVE_USER,method=RequestMethod.GET)
	public void activationUser(@RequestParam(required=true) String code,Model model)  throws Exception {
		
		IMessageBean iMessageBean = personaService.activeUser(code);
			
		
		if(iMessageBean.getCode() == ServiceConstants.SUCCESS_CODE){
			
			model.addAttribute("classs", "success");
			model.addAttribute("statusMessage", MessagePropertyUtil.getMessage("account.activation.success"));

		}
		else{
			model.addAttribute("classs", "error");
			model.addAttribute("statusMessage", MessagePropertyUtil.getMessage("account.activation.error"));
		}
		
		
	}

}
