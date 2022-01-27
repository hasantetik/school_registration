package com.school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_course_registration")
@Table(name = "tbl_course_registration")
public class CourseRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseRegistrationId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "studentId"
    )
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "coursId"
    )
    private Cours cours;

}
