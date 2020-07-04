package com.pensionrun.service;


import java.text.ParseException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;

import com.pensionrun.dao.OrderDao;
import com.pensionrun.dao.PaymentCancelCardDao;
import com.pensionrun.dao.PaymentCancelVbankDao;
import com.pensionrun.dao.PaymentCardDao;
import com.pensionrun.dao.PaymentDao;
import com.pensionrun.dao.PaymentVbankDao;
import com.pensionrun.dto.NicePayDto;
import com.pensionrun.dto.OrderDto;
import com.pensionrun.dto.PaymentCancelVbankDto;
import com.pensionrun.entity.Order;
import com.pensionrun.entity.Payment;
import com.pensionrun.entity.PaymentCancelVbank;
import com.pensionrun.entity.PaymentVbank;
import com.pensionrun.service.PaymentService;
import com.pensionrun.util.DateUtil;
import com.pensionrun.util.WooriPensionUtil;
import com.pensionrun.vo.WooriPensionVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/META-INF/core-context.xml","classpath:/META-INF/common-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class PaymentServiceTest {
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private PaymentCardDao paymentCardDao;
	@Autowired
	private PaymentVbankDao paymentVbankDao;
	@Autowired
	private PaymentCancelCardDao paymentCancelCardDao;
	@Autowired
	private PaymentCancelVbankDao paymentCancelVbankDao;
	@PersistenceContext
	private EntityManager em;
//	@Test
	public void test(){
		String sql = "from Order x order by x.idfOrder desc";
		Query q = em.createQuery(sql, Order.class);
	}

//	@Test
	public void nicePayCardTest() throws ParseException{		
		OrderDto orderDto=new OrderDto();
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
		Assert.assertEquals(true, paymentService.nicePayStart(new ExtendedModelMap(), orderDto, "0"));
		String sql = "from Order x order by x.idfOrder desc";
		Query q = em.createQuery(sql, Order.class);
		Order order=((Order) q.getResultList().get(0));
		int idfOrder=order.getIdfOrder();
		WooriPensionVo wooriPensionVo=new WooriPensionVo();
		System.out.println(order.toString());
		BeanUtils.copyProperties(order, wooriPensionVo);
		System.out.println(wooriPensionVo.toString());
		Assert.assertEquals("R",WooriPensionUtil.reservationInfo(wooriPensionVo));
		Assert.assertEquals('0',order.getState());
		NicePayDto nicePayDto=new NicePayDto();
		nicePayDto.setTID("test"+idfOrder);
		nicePayDto.setMoid(""+idfOrder);
		nicePayDto.setPayMethod("CARD");
		nicePayDto.setBuyerName("펜튀테스트");
		nicePayDto.setAmt((int)order.getAmount()+"");
		nicePayDto.setResultCode("3001");
		nicePayDto.setGoodsName("테스트");
		nicePayDto.setResultMsg("결과");
		nicePayDto.setAuthDate("160531154525");
		nicePayDto.setCardNo("49022082****5112");
		nicePayDto.setCardCode("01");
		nicePayDto.setCardName("비씨");
		nicePayDto.setCardQuota("00");
		nicePayDto.setAuthCode("33392665");
		order=orderDao.readById(idfOrder);
		Assert.assertEquals("S",paymentService.nicePayCallback(nicePayDto).getResultCode());
		Payment payment=paymentDao.readByIdfOrder(idfOrder);
		Assert.assertNotEquals(null,payment);
		Assert.assertNotEquals(null,payment);
		Assert.assertNotEquals(null,paymentCardDao.readById(payment.getIdfPayment()));
		Assert.assertEquals('1',orderDao.readById(idfOrder).getState());
		BeanUtils.copyProperties(order, wooriPensionVo);
		Assert.assertEquals("K",WooriPensionUtil.reservationInfo(wooriPensionVo));
		Assert.assertEquals("S",paymentService.cancelPaymentCard(idfOrder).getResultCode());
		Assert.assertNotEquals(null,paymentCancelCardDao.readById(payment.getIdfPayment()).getReceiveDate());
		Assert.assertEquals('5',orderDao.readById(idfOrder).getState());
		BeanUtils.copyProperties(order, wooriPensionVo);
		Assert.assertEquals("C",WooriPensionUtil.reservationInfo(wooriPensionVo));
		
	}
	@Test
	public void nicePayVbankTest() throws ParseException{
		OrderDto orderDto=new OrderDto();
		orderDto.setIdfRoom(9955);
		orderDto.setName("펜튀테스트");
		orderDto.setMobile("01041670478");
		orderDto.setEmail("tom@pensionrun.com");
		orderDto.setState('0');
		orderDto.setPeopleCount((byte)2);
		orderDto.setAmount(24000.0f);
		orderDto.setIdfAccount(8);
		orderDto.setCheckinDate(DateUtil.getDiffDay(new Date(),45 , "yyyy-MM-dd"));
		orderDto.setCheckoutDate(DateUtil.getDiffDay(new Date(), 46, "yyyy-MM-dd"));
		Assert.assertEquals(true, paymentService.nicePayStart(new ExtendedModelMap(), orderDto, "2"));
		String sql = "from Order x order by x.idfOrder desc";
		Query q = em.createQuery(sql, Order.class);
		Order order=((Order) q.getResultList().get(0));
		WooriPensionVo wooriPensionVo=new WooriPensionVo();
		BeanUtils.copyProperties(order, wooriPensionVo);
		int idfOrder=order.getIdfOrder();
		Assert.assertEquals("S",WooriPensionUtil.reservationInfo(wooriPensionVo));
		Assert.assertEquals('0',order.getState());
		NicePayDto nicePayDto=new NicePayDto();
		nicePayDto.setTID("test"+idfOrder);
		nicePayDto.setMoid(""+idfOrder);
		nicePayDto.setPayMethod("VBANK");
		nicePayDto.setBuyerName("펜튀테스트");
		nicePayDto.setAmt((int)order.getAmount()+"");
		nicePayDto.setResultCode("4100");
		nicePayDto.setGoodsName("펜션");
		nicePayDto.setResultMsg("결과");
		nicePayDto.setFnCd("01");
		nicePayDto.setAuthDate("160601143754");
		nicePayDto.setVbankExpDate("160601143754");
		nicePayDto.setVbankBankName("우리은행");
		nicePayDto.setVbankNum("62141069618591");
		order=orderDao.readById(idfOrder);
		Assert.assertEquals("S",paymentService.nicePayCallback(nicePayDto).getResultCode());
		Payment payment=paymentDao.readByIdfOrder(idfOrder);
		Assert.assertNotEquals(null,payment);
		PaymentVbank paymentVbank=paymentVbankDao.readById(payment.getIdfPayment());
		Assert.assertNotEquals(null,paymentVbank.getName());
		Assert.assertNotEquals(null,paymentVbank.getNumber());
		Assert.assertNotEquals(null,paymentVbank.getExpiryDate());
		BeanUtils.copyProperties(order, wooriPensionVo);
		Assert.assertEquals("S",WooriPensionUtil.reservationInfo(wooriPensionVo));
		Assert.assertEquals('0',order.getState());
		nicePayDto=new NicePayDto();
		nicePayDto.setMoid(""+idfOrder);
		nicePayDto.setAuthDate("160516224811");
		nicePayDto.setVbankInputName("펜튀테스");
		nicePayDto.setTID("test"+idfOrder);
		paymentService.nicePayVbankCallback(nicePayDto);
		paymentVbank=paymentVbankDao.readById(payment.getIdfPayment());
		
		Assert.assertNotEquals(null,paymentVbank.getDepositName());
		Assert.assertNotEquals(null,paymentVbank.getDepositDate());
		BeanUtils.copyProperties(order, wooriPensionVo);
		Assert.assertEquals("K",WooriPensionUtil.reservationInfo(wooriPensionVo));
		Assert.assertEquals('1',orderDao.readById(idfOrder).getState());
		
		PaymentCancelVbankDto paymentCancelVbankDto=new PaymentCancelVbankDto();
		paymentCancelVbankDto.setDepositBankName("우리은행");
		paymentCancelVbankDto.setDepositName("펜튀테스트");
		paymentCancelVbankDto.setDepositNumber("62141069618591");
		
		Assert.assertEquals("S",paymentService.cancelPaymentVbank(idfOrder, paymentCancelVbankDto).getResultCode());
		
		PaymentCancelVbank paymentCancelVbnk=paymentCancelVbankDao.readById(payment.getIdfPayment());
		
		Assert.assertEquals(paymentCancelVbankDto.getDepositBankName(),paymentCancelVbnk.getDepositBankName());
		Assert.assertEquals(paymentCancelVbankDto.getDepositName(),paymentCancelVbnk.getDepositName());
		Assert.assertEquals(paymentCancelVbankDto.getDepositNumber(),paymentCancelVbnk.getDepositNumber());
		Assert.assertEquals('4',orderDao.readById(idfOrder).getState());
		BeanUtils.copyProperties(order, wooriPensionVo);
		Assert.assertEquals("C",WooriPensionUtil.reservationInfo(wooriPensionVo));
	}

}
