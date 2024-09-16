package com.prabha.Employee.Management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prabha.Employee.Management.model.Employees;

@Repository
public interface EmployeeRepo extends JpaRepository<Employees,Integer>{

	List<Employees> findByDesignation(String str);

	Employees findByName(String name);

	
}
