package com.bt.jpa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bt.jpa.common.exception.DataAccessException;
import com.bt.jpa.common.exception.PmsServiceException;
import com.bt.jpa.common.vo.EmployeeReportVo;
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
			List<Employee> empList = employeeDao.read(Employee.class);
			for(Employee emp : empList) {
				if (emp.getDepartment() != null) {
					System.out.println(emp.getDepartment().getDepartmentName());
				}
			}
			return empList;
		} catch (DataAccessException e) {
			throw new PmsServiceException(e);
		}
	}

	@Override
	public List<EmployeeReportVo> getEmployeesReport() throws PmsServiceException {
		try {
			List<Employee> empList = employeeDao.read(Employee.class);
			List<EmployeeReportVo> empReportList = new ArrayList<>(empList.size());
			for(Employee emp : empList) {
				String deptName = "";
				if (emp.getDepartment() != null) {
					System.out.println(emp.getDepartment().getDepartmentName());
					deptName = emp.getDepartment().getDepartmentName();
				}
				EmployeeReportVo empReport = new EmployeeReportVo(emp.getId(), emp.getFirstName(), emp.getLastName(), deptName);
				empReportList.add(empReport);
			}
			return empReportList;
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
