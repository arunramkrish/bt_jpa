package com.bt.cart.dao;

import com.bt.cart.entity.Product;

public class CartDto {
	private Long id;
	private Integer quantity;
	private Float totalAmount;
	private String productName;
	private Product product;
	
	public CartDto(Long id, Integer quantity, Float totalAmount, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.product = product;
		if (this.product != null) {
			this.productName = product.getName();
		}
	}

	public CartDto(Long id, Integer quantity, Float totalAmount,
			String productName) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.productName = productName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "CartDto [id=" + id + ", quantity=" + quantity
				+ ", totalAmount=" + totalAmount + ", productName="
				+ productName + "]";
	}

}
