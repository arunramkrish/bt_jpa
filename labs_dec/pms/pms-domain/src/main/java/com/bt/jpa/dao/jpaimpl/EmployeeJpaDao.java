package com.bt.jpa.dao.jpaimpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bt.jpa.dao.EmployeeDao;
import com.bt.jpa.entity.Department;
import com.bt.jpa.entity.Employee;

@Repository
public class EmployeeJpaDao extends BaseJpaDao<Employee, Long> implements EmployeeDao {

	@Override
	public List<Employee> getEmployeesInDepartment(Department department) {
		TypedQuery<Employee> query = getEntityManager().createQuery(
				"from Employee e where e.department.id = " + department.getId(), Employee.class);
		return query.getResultList();
	}

}
