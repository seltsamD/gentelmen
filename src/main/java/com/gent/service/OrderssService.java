package com.gent.service;

import com.gent.dao.IOrdersDAO;
import com.gent.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

/**
 * Created by daria on 15.10.2016.
 */

@Service
public class OrderssService implements IOrdersService {

    @Autowired
    IOrdersDAO orderDAO;

    @Autowired
    JavaMailSenderImpl mailSender;

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


        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
        try {
            mailMsg.setFrom("gentlmen.in.ua@gmail.com");
            mailMsg.setTo("nahod.dar@gmail.com");
            mailMsg.setSubject("Новый заказ");
            mailMsg.setText(order.toString());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(mimeMessage);
        return true;
    }

    @Override
    public boolean changeStatus(int status, int orderId) {
        orderDAO.changeStatus(status, orderId);
        return true;
    }

    @Override
    public List<Orders> getOrdersByDate(Date date1, Date date2) {
        return orderDAO.getOrdersByDate(date1, date2);
    }

    @Override
    public List<Orders> getOrdersByPhone(String phone) {
        return orderDAO.getOrdersByPhone(phone);
    }
}
