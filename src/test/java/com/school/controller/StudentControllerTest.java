package com.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.entity.Cours;
import com.school.entity.Student;
import com.school.repository.StudentRepositroy;
import com.school.service.StudentService;
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

@WebMvcTest(StudentController.class)
@ExtendWith(SpringExtension.class)
public class StudentControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    StudentRepositroy studentRepositroy;

    @MockBean
    StudentService studentService;

    Student RECORD_1 = new Student(1L,"hasan","tetik",2626);
    Student RECORD_2 = new Student(2L,"ali","veli",4950);

    @Test
    public void saveStudent() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/students/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(RECORD_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void updateStudent() throws Exception{
        Mockito.when(studentService.updateStudent(RECORD_2,2L)).thenReturn(RECORD_2);

        mockMvc.perform(MockMvcRequestBuilders.put("/students/update/2")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(RECORD_2))
                ).andExpect(status().isOk())
                .andExpect(content().string("updated student : "+RECORD_2))
                .andDo(print());

    }

    @Test
    public void deleteStudent() throws Exception{
        Mockito.when(studentService.deleteStudent(2L)).thenReturn(RECORD_2);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/students/delete/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("deleted student : "+RECORD_2))
                .andDo(print());
    }

    @Test
    public void getStudent() throws Exception {
        Mockito.when(studentService.getStudent(1L)).thenReturn(RECORD_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
