package com.gent.dao;

import com.gent.model.Color;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by daria on 05.10.2016.
 */
@Transactional
@Repository
public class ColorDAO implements IColorDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @SuppressWarnings("unchecked")
    @Override
    public List<Color> getAllColor() {

        return sessionFactory.getCurrentSession().createQuery("from Color order by uaText").list();
    }

    @Override
    public Color getColorById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery(" from Color  where id = :id");
        query.setParameter("id", id);
        Color col = (Color) query.uniqueResult();
        return col;
    }

    @Override
    public boolean addColor(Color text) {
        sessionFactory.getCurrentSession().save(text);
        return true;
    }

    @Override
    public void updateColor(Color col) {

        Color color = new Color();
        color.setId(col.getId());
        color.setUaText(col.getUaText());
        color.setRuText(col.getRuText());
        sessionFactory.getCurrentSession().update(color);
    }

    @Override
    public void deleteColor(int id) {
        sessionFactory.getCurrentSession().createQuery("delete from Color  where id = :id")
                .setParameter("id", id).executeUpdate();
    }



}
