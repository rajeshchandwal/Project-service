package com.test.pm.controller;

import com.test.pm.dto.InstructorDto;
import com.test.pm.dto.TestDto;
import com.test.pm.entity.Instructor;
import com.test.pm.entity.InstructorData;
import com.test.pm.entity.InstructorView;
import com.test.pm.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sample")
public class SampleController {

    private final SampleService sampleService;


    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }
    @GetMapping("/test")
    public String test(){
        return "hello";
    }
    @GetMapping("/sampleProject")
    public void testGetData(@RequestParam int id) {

        sampleService.getData(id);
    }
    @GetMapping("/search")
    public List<String> searchFirstName(@RequestParam String searchString) {

        return sampleService.searchFirstName(searchString);
    }
    @GetMapping("/searchByFirstLastName")
    public  List<String> searchByfirstAndLastName(String str) {
        List<String> list = sampleService.SearchData(str);
        return list;
    }
    @DeleteMapping("deleteRecord")
    public String deleterecord(@RequestParam int id) {
        sampleService.deleteRecord(id);
        return String.valueOf(id)+" deleted successfully";
    }
    @GetMapping("/filterData")
   public List<Instructor> getDetailDtata(@RequestParam String  firstName) {
      return sampleService.filterByFirstName(firstName);
   }
    @GetMapping("/filterByLastName")
   public List<Instructor> getFilteredData(@RequestParam String  lastName) {
        List<Instructor> instructors = sampleService.filterByLastName(lastName);
        return instructors;
    }
    @PutMapping("/update")
    public Instructor updateRecord(@RequestBody InstructorDto instructorDto) {
       return sampleService.updateRecord(instructorDto);
    }
    @GetMapping("/streamPracties")
    public InstructorDto streamData(@RequestBody List<InstructorDto> instructorDto) {
       return sampleService.streamPracties(instructorDto);
    }

    public InstructorDto StreamsOperation() {

        return null;
    }
    @GetMapping("/count")
    public List<TestDto> getFirstNameCount(@RequestParam String  firstName) {
        return sampleService.getFirstNameCount(firstName);
    }
    @GetMapping("/updateInstructorDetail")
    public void updateInstructorDetail(@RequestBody InstructorDto instructorDto) {
        sampleService.updateInstructorDetail(instructorDto);
    }
    @GetMapping("/course")
    public InstructorDto getOneToMany(@RequestParam int  id) {
        InstructorDto instructorDto = sampleService.getOneToMany(id);
        return instructorDto;
    }
    @GetMapping("/nestedData")
    public List<String> getNestedData() {
       return sampleService.getNestedData();
    }
    @GetMapping("/viewData")
    public InstructorView getViewData(@RequestParam String  name) {
       return sampleService.getViewData(name);
    }
    @GetMapping("/procedureData")
    public List<Instructor> getProcedureData() {
        return sampleService.getProcedure();
    }
    @GetMapping("/paging"+"/"+"{name}")
    public List<Instructor> pagination(@PathVariable("name") String name) {
       return sampleService.pagination(name,1L,1);
    }

}
