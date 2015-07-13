package com.bt.cart.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import com.bt.cart.entity.Product;
import com.bt.cart.entity.User;

public class ShoppingCartClient {
	private static EntityManagerFactory emFactory;

	public static void main(String[] args) {
		emFactory = Persistence.createEntityManagerFactory("shopping-cart");

//		addUsers();
//		modifyUser(4L);
		
//		modifyUser(new User("New BT456", "bt@bt.com", "11111111"));
		
		addProducts();
	}

	private static void addProducts() {
		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.persist(new Product("Xiomi Redmi", 15999F));
		entityManager.persist(new Product("Samsung Note4", 45999F));
		entityManager.persist(new Product("iPhone 7", 55999F));
		entityManager.persist(new Product("Nokia 2210", 1999F));
		
		entityManager.close();
	}

	private static void modifyUser(User user) {

		EntityManager entityManager = emFactory.createEntityManager();
//		entityManager.setFlushMode(FlushModeType.AUTO);
		
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}

	private static void addUsers() {

		EntityManager entityManager = emFactory.createEntityManager();

		entityManager.getTransaction().begin();
		User user = new User("BT", "bt@bt.com", "11111111");
		entityManager.persist(user);
		
		user.setName("British Telecom");

		entityManager.getTransaction().commit();
		entityManager.close();
		
		user.setName("British Telecom New");

	}

	private static void modifyUser(Long id) {

		EntityManager entityManager = emFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.setFlushMode(FlushModeType.COMMIT);
		
		User user = entityManager.find(User.class, id);
		user.setName("BT modified1");
		entityManager.detach(user);
		
		
		User user2 = entityManager.find(User.class, id);
		System.out.println(user2.getName());
		
		entityManager.remove(user2);
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}

}
