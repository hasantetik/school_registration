package com.school.service.Imp;


import com.school.dto.CourseRegistrationDto;
import com.school.entity.CourseRegistration;
import com.school.exception.CourseRegistrationException;
import com.school.repository.CourseRegistrationRepository;
import com.school.repository.CoursesRepository;
import com.school.repository.StudentRepositroy;
import com.school.service.CourseRegistrationService;
import com.school.service.CoursesService;
import com.school.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseRegistrationImp implements CourseRegistrationService {

    private final CoursesService coursesService;

    private final CourseRegistrationRepository courseRegistrationRepository;

    private final StudentService studentService;

    public CourseRegistrationImp(CoursesService coursesService,CourseRegistrationRepository courseRegistrationRepository, StudentService studentService) {
        this.coursesService = coursesService;
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.studentService = studentService;
    }

    @Override
    public List<String> getStudentByCours(Long courseId) {
        return courseRegistrationRepository.getStudentByCours(courseId);
    }

    @Override
    public List<String> getCoursByStudent(Long studentId) {
        return courseRegistrationRepository.getCoursByStudent(studentId);
    }

    @Override
    public List<String> getNonStudentCourses() {
        List<String> removeList =courseRegistrationRepository.getCoursCount();
        List<String> courses = courseRegistrationRepository.getNonStudentCourses();

        for(int i =0;i<removeList.size();i++){
            courses.remove(removeList.get(i));
        }
        return courses;
    }

    @Override
    public List<String> getNonCoursesStudents() {
        List<String> removeList = courseRegistrationRepository.getStudentCount();
        List<String> students = courseRegistrationRepository.getNonCoursesStudents();

        for (int i = 0;i<removeList.size();i++){
            students.remove(removeList.get(i));
        }
        return students;
    }

    @Override
    public String registerCourse(CourseRegistrationDto courseRegistrationDto) {
        registerCourseValidate(courseRegistrationDto);
        CourseRegistration existingCourseRegistration = new CourseRegistration();
        existingCourseRegistration.setCours(coursesService.getCours(courseRegistrationDto.getCourseId()));
        existingCourseRegistration.setStudent(studentService.getStudent(courseRegistrationDto.getStudentId()));

        return ""+courseRegistrationRepository.save(existingCourseRegistration);
    }

    public Long countCourseByCourseId(Long courseId){
        Long count = courseRegistrationRepository.countCourseByCourseId(courseId);
        return count;
    }

    public Long countStudentByStudentId(Long studentId){
        Long count = courseRegistrationRepository.countStudentByStudentId(studentId);
        return count;
    }

    public String registerCourseValidate(CourseRegistrationDto courseRegistrationDto){
        if(countCourseByCourseId(courseRegistrationDto.getCourseId())>=50){
            throw new CourseRegistrationException("course is full.");
        }
        else if(countStudentByStudentId(courseRegistrationDto.getStudentId())>=5){
            throw new CourseRegistrationException("Your right to take the course has expired");
        }
        return "";
    }
}
