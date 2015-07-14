package com.bt.cart.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

public class GenericDaoJpaImpl<T, PK extends Serializable> implements
		GenericDao<T, PK> {

	protected Class<T> entityClass;

//	@PersistenceUnit(unitName = "test")
	private EntityManagerFactory entityManagerFactory;

//	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public GenericDaoJpaImpl() {
		entityManagerFactory = Persistence.createEntityManagerFactory("shopping-cart");
		entityManager = entityManagerFactory.createEntityManager();
		
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass
				.getActualTypeArguments()[0];
	}

	@Override
	public T create(T t) {
		entityManager.getTransaction().begin();
		this.entityManager.persist(t);
		entityManager.getTransaction().commit();
		return t;
	}

	@Override
	public T read(PK id) {
		return this.entityManager.find(entityClass, id);
	}

	@Override
	public T update(T t) {
		entityManager.getTransaction().begin();
		T merged = this.entityManager.merge(t);
		entityManager.getTransaction().commit();
		
		return merged;
	}

	@Override
	public void delete(T t) {
		entityManager.getTransaction().begin();
		t = this.entityManager.merge(t);
		this.entityManager.remove(t);
		entityManager.getTransaction().commit();
	}
}