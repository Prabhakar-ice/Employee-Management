package com.prabha.Employee.Management;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.prabha.Employee.Management.services.JwtService;
import com.prabha.Employee.Management.services.UsersDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	ApplicationContext context;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header=request.getHeader("Authorization");
		String token=null;
		String username=null;
		
		if(header != null && header.startsWith("Bearer ")) {
			token=header.substring(7);
			username=jwtService.extractTokenName(token);
		}
		
		if(username!= null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails user=context.getBean(UsersDetailService.class).loadUserByUsername(username);
			
			if(jwtService.validateToken(token,user)) {
				
				UsernamePasswordAuthenticationToken auth=
						new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
				
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		filterChain.doFilter(request, response);
	}

}
