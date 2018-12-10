package com.revolut.moneytransfer.dao;

import com.revolut.moneytransfer.model.Account;

/**
 * this will manage the accounts persistence
 * @author Diego
 *
 */
public class AccountDao extends AbstractDao<Account,Long>{

	@Override
	public Class<?> getClazz() {
		return Account.class;
	}
}
