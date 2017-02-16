package com.fantagame.be.business.data.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.fantagame.be.application.MessagePropertyUtil;
import com.fantagame.be.business.data.DataConstants;
import com.fantagame.be.business.data.bean.Persona;
import com.fantagame.be.business.data.bean.UserLogin;
import com.fantagame.be.business.data.exception.DataLayerException;
import com.fantagame.be.business.data.exception.InvalidDataException;

@Repository(value="personaDao")
public class JpaPersonaDao extends JpaBaseDao<Persona, Integer> {

	/*** Table fields ***/
	protected static final String ID_USER 				= "idUser";	
	protected static final String COGNOME 				= "cognome";	
	protected static final String NOME 					= "nome";	
	protected static final String SESSO 				= "sesso";	
	protected static final String DATA_NASCITA			= "dataNascita";	
	protected static final String EMAIL 				= "email";	
	protected static final String TELEFONO 				= "telefono";	
	protected static final String ACCETTA_CONDIZIONI 	= "accCond";
	
	protected static final String USER_LOGIN 			= "idUser";	
	protected static final String GRUPPO 				= "idUser";
	protected static final String SQUADRA 				= "idUser";
	
	public JpaPersonaDao() {
		super();
		
	}

	@Override
	public List<Persona> findByProperties(Persona instance)
			throws InvalidDataException, DataLayerException {
		
		if(instance == null)
			throw new InvalidDataException(MessagePropertyUtil.getMessage("dao.dataExcep"),
					DataConstants.DAO_LOGGIN, this.getClass() );
		
		List<Persona> results = null;
		try{		
			
			CriteriaBuilder criteriaBuilder =  getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Persona> query = criteriaBuilder.createQuery(Persona.class); 
						
			Root<Persona> root = query.from(Persona.class);
			
				
			if(instance.getUserLogin() != null){
				Join<Persona,UserLogin> userLogin = root.join(USER_LOGIN);
				query.where(criteriaBuilder.equal(userLogin.get(USER_LOGIN), instance.getUserLogin().getNick()));
			}
			
			
			if(instance.getId() != null)
				query.where(criteriaBuilder.equal(root.get(ID_USER), instance.getId()));
			if(instance.getCognome() != null)
				query.where(criteriaBuilder.equal(root.get(COGNOME), instance.getCognome()));			
			if(instance.getNome() != null)
				query.where(criteriaBuilder.equal(root.get(NOME), instance.getNome()));
			if(instance.getSesso() != null )
				query.where(criteriaBuilder.equal(root.get(SESSO), instance.getSesso()));
			if(instance.getDataNascita() != null)
				query.where(criteriaBuilder.equal(root.get(DATA_NASCITA), instance.getDataNascita()));
			if(instance.getEmail() != null)
				query.where(criteriaBuilder.equal(root.get(EMAIL), instance.getEmail()));
			if(instance.getTelefono() != null)
				query.where(criteriaBuilder.equal(root.get(TELEFONO), instance.getTelefono()));
			if(instance.getAccCond() != null)
				query.where(criteriaBuilder.equal(root.get(ACCETTA_CONDIZIONI), instance.getAccCond()));
			
			
			TypedQuery<Persona> q = getEntityManager().createQuery(query);
			
			results  = q.getResultList();
			
		}catch (RuntimeException e){
			throw new DataLayerException(MessagePropertyUtil.getMessage("dao.layerExcep"),e,
					DataConstants.DAO_LOGGIN,this.getClass());
		}
		
		return results;
	}

	

	
}
