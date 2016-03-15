package com.fantagame.be.business.data.dao.jpa;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fantagame.be.business.data.bean.squadra.FantaSquadra;
import com.fantagame.be.business.data.dao.Iface.GenericDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-application-context.xml"})
public class JpaPersonaDaoTest {

	
	@Resource(name="fantaSquadraDao")
	private GenericDAO<FantaSquadra, Integer> daoFantaSquadra;
	
	@Test
	public void globalDaoTest(){
		FantaSquadra s = daoFantaSquadra.findById(1);
		System.out.println(s.getNome());
	}
	
	
}
