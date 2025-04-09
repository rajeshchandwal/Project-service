package com.teksAcademy.springDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teksAcademy.springDemo.dto.UserDto;
import com.teksAcademy.springDemo.service.UserService;

@Controller
@RequestMapping("/data")
public class UserConroller {

private  UserService userService;
	
	@Autowired
	UserConroller(UserService userService){
		this.userService = userService;
	}
	
	@ResponseBody
	@GetMapping("/user")
	public List<UserDto> createUser() {
		System.out.println("inside Controller");
		List<UserDto> userDtoList =userService.getUserData();
		//System.out.println("userDtoList "+userDtoList.get(0).getCountry());
	    return userDtoList;
	}
}
