package com.bt.jpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bt.jpa.common.exception.DataAccessException;
import com.bt.jpa.common.exception.PmsServiceException;
import com.bt.jpa.dao.EmployeeDao;
import com.bt.jpa.entity.Employee;
import com.bt.jpa.service.EmployeeService;

@Service
@Transactional(readOnly=false)
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public void createEmployee(Employee employee)  throws PmsServiceException {
		try {
			employeeDao.create(employee);
		} catch (DataAccessException e) {
			throw new PmsServiceException(e);
		}
	}

	@Override
	public List<Employee> getEmployees() throws PmsServiceException {
		try {
			return employeeDao.read(Employee.class);
		} catch (DataAccessException e) {
			throw new PmsServiceException(e);
		}
	}

	@Override
	public Employee getEmployee(Long id) throws PmsServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

}
