package com.gent.dao;

import com.gent.model.Orders;

import java.util.Date;
import java.util.List;

/**
 * Created by daria on 15.10.2016.
 */
public interface IOrdersDAO {
    List<Orders> getAllOrders();
    Orders getOrdersById(int id);
    boolean addOrders(Orders order);
    boolean changeStatus(int status, int orderId);
    List<Orders> getOrdersByDate(Date date1, Date date2);
}
