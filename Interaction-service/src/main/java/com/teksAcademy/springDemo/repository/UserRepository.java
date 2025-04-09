package com.teksAcademy.springDemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teksAcademy.springDemo.entity.User;
import com.teksAcademy.springDemo.inter.UserInter;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer>{

	@Query("select id as userId, name as userName, email as userEmail, country as userCountry from User")
	List<UserInter> getData();
}
