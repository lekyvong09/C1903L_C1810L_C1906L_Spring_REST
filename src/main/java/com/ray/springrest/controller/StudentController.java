package com.ray.springrest.controller;

import com.ray.springrest.entity.Student;
import com.ray.springrest.exception.StudentNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private List<Student> studentList;

    @PostConstruct
    public void LoadData() {
        studentList = new ArrayList<>();
        studentList.add(new Student("Ray", "Le"));
        studentList.add(new Student("Tommy", "Le"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentList;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // handle exception
        if (studentId >= studentList.size() || studentId < 0)
            throw new StudentNotFoundException("Student not found - " + studentId);

        // Note: studentId is the index of the list
        return studentList.get(studentId);
    }



}
