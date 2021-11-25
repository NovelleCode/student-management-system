package se.iths.service;

import se.iths.entity.Teacher;
import se.iths.exception.ResourceNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager em;

    public Teacher createTeacher(Teacher teacher) {
        em.persist(teacher);
        return teacher;
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = em.createQuery("select t from Teacher t", Teacher.class).getResultList();
        if(teachers.isEmpty())
            throw new ResourceNotFoundException(Response.Status.OK, "No teachers seem to be registered yet.");
        return teachers;
    }

    public Teacher findTeacherById(Long id) {
        Teacher foundTeacher = em.find(Teacher.class, id);
        if (foundTeacher == null)
            throw new ResourceNotFoundException(Response.Status.NOT_FOUND, "No teacher found with id: " + id);
        return foundTeacher;
    }
}
