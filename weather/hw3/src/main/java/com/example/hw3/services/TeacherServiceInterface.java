package com.example.hw3.services;

import com.example.hw3.models.Teacher;

public interface TeacherServiceInterface {
    Teacher saveTeacher(Teacher teacher);
    Teacher getTeacher(Long id);
    String addStudentToTeacher(Long teacherId, Long studentId);
}
