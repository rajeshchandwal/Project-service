package com.teksAcademy.springDemo.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@DynamicUpdate
@Table(name="detail")
public class Login {
	
	  @Id
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
	   private int id;
	   
	   @Column(name= "name")
	   private String name;
	   
	   @Column(name= "password")
	   private String password;
	   
	   @Version
	   @Column(name= "version")
	   private long version;



	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	}
