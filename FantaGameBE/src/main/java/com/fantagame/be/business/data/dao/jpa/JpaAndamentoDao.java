package com.fantagame.be.business.data.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fantagame.be.business.data.bean.Andamento;
import com.fantagame.be.business.data.exception.DataLayerException;
import com.fantagame.be.business.data.exception.InvalidDataException;

@Repository(value="andamentoDao")
public class JpaAndamentoDao extends JpaBaseDao<Andamento, Integer>{

	@Override
	public List<Andamento> findByProperties(Andamento instance)
			throws InvalidDataException, DataLayerException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
