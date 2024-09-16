package com.prabha.Employee.Management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prabha.Employee.Management.model.Tokens;
import com.prabha.Employee.Management.model.UserVerify;
import com.prabha.Employee.Management.model.Users;
import com.prabha.Employee.Management.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	AuthenticationManager authManage;
	
	BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(12);
	
	public String  createUser(Users user) {
	
		Users u=new Users();
		u.setUsername(user.getUsername());
		u.setRole(user.getRole());
		u.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(u);
		return "Success";
	}

	public Tokens verifyUser(UserVerify verify) {
		
		Authentication auth=authManage.authenticate
				(new UsernamePasswordAuthenticationToken(verify.getUsername(),verify.getPassword()));
		Users user=userRepo.findByUsername(verify.getUsername());
		
		Tokens tokens=new Tokens();
		String token;
		String refreshToken;
		
		token=jwtService.generateToken(user);
		refreshToken=jwtService.generateRefreshToken(user);
		
		tokens.setToken(token);
		tokens.setRefreshToken(refreshToken);
		
		return tokens;
	}

	
}
