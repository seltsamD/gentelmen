package com.gent.dao;

import com.gent.model.Good;

import java.util.List;

/**
 * Created by daria on 30.09.2016.
 */

public interface IGoodDAO {
    List<Good> getAllGoods();

    Good getGoodById(int id);

    boolean addGood(Good good);

    void updateGood(Good good);

    void deleteGood(int id);

    List<Good> getRandomGoods();

    List<Good> getListGoods(List<Integer> list);

    void changeStatus(int id, int status);

    List<Good> getGoodsByCategorie(int catId);

    List<Good> getGoodsByColor(int colorId);

    int getMinPrice();

    int getMaxPrice();

    List<Good> getGoodBetweenPrice(int price1, int price2);

    List<Good> getGoodsBySize(String size);

    List<Good> getGoodsWithLimit(int page);
}
