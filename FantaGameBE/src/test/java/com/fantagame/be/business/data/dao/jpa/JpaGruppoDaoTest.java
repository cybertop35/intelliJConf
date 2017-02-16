package com.fantagame.be.business.data.dao.jpa;

import static org.junit.Assert.assertEquals;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fantagame.be.business.data.bean.Gruppo;
import com.fantagame.be.business.data.dao.Iface.GenericDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-application-context.xml"})
public class JpaGruppoDaoTest {

	@Resource(name="gruppoDao")
	private GenericDAO<Gruppo, Integer> dao;
	
	@SuppressWarnings("unused")
	private Gruppo gruppo;

	@PostConstruct
	private void init(){
		gruppo =  new Gruppo();
	}
		
	@Test
	@Order(value=1)
	public void globalDaoTest(){
		assertEquals("A","A");		
	}
	
}
