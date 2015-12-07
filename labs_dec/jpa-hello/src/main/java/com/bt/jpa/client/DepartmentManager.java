package com.bt.jpa.client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.bt.jpa.entity.Department;
import com.bt.jpa.entity.Employee;

public class DepartmentManager {

	public static void main(String[] args) {
//		create();
		
//		createEmployee();
		
		read();
	}
	
	public static void create() {
		Department d1 = new Department();
		d1.setDepartmentName("TinyData");
		
		Department d2 = new Department();
		d2.setDepartmentName("Telecom");

		Employee employee = new Employee();
		employee.setAddresses(null);
		employee.setEndDate(new Date());
		employee.setFirstName("Rahman");
		employee.setLastName("Krishnmurthy");
		employee.setPhoneNo("8050641876");
		employee.setSalary(new BigDecimal("1000000"));
		employee.setStartDate(new Date());

		d1.setManager(employee);
		d2.setManager(employee);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-hello");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
//		em.persist(employee);
		em.persist(d1);
		em.getTransaction().commit();
		
//		em.clear();
		
		em.getTransaction().begin();
//		em.persist(employee);
		em.persist(d2);
		em.getTransaction().commit();

		em.close();
	}
	
	public static void createEmployee() {
		Department d1 = new Department();
		d1.setDepartmentName("EAI2");
		
		Department d2 = new Department();
		d2.setDepartmentName("UX2");

		Employee employee = new Employee();
		employee.setAddresses(null);
		employee.setEndDate(new Date());
		employee.setFirstName("John2");
		employee.setLastName("K2");
		employee.setPhoneNo("8050641876");
		employee.setSalary(new BigDecimal("1000000"));
		employee.setStartDate(new Date());

		employee.setDepartment(d1);
		employee.getManagedDepartments().add(d2);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-hello");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(d1);
		em.persist(d2);
		
		d2.setManager(employee);
		
		em.persist(employee);
		em.getTransaction().commit();
		
		em.close();
	}
	
	public static void read() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-hello");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		TypedQuery<Employee> query = em.createQuery("from Employee where firstName like ('John%')", Employee.class);
		List<Employee> empList = query.getResultList();
		em.getTransaction().commit();
		
		
		for (Employee e : empList) {
			System.out.println(e);
		}
		em.close();
	}
}
