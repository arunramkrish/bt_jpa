package com.bt.jpa.dao.jpaimpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bt.jpa.common.exception.DataAccessException;
import com.bt.jpa.dao.BaseDao;
import com.bt.jpa.dao.BaseEntity;

public abstract class BaseJpaDao<T extends BaseEntity<V>, V extends Serializable> implements BaseDao<T, V> {
    @PersistenceContext(name = "entityManagerFactory", unitName = "pms")
    protected EntityManager entityManager;


	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void create(T entity) throws DataAccessException {
		try {
			entityManager.persist(entity);
		} catch (Exception e) {
			throw new DataAccessException("Error while creating entity of type " + entity.getClass(), e);
		}
	}

	@Override
	public List<T> read(Class<T> entityClass) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T read(V key) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T update(T entity) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(V key) throws DataAccessException {
		// TODO Auto-generated method stub

	}

}
