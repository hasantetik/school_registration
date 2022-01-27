package com.school.service.Imp;

import com.school.entity.Student;
import com.school.exception.ConstraintViolationException;
import com.school.exception.EntityNotFoundException;
import com.school.repository.StudentRepositroy;
import com.school.service.StudentService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


@Service
public class StudentServiceImp implements StudentService {

    private final StudentRepositroy studentRepositroy;

    public StudentServiceImp(StudentRepositroy studentRepositroy) {
        this.studentRepositroy = studentRepositroy;
    }

    @Override
    public Student createStudent(Student student) {
      try {
         return studentRepositroy.save(student);
      }catch (DataIntegrityViolationException exception){
          throw new com.school.exception.DataIntegrityViolationException("number already exist");
      }catch (Exception exception){
          throw new RuntimeException();
      }
    }

    @Override
    public Student updateStudent(Student student, Long studentId) {
        Student existingStudent = studentRepositroy.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student Entity not found"));
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setNumber(student.getNumber());
        return studentRepositroy.saveAndFlush(existingStudent);
    }

    @Override
    public Student deleteStudent(Long studentId) {
        Student existingStudent = studentRepositroy.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student Entity not found"));
        studentRepositroy.delete(existingStudent);
        return existingStudent;
    }

    @Override
    public Student getStudent(Long studentId) {
        return studentRepositroy.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student Entity not found"));
    }
}
