package com.fantagame.be.business.service;

import java.util.List;

import javax.annotation.Resource;

import com.fantagame.be.application.MessagePropertyUtil;

import com.fantagame.be.business.data.bean.UserLogin;
import com.fantagame.be.business.data.bean.squadra.FantaSquadra;
import com.fantagame.be.business.data.dao.Iface.GenericDAO;
import com.fantagame.be.business.service.Iface.IFantaSquadra;
import com.fantagame.be.business.service.Iface.IMessageBean;
import com.fantagame.be.business.service.model.MessageResponseService;

public class FantaSquadraService implements IFantaSquadra{
	
	@Resource(name="fantaSquadraDao")
	private GenericDAO<FantaSquadra, Integer> daoFantaSquadra;

	@Override
	public IMessageBean getSquadreFromUser(String username) {
		IMessageBean messageBean = new MessageResponseService();
		try{
			if(username != null){
				List<FantaSquadra> groups = daoFantaSquadra.findByProperties(new FantaSquadra(new UserLogin(username),null));
				if(groups.isEmpty()){
					messageBean.setCode(ServiceConstants.NO_DATA);
					messageBean.setMessage(MessagePropertyUtil.getMessage("service.nullvalue"));
					messageBean.setResult(null);
				}				
				else{
					messageBean.setCode(ServiceConstants.SUCCESS_CODE);
					messageBean.setResult(groups);
				}
			}
			else{
				messageBean.setCode(ServiceConstants.NULL_CODE);
				messageBean.setMessage(MessagePropertyUtil.getMessage("service.nullvalue"));
			}
		}catch(Exception e ){
			messageBean.setCode(ServiceConstants.DAO_GROUP_ERROR_CODE);
		}
		
		return messageBean;
	}

}
