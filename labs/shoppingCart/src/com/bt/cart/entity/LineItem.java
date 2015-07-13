package com.bt.cart.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class LineItem {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Product product;
	private Integer quantity;
	private Float totalAmount;

	public LineItem(Long id, Product product, Integer quantity,
			Float totalAmount) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
	}

	public LineItem() {
		super();
	}

	public LineItem(Product product, Integer quantity, Float totalAmount) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "LineItem [product=" + product + ", quantity=" + quantity
				+ ", totalAmount=" + totalAmount + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}
}
