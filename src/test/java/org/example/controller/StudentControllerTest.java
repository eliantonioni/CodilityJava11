package org.example.controller;

import java.util.List;

import org.example.model.Course;
import org.example.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void retrieveDetailsForCourse() throws Exception {
        Course mockCourse = Course.builder()
                .id("course1")
                .name("JUnit course")
                .steps(List.of("step1", "step2", "step3"))
                .build();
        Mockito.when(studentService.retrieveCourse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(mockCourse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/students/student1/courses/course1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedCourseJson = "{\"id\":\"course1\",\"name\":\"JUnit course\",\"steps\":[\"step1\",\"step2\",\"step3\"]}";
        JSONAssert.assertEquals(expectedCourseJson, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void createStudentCourse() throws Exception {
        Course mockCourse = Course.builder()
                .id("1")
                .name("AWS course")
                .steps(List.of("step1", "step2", "step3"))
                .build();
        Mockito.when(studentService.addCourse(Mockito.anyString(), Mockito.any(Course.class)))
                .thenReturn(mockCourse);

        String exampleCourseJson = "{\"name\":\"AWS course\",\"steps\":[\"step1\",\"step2\",\"step3\"]}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/students/student1/courses")
                .accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("http://localhost/students/student1/courses/1", response.getHeader(HttpHeaders.LOCATION));
    }

}
