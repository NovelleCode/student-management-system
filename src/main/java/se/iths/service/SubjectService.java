package se.iths.service;

import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager em;

    public Subject createSubject(Subject subject) {
        em.persist(subject);
        return subject;
    }

    public List<Subject> getAllSubjects() {
         return em.createQuery("select s from Subject s", Subject.class).getResultList();
    }

    public Subject findSubjectById(Long id) {
        Subject foundSubject = em.find(Subject.class, id);
        if(foundSubject == null)
            throw new WebApplicationException("Subject not found", Response.Status.NOT_FOUND);
        return foundSubject;
    }
}
