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
import com.fantagame.be.business.data.bean.Authorities;
import com.fantagame.be.business.data.bean.UserLogin;
import com.fantagame.be.business.data.exception.DataLayerException;
import com.fantagame.be.business.data.exception.InvalidDataException;
import com.fantagame.be.application.*;


@Repository(value="authoritiesDao")
public class JpaAuthoritiesDao extends JpaBaseDao <Authorities, Integer> {

	protected static String ID 			= "idAuthorities";
	protected static String AUTHORITY 	= "authority";
	protected static String NICK 		= "nick";
	
	
	public JpaAuthoritiesDao() {
		super();		
	}

	@Override
	public List<Authorities> findByProperties(Authorities instance)
			throws InvalidDataException, DataLayerException {
		List<Authorities> results = null;
		
		if(instance == null)
			throw new InvalidDataException(MessagePropertyUtil.getMessage("dao.dataExcep"),
					DataConstants.DAO_LOGGIN, this.getClass() );
		
		try{		
			
			CriteriaBuilder criteriaBuilder =  getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Authorities> query = criteriaBuilder.createQuery(Authorities.class); 
			Metamodel metamodel = getEntityManager().getMetamodel();
			
			EntityType<Authorities> Authorities_ = metamodel.entity(Authorities.class);
			
			Root<Authorities> authorities = query.from(Authorities.class);
			if(instance.getUserLogin() != null){
				Join<Authorities,UserLogin> userLogin = authorities.join(Authorities_.getAttribute(NICK).getName());
				query.where(criteriaBuilder.equal(userLogin.get(NICK), instance.getUserLogin().getNick()));
			}
			if(instance.getId() != null)
				query.where(criteriaBuilder.equal(authorities.get(ID), instance.getId()));
			if(instance.getAuthority() != null)
				query.where(criteriaBuilder.equal(authorities.get(AUTHORITY), instance.getAuthority()));
						
			TypedQuery<Authorities> q = getEntityManager().createQuery(query);
			
			results = q.getResultList();
			
		}catch (RuntimeException e){
			throw new DataLayerException(MessagePropertyUtil.getMessage("dao.layerExcep"),e,
					DataConstants.DAO_LOGGIN,this.getClass());
		}
		
		return results;
	}
	
	public List<Authorities> findByUserName(String instance)
			throws InvalidDataException, DataLayerException {
		List<Authorities> results = null;
		
		if(instance == null)
			throw new InvalidDataException(MessagePropertyUtil.getMessage("dao.dataExcep"),
					DataConstants.DAO_LOGGIN, this.getClass() );
		
		try{		
			
			
			
		}catch (RuntimeException e){
			throw new DataLayerException(MessagePropertyUtil.getMessage("dao.layerExcep"),e,
					DataConstants.DAO_LOGGIN,this.getClass());
		}
		
		return results;
	}
	

}
