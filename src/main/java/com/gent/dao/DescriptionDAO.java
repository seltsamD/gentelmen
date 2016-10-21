package com.gent.dao;

import com.gent.model.Description;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by daria on 10.10.2016.
 */
@Transactional
@Repository
public class DescriptionDAO implements IDescriptionDAO{
    @Autowired
    private SessionFactory sessionFactory;


    @SuppressWarnings("unchecked")
    @Override
    public List<Description> getAllDescription() {

        return sessionFactory.getCurrentSession().createQuery("from Description order by uaText").list();
    }

    @Override
    public Description getDescriptionById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery(" from Description  where id = :id");
        query.setParameter("id", id);
        Description desc = (Description) query.uniqueResult();
        return desc;
    }

    @Override
    public boolean addDescription(Description text) {
        sessionFactory.getCurrentSession().save(text);
        return true;
    }

    @Override
    public void updateDescription(Description desc) {

        Description color = new Description();
        color.setId(desc.getId());
        color.setUaText(desc.getUaText());
        color.setRuText(desc.getRuText());
        sessionFactory.getCurrentSession().update(color);
    }

    @Override
    public void deleteDescription(int id) {
        sessionFactory.getCurrentSession().createQuery("delete from Description  where id = :id")
                .setParameter("id", id).executeUpdate();
    }

}
