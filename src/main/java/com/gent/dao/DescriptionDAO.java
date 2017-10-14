package com.gent.dao;

import com.gent.model.Description;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by daria on 28.09.2017.
 */
@Transactional
@Repository
public class DescriptionDAO implements IDescriptionDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Description findById(int id) {
        return (Description) sessionFactory.getCurrentSession().createQuery("FROM Description as p where p.id = :id").setParameter("id", id).uniqueResult();

    }

    @Override
    public void update(Description input) {
//        Description description = findById(input.getId());
//        description.setRuText(input.getRuText());
//        description.setUaText(input.getUaText());
        sessionFactory.getCurrentSession().merge(input);
    }
}
