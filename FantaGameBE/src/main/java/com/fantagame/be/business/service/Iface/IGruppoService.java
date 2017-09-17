package com.fantagame.be.business.service.Iface;

import com.fantagame.be.business.data.bean.Gruppo;

public interface IGruppoService {

	IMessageBean createGruppo(Gruppo gruppo);
	IMessageBean changeGruppo(Gruppo gruppo);
	IMessageBean invita(Gruppo gruppo, String [] email);
	IMessageBean sendMessageToAll(Gruppo gruppo,String messString,String subject );
	IMessageBean getGruppi(String username);
}
