package com.fantagame.be.business.data.dao.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.fantagame.be.application.MessagePropertyUtil;
import com.fantagame.be.business.data.DataConstants;
import com.fantagame.be.business.data.bean.Iface.IBean;
import com.fantagame.be.business.data.dao.Iface.GenericDAO;
import com.fantagame.be.business.data.exception.DataLayerException;
import com.fantagame.be.business.data.exception.InvalidDataException;


public abstract class JpaBaseDao <T extends IBean<Id>, Id extends Serializable> 
				implements GenericDAO<T, Id> {
	
	@PersistenceContext(unitName="fantagame-jpa",type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;	
	
	private Class<T> entityClass;	
   
	private static Logger log = Logger.getLogger(JpaBaseDao.class); 
	
	@SuppressWarnings("unchecked")
	public JpaBaseDao() {
				
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();		
		this.entityClass = (Class<T>) genericSuperclass .getActualTypeArguments()[0];
		
	} 
	
	@Override
	public void save(T entity)throws InvalidDataException,DataLayerException{

		if(entity == null)
			throw new InvalidDataException(MessagePropertyUtil.getMessage("dao.dataExcep"),
					DataConstants.DAO_LOGGIN, this.getClass() );
		
		try {

			entityManager.persist(entity);
			log.info("Save object "+entity.getClass().getName() + " , object ID:" + entity.getId());
					
		} catch (HibernateException e){
			throw new DataLayerException(MessagePropertyUtil.getMessage("dao.layerExcep"),e,
					DataConstants.DAO_LOGGIN,this.getClass());
		}
		catch (RuntimeException e) {
			throw new DataLayerException(MessagePropertyUtil.getMessage("dao.layerExcep"),e,
					DataConstants.DAO_LOGGIN,this.getClass());
		}
	}
	
	@Override
	public void remove(T entity)throws DataLayerException{
		if(entity == null)
			throw new InvalidDataException(MessagePropertyUtil.getMessage("dao.dataExcep"),
					DataConstants.DAO_LOGGIN,this.getClass());
		try {

			T found = findById(entity.getId());
			if(found != null){
				entityManager.remove(found);
				log.info("Remove object "+entity.getClass().getName() + " , object ID:" + entity.getId());
			}
		} catch (RuntimeException e) {
			throw new DataLayerException(MessagePropertyUtil.getMessage("dao.layerExcep"),e,
					DataConstants.DAO_LOGGIN,this.getClass());
		}
	}
	    
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll()throws DataLayerException {
		try {
			
			return entityManager.createNativeQuery("SELECT A FROM "+entityClass.getName() + " A",entityClass).getResultList();
		
		} catch (RuntimeException e) {
			throw new DataLayerException(MessagePropertyUtil.getMessage("dao.layerExcep"),
					e,DataConstants.DAO_LOGGIN,this.getClass());
		}
	}
	   
	@Override
	public T merge(T entity)throws InvalidDataException,DataLayerException {
		
		if(entity == null)
			throw new InvalidDataException(MessagePropertyUtil.getMessage("dao.dataExcep"),
											DataConstants.DAO_LOGGIN,this.getClass());
		
		T newEntity= null;
		
		try {
			newEntity = entityManager.merge(entity);
			log.info("Merge object "+entity.getClass().getName() + " , object ID:" + entity.getId());
		} catch (RuntimeException e) {
			throw new DataLayerException(MessagePropertyUtil.getMessage("dao.layerExcep"),e,
					DataConstants.DAO_LOGGIN,this.getClass());
		}
		return newEntity;
	}
	
	@Override
	public void flush(T entity) throws InvalidDataException,DataLayerException {
		
		try {
			entityManager.flush();
		} catch (RuntimeException e) {
			
			throw new DataLayerException(MessagePropertyUtil.getMessage("dao.layerExcep"),e,
										 DataConstants.DAO_LOGGIN,this.getClass());
		}
	}
	
	public T findById(Id id) throws InvalidDataException,DataLayerException{
		
		if(id == null)
			throw new InvalidDataException(MessagePropertyUtil.getMessage("dao.dataExcep"),
											DataConstants.DAO_LOGGIN,this.getClass());
		
		T newEntity = null;
		
		try {
			newEntity = entityManager.find(entityClass, id );
			log.info("FinbById object "+entityClass.getName() + " , object ID:" + id);
		} catch (RuntimeException e) {
			
			throw new DataLayerException(MessagePropertyUtil.getMessage("dao.layerExcep"),e,
										 DataConstants.DAO_LOGGIN,this.getClass());
		}
		
		return newEntity;
	}

	@Override
	public List<T> sqlExecution(String s) throws InvalidDataException,
			DataLayerException {
		try {
			
			log.info("SqlExecution - query: "+s);
			
			return entityManager.createNamedQuery(s,entityClass).getResultList();
		
		} catch (RuntimeException e) {
			throw new DataLayerException(MessagePropertyUtil.getMessage("dao.layerExcep"),
					e,DataConstants.DAO_LOGGIN,this.getClass());
		}
	} 

	
	protected EntityManager getEntityManager(){
		return entityManager;
	}
	
	protected Class<T> getClassType(){
		return entityClass;
	}

}
