package com.antra.hw3.controllers;

import com.antra.hw3.models.Teacher;
import com.antra.hw3.services.TeacherServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherServiceInterface teacherService;

    @Autowired
    public TeacherController(TeacherServiceInterface teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        return new ResponseEntity<>(teacherService.saveTeacher(teacher), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacher(id));
    }

    @PostMapping("/{teacherId}/students/{studentId}")
    public ResponseEntity<String> addStudentToTeacher(@PathVariable Long teacherId, @PathVariable Long studentId) {
        return ResponseEntity.ok(teacherService.addStudentToTeacher(teacherId, studentId));
    }
}
