package com.pensionrun.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pensionrun.vo.OrderSearchVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/META-INF/test-core-context.xml" })
public class OrderDaoTest {
	@Autowired
	private OrderDao orderDao;
	
	

	@Test
	public void readByOrderSearchVoTest(){
		OrderSearchVo orderSearchVo=new OrderSearchVo();
//		orderSearchVo.setState('0');
//		orderSearchVo.setMobile("01041670477");
		orderSearchVo.setStartRegDate("2016-06-18");
		orderSearchVo.setEndRegDate("2016-06-20");
//		orderSearchVo.setCheckinDate("2016-06-18");
		orderSearchVo.setUnit(20);
		orderSearchVo.setPage(1);
		System.out.println(orderDao.getMaxSizeByOrderSearchVo(orderSearchVo));
//		System.out.println(orderDao.readByOrderSearchVo(orderSearchVo).size());
//		Assert.assertEquals(122, orderDao.readByOrderSearchVo(null).size());
	}
}
