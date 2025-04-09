package com.teksAcademy.springDemo.JWT;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
 

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class AuthTokenFilter extends OncePerRequestFilter{

	
	private JWTUtils jWTUtils;
	
	
	private CustomDetailImpl customDetailImpl;
	
	
	
	
	
	@Autowired
	public AuthTokenFilter(JWTUtils jWTUtils, CustomDetailImpl customDetailImpl) {
		super();
		this.jWTUtils = jWTUtils;
		this.customDetailImpl = customDetailImpl;
	}

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("inside customDetailImpl ");
		System.out.println("request "+request);
		try {
		String jwt =parseJwt(request);
		if(jwt != null ) {
			System.out.println("inside doFilterInternal ");
		String userName = 	jWTUtils.getUserNameFromJwtToken(jwt);
		System.out.println("userName "+userName);
		UserDetails userDetails = customDetailImpl.loadUserByUsername(userName);
		
		
		//I am authentication detail so that I can get it from security context 
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(userDetails,null,null);
		
		//also adding additional detail from header
		authentication
		.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		}
		}catch (Exception e) {
		logger.error("Cannot set User Authentication: {} ", e);	
		}
		
		//mean after doing above doFilterInternal custom filter do inbuilt filter also
		filterChain.doFilter(request, response);
	}
	
	private String parseJwt(HttpServletRequest request) {
		System.out.println("inside parseJwt method");
		System.out.println("request "+request);
		String jwt = jWTUtils.getJwtFromHeader(request);
		System.out.println("jwt "+jwt);
		logger.debug("AuthenticationFilter.java: {}", jwt);
		return jwt;
		
	}
	
	

}
