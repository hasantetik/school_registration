package com.school.service.Imp;

import com.school.entity.Cours;
import com.school.exception.EntityNotFoundException;
import com.school.repository.CoursesRepository;
import com.school.service.CourseService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImp implements CourseService {

    private final CoursesRepository coursesRepository;

    public CourseServiceImp(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    @Override
    public Cours createCours(Cours cours) {
        try {
            return coursesRepository.save(cours);
        }catch (DataIntegrityViolationException exception){
            throw new com.school.exception.DataIntegrityViolationException("cours already exist");
        }catch (Exception exception){
            throw new RuntimeException();
        }
    }

    @Override
    public Cours updateCours(Cours cours, Long coursesId) {
        Cours existingCours = coursesRepository.findById(coursesId)
                .orElseThrow(() -> new EntityNotFoundException("Course Entity not found"));
        existingCours.setCoursName(cours.getCoursName());
        coursesRepository.saveAndFlush(existingCours);
        return null;
    }

    @Override
    public Cours deleteCours(Long coursesId) {
        Cours existingCours = coursesRepository.findById(coursesId)
                .orElseThrow(() -> new EntityNotFoundException("Course Entity not found"));
        coursesRepository.delete(existingCours);
        return existingCours;
    }

    @Override
    public Cours getCours(Long coursesId) {
        return coursesRepository.findById(coursesId)
                .orElseThrow(() -> new EntityNotFoundException("Course Entity not found"));
    }
}
