package com.fantagame.be.business.data.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fantagame.be.business.data.bean.Calendario;
import com.fantagame.be.business.data.bean.complexId.CalendarioId;
import com.fantagame.be.business.data.exception.DataLayerException;
import com.fantagame.be.business.data.exception.InvalidDataException;

@Repository(value="calendarioDao")
public class JpaCalendarioDao extends JpaBaseDao<Calendario, CalendarioId>{

	@Override
	public List<Calendario> findByProperties(Calendario instance)
			throws InvalidDataException, DataLayerException {
		// TODO Auto-generated method stub
		return null;
	}

}
