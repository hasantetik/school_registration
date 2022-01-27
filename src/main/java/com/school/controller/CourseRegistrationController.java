package com.school.controller;

import com.school.dto.CourseRegistrationDto;
import com.school.entity.CourseRegistration;
import com.school.service.CourseRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CourseRegistrationController {

    private final CourseRegistrationService courseRegistrationService;

    public CourseRegistrationController(CourseRegistrationService courseRegistrationService) {
        this.courseRegistrationService = courseRegistrationService;
    }

    @PostMapping("/registerCourse")
    public ResponseEntity<String> registerCourse(@RequestBody CourseRegistrationDto courseRegistrationDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(""+courseRegistrationService.registerCourse(courseRegistrationDto));
    }

    @GetMapping("/getStudentByCours/{coursId}")
    public ResponseEntity<String> getStudentByCours(@PathVariable(value = "coursId") Long courseId){
        return ResponseEntity.status(HttpStatus.OK)
                .body("Students : "+courseRegistrationService.getStudentByCours(courseId));
    }

    @GetMapping("/getCoursByStudent/{studentId}")
    public ResponseEntity<String> getCoursByStudent(@PathVariable(value = "studentId") Long studentId){
        return ResponseEntity.status(HttpStatus.OK)
                .body("Courses : " +courseRegistrationService.getCoursByStudent(studentId));
    }

    @GetMapping("/getNonStudentCourses")
    public ResponseEntity<String> getNonStudentCourses(){
        return ResponseEntity.status(HttpStatus.OK)
                .body("getNonStudentCourses :"+courseRegistrationService.getNonStudentCourses());
    }

    @GetMapping("/getNonCoursesStudents")
    public ResponseEntity<String> getNonCoursesStudents(){
        return ResponseEntity.status(HttpStatus.OK)
                .body("getNonCourseStudents : "+ courseRegistrationService.getNonCoursesStudents());
    }
}
