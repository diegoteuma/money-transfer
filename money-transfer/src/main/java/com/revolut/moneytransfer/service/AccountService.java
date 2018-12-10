package com.revolut.moneytransfer.service;

import java.util.List;

import com.revolut.moneytransfer.dao.AccountDao;
import com.revolut.moneytransfer.model.Account;

/**
 * this will invoke concrete operations for accounts
 * @author Diego
 *
 */
public class AccountService implements CrudService<Account, Long>{

	private AccountDao accountDao;
	
	public AccountService() {
		this.setAccountDao(new AccountDao());
	}
	
	@Override
	public Account create(Account t) {
		return this.getAccountDao().create(t);
	}

	@Override
	public Account update(Account t) {
		return this.getAccountDao().update(t);
	}

	@Override
	public Account findById(Long id) {
		return this.getAccountDao().findById(id);
	}

	@Override
	public List<Account> findAll() {
		return this.getAccountDao().findAll();
	}

	@Override
	public void delete(Account t) {
		this.getAccountDao().delete(t);
		
	}

	/**
	 * @return the accountDao
	 */
	public AccountDao getAccountDao() {
		return accountDao;
	}

	/**
	 * @param accountDao the accountDao to set
	 */
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	

}
