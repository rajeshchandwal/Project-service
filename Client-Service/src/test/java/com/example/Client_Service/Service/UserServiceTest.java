package com.example.Client_Service.Service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.teksAcademy.springDemo.inter.UserInter;
import com.teksAcademy.springDemo.repository.UserRepository;
import com.teksAcademy.springDemo.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private  UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	@Test
	public void getUserDataTest() {
		List<UserInter> userList = new ArrayList<>();
		userList.add(null);
		when(userRepository.getData()).thenReturn(userList);
		userService.getUserData();
	}
	

}
