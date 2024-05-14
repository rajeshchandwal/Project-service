package com.test.pm.repository;

import com.test.pm.entity.InstructorView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorViewRepository extends JpaRepository<InstructorView, Integer> {


    InstructorView findByLastName(String lastName);
}
