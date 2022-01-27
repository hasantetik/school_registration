package com.school.service;

import com.school.dto.CourseRegistrationDto;
import com.school.entity.CourseRegistration;

import java.util.List;

public interface CourseRegistrationService {

    List<String> getStudentByCours(Long courseId);

    List<String> getCoursByStudent(Long studentId);

    List<String> getNonStudentCourses();

    List<String> getNonCoursesStudents();

    String registerCourse(CourseRegistrationDto courseRegistrationDto);

}
