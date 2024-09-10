package com.example.hw3.services;

import com.example.hw3.models.Student;

import java.util.List;

public interface StudentServiceInterface {
    Student saveStudent(Student student);
    Student getStudent(Long id);
    List<Student> getStudentsWithCriteria();
    String addTeacherToStudent(Long studentId, Long teacherId);
}
