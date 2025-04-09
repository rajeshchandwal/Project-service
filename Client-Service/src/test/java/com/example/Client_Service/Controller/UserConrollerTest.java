package com.example.Client_Service.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.teksAcademy.springDemo.controller.UserConroller;
import com.teksAcademy.springDemo.dto.UserDto;
import com.teksAcademy.springDemo.service.UserService;
@ExtendWith(MockitoExtension.class)
public class UserConrollerTest {

	@Mock
	private  UserService userService;
	
	@InjectMocks
	private UserConroller userConroller;
	
	
	@Test
	public void createUserTest() {
		List<UserDto> userDtoList = new ArrayList<>();
		UserDto userDto =new UserDto();
		userDto.setCountry("India");
		userDtoList.add(userDto);
		when(userService.getUserData()).thenReturn(userDtoList);
		 List<UserDto> dto =userConroller.createUser();
		assertEquals("India", dto.get(0).getCountry());
	}
	
	
	
}
