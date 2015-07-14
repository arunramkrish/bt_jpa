package com.bt.cart.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class CartLineItem extends LineItem {
	@ManyToOne(fetch=FetchType.LAZY)
	private Cart cart;

	public CartLineItem() {
		super();
	}

	public CartLineItem(Long id, Product product, Integer quantity,
			Float totalAmount, Cart Cart) {
		super(id, product, quantity, totalAmount);
		this.cart = Cart;
	}

	public CartLineItem(Product product, Integer quantity, Float totalAmount,
			Cart Cart) {
		super(product, quantity, totalAmount);
		this.cart = Cart;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart Cart) {
		this.cart = Cart;
	}
	
	
	
	

}
