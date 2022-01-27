package com.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.entity.Cours;
import com.school.repository.CoursesRepository;
import com.school.service.CourseService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    CoursesRepository coursesRepository;

    @MockBean
    CourseService coursesService;

    Cours RECORD_1 = new Cours(1L,"java");

    @Test
    public void getCours() throws Exception {
        Mockito.when(coursesService.getCours(1L)).thenReturn(RECORD_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cours/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createCourse() throws Exception {
        Mockito.when(coursesRepository.save(RECORD_1)).thenReturn(RECORD_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/cours/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(RECORD_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void updateCours() throws Exception {
        Cours existCours = new Cours(1L,"c++");
        Mockito.when(coursesService.updateCours(existCours,1L)).thenReturn(existCours);

        mockMvc.perform(MockMvcRequestBuilders.put("/cours/update/1")
               .contentType("application/json")
                .content(objectMapper.writeValueAsString(existCours))
        ).andExpect(status().isOk())
                .andExpect(content().string("updated cours : "+existCours))
                .andDo(print());

    }

    @Test
    public void deleteCours() throws Exception{
        Cours RECORD_2 = new Cours(2L,"c");

        Mockito.when(coursesService.deleteCours(2L)).thenReturn(RECORD_2);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/cours/delete/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("deleted cours : "+RECORD_2))
                .andDo(print());
    }

}
