package com.bt.cart.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseEntity {
	
	@Temporal(TemporalType.DATE)
	@Column(insertable=true, updatable=false)
	private Date createdDate;

	@Temporal(TemporalType.DATE)
	@Column(insertable=false, updatable=true)
	private Date modifiedDate;

	@PrePersist
	public void initCreatedDate() {
		System.out.println("Init create date");
		this.createdDate = new Date();
	}

	@PreUpdate
	public void initModifiedDate() {
		System.out.println("Init modified date");
		this.modifiedDate = new Date();
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

}
