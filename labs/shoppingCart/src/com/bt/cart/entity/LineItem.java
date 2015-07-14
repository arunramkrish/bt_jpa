package com.bt.cart.entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class LineItem {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? (hashCodeAlternate()) : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineItem other = (LineItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
			else {
				return equalsAlternate(obj);
			}
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public int hashCodeAlternate() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result
				+ ((totalAmount == null) ? 0 : totalAmount.hashCode());
		return result;
	}

	public boolean equalsAlternate(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineItem other = (LineItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (totalAmount == null) {
			if (other.totalAmount != null)
				return false;
		} else if (!totalAmount.equals(other.totalAmount))
			return false;
		return true;
	}
	
}
