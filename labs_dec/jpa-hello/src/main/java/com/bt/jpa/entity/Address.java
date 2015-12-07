package com.bt.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
  @Id
  @GeneratedValue
  private Long id;
  @Column(name = "street")
  private String street;
  @Column(name = "city")
  private String city;
  @Column(name = "state")
  private String state;
  @Column(name = "country")
  private String country;
  @Column(name = "post_code")
  private String postcode;
//  @ManyToOne
//  @JoinColumn(name = "employee_id")
//  private Employee employee;
  
public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getStreet() {
	return street;
}

public void setStreet(String street) {
	this.street = street;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public String getPostcode() {
	return postcode;
}

public void setPostcode(String postcode) {
	this.postcode = postcode;
}

//public Employee getEmployee() {
//	return employee;
//}
//
//public void setEmployee(Employee employee) {
//	this.employee = employee;
//}

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
	Address other = (Address) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}

@Override
public String toString() {
	return "Address [id=" + id + ", street=" + street + ", city=" + city
			+ ", state=" + state + ", country=" + country + ", postcode="
			+ postcode + "]";
}
}
