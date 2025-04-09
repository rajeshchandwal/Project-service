package com.teksAcademy.springDemo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.teksAcademy.springDemo.JWT.AuthEntryPointJWT;
import com.teksAcademy.springDemo.JWT.AuthTokenFilter;
import com.teksAcademy.springDemo.JWT.CustomDetailImpl;
import com.teksAcademy.springDemo.JWT.JWTUtils;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
	
	@Autowired
	private CustomDetailImpl customDetailImpl;
	@Autowired
	private JWTUtils jWTUtils;

	@Autowired
	private AuthEntryPointJWT unAuthorisedHandler;
	@Autowired
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter(jWTUtils, customDetailImpl);
	}
	
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		System.out.println("Inside filterChain");
        http
        .csrf(csrf ->csrf.disable())
        .authorizeHttpRequests(auth ->
        auth .requestMatchers("/login/**").permitAll()
      .anyRequest().authenticated())
        //.requestMatchers("/data/**").permitAll())
        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(unAuthorisedHandler));
        http.headers(headers -> headers.frameOptions(frameOption->frameOption.sameOrigin()));
        //first do customfilter doFilterInternal() then do UsernamePasswordAuthenticationFilter filter method
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // disable page caching
        return http.build();
    }
	
	@Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		
		return builder.getAuthenticationManager();
		
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(getPasswordEncoder());
		dao.setUserDetailsService(customDetailImpl);
		return dao;
	}
	
}
