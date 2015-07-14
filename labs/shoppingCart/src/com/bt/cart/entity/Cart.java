package com.bt.cart.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cart extends BaseEntity {
	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "cart")
	private Set<CartLineItem> lineItems = new LinkedHashSet<CartLineItem>();

	public Cart(Long id, Set<CartLineItem> lineItems) {
		super();
		this.id = id;
		this.lineItems = lineItems;
	}

	public Cart(Set<CartLineItem> lineItems) {
		super();
		this.lineItems = lineItems;
	}

	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<CartLineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<CartLineItem> lineItems) {
		this.lineItems = lineItems;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", lineItems=" + lineItems + ", createdDate="
				+ getCreatedDate() + ", modifiedDate=" + getModifiedDate() + "]";
	}

}
