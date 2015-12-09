package com.bt.jpa.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bt.jpa.common.exception.PmsServiceException;
import com.bt.jpa.common.vo.EmployeeReportVo;
import com.bt.jpa.entity.Employee;
import com.bt.jpa.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = { "/createEmployees" }, method = { RequestMethod.GET })
	public String showCreateEmployee(Model model) {
		model.addAttribute("employee", new Employee());

		return "employeeForm";

	}

	@RequestMapping(value = { "/employees" }, method = { RequestMethod.POST })
	public String addEmployee(@ModelAttribute("employee") Employee employee, BindingResult errors, Model model) {
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

	@RequestMapping(value = { "/api/employees" }, method = { RequestMethod.GET })
	public void getEmployeesJson(HttpServletRequest request, HttpServletResponse response) throws JsonGenerationException,
			JsonMappingException, IOException {
		List<EmployeeReportVo> employees = null;
		try {
			employees = employeeService.getEmployeesReport();

			ObjectMapper mapper = new ObjectMapper();
			mapper.getSerializationConfig().disable(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS);
			mapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

			response.getOutputStream().print(mapper.writeValueAsString(employees));
		} catch (PmsServiceException e) {
			e.printStackTrace();
			response.getOutputStream().print("{'errorMessage':'Error occurred while fetching employees " + e.getMessage() + "'");
		}

	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}
