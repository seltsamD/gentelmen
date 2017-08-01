package com.gent.service;

import com.gent.controller.SitemapController;
import com.gent.dao.IGoodDAO;
import com.gent.dto.GoodDTO;
import com.gent.model.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gent.dto.GoodDTO.convertListToMiniDTO;

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
        return goodDAO.getGoodById(id);
    }

    @Override
    public boolean addGood(Good good) {
        goodDAO.addGood(good);
        SitemapController.addGoodToSitemap(good);
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
    public List<GoodDTO> getRandomGoods() {
        return convertListToMiniDTO(goodDAO.getRandomGoods());
    }

    @Override
    public List<Good> getListGoods(List<Integer> list)
    {
        return goodDAO.getListGoods(list);
    }

    @Override
    public void changeStatus(int id, int status) {
        goodDAO.changeStatus(id, status);
    }

    @Override
    public List<Good> getGoodsByCategorie(int catId) {
        return goodDAO.getGoodsByCategorie(catId);
    }

    @Override
    public List<Good> getGoodsByColor(int colorId) {
        return goodDAO.getGoodsByColor(colorId);
    }

    @Override
    public int getMinPrice() {
        return goodDAO.getMinPrice();
    }

    @Override
    public int getMaxPrice() {
        return goodDAO.getMaxPrice();
    }

    @Override
    public List<Good> getGoodBetweenPrice(int price1, int price2) {
        return goodDAO.getGoodBetweenPrice(price1, price2);
    }

    @Override
    public List<Good> getGoodsBySize(String size) {
        return goodDAO.getGoodsBySize(size);
    }

    @Override
    public List<GoodDTO> getGoodsWithLimit(int page) {
        return convertListToMiniDTO(goodDAO.getGoodsWithLimit(page));
    }

}
