package com.fantagame.be.business.data.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fantagame.be.business.data.bean.Classifica;
import com.fantagame.be.business.data.exception.DataLayerException;
import com.fantagame.be.business.data.exception.InvalidDataException;

@Repository(value="classificaDao")
public class JpaClassificaDao extends JpaBaseDao<Classifica, Integer>{



	@Override	
	public List<Classifica> findByProperties(Classifica instance)
		throws InvalidDataException, DataLayerException {
		// TODO Auto-generated method stub
		return null;
	}
}
