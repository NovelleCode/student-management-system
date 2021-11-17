package se.iths.service;

import se.iths.entity.Student;
import se.iths.exception.StudentNotFoundException;

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
        if(studentAlreadyExists(student))
            throw new WebApplicationException(Response.Status.CONFLICT);
        em.persist(student);
        return student;
    }

    public Student findStudentById(Long id) {
        Student foundStudent = em.find(Student.class, id);
        if (foundStudent == null)
            throw new StudentNotFoundException("Student with id: " + id + ", is not found.");
        return foundStudent;
    }

    public List<Student> getAllStudents() {
        List<Student> allStudents = em.createQuery("select s from Student s", Student.class).getResultList();
        if (allStudents.isEmpty())
            throw new StudentNotFoundException("No students in database yet.");
        return allStudents;
    }

    public List<Student> findAllStudentsByLastName(String lastName) {
        List<Student> foundStudents = em
                .createQuery("select s from Student s where s.lastName = ?1", Student.class)
                .setParameter(1, lastName)
                .getResultList();

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

    public Student updateStudentEmail(Long id, String email) {
        Student foundStudent = findStudentById(id);
        foundStudent.setEmail(email);
        return foundStudent;
    }

    private boolean studentAlreadyExists(Student student) {
        List<Student> studentExists = em
                .createQuery("select s from Student s where s.firstName = ?1 and s.lastName = ?2 and s.email = ?3", Student.class)
                .setParameter(1, student.getFirstName()).setParameter(2, student.getLastName()).setParameter(3, student.getEmail())
                .getResultList();
        return !studentExists.isEmpty();
    }
}
