package com.school.repository;

import com.school.entity.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration,Long> {

    @Query(
           value = "select count(*) FROM test.tbl_course_registration s where s.course_id=?1",
           nativeQuery = true
    )
    Long countCourseByCourseId(Long course_id);

    @Query(
            value = "select count(*) FROM test.tbl_course_registration s where s.student_id=?1",
            nativeQuery = true
    )
    Long countStudentByStudentId(Long student_id);

    @Query(
            value = "select DISTINCT c.cours_name FROM test.tbl_course_registration s LEFT JOIN test.tbl_course c ON s.course_id=c.cours_id where s.student_id=?1",
            nativeQuery = true
    )
    List<String> getCoursByStudent(Long student_id);

    @Query(
            value = "select DISTINCT CONCAT(c.first_name,' ',c.last_name) FROM test.tbl_course_registration s LEFT JOIN test.tbl_student c ON s.student_id=c.student_id where s.course_id=?1",
            nativeQuery = true
    )
    List<String> getStudentByCours(Long courseId);

    @Query(
            value = "select DISTINCT a.cours_name FROM test.tbl_course a LEFT JOIN test.tbl_course_registration s ON a.cours_id=s.course_id",
            nativeQuery = true
    )
    List<String> getNonStudentCourses();

    @Query(
            value = "select DISTINCT CONCAT(a.first_name,' ',a.last_name) FROM test.tbl_course_registration s LEFT JOIN test.tbl_student a ON s.student_id=a.student_id",
            nativeQuery = true
    )
    List<String> getStudentCount();

    @Query(
            value = "select DISTINCT CONCAT(a.first_name,' ',a.last_name) FROM test.tbl_student a LEFT JOIN test.tbl_course_registration s ON a.student_id=s.student_id",
            nativeQuery = true
    )
    List<String> getNonCoursesStudents();

    @Query(
            value = "select DISTINCT a.cours_name FROM test.tbl_course_registration s LEFT JOIN test.tbl_course a ON s.course_id=a.cours_id",
            nativeQuery = true
    )
    List<String> getCoursCount();
}
