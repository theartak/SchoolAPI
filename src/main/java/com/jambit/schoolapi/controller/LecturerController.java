package com.jambit.schoolapi.controller;

import com.jambit.schoolapi.model.Lecturer;
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
@RequestMapping("/lecturers")
public class LecturerController {
    private final SchoolService schoolService;

    @Autowired
    public LecturerController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public List<Lecturer> getLecturers() {
        return schoolService.getLecturers();
    }

    @PostMapping
    public void createLecturer(@RequestBody Lecturer lecturer) {
        schoolService.addLecturer(lecturer);
    }

    @DeleteMapping(path = "{lecturerId}")
    public void deleteLecturer(@PathVariable("lecturerId") Long lecturerId) {
        schoolService.deleteStudent(lecturerId);
    }

    @PutMapping(path = "{lecturerId}")
    public void updateLecturer(@PathVariable("lecturerId") Long lecturerId,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String email) {
        schoolService.updateLecturer(lecturerId, name, email);
    }
}