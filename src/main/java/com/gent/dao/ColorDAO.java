package com.gent.dao;

import com.gent.model.Color;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gent.util.Constants.RU_LANG;
import static com.gent.util.Constants.UK_LANG;

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

    @Override
    public int getColoryByName(String lang, String text) {
        int id = 0;

        if (lang.equals(UK_LANG))
            id = (Integer) sessionFactory.getCurrentSession().createQuery("select id from Color where uaText LIKE :text")
                    .setParameter("text", text)
                    .uniqueResult();
        else if (lang.equals(RU_LANG))
            id = (Integer) sessionFactory.getCurrentSession().createQuery("select id from Color where ruText LIKE :text")
                    .setParameter("text", text)
                    .uniqueResult();
        return id;
    }

}
