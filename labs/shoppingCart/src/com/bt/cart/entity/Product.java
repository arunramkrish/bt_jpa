package com.bt.cart.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.bt.cart.dao.LogListener;

@Entity
//@EntityListeners(LogListener.class)
public class Product {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private Float price;

	public Product() {
		super();
	}

	public Product(Long id, String name, Float price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Product(String name, Float price) {
		super();
		this.name = name;
		this.price = price;
	}

	@PrePersist
	public void log() {
		System.out.println("Before persisting product");
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ "]";
	}

}
