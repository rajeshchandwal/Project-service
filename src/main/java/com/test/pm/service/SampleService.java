package com.test.pm.service;

import com.test.pm.dto.*;
import com.test.pm.entity.Instructor;
import com.test.pm.entity.InstructorData;
import com.test.pm.entity.InstructorDetail;
import com.test.pm.entity.InstructorView;
import com.test.pm.repository.InstructorRepository;
import com.test.pm.repository.InstructorViewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class SampleService {
    private final InstructorRepository instructorRepository;

    private final InstructorViewRepository instructorViewRepository;
    public SampleService(InstructorRepository instructorRepository, InstructorViewRepository instructorViewRepository) {
        this.instructorRepository = instructorRepository;
        this.instructorViewRepository = instructorViewRepository;
    }


    public void getData(int id) {
        Optional<Instructor> byId = instructorRepository.findById(id);
        Instructor instructor = null;
        if(byId.isPresent()) {
            instructor = byId.get();
        }
        System.out.println("instructor.getId()"+instructor.getEmail());
    }

    public List<String> searchFirstName(String searchString) {
        return instructorRepository.searchByFirstNameTest(searchString);

    }

    public  List<String> SearchData(String searchStr) {
        return  instructorRepository.searchByFirstAndLastName(searchStr);
       // System.out.println("firstAndLastName"+firstAndLastName);
    }

    public void deleteRecord(int id) {
        instructorRepository.deleteById(id);
    }

    public List<Instructor> filterByFirstName(String name) {
        List<InstructorData> data = instructorRepository.filterByFirstName(name.toUpperCase());
        List<Instructor> listIns = new ArrayList<>();
//        for(InstructorData instructorData: data) {
//            Instructor ins = new Instructor();
//            ins.setFirstName(instructorData.getFirstName());
//            ins.setLastName(instructorData.getLastName());
//            InstructorDetail ind = new InstructorDetail();
//            ind.setHobby(instructorData.getHobby());
//            ind.setYoutubeChannel(instructorData.getYoutubeChannel());
//            ins.setInstructorDetail(ind);
//            listIns.add(ins);
//        }

//        return listIns;
      return  data.stream().map(a->{
            Instructor ins = new Instructor();
            ins.setFirstName(a.getFirstName());
            ins.setLastName(a.getLastName());
            InstructorDetail ind = new InstructorDetail();
            ind.setHobby(a.getHobby());
            ind.setYoutubeChannel(a.getYoutubeChannel());
            ins.setInstructorDetail(ind);
            return ins;
        }).collect(Collectors.toList());
    }

    public List<Instructor> filterByLastName(String lastName) {
        List<Instructor> instructors = instructorRepository.filterByLastName(lastName);
        return instructors;
    }

    public Instructor updateRecord(InstructorDto instructorDto) {
        Optional<Instructor> byId = instructorRepository.findById(instructorDto.getId());
        Instructor instructor = null;
        if(byId.isPresent()) {
            instructor = byId.get();
        }
        BeanUtils.copyProperties(instructorDto, instructor);
        return instructorRepository.save(instructor);
    }

    public InstructorDto streamPracties(List<InstructorDto> instructorDtoList) {

//        instructorDtoList.stream().map(InstructorDto->{
//            Instructor instructor = new Instructor();
//            instructor.setLastName(InstructorDto::getLastName())
//        })
        instructorDtoList.stream().map(instructorDto -> {
            Instructor instructor = new Instructor();
            instructor.setEmail(instructorDto.getEmail());
            instructor.setLastName(instructorDto.getEmail());
            return instructor;
        }).collect(Collectors.toList());

        InstructorDto test = instructorDtoList.stream()
                .filter(instructorDto -> instructorDto.getFirstName().equals("rajesh"))
                .findFirst().get();
        System.out.println("test  "+ test.getFirstName());

        return test;
    }

    public void streamsOperation(List<InstructorDto> instructorDtoList) {
        List<InstructorDto> collect = instructorDtoList.stream().map(a -> {
            a.getFirstName().equals("");
            InstructorDto dto = new InstructorDto();
            dto.setEmail(a.getEmail());
            return dto;
        }).collect(Collectors.toList());

    }


    public List<TestDto> getFirstNameCount(String firstName) {
        List<InsFirstNameCount> firstNameCount = instructorRepository.getFirstNameCount(firstName);
        return firstNameCount.stream().map(a -> {
            TestDto testDto = new TestDto();
            testDto.setCount(a.getCount());
            testDto.setFirstName(a.getFirstName());
            return testDto;
        }).collect(Collectors.toList());
    }

    public void updateInstructorDetail(InstructorDto instructorDto) {
        Optional<Instructor> byId = instructorRepository.findById(instructorDto.getId());
        Instructor instructor = null;
        if(byId.isPresent()) {
            instructor = byId.get();
        }

       BeanUtils.copyProperties(instructorDto, instructor);
        List<InstructorDetailDto> instructorDetailDto = null;
        
        updateInstructorDetail(instructor, instructorDto);

    }

    private Instructor updateInstructorDetail(Instructor instructor, InstructorDto instructorDto) {

        BeanUtils.copyProperties(instructorDto.getInstructorDetailDto(), instructor.getInstructorDetail());
        return instructor;
    }


    public void printAllNumberInListFunctional() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(4);
        numbers.add(8);
        numbers.add(76);
        numbers.stream()
                .filter(numb->numb%2 == 0) //lamda expression
                .map(num->num*num)
                .forEach(SampleService::print); //method reference

        List<String> courses =List.of("spring","spring boot", "microservices","API");

        //course contain spring
        courses.stream()
                .filter(course->course.contains("spring"))
                .forEach(System.out::println);
        //course length is 4
        courses.stream()
                .filter(course->course.length()>=4) //returning stream
                .forEach(System.out::println);

        //reduce
        numbers.stream()
                .reduce(0,(x,y)->x+y);//returing specific
        //distinct
        numbers.stream()
                .distinct() //returning stream
                .forEach(System.out::println);
        //sort
        numbers.stream()
                .sorted() //returning stream
                .forEach(System.out::println);

        //comparator to sort ascending order
        numbers.stream()
                .sorted(Comparator.naturalOrder()) //returning stream
                .forEach(System.out::println);

        //comparator to sort descending order
        numbers.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        //comparator to sort based on length
        courses.stream()
                .sorted(Comparator.comparing(str->str.length()))
                .forEach(System.out::println); //return void

        //create double of each number from a list and create new list
        List<Integer> collect = numbers.stream()
                .map(num -> num * num) //returning stream
                .parallel()
                .collect(Collectors.toList()); //returning specific
        //Intermediate operatoion :: the operation which are performing on a stream and returning back stream
        //are called as intermediate operation


        //predicate
        Predicate<Integer> predicate = x->x%2==0;
        //function
        Function<Integer,Integer> function = num -> num*num;
        numbers.stream()
                .filter(predicate)
                .map(function)
                .collect(Collectors.toList());

        //BinaryOperator
        BinaryOperator<Integer> binaryOperator = Integer::sum;
        numbers.stream()
                .reduce(0, binaryOperator);

          numbers.stream()
                .filter(getIntegerPredicate());

        List<CoursesDto> courseDtosList = new ArrayList<>();
        CoursesDto courseDtos = new CoursesDto();
        courseDtos.setName("spring");
        courseDtos.setNoOfStudent(98);
        courseDtos.setCategory("framework");
        courseDtos.setReviewScore(56);
        courseDtosList.add(courseDtos);
//        List<CourseDto> courseDtosList = List.of(new CourseDto("spring", "framework", 98, 2000),
//                new CourseDto("API", "framework", 78, 7000),
//                new CourseDto("spring cloud", "cloud", 56, 9000),
//                new CourseDto("AWS", "cloud", 45, 4000)
//        );

        //allMatch, noneMatch, anyMatch
         courseDtosList.stream()
                .allMatch(getCourseDtoPredicate());

        courseDtosList.stream()
                .noneMatch(getCourseDtoPredicate());
        courseDtosList.stream()
                .anyMatch(getCourseDtoPredicate());

        //comparator
        List<CoursesDto> courseDtoListIncreasingOrder = courseDtosList.stream()
                .sorted(Comparator.comparing(CoursesDto::getName))
                .collect(Collectors.toList());

        List<CoursesDto> courseDtoListdecreasingOrder = courseDtosList.stream()
                .sorted(Comparator.comparing(CoursesDto::getNoOfStudent).reversed())
                .collect(Collectors.toList());

        List<CoursesDto> courseDtoListComparingByNoofStuAndReview = courseDtosList.stream()
                .sorted(Comparator
                        .comparing(CoursesDto::getNoOfStudent)
                        .thenComparing(CoursesDto::getReviewScore).reversed())
                .collect(Collectors.toList());

        //skip, limit, takewhile, dropwhile
        courseDtosList.stream()
                .sorted(Comparator
                        .comparing(CoursesDto::getNoOfStudent)
                        .thenComparing(CoursesDto::getReviewScore).reversed())
                .limit(5)
                .collect(Collectors.toList());

        courseDtosList.stream()
                .sorted(Comparator
                        .comparing(CoursesDto::getNoOfStudent)
                        .thenComparing(CoursesDto::getReviewScore).reversed())
                .skip(3)
                .collect(Collectors.toList());

        courseDtosList.stream()
                .takeWhile(courseDto -> courseDto.getReviewScore() >60)
                .collect(Collectors.toList());

        courseDtosList.stream()
                .dropWhile(courseDto -> courseDto.getReviewScore() >60)
                .collect(Collectors.toList());
        //grouping
        courseDtosList.stream()
                .collect(Collectors.groupingBy(CoursesDto::getCategory,Collectors.counting()))
                .entrySet().stream()
                .filter(category->category.getValue()>24)
                .collect(Collectors.toList());

        courseDtosList.stream()
                .collect(Collectors.groupingBy(CoursesDto::getCategory));

        courseDtosList.stream()
                .collect(Collectors
                        .groupingBy(CoursesDto::getCategory,Collectors
                                .maxBy(Comparator.comparing(CoursesDto::getReviewScore))));

        courseDtosList.stream()
                .collect(Collectors
                        .groupingBy(CoursesDto::getNoOfStudent, Collectors.mapping(CoursesDto::getName, Collectors.toList())));



        //FlatMap
        // Creating a list of Prime Numbers
        List<Integer> primeNumbers = Arrays.asList(5, 7, 11,13);

        // Creating a list of Odd Numbers
        List<Integer> oddNumbers = Arrays.asList(1, 3, 5);

        // Creating a list of Even Numbers
        List<Integer> evenNumbers = Arrays.asList(2, 4, 6, 8);
        List<List<Integer>> doubleLists = Arrays.asList(primeNumbers, oddNumbers, evenNumbers);
        List<Integer> singleList = doubleLists.stream()
                .flatMap(list -> list.stream()).collect(Collectors.toList());

        //Intermediate stream operations are lazy
        courses.stream()
                .filter(course->course.length()>11)
                .peek(System.out::println).map(String::toUpperCase).peek(System.out::println).findFirst();

        //Modifying list replaceAll and removeIf

       List<String> modifyableCourses = new ArrayList<>(courses);

        modifyableCourses.replaceAll( str -> str.toUpperCase());

        modifyableCourses.removeIf(course ->course.length()<6);
    }

    private static Predicate<CoursesDto> getCourseDtoPredicate() {
        return courseDto -> courseDto.getReviewScore() > 50;
    }


    private static Predicate<Integer> getIntegerPredicate() {
        return x -> x % 2 == 0;
    }


    private static void print(int num) {
        System.out.println(num);
    }

    private static int sum(int a, int b) {
        return a+b;
    }


    public InstructorDto getOneToMany(int id) {
        Optional<Instructor> instructorOptional = instructorRepository.findById(id);
        Instructor instructor = null;
        if(instructorOptional.isPresent()) {
            instructor =  instructorOptional.get();
        }
        InstructorDto instructorDto = new InstructorDto();
        BeanUtils.copyProperties(instructor, instructorDto);

        List<CoursesDto> courseDtoList = instructor.getCourses().stream().map(course -> {
            CoursesDto coursesDto = new CoursesDto();
            BeanUtils.copyProperties(course, coursesDto);
            return coursesDto;
        }).collect(Collectors.toList());
        instructorDto.setCourseDtoList(courseDtoList);
        return instructorDto;
    }

    public List<String> getNestedData() {
     return    instructorRepository.getNestedQuery();
    }

    public InstructorView getViewData(String name) {
        return  instructorViewRepository.findByLastName(name);
//        System.out.println(obj.getLastName());

    }

    public List<Instructor> getProcedure() {
        return  instructorRepository.getProceduredata();
//        System.out.println(obj.getLastName());
    }

    public  List<Instructor>  pagination(String name,Long page, Integer rpp) {
        Pageable paging;
        if (page == 0 || rpp == 0) {
            paging = Pageable.unpaged();
        }
        else {
            paging = PageRequest.of(Math.toIntExact(page > 0 ? page - 1 : 0), rpp);
        }

        return instructorRepository.findAllByLastName(name, paging);
    }



}
