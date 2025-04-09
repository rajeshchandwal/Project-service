package com.teksAcademy.springDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.teksAcademy.springDemo.config.WebClientConfiguration;
import com.teksAcademy.springDemo.dto.UserDto;
import com.teksAcademy.springDemo.entity.Login;
import com.teksAcademy.springDemo.entity.User;
import com.teksAcademy.springDemo.exception.DuplicateUserName;
import com.teksAcademy.springDemo.inter.UserInter;
import com.teksAcademy.springDemo.repository.DetailRepository;
import com.teksAcademy.springDemo.repository.UserRepository;

import jakarta.transaction.Transactional;
import reactor.core.publisher.Mono;

@Service
public class UserService {

	@Autowired
	private  UserRepository userRepository;
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private DetailRepository detailRepository;
	private final static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	
	
//	 public UserService(WebClient.Builder webClientBuilder) {
//	        this.webClient = webClientBuilder.baseUrl("http://localhost:8222/Interaction-service").build();
//	    }

	
	
	public List<UserDto> getUserData() {
		System.out.println("inside Service");
		List<UserInter> userList = userRepository.getData();
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
	
	public void save(User user) {
		System.out.println("name "+user.getName());
		System.out.println("inside save  service");
		userRepository.save(user);
	}

	
	public List<UserDto>  externalService() {
		System.out.println("Inside external service");
		Mono<List<UserDto>> response =  webClient.get().uri(uriBuilder->uriBuilder.path("/user").build()).retrieve()
		.bodyToMono(new ParameterizedTypeReference<List<UserDto>>() {});
		List<UserDto> userList = response.block();
		return userList;
	}
	
		
	  public Login loginForm(Login detail) throws DuplicateUserName  {
			System.out.println("name "+detail.getName());
			detail.setPassword(passwordEncoder.encode(detail.getPassword()));
			System.out.println("encripted password "+detail.getPassword());
			detailRepository.save(detail);
			return detail;
		}
		
}
