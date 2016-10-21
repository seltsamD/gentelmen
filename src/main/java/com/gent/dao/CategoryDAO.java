package com.gent.dao;

import com.gent.model.Category;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Query query = sessionFactory.getCurrentSession().createQuery(" from Category  where id = :id");
        query.setParameter("id", id);
        Category category = (Category) query.uniqueResult();
        return category;
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
       sessionFactory.getCurrentSession().update(category);
    }

    @Override
    public void deleteCategory(int id) {
        sessionFactory.getCurrentSession().createQuery("delete from Category  where id = :id")
                .setParameter("id", id).executeUpdate();
    }


}
