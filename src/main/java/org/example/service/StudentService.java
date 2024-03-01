package org.example.service;

import org.example.model.Course;
import org.example.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private final String STUDENT_NOT_FOUND_EXCEPTION_TEMPLATE = "Not found student with studentId = %s";
    private final String COURSE_NOT_FOUND_EXCEPTION_TEMPLATE = "Not found course with courseId = %s for studentId = %s";

    private Map<Student, List<Course>> data = new HashMap<>(Map.of(
            Student.builder().id("student1").build(), new ArrayList<>(List.of(
                    Course.builder().id("course1").name("JUnit course").steps(List.of("step1", "step2", "step3")).build(),
                    Course.builder().id("course2").name("Mockito course").steps(List.of("step1", "step2")).build()
            )),
            Student.builder().id("student2").build(), new ArrayList<>(List.of(
                    Course.builder().id("course1").name("JUnit course").steps(List.of("step1", "step2", "step3")).build()
            ))
    ));

    public List<Course> retrieveCourses(String studentId) {
        return Optional.ofNullable(data.get(Student.builder().id(studentId).build()))
                .orElseThrow(() -> new RuntimeException(String.format(STUDENT_NOT_FOUND_EXCEPTION_TEMPLATE, studentId)));
    }

    public Course retrieveCourse(String studentId, String courseId) {
        return retrieveCourses(studentId).stream()
                .filter(course -> Objects.equals(courseId, course.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format(COURSE_NOT_FOUND_EXCEPTION_TEMPLATE, courseId, studentId)));
    }

    public Course addCourse(String studentId, Course newCourse) {
        List<Course> courses = Optional.ofNullable(retrieveCourses(studentId))
                        .orElse(new ArrayList<>());
        Random rnd = new Random();
        newCourse.setId("course-" + rnd.nextLong());
        courses.add(newCourse);
        data.put(Student.builder().id(studentId).build(), courses);
        return newCourse;
    }

}
