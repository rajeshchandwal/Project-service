package com.teksAcademy.springDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teksAcademy.springDemo.dto.UserDto;
import com.teksAcademy.springDemo.inter.UserInter;
import com.teksAcademy.springDemo.repository.UserRepository;

@Service
public class UserService {

	
	private  UserRepository userRepository;
	
	@Autowired
	UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	
	public List<UserDto> getUserData() {
		System.out.println("inside Service");
		List<UserInter> userList = userRepository.getData();
		System.out.println("List empty or not "+userList.isEmpty());
		List<UserDto> userDtoList = userList.stream().map(user->{
			UserDto userDto = new UserDto();
			System.out.println("country "+user.getUserCountry());
			userDto.setId(user.getUserId());
			userDto.setName(user.getUserName());
			userDto.setEmail(user.getUserEmail());
			userDto.setCountry(user.getUserCountry());
			return userDto;
		}).toList();
		
		return userDtoList;
	}
}
