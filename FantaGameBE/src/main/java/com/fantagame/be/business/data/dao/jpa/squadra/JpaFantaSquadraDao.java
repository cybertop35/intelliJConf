package com.fantagame.be.business.data.dao.jpa.squadra;

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
import com.fantagame.be.business.data.bean.Classifica;
import com.fantagame.be.business.data.bean.Gruppo;
import com.fantagame.be.business.data.bean.Persona;
import com.fantagame.be.business.data.bean.UserLogin;
import com.fantagame.be.business.data.bean.squadra.FantaSquadra;
import com.fantagame.be.business.data.dao.jpa.JpaBaseDao;
import com.fantagame.be.business.data.exception.DataLayerException;
import com.fantagame.be.business.data.exception.InvalidDataException;
import com.fantagame.be.application.*;

@Repository(value="fantaSquadraDao")
public class JpaFantaSquadraDao extends JpaBaseDao<FantaSquadra, Integer>  {

	protected static final String ID 			= "idSquadra";
	protected static final String NOME 			= "nome";
	
	protected static final String USERLOGIN 	= "userNick";
	protected static final String GRUPPO 		= "idGruppo" ;
	protected static final String CLASSIFICA 	= "idSquadra";
	protected static final String ROSA 			= "idGiocatore";
	
	public JpaFantaSquadraDao() {
		super();		
	}

	@Override
	public List<FantaSquadra> findByProperties(FantaSquadra instance)
			throws InvalidDataException, DataLayerException {
		
		if(instance == null)
			throw new InvalidDataException(MessagePropertyUtil.getMessage("dao.dataExcep"),
					DataConstants.DAO_LOGGIN, this.getClass() );
		
		List<FantaSquadra> results = null;
		try{		
			
			CriteriaBuilder criteriaBuilder =  getEntityManager().getCriteriaBuilder();
			CriteriaQuery<FantaSquadra> query = criteriaBuilder.createQuery(FantaSquadra.class); 
			
			Metamodel metamodel = getEntityManager().getMetamodel();
			
			EntityType<FantaSquadra> FantaSquadra_ = metamodel.entity(FantaSquadra.class);
			
			Root<FantaSquadra> fantaSquadra = query.from(FantaSquadra.class);
			
			if(instance.getUserLogin() != null){
				Join<FantaSquadra,UserLogin> gJoin = fantaSquadra.join(FantaSquadra_.getAttribute(USERLOGIN).getName());
				query.where(criteriaBuilder.equal(gJoin.get("nick"),  instance.getUserLogin().getId()));
			}	
			if(instance.getGruppo() != null){
				Join<FantaSquadra,Gruppo> gJoin = fantaSquadra.join(FantaSquadra_.getAttribute(GRUPPO).getName());
				query.where(criteriaBuilder.equal(gJoin.get(GRUPPO), instance.getGruppo()));
			}	
			if(instance.getClassifica() != null){
				Join<FantaSquadra,Classifica> gJoin = fantaSquadra.join(FantaSquadra_.getAttribute(CLASSIFICA).getName());
				query.where(criteriaBuilder.equal(gJoin.get(CLASSIFICA), instance.getClassifica()));
			}	
			if(instance.getRosa() != null){
				Join<FantaSquadra,Persona> gJoin = fantaSquadra.join(FantaSquadra_.getAttribute(ROSA).getName());
				query.where(criteriaBuilder.equal(gJoin.get(ROSA), instance.getRosa()));
			}	
			
			
			if(instance.getId() != null)
				query.where(criteriaBuilder.equal(fantaSquadra.get(ID), instance.getId()));
			if(instance.getNome() != null)
				query.where(criteriaBuilder.equal(fantaSquadra.get(NOME), instance.getNome()));
					
			TypedQuery<FantaSquadra> q = getEntityManager().createQuery(query);
			
			results  = q.getResultList();
		
			
	}catch (RuntimeException e){
		throw new DataLayerException(MessagePropertyUtil.getMessage("dao.layerExcep"),e,
				DataConstants.DAO_LOGGIN,this.getClass());
	}
	
	return results;
	}
}
