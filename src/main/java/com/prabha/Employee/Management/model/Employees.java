package com.prabha.Employee.Management.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Employees {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String regNo;
	
	@NotBlank(message="Name cannot be blank or empty")
	private String name;
	
	@NotBlank(message="Designation cannot be blank or empty")
	private String designation;
	
	@NotNull(message="Salary cannot be blank or empty")
	@Min(value=20000)
	@Max(value=100000)
	private double salary;
	
	private Date dateOfJoin;

	
	public Employees(int id, String regNo, String name, String designation, double salary, Date dateOfJoin) {
		super();
		this.id = id;
		this.regNo = regNo;
		this.name = name;
		this.designation = designation;
		this.salary = salary;
		this.dateOfJoin = dateOfJoin;
	}

	public Employees() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegNo() {
		return regNo;
	}


	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getDateOfJoin() {
		return dateOfJoin;
	}

	public void setDateOfJoin(Date dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}


	@Override
	public String toString() {
		return "Employees [id=" + id + ", regNo=" + regNo + ", name=" + name + ", designation=" + designation
				+ ", salary=" + salary + ", dateOfJoin=" + dateOfJoin + "]";
	}
	
	
}
