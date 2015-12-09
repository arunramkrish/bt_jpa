package com.bt.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bt.jpa.dao.BaseEntity;

/**
 * Allocation of Employees to projects
 * 
 * @author arun
 * 
 */
@Entity
public class ProjectAllocation extends BaseEntity<ProjectAllocationId> {
	@EmbeddedId
	private ProjectAllocationId allocId;

	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Column(name = "role")
	private ProjectRole role;

	public ProjectAllocationId getAllocId() {
		return allocId;
	}

	public void setAllocId(ProjectAllocationId allocId) {
		this.allocId = allocId;
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

	public ProjectRole getRole() {
		return role;
	}

	public void setRole(ProjectRole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "ProjectAllocation [allocId=" + allocId + ", startDate=" + startDate + ", endDate=" + endDate + ", role=" + role
				+ "]";
	}
}
