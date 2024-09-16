package com.prabha.Employee.Management.model;

public class Tokens {

	private String token;
	private String refreshToken;
	
	public Tokens() {
		super();
	}
	public Tokens(String token, String refreshToken) {
		super();
		this.token = token;
		this.refreshToken = refreshToken;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	
}
