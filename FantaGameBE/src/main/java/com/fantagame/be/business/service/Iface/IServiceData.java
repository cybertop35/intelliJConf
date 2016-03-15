package com.fantagame.be.business.service.Iface;

import java.io.Serializable;
import java.util.List;

import com.fantagame.be.business.data.dao.Iface.GenericDAO;

public interface IServiceData <T,Id extends Serializable> {

	public void setDao(GenericDAO<T,Id> dao);
	
	public List<T> getAll();
	
	public List<T> findBy(T entity);
	
	public void save(T entity);
	
	public T update(T enity);
	
	
}
