package com.fantagame.be.business.data.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.springframework.stereotype.Repository;

import com.fantagame.be.business.data.DataConstants;
import com.fantagame.be.business.data.bean.Gruppo;
import com.fantagame.be.business.data.bean.Persona;
import com.fantagame.be.business.data.exception.DataLayerException;
import com.fantagame.be.business.data.exception.InvalidDataException;
import com.fantagame.be.application.*;

@Repository(value="gruppoDao")
public class JpaGruppoDao extends JpaBaseDao<Gruppo, Integer>{

	protected static final String ID 					= "idGruppo";	 
	protected static final String DATA_CREAZIONE 		= "dataCreazione";	 
	protected static final String MAX_CREDITO 			= "maxCredito";	 
	protected static final String MAX_PORTIERI			= "maxPortieri"; 
	protected static final String MAX_DIFENSORI 		= "maxDifensori";	 
	protected static final String MAX_CENTRO_CAMPISTI 	= "maxCentroCamposti"; 
	protected static final String MAX_ATTACANTI 		= "maxAttaccanti"; 
	protected static final String NOME 					= "nome";	 
	protected static final String PERSONA 				= "owner";
	
	public JpaGruppoDao() {
		super();
	}	
	
	@Override
	public List<Gruppo> findByProperties(Gruppo instance){
		
		if(instance == null)
			throw new InvalidDataException(MessagePropertyUtil.getMessage("dao.dataExcep"),
					DataConstants.DAO_LOGGIN, this.getClass() );
		
		List<Gruppo> results = null;
		try{		
			
			CriteriaBuilder criteriaBuilder =  getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Gruppo> query = criteriaBuilder.createQuery(Gruppo.class); 
			
			Metamodel metamodel = getEntityManager().getMetamodel();
			
			EntityType<Gruppo> Gruppo_ = metamodel.entity(Gruppo.class);
			
			Root<Gruppo> gruppo = query.from(Gruppo.class);
			
			if(instance.getOwner() != null){
				Join<Gruppo,Persona> gJoin = gruppo.join(Gruppo_.getAttribute(PERSONA).getName());
				query.where(criteriaBuilder.equal(gJoin.get(PERSONA), instance.getOwner().getId()));
			}
						
			
			if(instance.getId() != null)
				query.where(criteriaBuilder.equal(gruppo.get(ID), instance.getId()));
			if(instance.getNome() != null)
				query.where(criteriaBuilder.equal(gruppo.get(NOME), instance.getNome()));
			if( instance.getDateCreazione() != null )
				query.where(criteriaBuilder.equal(gruppo.get(DATA_CREAZIONE), instance.getDateCreazione()));
			if(instance.getMaxAttaccanti() != null)
				query.where(criteriaBuilder.equal(gruppo.get(MAX_ATTACANTI), instance.getMaxAttaccanti()));
			if(instance.getMaxCentroCamposti() != null)
				query.where(criteriaBuilder.equal(gruppo.get(MAX_CENTRO_CAMPISTI), instance.getMaxCentroCamposti()));
			if(instance.getMaxCredito() != null)
				query.where(criteriaBuilder.equal(gruppo.get(MAX_CREDITO), instance.getMaxCredito()));
			if(instance.getMaxDifensori() != null)
				query.where(criteriaBuilder.equal(gruppo.get(MAX_DIFENSORI), instance.getMaxDifensori()));
			if(instance.getMaxPortieri() != null)
				query.where(criteriaBuilder.equal(gruppo.get(MAX_PORTIERI), instance.getMaxPortieri()));
					
			TypedQuery<Gruppo> q = getEntityManager().createQuery(query);
			
			results  = q.getResultList();
	}catch (RuntimeException e){
		throw new DataLayerException(MessagePropertyUtil.getMessage("dao.layerExcep"),e,
				DataConstants.DAO_LOGGIN,this.getClass());
	}
	
	return results;
	}
	

}
