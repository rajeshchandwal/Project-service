package com.teksAcademy.springDemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="user")
public class User {
	
	 @Id
	   @GeneratedValue(strategy=GenerationType.AUTO)
	   private int id;
	   
	   @Column(name= "name")
	   private String name;
	   
	   @Column(name= "email")
	   private String email;
	   
	   @Column(name= "country")
	   private String country;
	   
//	   @Column(name= "JWTToken")
//	   private String jwtToken;

}
