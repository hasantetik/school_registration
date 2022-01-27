package com.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.dto.CourseRegistrationDto;
import com.school.entity.CourseRegistration;
import com.school.repository.CourseRegistrationRepository;
import com.school.service.CourseRegistrationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CourseRegistrationController.class)
public class CourseRegistrationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CourseRegistrationRepository courseRegistrationRepository;

    @MockBean
    CourseRegistrationService courseRegistrationService;

    CourseRegistrationDto RECORD_1 = new CourseRegistrationDto(1L,1L);
    CourseRegistrationDto RECORD_2 = new CourseRegistrationDto(2L,2L);

    @Test
    public void registerCourse() throws Exception{
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/registerCourse")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(RECORD_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void getStudentByCours() throws Exception{
        List<String> cours = new ArrayList<>();
        cours.add("java");
        cours.add("c++");
        Mockito.when(courseRegistrationService.getStudentByCours(1L)).thenReturn(cours);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/getStudentByCours/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test void getCoursByStudent() throws Exception{
        List<String> students = new ArrayList<>();
        students.add("hasan tetik");
        students.add("ali veli");
        Mockito.when(courseRegistrationService.getCoursByStudent(1L)).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/getCoursByStudent/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getNonStudentCourses() throws Exception {
        List<String> cours = new ArrayList<>();
        cours.add("java");
        cours.add("c++");
        Mockito.when(courseRegistrationService.getNonStudentCourses()).thenReturn(cours);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/getNonStudentCourses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getNonCoursesStudents() throws Exception{
        List<String> students = new ArrayList<>();
        students.add("hasan tetik");
        students.add("ali veli");
        Mockito.when(courseRegistrationService.getNonCoursesStudents()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/getNonCoursesStudents")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
