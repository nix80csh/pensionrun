package com.pensionrun.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Account;

@Repository
public class AccountDaoImpl extends GenericDaoImpl<Account> implements AccountDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Account readByUserId(String userId) {
		Account account = new Account();
		try {
			Query q = em.createQuery("from Account a where a.userid = :userId", Account.class);
			account = (Account) q.setParameter("userId", userId).getSingleResult();
		} catch (NoResultException e) {
			account = null;
		}
		return account;
	}


}
