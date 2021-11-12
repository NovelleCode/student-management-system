package se.iths.service;

import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    public Student findStudentById(Long id) {
        Student foundStudent = em.find(Student.class, id);
        if (foundStudent == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        return foundStudent;
    }

    public List<Student> getAllStudents() {
        List<Student> allStudents = em.createQuery("select s from Student s", Student.class).getResultList();
        if (allStudents.isEmpty())
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        return allStudents;
    }

    public List<Student> findAllStudentsByLastName(String lastName) {
        TypedQuery<Student> query = em.createQuery("select s from Student s where s.lastName = ?1", Student.class);
        List<Student> foundStudents = query.setParameter(1, lastName).getResultList();
        if(foundStudents.isEmpty())
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        return foundStudents;
    }

    public Student updateStudent(Student student) {
        return em.merge(student);
    }

    public void deleteStudent(Long id) {
        Student foundStudent = findStudentById(id);
        em.remove(foundStudent);
    }
}
