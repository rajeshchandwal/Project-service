package com.test.pm.dto;

import com.test.pm.entity.InstructorDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
public class InstructorDto {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private int id;
    private String firstName;
    private String lastName;
    private String email;


    public InstructorDetailDto getInstructorDetailDto() {
        return instructorDetailDto;
    }

    public void setInstructorDetailDto(InstructorDetailDto instructorDetailDto) {
        this.instructorDetailDto = instructorDetailDto;
    }

    InstructorDetailDto instructorDetailDto;

    public List<CoursesDto> getCourseDtoList() {
        return courseDtoList;
    }

    public void setCourseDtoList(List<CoursesDto> courseDtoList) {
        this.courseDtoList = courseDtoList;
    }

    private List<CoursesDto> courseDtoList;

}
