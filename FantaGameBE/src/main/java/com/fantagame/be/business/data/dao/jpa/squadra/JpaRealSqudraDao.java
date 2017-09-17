package com.fantagame.be.business.data.dao.jpa.squadra;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.fantagame.be.application.MessagePropertyUtil;
import com.fantagame.be.business.data.DataConstants;
import com.fantagame.be.business.data.bean.squadra.RealSquadra;
import com.fantagame.be.business.data.dao.jpa.JpaBaseDao;
import com.fantagame.be.business.data.exception.DataLayerException;
import com.fantagame.be.business.data.exception.InvalidDataException;

@Repository(value="realSqudraDao")
public class JpaRealSqudraDao extends JpaBaseDao<RealSquadra, Integer> {

	protected static final String ID 	= "idGruppo";
	protected static final String NOME 	= "nome";	
	protected static final String IMG	= "IMG";
	@Override
	public List<RealSquadra> findByProperties(RealSquadra instance)
			throws InvalidDataException, DataLayerException {
		if(instance == null)
			throw new InvalidDataException(MessagePropertyUtil.getMessage("dao.dataExcep"),
					DataConstants.DAO_LOGGIN, this.getClass() );
		
		List<RealSquadra> results = null;
		try{	
			CriteriaBuilder criteriaBuilder =  getEntityManager().getCriteriaBuilder();
			CriteriaQuery<RealSquadra> query = criteriaBuilder.createQuery(RealSquadra.class); 
					
			Root<RealSquadra> root = query.from(RealSquadra.class);
			
			if(instance.getId() != null)
				query.where(criteriaBuilder.equal(root.get(ID), instance.getId()));
			if(instance.getNome() != null)
				query.where(criteriaBuilder.equal(root.get(NOME), instance.getNome()));
			if( instance.getImg() != null )
				query.where(criteriaBuilder.equal(root.get(IMG), instance.getImg()));
			
					
			TypedQuery<RealSquadra> q = getEntityManager().createQuery(query);
			
			results  = q.getResultList();
			
			
		}catch (RuntimeException e){
			throw new DataLayerException(MessagePropertyUtil.getMessage("dao.layerExcep"),e,
					DataConstants.DAO_LOGGIN,this.getClass());
		}
		
		
		return results;
	}


	
}
