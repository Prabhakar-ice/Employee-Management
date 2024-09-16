package com.prabha.Employee.Management;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.prabha.Employee.Management.model.Role;
import com.prabha.Employee.Management.model.Users;

public class UserPrinciple implements UserDetails {

	private Users user;
	
	public UserPrinciple(Users user) {
		this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Role role=user.getRole();
		String roles=role.name();
		return Collections.singleton(new SimpleGrantedAuthority(roles));
	}

	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

}
