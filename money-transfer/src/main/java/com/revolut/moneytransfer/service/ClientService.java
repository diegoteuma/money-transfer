package com.revolut.moneytransfer.service;

import java.util.List;

import com.revolut.moneytransfer.dao.ClientDao;
import com.revolut.moneytransfer.model.Client;

/**
 * this will invoke concrete operations for clients
 * @author Diego
 *
 */
public class ClientService implements CrudService<Client, Long>{

	
	private ClientDao clientDao;
	
	public ClientService() {
		this.setClientDao(new ClientDao());
	}

	@Override
	public Client create(Client t) {
		return this.getClientDao().create(t);
	}

	@Override
	public Client update(Client t) {
		return this.getClientDao().update(t);
	}

	@Override
	public Client findById(Long id) {
		return this.getClientDao().findById(id);
	}
	
	@Override
	public List<Client> findAll() {
		return this.getClientDao().findAll();
	}

	@Override
	public void delete(Client t) {
		this.getClientDao().delete(t);
		
	}

	/**
	 * @return the clientDao
	 */
	public ClientDao getClientDao() {
		return clientDao;
	}

	/**
	 * @param clientDao the clientDao to set
	 */
	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	
	

}
