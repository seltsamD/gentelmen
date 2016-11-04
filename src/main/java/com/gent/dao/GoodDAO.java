package com.gent.dao;

import com.gent.model.Category;
import com.gent.model.Color;
import com.gent.model.Good;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by daria on 30.09.2016.
 */
@Transactional
@Repository
public class GoodDAO implements IGoodDAO {

    @Autowired
    private SessionFactory sessionFactory;
    @SuppressWarnings("unchecked")
    @Override
    public List<Good> getAllGoods(int page) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Good.class);
        criteria.setFirstResult(page*3);
        criteria.setMaxResults(page+3);
        List<Good> list = criteria.list();
        return list;
//        String hql = "FROM Good as p where p.status = 1 ORDER BY p.id ";
//        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public Good getGoodById(int id) {
        return (Good) sessionFactory.getCurrentSession().get(Good.class, id);
    }

    @Override
    public boolean addGood(Good good) {
        sessionFactory.getCurrentSession().save(good);
        return true;
    }

    @Override
    public void updateGood(Good good) {
        Good  g = getGoodById(good.getId());
        g.setColor(good.getColor());
        g.setFirm(good.getFirm());
        g.setPrice(good.getPrice());
        g.setCategory(good.getCategory());
        g.setCountImg(good.getCountImg());
        g.setSize(good.getSize());
        sessionFactory.getCurrentSession().update(g);
    }

    @Override
    public void deleteGood(int id) {
        sessionFactory.getCurrentSession().delete(getGoodById(id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Good> getRandomGoods() {

        List<Good> list = sessionFactory.getCurrentSession().createQuery("select o from Good o where o.status = 1 order by rand()")
                .setMaxResults(10)
                .list();
        return list;
//        String hql = "FROM Good as p ORDER BY rand() ";
//        return (List<Good>) hibernateTemplate.find(hql).set();
    }

    @Override
    public List<Good> getListGoods(List inList) {
        List<Good> outList = sessionFactory.getCurrentSession().createQuery("from Good o where o.id in (:lis)")
                .setParameterList("lis", inList)
                .list();
        return outList;
    }

    @Override
    public void changeStatus(int id, int status) {
        Good g = getGoodById(id);
        g.setStatus(status);
        sessionFactory.getCurrentSession().update(g);
    }

    @Override
    public List<Good> getGoodsByCategorie(int catId) {

        List<Good> list = sessionFactory.getCurrentSession().createQuery("select p FROM Good  p where p.status = 1 and p.category.id = :id ORDER BY p.id ")
                .setParameter("id", catId)
                .list();

        return list;
    }

    @Override
    public List<Good> getGoodsByColor(int colorId) {
        List<Good> list = sessionFactory.getCurrentSession().createQuery("select p FROM Good  p where p.status = 1 and p.color.id = :id ORDER BY p.id ")
                .setParameter("id", colorId)
                .list();

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
        Integer maxAge = (Integer)criteria.uniqueResult();
        return maxAge;
    }

    @Override
    public List<Good> getGoodBetweenPrice(int price1, int price2) {
        List<Good> list = sessionFactory.getCurrentSession().createQuery("select p FROM Good  p where p.status = 1 and p.price BETWEEN :price1 and :price2 ORDER BY p.id ")
                .setParameter("price1", price1)
                .setParameter("price2", price2)
                .list();

        return list;
    }

    public List search(String text) {
        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
        Transaction tx = fullTextSession.beginTransaction();
        QueryBuilder qb = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity(Good.class).get();
        org.apache.lucene.search.Query query = qb
                .keyword()
                .onFields("firm", "color.ruText", "color.uaText", "category.ruText", "category.uaText")
                .matching("Java rocks!")
                .createQuery();


        org.hibernate.Query hibQuery =     fullTextSession.createFullTextQuery(query, Good.class);


        List result = hibQuery.list();

        tx.commit();
        return result;
    }


}
