package com.school.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.entity.Cours;
import com.school.repository.CoursesRepository;
import com.school.service.CoursesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CoursesController.class)
@ContextConfiguration(classes = {CoursesService.class})
public class CourseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    CoursesRepository coursesRepository;

    Cours RECORD_1 = new Cours(1L,"java");
    Cours RECORD_2 = new Cours(2L,"c");

    @Test
    public void getCoursById_success() throws Exception {

        coursesRepository.save(RECORD_1);

        Mockito.when(coursesRepository.findById(1L)).thenReturn(Optional.of(RECORD_1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cours/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCourse() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/cours/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(RECORD_1));

        Mockito.when(coursesRepository.save(RECORD_1)).thenReturn(RECORD_1);

        mockMvc.perform(mockRequest).andExpect(status().is4xxClientError());
    }
}
