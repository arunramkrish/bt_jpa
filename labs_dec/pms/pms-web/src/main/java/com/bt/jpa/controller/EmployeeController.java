package com.bt.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bt.jpa.common.exception.PmsServiceException;
import com.bt.jpa.entity.Employee;
import com.bt.jpa.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = { "/employees/create" }, method = { RequestMethod.GET })
	public String showCreateEmployee(Model model) {
		model.addAttribute("employee", new Employee());

		return "employeeForm";

	}

	@RequestMapping(value = { "/employees" }, method = { RequestMethod.POST})
	public String addEmployee(@ModelAttribute("employee")Employee employee,
			BindingResult errors, Model model) {
		try {
			employeeService.createEmployee(employee);
			
		} catch (PmsServiceException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error occurred while fetching employees " + e.getMessage());
			return "error";
		}

		return "redirect:employees";
	}

	@RequestMapping(value = { "/employees" }, method = { RequestMethod.GET })
	public String getEmployees(Model model) {
		List<Employee> employees = null;
		try {
			employees = employeeService.getEmployees();
		} catch (PmsServiceException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error occurred while fetching employees " + e.getMessage());
			return "error";
		}

		model.addAttribute("employees", employees);

		return "employees";

	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}
