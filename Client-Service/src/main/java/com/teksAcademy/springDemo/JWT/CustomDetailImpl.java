package com.teksAcademy.springDemo.JWT;

import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teksAcademy.springDemo.entity.Login;
import com.teksAcademy.springDemo.entity.LoginPrincipal;
import com.teksAcademy.springDemo.repository.DetailRepository;
@Service
public class CustomDetailImpl implements UserDetailsService {


	private DetailRepository detailRepository;
		
	
   @Autowired
	public CustomDetailImpl(DetailRepository detailRepository) {
		super();
		this.detailRepository = detailRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Login> optionalDetail = detailRepository.findByName(username);
		System.out.println("inside securit ");
		if(optionalDetail.isEmpty()) {
			throw new UsernameNotFoundException("user not found");
		}else {
			Login detail = optionalDetail.get();
//			UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
//					.username(detail.getName())
//					.password(detail.getPassword())
//					.build();
			return new LoginPrincipal(detail);
		}
		
	}
	
	
  
	

}
