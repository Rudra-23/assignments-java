package com.antra.hw3.controllers;

import com.antra.hw3.models.Student;
import com.antra.hw3.services.StudentService;
import com.antra.hw3.services.StudentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentServiceInterface studentService;

    @Autowired
    public StudentController(StudentServiceInterface studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PostMapping("/{studentId}/teachers/{teacherId}")
    public ResponseEntity<String> addTeacherToStudent(@PathVariable Long studentId, @PathVariable Long teacherId) {
        return ResponseEntity.ok(studentService.addTeacherToStudent(studentId, teacherId));
    }

    @GetMapping("/criteria")
    public ResponseEntity<List<Student>> getStudent() {
        return ResponseEntity.ok(studentService.getStudentsWithCriteria());
    }

}
