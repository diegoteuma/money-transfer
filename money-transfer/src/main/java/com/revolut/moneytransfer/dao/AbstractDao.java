package com.revolut.moneytransfer.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * this will manage the database operations
 * @author Diego Umaña
 *
 */
public abstract class AbstractDao<T, PK extends Serializable> {

	/**
	 * this will give the exact entity class type
	 * @return Class<?>
	 */
	public abstract Class<?> getClazz();

	private EntityManagerFactory entityManagerFactory;

	public AbstractDao() {
		entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
	}

	protected EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	/**
	 * method to persist an entity
	 * @param t
	 * @return T
	 */
	public T create(T t) {
		final EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			em.close();
		}
		return t;
	}

	/**
	 * method to update an entity
	 * @param t
	 * @return T
	 */
	public T update(T t) {
		final EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(t);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			em.close();
		}
		return t;
	}

	/**
	 * method to delete an entity
	 * @param t
	 */
	public void delete(T t) {
		final EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(em.contains(t) ? t : em.merge(t));
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			em.close();
		}
	}

	/**
	 * method to find an entity by id
	 * @param id
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public T findById(Long id) {
		final EntityManager em = getEntityManager();
		T obj = null;
		try {
			em.getTransaction().begin();
			obj = (T) em.find(getClazz(), id);
			em.getTransaction().commit();
			return obj;
		} catch (Exception e) {
			throw e;
		} finally {
			em.close();
		}
	}

	/**
	 * method to list all entities given a entity class
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getEntityManager().createQuery("from " + getClazz().getName()).getResultList();
	}

}