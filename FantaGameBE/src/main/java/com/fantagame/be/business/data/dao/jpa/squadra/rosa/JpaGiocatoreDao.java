package com.fantagame.be.business.data.dao.jpa.squadra.rosa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fantagame.be.business.data.bean.squadra.rosa.Giocatore;
import com.fantagame.be.business.data.dao.jpa.JpaBaseDao;
import com.fantagame.be.business.data.exception.DataLayerException;
import com.fantagame.be.business.data.exception.InvalidDataException;

@Repository(value="giocatoreDao")
public class JpaGiocatoreDao extends JpaBaseDao<Giocatore, Integer>{

	@Override
	public List<Giocatore> findByProperties(Giocatore instance)
			throws InvalidDataException, DataLayerException {
		// TODO Auto-generated method stub
		return null;
	}

}
