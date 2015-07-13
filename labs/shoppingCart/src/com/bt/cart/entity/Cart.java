package com.bt.cart.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cart {
	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "cart")
	private Set<CartLineItem> lineItems;

	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Temporal(TemporalType.DATE)
	private Date modifiedDate;

	public Cart(Long id, Set<CartLineItem> lineItems, Date createdDate,
			Date modifiedDate) {
		super();
		this.id = id;
		this.lineItems = lineItems;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public Cart(Set<CartLineItem> lineItems, Date createdDate, Date modifiedDate) {
		super();
		this.lineItems = lineItems;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", lineItems=" + lineItems + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + "]";
	}

}
