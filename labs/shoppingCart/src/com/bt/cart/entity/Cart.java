package com.bt.cart.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

@Entity
//@NamedQueries({ @NamedQuery(name = "cartsWithProduct", query = "select c from Cart c join CartLineItem li where li.product.id = :product_id") })
public class Cart extends BaseEntity {
	@Id
	@GeneratedValue
	private Long id;

	// @OneToMany(mappedBy = "cart", cascade={CascadeType.PERSIST,
	// CascadeType.MERGE}, orphanRemoval=true)
	@OneToMany(mappedBy = "cart", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.EAGER)
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

	@PreRemove
	public void setLineItemNull() {
		for (CartLineItem li : lineItems) {
			li.setCart(null);
		}
	}

	public Set<CartLineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<CartLineItem> lineItems) {
		this.lineItems = lineItems;
	}

	// @Override
	// public String toString() {
	// return "Cart [id=" + id + ", lineItems=" + lineItems + ", createdDate="
	// + getCreatedDate() + ", modifiedDate=" + getModifiedDate()
	// + "]";
	// }

}
