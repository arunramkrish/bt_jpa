package com.bt.cart.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "order")
	private Set<OrderLineItem> lineItems;
	
	@Temporal(TemporalType.DATE)
	private Date orderDate;

	private Float totalAmount;

	public Order(Long id, Set<OrderLineItem> lineItems, Date orderDate,
			Float totalAmount) {
		super();
		this.id = id;
		this.lineItems = lineItems;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Set<OrderLineItem> lineItems, Date orderDate, Float totalAmount) {
		super();
		this.lineItems = lineItems;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<OrderLineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<OrderLineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", lineItems=" + lineItems + ", orderDate="
				+ orderDate + ", totalAmount=" + totalAmount + "]";
	}

}
