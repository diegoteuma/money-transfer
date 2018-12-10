package com.revolut.moneytransfer.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.revolut.moneytransfer.model.Transfer;

/**
 * this will manage the transfer persistence
 * 
 * @author Diego
 *
 */
public class TransferDao extends AbstractDao<Transfer, Long> {

	@Override
	public Class<?> getClazz() {
		return Transfer.class;
	}

	/**
	 * this method will create the transfer and update the origin and destiny
	 * accounts
	 */
	@Override
	public Transfer create(Transfer t) {
		final EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();

			t.setDate(new Date());
			em.persist(t);

			t.getOriginAccount().getTransfers().add(t);
			t.getOriginAccount().setBalance(t.getOriginAccount().getBalance() - t.getAmount());

			t.getDestinyAccount().getTransfers().add(t);
			t.getDestinyAccount().setBalance(t.getDestinyAccount().getBalance() + t.getAmount());

			em.merge(t.getOriginAccount());
			em.merge(t.getDestinyAccount());

			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			em.close();
		}
		return t;
	}

	/**
	 * this method will find all the transfer given an account id
	 * @param accountId
	 * @return List<Transfer>
	 */
	@SuppressWarnings("unchecked")
	public List<Transfer> findByAccountId(long accountId) {
		final EntityManager em = getEntityManager();
		List<Transfer> transfers = null;
		try {
			em.getTransaction().begin();

			Query query = em.createQuery("Select o from Transfer o where o.originAccount.id=:accountId");
			query.setParameter("accountId", accountId);
			transfers = query.getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			em.close();
		}
		return transfers;

	}
}
