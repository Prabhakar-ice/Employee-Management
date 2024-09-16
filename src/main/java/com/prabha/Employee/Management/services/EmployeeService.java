package com.prabha.Employee.Management.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prabha.Employee.Management.model.Employees;
import com.prabha.Employee.Management.model.Increment;
import com.prabha.Employee.Management.model.Promotion;
import com.prabha.Employee.Management.repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	public Employees addEmployee(Employees employee) {
		
		Employees emp=new Employees();
		emp.setName(employee.getName());
		emp.setSalary(employee.getSalary());
		emp.setDateOfJoin(new Date(System.currentTimeMillis()));
		emp.setDesignation(employee.getDesignation());
		emp.setRegNo(employee.getRegNo());
		
		employeeRepo.save(emp);
		
		return emp;
	}
	
	public List<Employees> showAllEmployees(){
		return employeeRepo.findAll();
	}
	
	public List<Employees> showByDesignation(String str){
		return employeeRepo.findByDesignation(str);
	}
	
	public String increment(Increment inc) {
		Employees emp=employeeRepo.findByName(inc.getName());
		emp.setSalary(inc.getSalary());
		employeeRepo.save(emp);
		return "Updated salary";
	}
	
	public String givePromotion(Promotion promote) {
		
		Employees emp=employeeRepo.findByName(promote.getName());
		emp.setDesignation(promote.getDesignation());
		emp.setSalary(promote.getSalary());
		emp.setRegNo(promote.getRegNo());
		employeeRepo.save(emp);
		
		return"Promoted";
	}
}
