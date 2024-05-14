package com.test.pm.repository;

import com.test.pm.dto.InsFirstNameCount;
import com.test.pm.entity.InstructorData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import com.test.pm.entity.Instructor;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    @Query("select  ins.email from Instructor ins where ins.firstName like %:firstName%")
    List<String> searchByFirstNameTest(String firstName);

    @Query("select concat(ins.firstName,'-', ins.lastName) from Instructor ins where concat(ins.firstName,'-', ins.lastName) like %:searchStringData%")
    List<String> searchByFirstAndLastName(String searchStringData);


    void deleteById(Integer id);
    @Query("select ins.firstName as firstName, ins.lastName as lastName," +
            "ind.hobby as hobby, ind.youtubeChannel as youtubeChannel from Instructor ins join InstructorDetail ind on ins.instructorDetail.id = ind.id where upper(ins.firstName) like %:firstName%")
    List<InstructorData> filterByFirstName(String firstName);

    @Query("select ins from Instructor ins where upper(ins.lastName) like  %:lastName%")
    List<Instructor> filterByLastName(String lastName);


    @Query("select ins.firstName as firstName, count(ins) as count from Instructor ins  where ins.lastName like %:lastName% group by ins.firstName")
    List<InsFirstNameCount> getFirstNameCount(String lastName);
    @Query("select ins.lastName from Instructor ins where ins.id> (select avg(ind.id) from Instructor ind )")
    List<String> getNestedQuery();



    @Procedure("poprocedure")
    List<Instructor> getProceduredata();

    List<Instructor> findAllById(Long id, Pageable paging);

    List<Instructor> findAllByLastName(String lastName, Pageable paging);
}
