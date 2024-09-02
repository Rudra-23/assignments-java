package com.antra.hw3.services;

import com.antra.hw3.models.Teacher;

public interface TeacherServiceInterface {
    Teacher saveTeacher(Teacher teacher);
    Teacher getTeacher(Long id);
    String addStudentToTeacher(Long teacherId, Long studentId);
}
