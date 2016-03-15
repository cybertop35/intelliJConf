package com.fantagame.be.business.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.fantagame.be.application.MessagePropertyUtil;
import com.fantagame.be.application.RequestCTX;
import com.fantagame.be.business.data.bean.Gruppo;
import com.fantagame.be.business.data.bean.UserLogin;
import com.fantagame.be.business.data.bean.squadra.FantaSquadra;
import com.fantagame.be.business.data.dao.Iface.GenericDAO;
import com.fantagame.be.business.service.Iface.IGruppoService;
import com.fantagame.be.business.service.Iface.IMessageBean;
import com.fantagame.be.business.service.model.MessageResponseService;
import com.fantagame.be.mail.EmailSenderService;
import com.fantagame.be.mail.MailConstant;
import com.fantagame.be.util.CryptoUtils;

public class GruppoService implements IGruppoService{

	@Resource(name="gruppoDao")
	private GenericDAO<Gruppo, Integer> daoGruppo;
	
	@Resource(name="emailSenderService")
	private EmailSenderService emailSenderService;

	@Resource(name="fantaSquadraDao")
	private GenericDAO<FantaSquadra, Integer> daoFantaSquadra;
	
	@Override
	public IMessageBean createGruppo(Gruppo gruppo) {
		IMessageBean messageBean = new MessageResponseService();
		if(gruppo != null ){
			try{
				if(!checkName(gruppo.getNome())){
					daoGruppo.save(gruppo);
					messageBean.setResult(gruppo);
					messageBean.setClazzToCast(Gruppo.class.getName());
					messageBean.setCode(ServiceConstants.SUCCESS_CODE);
					messageBean.setMessage(MessagePropertyUtil.getMessage("service.gruppo.save"));
				}
				else{
					messageBean.setCode(ServiceConstants.DUPLICATE_CODE);
					messageBean.setMessage(MessagePropertyUtil.getMessage("service.gruppo.duplicate"));
				}
					
			}catch (Exception e){
				messageBean.setCode(ServiceConstants.DAO_GROUP_ERROR_CODE);				
			}
		}
		else{
			messageBean.setCode(ServiceConstants.NULL_CODE);
			messageBean.setMessage(MessagePropertyUtil.getMessage("service.nullvalue"));
		}
		
		return messageBean;
	}

	@Override
	public IMessageBean changeGruppo(Gruppo gruppo) {
		IMessageBean messageBean = new MessageResponseService();
		if(gruppo != null ){
			try{
				Gruppo gruppo2 = daoGruppo.merge(gruppo);
				messageBean.setResult(gruppo2);
				messageBean.setClazzToCast(Gruppo.class.getName());
				messageBean.setCode(ServiceConstants.SUCCESS_CODE);
				messageBean.setMessage(MessagePropertyUtil.getMessage("service.gruppo.save"));
					
			}catch (Exception e){
				messageBean.setCode(ServiceConstants.DAO_GROUP_ERROR_CODE);				
			}
		}
		else{
			messageBean.setCode(ServiceConstants.NULL_CODE);
			messageBean.setMessage(MessagePropertyUtil.getMessage("service.nullvalue"));
		}
		
		return messageBean;
	}

	@Override
	public IMessageBean invita(Gruppo gruppo, String[] emails) {
		IMessageBean messageBean = new MessageResponseService();
		if(gruppo != null  && emails != null && emails.length > 0 ){
			try {
				String invitationCode = CryptoUtils.sha1(gruppo.getNome(), 
													ServiceConstants.ACTIVATION_KEY).substring(0, 6);
				
				String nome = gruppo.getOwner().getNick();
				gruppo.setInvitationCode(invitationCode);
				
				for(String email:emails)
					emailSenderService.send(emailSenderService.createMessage(email,nome+" "+MailConstant.INVITATION_SUBJECT), 
						MailConstant.TEMPLATE_INVITE, 
						inviteTemplateVariables(nome,invitationCode,gruppo.getNome()));
				
				messageBean.setCode(ServiceConstants.SUCCESS_CODE);
				messageBean.setMessage(MessagePropertyUtil.getMessage("service.gruppo.invite"));
				
			} catch ( Exception e ) {
				messageBean.setCode(ServiceConstants.DAO_GROUP_ERROR_CODE);
			}
		}
		else{
			messageBean.setCode(ServiceConstants.NULL_CODE);
			messageBean.setMessage(MessagePropertyUtil.getMessage("service.nullvalue"));
		}
		return messageBean;
	}

	@Override
	public IMessageBean sendMessageToAll(Gruppo gruppo, String messString,String subject ) {
		IMessageBean messageBean = new MessageResponseService();
		if(gruppo != null   && messString.length() > 0 ){
			try{
				FantaSquadra squadra = new FantaSquadra();
				squadra.setGruppo(gruppo);
				List<FantaSquadra> squadre = daoFantaSquadra.findByProperties(squadra);
				for(FantaSquadra sq : squadre){
					emailSenderService.send(emailSenderService.createMessage(sq.getUserLogin().getPersona(),subject), 
							MailConstant.TEMPLATE_SEND_MESSAGE, 
							messageTemplateVariables(gruppo.getNome(),messString));
				}
				
				messageBean.setCode(ServiceConstants.SUCCESS_CODE);
				messageBean.setMessage(MessagePropertyUtil.getMessage("service.gruppo.sendmessage"));
				
			}catch (Exception e){
				messageBean.setCode(ServiceConstants.DAO_GROUP_ERROR_CODE);
			}
		}
		else{
			messageBean.setCode(ServiceConstants.NULL_CODE);
			messageBean.setMessage(MessagePropertyUtil.getMessage("service.nullvalue"));
		}
		return messageBean;
	}

	@Override
	public IMessageBean getGruppi(String username){
		IMessageBean messageBean = new MessageResponseService();
		try{
			if(username != null){
				List<Gruppo> groups = daoGruppo.findByProperties(new Gruppo(new UserLogin(username)));
				messageBean.setCode(ServiceConstants.SUCCESS_CODE);
				messageBean.setResult(groups);
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
	/**
	 * Check if the name of group already exist.
	 *
	 * @param groupName
	 * @return true if exist, else otherwise
	 */

	private boolean checkName(String groupName){
		try{
			List<Gruppo> list = daoGruppo.findByProperties(new Gruppo(groupName));
			
			if(list!= null && list.size() > 0)
				return true;
			
		}catch(Exception e){return true;}
		
		return false;
	}
	
	private Map<Object, Object> inviteTemplateVariables(String name,String inviteCode,String gruppoName) {
		Map<Object, Object> variables = new HashMap<Object, Object>();
		variables.put(MailConstant.TEMPLATE_FIELD_NAME, name);
		variables.put(MailConstant.TEMPLATE_FIELD_LINK,RequestCTX.getContextPath());
		variables.put(MailConstant.TEMPLATE_FIELD_INVITE_CODE,inviteCode);
		variables.put(MailConstant.TEMPLATE_FIELD_GROUP_NAME,gruppoName);
		
		return variables;
	}
	
	private Map<Object, Object> messageTemplateVariables(String name,String message) {
		Map<Object, Object> variables = new HashMap<Object, Object>();
		variables.put(MailConstant.TEMPLATE_FIELD_NAME, name);
		variables.put(MailConstant.TEMPLATE_FIELD_MESSAGE,message);
		
		return variables;
	}
	
}
