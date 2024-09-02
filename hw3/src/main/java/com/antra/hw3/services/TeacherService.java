package com.antra.hw3.services;

import com.antra.hw3.models.Student;
import com.antra.hw3.models.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional // (same as StudentService) it was giving error with table not found without Transactional.
public class TeacherService implements TeacherServiceInterface {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public TeacherService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Teacher saveTeacher(Teacher teacher) {
        entityManager.persist(teacher);
        return teacher;
    }

    public Teacher getTeacher(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    public String addStudentToTeacher(Long teacherId, Long studentId) {
        Teacher teacher = entityManager.find(Teacher.class, teacherId);
        Student student = entityManager.find(Student.class, studentId);

        try {
            if (teacher != null && student != null) {
                teacher.getStudents().add(student);
                student.getTeachers().add(teacher);
                entityManager.merge(teacher);
                entityManager.merge(student);
                return "Success";
            }
        }
        catch (Exception e) {
            return "Failed";
        }
        return "Failed";
    }
}

