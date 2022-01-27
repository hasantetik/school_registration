package com.school.service;

import com.school.entity.Cours;

public interface CourseService {

     Cours createCours(Cours save);

     Cours updateCours(Cours cours, Long coursesId);

     Cours deleteCours(Long coursesId);

     Cours getCours(Long coursesId);

}
