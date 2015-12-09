package com.bt.jpa.entity;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.bt.jpa.dao.BaseEntity;

@Entity
public class Project extends BaseEntity<Long> {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private Date startDate;
	
	private Date endDate;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE, targetEntity=ProjectAllocation.class)
	private Set<ProjectAllocation> allocations = new LinkedHashSet<ProjectAllocation>();

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<ProjectAllocation> getAllocations() {
		return allocations;
	}

	public void setAllocations(Set<ProjectAllocation> allocations) {
		this.allocations = allocations;
	}
	
	
}
