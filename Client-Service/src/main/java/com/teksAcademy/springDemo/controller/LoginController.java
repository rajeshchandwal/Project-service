package com.teksAcademy.springDemo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.naming.AuthenticationException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teksAcademy.springDemo.JWT.CustomDetailImpl;
import com.teksAcademy.springDemo.JWT.JWTUtils;
import com.teksAcademy.springDemo.dto.LoginDto;
import com.teksAcademy.springDemo.entity.Login;
import com.teksAcademy.springDemo.entity.User;
import com.teksAcademy.springDemo.exception.DuplicateUserName;
import com.teksAcademy.springDemo.repository.DetailRepository;
import com.teksAcademy.springDemo.response.ResponseHandler;
import com.teksAcademy.springDemo.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private  UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTUtils jwtUtils;
	
	@Autowired
	private DetailRepository detailRepository;
	
	@ResponseBody
	@PostMapping("/saveUser")
	public ResponseEntity<Object> loginUser(@RequestBody Login login) throws AuthenticationException {
		
		 Authentication  authentication;

	ResponseEntity<Object> responsehandler = null;
	LoginDto loginDto = null ;
		try {
			if(detailRepository.existsByName(login.getName())) {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(login.getName(), login.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String jwtToken =jwtUtils.generateTokenFromUserName(userDetails);
			 loginDto = new LoginDto(userDetails.getUsername(),jwtToken);
//			 userService.loginForm(login);
			 responsehandler =	ResponseHandler.responseBuilder("data fetched successfully", HttpStatus.OK, loginDto);
			}
			else {
				 Login loginResponse = userService.loginForm(login);
				 authentication = authenticationManager
							.authenticate(new UsernamePasswordAuthenticationToken(login.getName(), login.getPassword()));
					SecurityContextHolder.getContext().setAuthentication(authentication);
					
					UserDetails userDetails = (UserDetails) authentication.getPrincipal();
					String jwtToken =jwtUtils.generateTokenFromUserName(userDetails);
					 loginDto = new LoginDto(userDetails.getUsername(),jwtToken);
				responsehandler =	ResponseHandler.responseBuilder("data fetched successfully", HttpStatus.OK, loginDto);
					 
			}
			
		} catch (DuplicateUserName e) {
			// TODO Auto-generated catch block
			 responsehandler =	ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
				
		return responsehandler;
		
	}
}
