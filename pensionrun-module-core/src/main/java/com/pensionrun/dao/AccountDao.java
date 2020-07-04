package com.pensionrun.dao;

import com.pensionrun.entity.Account;

public interface AccountDao extends GenericDao<Account> {

	Account readByUserId(String userId);
	
}
