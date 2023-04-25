package com.zkteco.departmentservice.controller;

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

import com.zkteco.departmentservice.client.EmployeeClient;
import com.zkteco.departmentservice.model.Department;
import com.zkteco.departmentservice.repository.DepartmentRepository;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private EmployeeClient employeeClient;
	
	@PostMapping("/")
	public Department add(@RequestBody Department department) {
		LOGGER.info("Inside Add Department Method");
		return departmentRepository.addDepartment(department);
	}
	
	@GetMapping("/")
	public List<Department> findAll(){
		LOGGER.info("Inside Find All Departments Method");
		return departmentRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Department findById(@PathVariable("id") Long id) {
		LOGGER.info("Inside find Department By Id Method");
		return departmentRepository.findById(id);
	}
	
	@GetMapping("/withemployees")
	public List<Department> findAllWithEmployees(){
		LOGGER.info("Inside Find All Departments Method");
		List<Department> departments = departmentRepository.findAll();
		departments.forEach(department->
		department.setEmployees(employeeClient.findByDepartment(department.getId())));
		return departments;
	}
	
}
