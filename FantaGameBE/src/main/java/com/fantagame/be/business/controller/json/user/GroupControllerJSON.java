package com.fantagame.be.business.controller.json.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fantagame.be.business.controller.ControllerMapping;
import com.fantagame.be.business.data.bean.Gruppo;
import com.fantagame.be.business.data.bean.squadra.FantaSquadra;
import com.fantagame.be.business.service.Iface.IFantaSquadra;
import com.fantagame.be.business.service.Iface.IGruppoService;
import com.fantagame.be.business.service.Iface.IMessageBean;

@Controller
@RequestMapping(value=ControllerMapping.DEFAULT_PRIVATE_APP+
						ControllerMapping.JSON + 
						ControllerMapping.GROUP_SERVICE )
public class GroupControllerJSON {

	@Autowired
	private IGruppoService gruppoService;
	
	@Autowired
	private IFantaSquadra fantaSquadraService;
	
	@RequestMapping(value=ControllerMapping.GET_GRUPPI_JSON,method=RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody List<FantaSquadra> getGroups(String username)  throws Exception {
		return fantaSquadraService.getSquadreFromUser(username).getResult();
	}
	
	@RequestMapping(value=ControllerMapping.GET_GRUPPI_JSON,method=RequestMethod.POST)
	public @ResponseBody IMessageBean createGroup(@RequestBody Gruppo gruppo)  throws Exception {
		return gruppoService.createGruppo(gruppo);
	}
}
