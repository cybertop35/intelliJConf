package com.i4c.isp.auth.aaap;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.i4c.isp.auth.aaap.entity.Profile;


@Singleton
@Startup
public class ManagerProfileSession implements ManagerProfileLocal {
	
	private static final Logger logger = Logger.getLogger(ManagerProfileSession.class);
	
	@PersistenceContext(unitName = "profile-unit")
	private EntityManager em;
	private List<Profile> profiles;
	

	@SuppressWarnings("unchecked")
	@PostConstruct
	void  init() {
		
		logger.info("--------Start ManagerProfileSession -------");
		
		Query query = em.createNamedQuery("findAllProfiles");
		profiles = query.getResultList();
		
		logger.info("-------Profiles mapping:"+ profiles.size()+" -------");

	}

	@Override
	public List<String> getAAAPProfile(String externalProfile) {
		List<String> profile = new ArrayList<String>();

		for(Profile p : profiles){
			
			if(p.getSystemProfile().equalsIgnoreCase(externalProfile))
				profile.add(p.getAaapProfile());
		}
			
		return profile;
	}

	@Override
	public void reload() {
		init();
		
	}
	
}