package com.gent.dao;

import com.gent.model.Orders;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by daria on 15.10.2016.
 */

@Transactional
@Repository
public class OrdersDAO implements IOrdersDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Orders> getAllOrders() {
        List<Orders> list = sessionFactory.getCurrentSession().createQuery("from Orders o order by o.date").list();
        return list;
    }

    @Override
    public Orders getOrdersById(int id) {
        return (Orders) sessionFactory.getCurrentSession().createQuery("from Orders o where o.id =: id").setParameter("id", id).uniqueResult();
    }

    @Override
    public boolean addOrders(Orders order) {
        sessionFactory.getCurrentSession().save(order);
        return true;
    }

    @Override
    public boolean changeStatus(int status, int orderId) {
        Orders order = getOrdersById(orderId);
        order.setStatus(status);
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Orders> getOrdersByDate(Date date1, Date date2) {
        List<Orders> list = sessionFactory.getCurrentSession().createQuery("from Orders where date between :date1 and :date2")
                .setParameter("date1", date1).setParameter("date2", date2).list();
        return list;
    }

    @Override
    public List<Orders> getOrdersByPhone(String phone) {
        List<Orders> list = sessionFactory.getCurrentSession().createQuery("from Orders where phone LIKE :phone")
                .setParameter("phone", phone)
                .list();
        return list;
    }
}
