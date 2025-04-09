package com.teksAcademy.springDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teksAcademy.springDemo.dto.UserDto;
import com.teksAcademy.springDemo.entity.User;

import com.teksAcademy.springDemo.service.UserService;

@Controller
@RequestMapping("/data")
public class UserConroller {

private  UserService userService;
	



	@Autowired
	public UserConroller(UserService userService) {
	super();
	this.userService = userService;

}
   @ResponseBody
	@GetMapping("/user")
	public List<UserDto> createUser() {
		System.out.println("inside Controller");
		List<UserDto> userDtoList =userService.getUserData();
		
//		 String jwtToken = 	jwtService.generateJWTToken();
//		 System.out.println("jwtToken   "+jwtToken);
	    return userDtoList;
	}
	
   @PostMapping("/save")
   public void save(@RequestBody User user) {
	   System.out.println("name "+user.getName());
	   
	   System.out.println("inside save Controller");
	   userService.save(user);
   }
	
	@GetMapping("/externalService")
	public List<UserDto>  externalService() {
		System.out.println("Inside external controller");
		List<UserDto> userList =	userService.externalService();
		return userList;
	}
	

}
