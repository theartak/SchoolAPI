package com.jambit.schoolapi.controller;

import com.jambit.schoolapi.model.Student;
import com.jambit.schoolapi.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final SchoolService schoolService;

    @Autowired
    public StudentController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return schoolService.getStudents();
    }

    @PostMapping
    public void createStudent(@RequestBody Student student) {
        schoolService.addStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        schoolService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        schoolService.updateStudent(studentId, name, email);
    }
}