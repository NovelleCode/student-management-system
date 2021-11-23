package se.iths.service;

import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
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
        return em.createQuery("select t from Teacher t", Teacher.class).getResultList();
    }

    public Teacher findTeacherById(Long id) {
        Teacher foundTeacher = em.find(Teacher.class, id);
        if (foundTeacher == null)
            throw new WebApplicationException("Subject not found", Response.Status.NOT_FOUND);
        return foundTeacher;
    }
}
