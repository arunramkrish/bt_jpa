package com.bt.cart.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.bt.cart.entity.Cart;
import com.bt.cart.entity.CartLineItem;

public class CartDaoJpaImpl extends GenericDaoJpaImpl<Cart, Long> implements
		CartDao {

	public List<Cart> getCartsHavingProduct(Long productId) {
		entityManager.getTransaction().begin();
		TypedQuery<Cart> typedQuery = entityManager.createNamedQuery(
				"cartsWithProduct", Cart.class);
		typedQuery.setParameter("product_id", productId);

		List<Cart> cars = typedQuery.getResultList();

		entityManager.getTransaction().commit();
		return cars;

	}

	public List<Cart> getCartsHavingProductWithNative(Long productId) {
		entityManager.getTransaction().begin();
		Query q = entityManager
				.createNativeQuery("SELECT c.id from cart c, cartlineitem l, product p where p.id = :prod_id and p.id = l.product_id and l.cart_id = c.id");
		q.setParameter("prod_id", productId);
		
		List result = q.getResultList();
		for (int i=0; i < result.size(); i++) {
			System.out.println(result.get(i) + " type " + result.get(i).getClass());
		}
		entityManager.getTransaction().commit();
		return null;

	}

	public static void main(String[] args) {
		CartDaoJpaImpl dao = new CartDaoJpaImpl();
		dao.getCartsHavingProductWithNative(13L);
		
	}
	
	@Override
	public void updateWithLineItems(Cart c) {
		entityManager.getTransaction().begin();

		c = entityManager.find(Cart.class, c.getId());

		// CartLineItem toDelete = null;
		// for (CartLineItem li : c.getLineItems()) {
		// if (toDelete == null) {
		// toDelete = li;
		// }
		//
		// li.setQuantity(li.getQuantity() * 10);
		//
		// }
		// c.getLineItems().remove(toDelete);

		// CartLineItem c1 = new CartLineItem(null, 24, 10000F, c);
		// CartLineItem c2 = new CartLineItem(null, 25, 10000F, c);
		// CartLineItem c3 = new CartLineItem(null, 26, 10000F, c);
		// c.getLineItems().add(c1);
		// c.getLineItems().add(c2);
		// c.getLineItems().add(c3);

		entityManager.remove(c);

		entityManager.getTransaction().commit();

	}

}
