package com.fantagame.be.business.data.dao.Iface;

import java.io.Serializable;
import java.util.List;

import com.fantagame.be.business.data.exception.DataLayerException;
import com.fantagame.be.business.data.exception.InvalidDataException;

public interface GenericDAO<T , Id extends Serializable> {
	 
   
   public List<T> findByProperties(T instance)throws InvalidDataException,DataLayerException;
   public List<T> sqlExecution(String s) throws InvalidDataException,DataLayerException;    
 
    
   public void save(T entity)throws InvalidDataException,DataLayerException;   
   public void remove(T entity)throws DataLayerException;
    
   public List<T> findAll()throws DataLayerException; 
    
   public T merge(T entity)throws InvalidDataException,DataLayerException;
   public void flush(T entity) throws InvalidDataException,DataLayerException;
   public T findById(Id id) throws InvalidDataException,DataLayerException;
    
}
