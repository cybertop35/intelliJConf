package com.fantagame.be.business.data.dao.jpa.squadra.rosa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fantagame.be.business.data.bean.squadra.rosa.Formazione;
import com.fantagame.be.business.data.dao.jpa.JpaBaseDao;
import com.fantagame.be.business.data.exception.DataLayerException;
import com.fantagame.be.business.data.exception.InvalidDataException;

@Repository(value="formazioneDao")
public class JpaFormazioneDao extends JpaBaseDao<Formazione, Integer>{

	@Override
	public List<Formazione> findByProperties(Formazione instance)
			throws InvalidDataException, DataLayerException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
