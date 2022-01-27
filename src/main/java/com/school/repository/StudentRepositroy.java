package com.school.repository;

import com.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositroy extends JpaRepository<Student,Long> {
}
