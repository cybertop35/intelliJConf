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

import com.fantagame.be.application.MessagePropertyUtil;
import com.fantagame.be.business.data.DataConstants;
import com.fantagame.be.business.data.bean.Authorities;
import com.fantagame.be.business.data.bean.Persona;
import com.fantagame.be.business.data.bean.UserLogin;
import com.fantagame.be.business.data.exception.DataLayerException;
import com.fantagame.be.business.data.exception.InvalidDataException;



@Repository(value="userLoginDao")
public class JpaUserLoginDao extends JpaBaseDao<UserLogin, String>{

	protected static final String ID = "nick";
	protected static final String PWS = "password";
	protected static final String STATO = "attivo";
    protected static final String PERSONA = "idUser";
    protected static final String AUTHO = "nick";
	
    public  JpaUserLoginDao(){
    	super();
    }
    
	@Override
	public List<UserLogin> findByProperties(UserLogin instance)
			throws InvalidDataException, DataLayerException {
		if(instance == null)
			throw new InvalidDataException(MessagePropertyUtil.getMessage("dao.dataExcep"),
					DataConstants.DAO_LOGGIN, this.getClass() );
		
		List<UserLogin> results = null;
		try{		
			
			CriteriaBuilder criteriaBuilder =  getEntityManager().getCriteriaBuilder();
			CriteriaQuery<UserLogin> query = criteriaBuilder.createQuery(UserLogin.class); 
			
			Metamodel metamodel = getEntityManager().getMetamodel();
			
			EntityType<UserLogin> UserLogin_ = metamodel.entity(UserLogin.class);
			
			Root<UserLogin> userLogin = query.from(UserLogin.class);
			
			if(instance.getAuthorities() != null){
				Join<UserLogin,Authorities> gJoin = userLogin.join(UserLogin_.getAttribute(AUTHO).getName());
				query.where(criteriaBuilder.equal(gJoin.get(AUTHO), ((Authorities) instance.getAuthorities()).getId()));
			}
			
			if(instance.getPersona() != null){
				Join<UserLogin,Persona> gJoin = userLogin.join(UserLogin_.getAttribute(PERSONA).getName());
				query.where(criteriaBuilder.equal(gJoin.get(PERSONA), instance.getPersona()));
			}
					
			if(instance.getId() != null)
				query.where(criteriaBuilder.equal(userLogin.get(ID), instance.getId()));
			if(instance.getPassword() != null)
				query.where(criteriaBuilder.equal(userLogin.get(PWS), instance.getPassword()));			
			if(instance.getAttivo() != null)
				query.where(criteriaBuilder.equal(userLogin.get(STATO), instance.getAttivo()));
						
			TypedQuery<UserLogin> q = getEntityManager().createQuery(query);
			
			results  = q.getResultList();
			
		}catch (RuntimeException e){
			throw new DataLayerException(MessagePropertyUtil.getMessage("dao.layerExcep"),e,
					DataConstants.DAO_LOGGIN,this.getClass());
		}
		
		return results;
	}

	public UserLogin getByActivationCode(String code)throws InvalidDataException, DataLayerException{
		if(code == null)
			throw new InvalidDataException(MessagePropertyUtil.getMessage("dao.dataExcep"),
					DataConstants.DAO_LOGGIN, this.getClass() );
		
		List<UserLogin> list = getEntityManager().createNamedQuery("SELECT A FROM "+getClassType().getName() + " A WHERE ACTIVATIONCODE='"+code+"'",getClassType() ).getResultList();
		if(list != null && list.size()>0)
			return list.get(0);
		
		return null;
	}
}
