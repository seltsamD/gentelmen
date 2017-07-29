package com.gent.service;

import com.gent.dto.GoodDTO;
import com.gent.dto.MiniGoodDTO;
import com.gent.model.Good;

import java.util.List;

/**
 * Created by daria on 30.09.2016.
 */
public interface IGoodService {

//    List<Integer> listGood = new ArrayList<Integer>();
    List<Good> getAllGoods();

    GoodDTO getGoodById(int id);
    boolean addGood(Good good);
    void updateGood(Good good);
    void deleteGood(int id);

    List<MiniGoodDTO> getRandomGoods();
    List<Good> getListGoods(List<Integer> list);
    void changeStatus(int id, int status);
    List<Good> getGoodsByCategorie(int catId);
    List<Good> getGoodsByColor(int colorId);
    int getMinPrice();
    int getMaxPrice();
    List<Good> getGoodBetweenPrice(int price1, int price2);
    List<Good> getGoodsBySize(String size);

    List<MiniGoodDTO> getGoodsWithLimit(int page);
}
