package com.prabha.Employee.Management.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.prabha.Employee.Management.model.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	String secretKey="12365478965plokijmnh456ygtftgrfedwsaqwfcvbgfdtylotbgbkj986451948619";
	public String generateToken(Users user) {
		
		Map<String,Object> claims=new HashMap<>();
		
		
		return Jwts.builder()
				.claims().add(claims)
				.subject(user.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*60))
				.and()
				.signWith(getKey())
				.compact();
	}

	

	public String generateRefreshToken(Users user) {
		Map<String,Object> claims=new HashMap<>();
		
		return Jwts.builder()
				.claims().add(claims)
				.subject(user.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*60*24*10))
				.and()
				.signWith(getKey())
				.compact();
	}

	private Key getKey() {
		byte[] key=Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(key);
	}

	public String extractTokenName(String token) {
		
		return extractClaim(token, Claims::getSubject);
	}

	private <T> T extractClaim(String token,Function<Claims,T> claimResolver) {
		final Claims claim=extractAllClaims(token);
		return claimResolver.apply(claim);
	}

	private Claims extractAllClaims(String token) {
		
		return Jwts.parser()
				.verifyWith((SecretKey)getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	public boolean validateToken(String token, UserDetails user) {
		final String extractUsername= extractTokenName(token);
		return extractUsername.equals(user.getUsername()) && !isTokenExpired(token);
	}



	private boolean isTokenExpired(String token) {
		
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		
		return extractClaim(token,Claims::getExpiration);
	}
}
