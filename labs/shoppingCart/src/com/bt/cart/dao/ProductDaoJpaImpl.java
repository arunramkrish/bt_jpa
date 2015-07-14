package com.bt.cart.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import com.bt.cart.entity.Cart;
import com.bt.cart.entity.CartLineItem;
import com.bt.cart.entity.Product;

public class ProductDaoJpaImpl extends GenericDaoJpaImpl<Product, Long>
		implements ProductDao {

	public List<Product> getProductsByName(String name) {
		TypedQuery<Product> productQuery = entityManager.createQuery(
				"select p from Product p where p.name like :name",
				Product.class);
		productQuery.setParameter("name", name + "%");

		return productQuery.getResultList();
	}

	public List<Product> getProductsByNameUsingNamedQuery(String name) {
		TypedQuery<Product> productQuery = entityManager.createNamedQuery(
				"productsByName", Product.class);
		productQuery.setParameter("name", name + "%");

		return productQuery.getResultList();
	}

	public List<CartLineItem> getCartLineItemsByProductName(String name) {
		TypedQuery<CartLineItem> productQuery = entityManager.createQuery(
				"select l from CartLineItem l where l.product.name like :name", CartLineItem.class);
		productQuery.setParameter("name", name + "%");

		return productQuery.getResultList();
	}

	public List<Cart> getCartsWithProduct(String name) {
		TypedQuery<Cart> cartsQuery = entityManager.createQuery("select distinct c "
				+ "from Cart c, CartLineItem l where l.product.name like :name "
				+ "and l.cart.id = c.id", Cart.class);
		cartsQuery.setParameter("name", name + "%");

		return cartsQuery.getResultList();

	}

	public List<CartLineItem> getCartsWithFetchJoin() {
		TypedQuery<CartLineItem> cartsQuery = entityManager.createQuery("select c.lineItems "
				+ "from Cart c ", CartLineItem.class);

		return cartsQuery.getResultList();
		
	}
	
	public List<CartDto> searchCartsWithProduct() {
		TypedQuery<CartDto> cartsQuery = entityManager.createQuery("select NEW com.bt.cart.dao.CartDto(l.id, l.quantity, "
				+ "l.totalAmount, l.product) from CartLineItem l", CartDto.class);

		return cartsQuery.getResultList();
	}
	
	public static void main(String[] args) {
		ProductDaoJpaImpl dao = new ProductDaoJpaImpl();

//		System.out.println(dao.getProductsByNameUsingNamedQuery("Xiomi"));
//		System.out.println(dao.getCartLineItemsByProductName("Xiomi").size());
//		System.out.println(dao.getCartsWithProduct("Xiomi"));
//		System.out.println(dao.getCartsWithFetchJoin());
		System.out.println(dao.searchCartsWithProduct());
	}
}
