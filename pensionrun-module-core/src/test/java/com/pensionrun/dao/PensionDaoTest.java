package com.pensionrun.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pensionrun.entity.Pension;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/META-INF/test-core-context.xml" })
public class PensionDaoTest {
	@Autowired
	private PensionDao pensionDao;
	
	@Test
	@Rollback(true)
	public void readIdfArea2ListGroupByIdfArea2Test() {
		List<Integer> idfArea2List  = pensionDao.readIdfArea2ListGroupByIdfArea2();
//		for(Integer idfArea2 : idfArea2List){
//			System.out.println(idfArea2+"// count :"+ pensionDao.readPensionCount(idfArea2));
//		}
	}
	
	@Test
	public void readPensionCountTest(){
		Long pensionCount = pensionDao.readPensionCount(1);
		System.out.println(pensionCount);
	}
	
	@Test
	public void checkTodayNewPensionTest(){
//		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
//		String todayDate = df.format(new Date());
//		System.out.println(todayDate);
		Long checkCount = pensionDao.checkTodayNewPension(18);
		System.out.println(checkCount);
	}
	@Test
	public void readByNameTest() {
		List<Pension> pensionList = pensionDao.readByName("천");		
//		for(Pension p : pensionList){
//			System.out.println(p.getName());
//			System.out.println(a.getIdfArea2());
//		}
	}
	
}
