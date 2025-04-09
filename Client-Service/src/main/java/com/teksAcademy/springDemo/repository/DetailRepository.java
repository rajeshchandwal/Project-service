package com.teksAcademy.springDemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.teksAcademy.springDemo.entity.Login;

import jakarta.transaction.Transactional;

@Repository
public interface DetailRepository extends JpaRepository<Login, Integer>{
	
	Optional<Login> findByName(String name);
	boolean existsByName(String name);

}
