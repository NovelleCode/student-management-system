package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.exception.ResourceNotFoundException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager em;

    @Inject
    StudentService studentService;
    @Inject
    TeacherService teacherService;

    public Subject createSubject(Subject subject) {
        em.persist(subject);
        return subject;
    }

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = em.createQuery("select s from Subject s", Subject.class).getResultList();
        if (subjects.isEmpty())
            throw new ResourceNotFoundException(Response.Status.OK, "No subjects seem to be registered yet.");
        return subjects;
    }

    public Subject findSubjectById(Long id) {
        Subject foundSubject = em.find(Subject.class, id);
        if (foundSubject == null)
            throw new ResourceNotFoundException(Response.Status.NOT_FOUND, "No subject found with id: " + id);
        return foundSubject;
    }

    public Subject addStudentToSubject(Long subjectId, Long studentId) {
        Subject foundSubject = findSubjectById(subjectId);
        Student foundStudent = studentService.findStudentById(studentId);

        foundSubject.addStudent(foundStudent);

        em.persist(foundSubject);
        return foundSubject;
    }

    public Subject addTeacherToSubject(Long subjectId, Long teacherId) {
        Subject foundSubject = findSubjectById(subjectId);
        Teacher foundTeacher = teacherService.findTeacherById(teacherId);

        foundSubject.addTeacher(foundTeacher);

        em.persist(foundSubject);
        return foundSubject;
    }
}
