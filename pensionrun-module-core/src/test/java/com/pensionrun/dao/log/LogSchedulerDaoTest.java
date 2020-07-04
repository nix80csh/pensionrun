package com.pensionrun.dao.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pensionrun.entity.Account;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/META-INF/test-core-context.xml" })
public class LogSchedulerDaoTest {
	@Autowired
	private LogSchedulerDao logSchedulerDao;
	
	@Test
	@Rollback(true)
	public void readByUserIdTest() {
		String previousUpdateDate = logSchedulerDao.readMaxUpdDate('1').toString();
		System.out.println(previousUpdateDate);
	}
}
