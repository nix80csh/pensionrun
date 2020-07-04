package com.pensionrun.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pensionrun.entity.Account;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/META-INF/test-core-context.xml" })
public class AccountDaoTest {
	@Autowired
	private AccountDao accountDao;
	
	@Test
	@Rollback(true)
	public void readByUserIdTest() {
		Account account = accountDao.readByUserId("nix80_csh@nate.com");
		System.out.println(account.getEmail());
	}
}
