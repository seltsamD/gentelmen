package com.gent.dao;

import com.gent.model.Category;
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
public class CategoryDAO implements ICategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;



    @SuppressWarnings("unchecked")
    @Override
    public List<Category> getAllCategory() {

        return sessionFactory.getCurrentSession().createQuery("from Category o order by o.uaText").list();
    }

    @Override
    public Category getCategoryById(int id) {
        return (Category) sessionFactory.getCurrentSession().createQuery("from Category  where id = :id")
                .setParameter("id", id).uniqueResult();
    }

    @Override
    public boolean addCategory(Category category) {
       sessionFactory.getCurrentSession().save(category);
        return true;
    }

    @Override
    public void updateCategory(Category cat) {

        Category category = new Category();
        category.setId(cat.getId());
        category.setRuText(cat.getRuText());
        category.setUaText(cat.getUaText());
        category.setParent(cat.getParent());
        category.setLevel(cat.getLevel());
       sessionFactory.getCurrentSession().update(category);
    }

    @Override
    public void deleteCategory(int id) {
        sessionFactory.getCurrentSession().createQuery("delete from Category  where id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public List<Category> getFirstLevel() {
        return sessionFactory.getCurrentSession().createQuery("from Category where level = :lev")
                .setParameter("lev", 0)
                .list();
    }

    @Override
    public List<Category> getChild(int id) {
        return sessionFactory.getCurrentSession().createQuery("from Category where parent = :id")
                .setParameter("id", id)
                .list();
    }

    @Override
    public List<Category> getSecondLevel() {
        return sessionFactory.getCurrentSession().createQuery("from Category where level = :lev")
                .setParameter("lev", 1)
                .list();
    }

    @Override
    public int getCategoryByName(String lang, String text) {
       int id = 0;

        if (lang.equals(UK_LANG))
            id = (Integer) sessionFactory.getCurrentSession().createQuery("select id from Category where uaText LIKE :text")
                .setParameter("text", text)
                .uniqueResult();
        else if (lang.equals(RU_LANG))
            id = (Integer) sessionFactory.getCurrentSession().createQuery("select id from Category where ruText LIKE :text")
                    .setParameter("text", text)
                    .uniqueResult();
        return id;
    }


}
