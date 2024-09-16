package com.prabha.Employee.Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prabha.Employee.Management.model.Tokens;
import com.prabha.Employee.Management.model.UserVerify;
import com.prabha.Employee.Management.model.Users;
import com.prabha.Employee.Management.services.UserService;

@RestController
@RequestMapping("/user")
public class UsersController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String greet() {
		return "Welcome";
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestBody Users user){
		System.out.println(user.toString());
		return new ResponseEntity<>(userService.createUser(user),HttpStatus.CREATED);
	}
	
	@PostMapping("login")
	public ResponseEntity<Tokens> verifyUser(@RequestBody UserVerify verify){
		
		return new ResponseEntity<>(userService.verifyUser(verify),HttpStatus.OK);
	}
}
