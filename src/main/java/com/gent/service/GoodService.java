package com.gent.service;

import com.gent.dao.IGoodDAO;
import com.gent.model.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daria on 30.09.2016.
 */
@Service
public class GoodService implements IGoodService {

    @Autowired
    private IGoodDAO goodDAO;



    @Override
    public List<Good> getAllGoods() {
       return goodDAO.getAllGoods();
    }

    @Override
    public Good getGoodById(int id) {
        Good obj = goodDAO.getGoodById(id);
        return obj;
    }

    @Override
    public boolean addGood(Good good) {
        goodDAO.addGood(good);
        return true;
    }

    @Override
    public void updateGood(Good good) {
        goodDAO.updateGood(good);

    }

    @Override
    public void deleteGood(int id) {
        goodDAO.deleteGood(id);
    }

    @Override
    public List<Good> getRandomGoods() {
        return goodDAO.getRandomGoods();
    }

    @Override
    public List<Good> getListGoods(List list)
    {
        return goodDAO.getListGoods(list);
    }

    @Override
    public void changeStatus(int id, int status) {
        goodDAO.changeStatus(id, status);
    }
}