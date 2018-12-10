package com.revolut.moneytransfer.dao;

import com.revolut.moneytransfer.model.Client;

/**
 * this will manage the clients persistence
 * @author Diego
 *
 */
public class ClientDao extends AbstractDao<Client,Long>{

	@Override
	public Class<?> getClazz() {
		return Client.class;
	}
}
