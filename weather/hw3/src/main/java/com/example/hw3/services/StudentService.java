package com.example.hw3.services;

import com.example.hw3.models.Student;
import com.example.hw3.models.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional // it was giving error of table not found without Transactional.
public class StudentService implements StudentServiceInterface {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public StudentService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Student saveStudent(Student student) {
        entityManager.persist(student);
        return student;
    }

    public Student getStudent(Long id) {
        return entityManager.find(Student.class, id);
    }


    public List<Student> getStudentsWithCriteria() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);

        Root<Student> student = query.from(Student.class);
        Join<Student, Teacher> teacher = student.join("teachers");

        int number = 10;
        query.select(student).where(cb.gt(student.get("id"), number));

        return entityManager.createQuery(query).getResultList();
    }

    public String addTeacherToStudent(Long studentId, Long teacherId) {
        Student student = entityManager.find(Student.class, studentId);
        Teacher teacher = entityManager.find(Teacher.class, teacherId);

        try {
            if (student != null && teacher != null) {
                student.getTeachers().add(teacher);
                teacher.getStudents().add(student);

                entityManager.merge(student);
                entityManager.merge(teacher);
                return "Success";
            }

        }
        catch (Exception e) {
            return "Failed";
        }

        return "Failed";
    }
}

