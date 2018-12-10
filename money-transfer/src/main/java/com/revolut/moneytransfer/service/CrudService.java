package com.revolut.moneytransfer.service;

import java.io.Serializable;
import java.util.List;

/**
 * this will define the operation that will be invoke by the controllers,
 * the service is intermediary between controller and Dao
 * 
 * @author Diego
 *
 * @param <T>
 * @param <PK>
 */
public interface CrudService<T, PK extends Serializable> {

	T create(T t);

	T update(T t);

	T findById(PK id);
	
	List<T> findAll();

	void delete(T t);

}
