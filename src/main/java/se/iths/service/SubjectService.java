package se.iths.service;

import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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
        return em.find(Subject.class, id);
    }
}
