package com.fantagame.be.business.data.dao.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fantagame.be.business.data.bean.Authorities;
import com.fantagame.be.business.data.dao.Iface.GenericDAO;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-application-context.xml"})
public class JpaAuthoritiesDaoTest {

	private static final String DEFAULT_AUTHORITY	= "Admin";
	private static final String NEW_AUTHORITY 		= "User";

	@Resource(name="authoritiesDao")
	private GenericDAO<Authorities, Integer> dao;
	
	private Authorities authorities;
	
	@PostConstruct
	private void init(){
		
	 authorities =  new Authorities();
	 authorities.setAuthority(DEFAULT_AUTHORITY);
	 
	}
	
	
	@Test
	@Order(value=1)
	public void globalDaoTest(){
		dao.save(authorities);
		
		//test findById --> Test su save
		assertEquals(authorities,dao.findById(authorities.getId()));
		
		//test update
		authorities.setAuthority(NEW_AUTHORITY);
		dao.merge(authorities);
		assertEquals(NEW_AUTHORITY, dao.findById(authorities.getId()).getAuthority());
		
		//test find all
		assertTrue(dao.findAll().size() > 0);
		
		//test find by properties
		Authorities te = new Authorities();
		te.setAuthority(NEW_AUTHORITY);
		List<Authorities> list = dao.findByProperties(te);
		assertEquals(list.get(0).getAuthority(),NEW_AUTHORITY);
		
		//test remuve
		dao.remove(authorities);
		assertNull(dao.findById(authorities.getId()));
		
	}
	
}
