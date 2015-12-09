package com.bt.jpa.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.bt.jpa.dao.BaseEntity;

@Entity
public class UserGroup extends BaseEntity<Long> {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER, targetEntity=Employee.class)
	@JoinColumn(table="user_group_employees", name="user_group_id")
	private Set<Employee> members;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Employee> getMembers() {
		return members;
	}

	public void setMembers(Set<Employee> members) {
		this.members = members;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
