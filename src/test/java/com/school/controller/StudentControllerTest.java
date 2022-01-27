package com.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.entity.Cours;
import com.school.entity.Student;
import com.school.repository.CoursesRepository;
import com.school.repository.StudentRepositroy;
import com.school.service.CoursesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CoursesController.class)
@ContextConfiguration(classes = {CoursesService.class})
public class StudentControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    StudentRepositroy studentRepositroy;

    Student RECORD_1 = new Student(1L,"hasan","tetik",2626);
    Student RECORD_2 = new Student(2L,"ali","veli",4950);

    @Test
    public void getStudentById_success() throws Exception {
        studentRepositroy.save(RECORD_1);
        Mockito.when(studentRepositroy.findById(1L)).thenReturn(Optional.of(RECORD_1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/students/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
