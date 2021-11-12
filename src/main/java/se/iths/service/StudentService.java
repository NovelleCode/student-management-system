package se.iths.service;

import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager em;

    public Student createStudent(Student student) {
        em.persist(student);
        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> allStudents = em.createQuery("select s from Student s", Student.class).getResultList();
        if (allStudents.isEmpty())
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        return allStudents;
    }

    public Student updateStudent(Student student) {
        return em.merge(student);
    }
}
