package com.bt.jpa.service;

import java.util.List;

import com.bt.jpa.common.exception.PmsServiceException;
import com.bt.jpa.common.vo.EmployeeReportVo;
import com.bt.jpa.entity.Employee;

public interface EmployeeService {
	void createEmployee(Employee employee) throws PmsServiceException;

	List<Employee> getEmployees() throws PmsServiceException;

	Employee getEmployee(Long id) throws PmsServiceException;

	List<EmployeeReportVo> getEmployeesReport() throws PmsServiceException;
}
