package com.zkteco.employeeservice.employeeservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zkteco.employeeservice.employeeservice.model.Employee;
import com.zkteco.employeeservice.employeeservice.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@PostMapping("/")
	public Employee add(@RequestBody Employee employee) {
		LOGGER.info("Inside Add Employee Method");
		return employeeRepository.addEmployee(employee);
	}
	
	@GetMapping("/")
	public List<Employee> findAll(){
		LOGGER.info("Inside Find All Employee Method");
		return employeeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee findById(@PathVariable("id") Long id) {
		LOGGER.info("Inside find Employee By Id Method");
		return employeeRepository.findById(id);
	}
	
	@GetMapping("/department/{departmentId}")
	public List<Employee> findByDepartment(@PathVariable("departmentId") Long id){
		LOGGER.info("Inside find Employee By Department Method");
		return employeeRepository.findByDepartment(id);
	}
	
}
