package com.gent.service;

import com.gent.dao.IOrdersDAO;
import com.gent.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

/**
 * Created by daria on 15.10.2016.
 */

@Service
public class OrderssService implements IOrdersService {

    @Autowired
    IOrdersDAO orderDAO;

    @Override
    public List<Orders> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    public Orders getOrdersById(int id) {
        Orders obj = orderDAO.getOrdersById(id);
        return obj;
    }

    @Override
    public boolean addOrders(Orders order) {
        Date now = new Date();
        order.setDate(now);
        orderDAO.addOrders(order);
        return true;
    }

    @Override
    public boolean changeStatus(int status, int orderId) {
        orderDAO.changeStatus(status, orderId);
        return true;
    }

    @Override
    public List<Orders> getOrdersByDate(Date date1, Date date2) {
        return getOrdersByDate(date1, date2);
    }
}
