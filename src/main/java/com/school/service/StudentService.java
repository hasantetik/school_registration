package com.school.service;

import com.school.entity.Student;

public interface StudentService {

    Student createStudent(Student student);

    Student updateStudent(Student student,Long studentId);

    Student deleteStudent(Long studentId);

    Student getStudent(Long studentId);
}
