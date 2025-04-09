package com.teksAcademy.springDemo.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginPrincipal  implements UserDetails{

	@Autowired
	private Login detail;
	public LoginPrincipal(Login detail){
		this.detail = detail;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("User"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return detail.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return detail.getName();
	}

	
	



}
