package com.prabha.Employee.Management.model;

public class Promotion {

private String regNo;
	
	private String name;
	
	private String designation;
	
	private double salary;

	
	public Promotion(String regNo, String name, String designation, double salary) {
		super();
		this.regNo = regNo;
		this.name = name;
		this.designation = designation;
		this.salary = salary;
	}


	public Promotion() {
		super();
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


	@Override
	public String toString() {
		return "Promotion [regNo=" + regNo + ", name=" + name + ", designation=" + designation + ", salary=" + salary
				+ "]";
	}
	
	
}
