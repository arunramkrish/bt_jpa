package com.bt.cart.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class OrderLineItem extends LineItem {

	@ManyToOne
	private Order order;

	public OrderLineItem() {
		super();
	}

	public OrderLineItem(Long id, Product product, Integer quantity,
			Float totalAmount, Order order) {
		super(id, product, quantity, totalAmount);
		this.order = order;
	}

	public OrderLineItem(Product product, Integer quantity, Float totalAmount,
			Order order) {
		super(product, quantity, totalAmount);
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
