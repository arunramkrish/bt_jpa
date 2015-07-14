package com.bt.cart.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;

import com.bt.cart.dao.LogListener;

@Entity
@EntityListeners(LogListener.class)
@NamedQueries({ @NamedQuery(name = "productsByName", query = "select p from Product p where p.name like :name") })
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
