package com.school.controller;

import com.school.entity.Cours;
import com.school.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/cours",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public class CourseController {

    private final CourseService coursesService;

    public CourseController(CourseService coursesService) {
        this.coursesService = coursesService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> createCours(@RequestBody Cours cours){
        return ResponseEntity.status(HttpStatus.OK).body("created cours : " + coursesService.createCours(cours));
    }

    @PutMapping("/update/{coursId}")
    public ResponseEntity<String> updateCours(@RequestBody Cours cours,@PathVariable(value = "coursId") Long coursId){
        return ResponseEntity.status(HttpStatus.OK).body("updated cours : "+coursesService.updateCours(cours,coursId));
    }

    @DeleteMapping("/delete/{coursId}")
    public ResponseEntity<String> deleteCours(@PathVariable(value = "coursId") Long coursId){
        return ResponseEntity.status(HttpStatus.OK).body("deleted cours : " + coursesService.deleteCours(coursId));
    }

    @GetMapping("/{coursId}")
    public ResponseEntity<Cours> getCours(@PathVariable(value = "coursId") Long coursId ){
        return ResponseEntity.status(HttpStatus.OK).body(coursesService.getCours(coursId));
    }
}
