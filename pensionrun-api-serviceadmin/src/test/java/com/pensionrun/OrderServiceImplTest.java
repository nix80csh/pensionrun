package com.pensionrun;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pensionrun.dto.OrderDto;
import com.pensionrun.entity.Account;
import com.pensionrun.service.OrderService;
import com.pensionrun.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/META-INF/core-context.xml","classpath:/META-INF/common-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class OrderServiceImplTest {
	@Autowired
	private OrderService orderService;
	
	@Test
	public void orderCreateTest()throws Exception{
		OrderDto orderDto=new OrderDto();
		orderDto.setIdfAccount(8);
		orderDto.setIdfRoom(9955);
		orderDto.setName("펜튀테스트");
		orderDto.setMobile("01041670478");
		orderDto.setEmail("tom@pensionrun.com");
		orderDto.setState('0');
		orderDto.setPeopleCount((byte)2);
		orderDto.setAmount(24000.0f);
		orderDto.setIdfAccount(8);
		orderDto.setCheckinDate(DateUtil.getDiffDay(new Date(),35, "yyyy-MM-dd"));
		orderDto.setCheckoutDate(DateUtil.getDiffDay(new Date(), 36, "yyyy-MM-dd"));
		orderDto.setReservation(true);
		orderService.orderCreate(orderDto);
	}
}
