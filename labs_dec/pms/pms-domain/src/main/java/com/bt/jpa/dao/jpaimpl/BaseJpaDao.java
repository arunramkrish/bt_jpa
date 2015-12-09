package com.bt.jpa.dao.jpaimpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bt.jpa.common.exception.DataAccessException;
import com.bt.jpa.dao.BaseDao;
import com.bt.jpa.dao.BaseEntity;

@Repository
@Transactional(propagation = Propagation.MANDATORY, readOnly = false)
public abstract class BaseJpaDao<T extends BaseEntity<V>, V extends Serializable> implements BaseDao<T, V> {

	@PersistenceContext(type=PersistenceContextType.TRANSACTION, name = "entityManagerFactory", unitName = "pms")
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
		TypedQuery<T> query = entityManager.createQuery("from Employee", entityClass);
		return query.getResultList();
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
