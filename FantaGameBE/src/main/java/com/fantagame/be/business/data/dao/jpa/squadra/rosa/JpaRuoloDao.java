package com.fantagame.be.business.data.dao.jpa.squadra.rosa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fantagame.be.business.data.bean.squadra.rosa.Ruolo;
import com.fantagame.be.business.data.dao.jpa.JpaBaseDao;
import com.fantagame.be.business.data.exception.DataLayerException;
import com.fantagame.be.business.data.exception.InvalidDataException;

@Repository(value="ruoloDao")
public class JpaRuoloDao extends JpaBaseDao<Ruolo, Integer> {

	@Override
	public List<Ruolo> findByProperties(Ruolo instance)
			throws InvalidDataException, DataLayerException {
		// TODO Auto-generated method stub
		return null;
	}

}
