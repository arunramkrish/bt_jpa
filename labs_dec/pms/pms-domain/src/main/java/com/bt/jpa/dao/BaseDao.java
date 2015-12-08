package com.bt.jpa.dao;

import java.io.Serializable;
import java.util.List;

import com.bt.jpa.common.exception.DataAccessException;

public interface BaseDao<E extends BaseEntity<K>, K extends Serializable> {
	void create(E entity) throws DataAccessException;

	List<E> read(Class<E> entityClass) throws DataAccessException;

	E read(K key) throws DataAccessException;

	E update(E entity) throws DataAccessException;

	void delete(K key) throws DataAccessException;
}
