package com.bt.jpa.dao;

import java.util.List;

import com.bt.jpa.entity.Department;
import com.bt.jpa.entity.Employee;

public interface EmployeeDao extends BaseDao<Employee, Long> {
	List<Employee> getEmployeesInDepartment(Department department);
}
