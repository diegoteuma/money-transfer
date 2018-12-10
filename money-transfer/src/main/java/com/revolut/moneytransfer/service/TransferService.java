package com.revolut.moneytransfer.service;

import java.util.List;

import com.revolut.moneytransfer.dao.TransferDao;
import com.revolut.moneytransfer.model.Transfer;

/**
 * this will invoke concrete operations for transfers
 * @author Diego
 *
 */
public class TransferService implements CrudService<Transfer, Long> {

	private TransferDao transferDao;

	public TransferService() {
		this.setTransferDao(new TransferDao());
	}

	@Override
	public Transfer create(Transfer t) {
		return this.getTransferDao().create(t);
	}

	@Override
	public Transfer update(Transfer t) {
		return this.getTransferDao().update(t);
	}

	@Override
	public Transfer findById(Long id) {
		return this.getTransferDao().findById(id);
	}

	@Override
	public List<Transfer> findAll() {
		return this.getTransferDao().findAll();
	}

	@Override
	public void delete(Transfer t) {
		this.getTransferDao().delete(t);

	}
	
	public List<Transfer> findByAccountId(long accountId){
		
		return this.getTransferDao().findByAccountId(accountId);
	}

	/**
	 * @return the transferDao
	 */
	public TransferDao getTransferDao() {
		return transferDao;
	}

	/**
	 * @param transferDao the transferDao to set
	 */
	public void setTransferDao(TransferDao transferDao) {
		this.transferDao = transferDao;
	}

}
