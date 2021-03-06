package com.gent.dao;


import com.gent.model.Category;
import com.gent.model.Color;
import com.gent.model.Description;
import com.gent.model.Good;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by daria on 30.09.2016.
 */

@Repository
public class GoodDAO implements IGoodDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IDescriptionDAO descriptionDAO;

    @Autowired
    private ICategoryDAO categoryDAO;

    @Autowired
    private IColorDAO colorDAO;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Good> getAllGoods() {

        String hql = "FROM Good as p where p.status = 1 ORDER BY p.id ";
        return entityManager.createQuery(hql).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Good getGoodById(int id) {
        List resultList = entityManager.createQuery("FROM Good as p where p.id = :id").setParameter("id", id).getResultList();
        Good good = (Good) resultList.stream().findFirst().orElse(null);
        return good;
    }

    @Override
    @Transactional
    public boolean addGood(Good good) {
        Description description = new Description();
        description.setRuText(good.getDescription().getRuText());
        description.setUaText(good.getDescription().getUaText());
        entityManager.persist(description);
        good.setDescription(description);
        sessionFactory.getCurrentSession().save(good);
        return true;
    }

    @Override
    @Transactional
    public void updateGood(Good good) {
        Good  g = getGoodById(good.getId());
        Color color = colorDAO.getColorById(good.getColor().getId());
        g.setColor(color);

        g.setFirm(good.getFirm());
        g.setPrice(good.getPrice());

        if (null != good.getCategory()) {
            Category category = categoryDAO.getCategoryById(good.getCategory().getId());
            g.setCategory(category);
        }

        g.setCountImg(good.getCountImg());
        g.setSize(good.getSize());
        g.setNameRu(good.getNameRu());
        g.setNameUa(good.getNameUa());

        Description description = descriptionDAO.findById(good.getDescription().getId());
        description.setRuText(good.getDescription().getRuText());
        description.setUaText(good.getDescription().getUaText());
        entityManager.merge(description);

        g.setDescription(description);
        sessionFactory.getCurrentSession().update(g);
    }

    @Override
    @Transactional
    public void deleteGood(int id) {
        sessionFactory.getCurrentSession().delete(getGoodById(id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Good> getRandomGoods() {
        return entityManager.createQuery("from Good o where o.status = 1 order by rand()")
                .setMaxResults(10)
                .getResultList();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Good> getRandomGoodsByCategory(int goodId, int categoryId, int count) {
        return entityManager.createQuery("from Good o where o.status = 1  and o.id != :goodId and o.category.id = :categoryId order by rand()")
                .setParameter("goodId", goodId)
                .setParameter("categoryId", categoryId)
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    public List<Good> getListGoods(List<Integer> inList) {
        List<Good> outList = entityManager.createQuery("from Good o where  o.status = 1 and o.id in (:lis)")
                .setParameter("lis", inList)
                .getResultList();
        return outList;
    }

    @Override
    @Transactional
    public void changeStatus(int id, int status) {
        Good g = getGoodById(id);
        g.setStatus(status);
        sessionFactory.getCurrentSession().update(g);
    }

    @Override
    public List<Good> getGoodsByCategorie(int catId, int page) {
        Query q = entityManager.createQuery("FROM Good p where p.status = 1 and p.category.id = :cat_id");
        q.setParameter("cat_id", catId);
        q.setFirstResult(page);
        q.setMaxResults(10);
        return q.getResultList();
    }

    @Override
    public List<Good> getGoodsByColor(int colorId) {
        List<Good> list = entityManager.createQuery("FROM Good  p where p.status = 1 and p.color.id = :id ORDER BY p.id ")
                .setParameter("id", colorId)
                .getResultList();

        return list;
    }

    @Override
    public int getMinPrice() {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Good.class)
                .setProjection(Projections.min("price"));
        Integer minAge = (Integer)criteria.uniqueResult();
        return minAge;
    }

    @Override
    public int getMaxPrice() {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Good.class)
                .setProjection(Projections.max("price"));
        Integer maxPrice = (Integer)criteria.uniqueResult();
        return maxPrice;
    }

    @Override
    public List<Good> getGoodBetweenPrice(int price1, int price2) {
        List<Good> list = entityManager.createQuery("select p FROM Good  p where p.status = 1 and p.price BETWEEN :price1 and :price2 ORDER BY p.id ")
                .setParameter("price1", price1)
                .setParameter("price2", price2)
                .getResultList();

        return list;
    }

    @Override
    public List<Good> getGoodsBySize(String size) {
        List<Good> list = entityManager.createQuery("select p FROM Good p where p.status = 1 and p.size LIKE :text")
                .setParameter("text", size)
                .getResultList();

        return list;
    }

    @Override
    public List<Good> getGoodsWithLimit(int page) {
        Query q = entityManager.createQuery("FROM Good p where p.status = 1 ");
        q.setFirstResult(page);
        q.setMaxResults(10);
        return q.getResultList();
    }
}
