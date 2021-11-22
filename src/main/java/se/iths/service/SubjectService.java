package se.iths.service;

import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager em;

    public Subject createSubject(Subject subject) {
        em.persist(subject);
        return subject;
    }

    public Subject findSubjectById(Long id) {
        return em.find(Subject.class, id);
    }
}
