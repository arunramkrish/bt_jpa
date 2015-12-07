package com.bt.jpa.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bt.jpa.entity.Address;
import com.bt.jpa.entity.Employee;

/**
 * @author Ravikant
 * 
 */
public class Manager {
	public static void main(String[] args) {
		/********************** Create Employee ***********************/
		System.out.println("************ Creating Employee *************");
		Address address = new Address();
		address.setCity("Bangalore");
		address.setCountry("India");
		address.setPostcode("560034");
		address.setState("Karnataka");
		address.setStreet("Koramangala");

		Address address1 = new Address();
		address1.setCity("Erode");
		address1.setCountry("India");
		address1.setPostcode("465656");
		address1.setState("Tamil Nadu");
		address1.setStreet("Madurai");

		Collection<Address> addresses = new ArrayList<Address>();
		addresses.add(address);
		addresses.add(address1);
		
		Employee employee = new Employee();
		employee.setAddresses(addresses);
		employee.setEndDate(new Date());
		employee.setFirstName("Arun Kumar");
		employee.setLastName("Krishnmurthy");
		employee.setPhoneNo("8050641876");
		employee.setSalary(new BigDecimal("1000000"));
		employee.setStartDate(new Date());
		
//		address.setEmployee(employee);
//		address1.setEmployee(employee);
//		
		System.out.println("************** Created Employee ************"+createEmplyee(employee));
		
		//System.out.println("************** Getting All Employees ****************");
		
		/*for(Employee emp : getAllEmployee()){
			System.out.println("************* Getting Employee of id :"+emp.getId()+"********* :"+emp);
			System.out.println("************* Addresses of Employee **********");
			for(Address addr : emp.getAddresses()){
				System.out.println("******* Address of employee ***** :"+emp.getId()+": *******"+addr);
			}
		}*/
		/*System.out.println("*********** Getting particular employee **************");
		Employee emp = new Employee();
		emp.setId(new Long(1));
		emp = loadEmployee(emp);
		System.out.println("************* Getting Employee of id :"+emp.getId()+"********* :"+emp);
		System.out.println("************* Addresses of Employee **********");
		for(Address addr : emp.getAddresses()){
			System.out.println("******* Address of employee ***** :"+emp.getId()+": *******"+addr);
		}*/
		/*System.out.println("********** Updating Employee **********");
		Employee emp = new Employee();
		emp.setId(new Long(1));
		emp = loadEmployee(emp);
		System.out.println("******** Employee before update *******"+emp);
		emp.setFirstName("Ravikanth");
		emp = update(emp);
		System.out.println("******** Employee After update *******"+emp);*/
		
		/*System.out.println("********** Deleting Employee **********");
		Employee emp = new Employee();
		emp.setId(new Long(1));
		emp = delete(emp);
		System.out.println("********** Deleted Employee **********"+emp);*/
		
		/*System.out.println("********** Deleting all Employee **********");
		int noOfEmpDeleted = deleteAll();
		System.out.println("********** No. of deleted employees **********"+noOfEmpDeleted);*/

	}

	private static Employee createEmplyee(Employee employee) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		em.persist(employee);
		
		employee.setFirstName("BTBTBT");
		
		em.getTransaction().commit();
		em.close();
		PersistenceManager.INSTANCE.close();
		return employee;
	}
	@SuppressWarnings("unchecked")
	private static List<Employee> getAllEmployee(){
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		Query query = em.createQuery("from Employee");
		return query.getResultList();
	}
	private static Employee loadEmployee(Employee emp){
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		/*Query query = em.createQuery("from Employee where id = :id");
		query.setParameter("id", emp.getId());*/
		emp = (Employee)em.find(Employee.class, emp.getId());
		return emp;
	}
	private static Employee update(Employee emp){
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		emp = em.merge(emp);
		em.getTransaction().commit();
		em.close();
		PersistenceManager.INSTANCE.close();
		return emp;
	}
	private static Employee delete(Employee emp){
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		emp = (Employee)em.find(Employee.class, emp.getId());
		em.remove(emp);
		em.getTransaction().commit();
		em.close();
		PersistenceManager.INSTANCE.close();
		return emp;
	}
	private static int deleteAll(){
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("delete from Address");
		query.executeUpdate();
		Query query1 = em.createQuery("delete from Employee");
		int noOfRowDeleted = query1.executeUpdate();
		em.getTransaction().commit();
		em.close();
		PersistenceManager.INSTANCE.close();
		return noOfRowDeleted;
	}
}
