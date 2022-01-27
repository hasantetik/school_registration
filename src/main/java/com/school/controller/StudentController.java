package com.school.controller;

import com.school.entity.Student;
import com.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/students",
        produces= MediaType.APPLICATION_JSON_VALUE,
        consumes=MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveStudent(@RequestBody Student student){
        return ResponseEntity.status(HttpStatus.OK)
                .body("created student : " + studentService.createStudent(student));
    }

    @PutMapping("/update/{studentId}")
     public ResponseEntity<String> updateStudent(@RequestBody Student student,@PathVariable(value = "studentId") Long studentId){
        return ResponseEntity.status(HttpStatus.OK)
                .body("updated student : " + studentService.updateStudent(student,studentId));
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable(value = "studentId") Long studentId){
        return ResponseEntity.status(HttpStatus.OK)
                .body("deleted student : " + studentService.deleteStudent(studentId));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<String> getStudent(@PathVariable(value = "studentId") Long userId){
        return ResponseEntity.status(HttpStatus.OK)
                .body("student : " + studentService.getStudent(userId));
    }

}
