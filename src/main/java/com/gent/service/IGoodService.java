package com.gent.service;

import com.gent.dto.GoodDTO;
import com.gent.dto.GoodDTOExtend;
import com.gent.model.Good;
import com.gent.util.NotFoundException;

import java.util.List;

/**
 * Created by daria on 30.09.2016.
 */
public interface IGoodService {

//    List<Integer> listGood = new ArrayList<Integer>();
List<GoodDTO> getAllGoodsDTO();
    List<Good> getAllGoods();

    Good getGoodById(int id) throws NotFoundException;
    boolean addGood(Good good);
    void updateGood(Good good);
    void deleteGood(int id);

    List<GoodDTO> getRandomGoods();

    List<GoodDTOExtend> getListGoodsDTO(List<Integer> list);
    List<Good> getListGoods(List<Integer> list);

    void changeStatus(int id, int status);

    List<GoodDTO> getGoodsByCategorie(int catId, int page);
    List<Good> getGoodsByColor(int colorId);
    int getMinPrice();
    int getMaxPrice();
    List<Good> getGoodBetweenPrice(int price1, int price2);
    List<Good> getGoodsBySize(String size);

    List<GoodDTO> getGoodsWithLimit(int page);

    List<GoodDTO> getListGoodsByCategories(List<Integer> categories, int page);

    GoodDTO convertToMiniDTO(Good good);

    GoodDTO convertToMiniDTO(Good good, String lang);

    List<GoodDTO> convertListToMiniDTO(List<Good> goodList);

    List<GoodDTO> convertListToMiniDTO(List<Good> goodList, String lang);

    List<GoodDTO> findTheSame(int id);
}
