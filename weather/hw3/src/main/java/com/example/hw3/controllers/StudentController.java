package com.example.hw3.controllers;

import com.example.hw3.models.Student;
import com.example.hw3.services.StudentServiceInterface;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Value("${server.port}")
    private int randomServerPort;

    private final StudentServiceInterface studentService;

    @Autowired
    public StudentController(StudentServiceInterface studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    public ResponseEntity<Student> getStudentFallback(Long id) {
        return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "getStudentFallback")
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

    @GetMapping("/details/port")
    public ResponseEntity<?> getDetailsPort() {
        return new ResponseEntity<>("hw3 service + " + randomServerPort, HttpStatus.OK);
    }
}
