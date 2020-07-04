package com.pensionrun.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pensionrun.entity.Area2;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/META-INF/test-core-context.xml" })
public class Area2DaoTest {
	@Autowired
	private Area2Dao area2Dao;
	
	@Test
	@Rollback(true)
	public void readByNameTest() {
		List<Area2> area2List = area2Dao.readByName("천");		
//		for(Area2 a : area2List){
//			System.out.println(a.getName());
//			System.out.println(a.getIdfArea2());
//		}
	}
}
