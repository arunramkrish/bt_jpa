package com.bt.jpa.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bt.jpa.dao.BaseEntity;

@Entity
@Table(name = "employee")
public class Employee extends BaseEntity<Long> {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "salary")
	private BigDecimal salary;

	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	// @OneToMany(mappedBy = "employee" ,cascade =
	// {CascadeType.ALL},orphanRemoval=true)
	@OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "employee_id")
	private Collection<Address> addresses = new ArrayList<Address>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manager")
	private Set<Department> managedDepartments = new LinkedHashSet<Department>();

	@ManyToOne(fetch = FetchType.LAZY, optional = true, targetEntity = Department.class)
	private Department department;

	@Column(name = "phone_no")
	private String phoneNo;

	public Long getId() {
		return id;
	}

	public Employee setId(Long id) {
		this.id = id;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public Employee setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public Employee setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public Employee setSalary(BigDecimal salary) {
		this.salary = salary;
		return this;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Employee setStartDate(Date startDate) {
		this.startDate = startDate;
		return this;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Employee setEndDate(Date endDate) {
		this.endDate = endDate;
		return this;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Collection<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
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
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Set<Department> getManagedDepartments() {
		return managedDepartments;
	}

	public void setManagedDepartments(Set<Department> managedDepartments) {
		this.managedDepartments = managedDepartments;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", salary=" + salary
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", phoneNo=" + phoneNo + "]" + department.getDepartmentName();
	}
}
