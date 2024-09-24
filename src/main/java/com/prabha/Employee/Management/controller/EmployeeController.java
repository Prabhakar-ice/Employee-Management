package com.prabha.Employee.Management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prabha.Employee.Management.model.Employees;
import com.prabha.Employee.Management.model.Increment;
import com.prabha.Employee.Management.model.Promotion;
import com.prabha.Employee.Management.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("employee-manage")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("findAll")
	public ResponseEntity<List<Employees>> getAllEmployees(){
		List<Employees> employee=employeeService.showAllEmployees();
		return new ResponseEntity<>(employee,HttpStatus.OK);
	}

	
	@GetMapping("findAll/{designation}")
	public ResponseEntity<List<Employees>> getEmployeesByDesignation(@PathVariable String designation){
		List<Employees> employee=employeeService.showByDesignation(designation);
		return new ResponseEntity<>(employee,HttpStatus.OK);
	}
	
	@PostMapping("create")
	public ResponseEntity<Employees> addEmployee(@Valid @RequestBody Employees employee){
		Employees emp=employeeService.addEmployee(employee);
		return new ResponseEntity<> (emp,HttpStatus.CREATED);
	}
	
	@PutMapping("promote")
	public ResponseEntity<String> promote(@RequestBody Promotion promote){
		String str=employeeService.givePromotion(promote);
		return new ResponseEntity<> (str,HttpStatus.OK);
	}
	
	@PutMapping("increment")
	public ResponseEntity<String> increment(@RequestBody Increment inc){
		
		String str=employeeService.increment(inc);
		return new ResponseEntity<>(str,HttpStatus.OK);
	}
}
