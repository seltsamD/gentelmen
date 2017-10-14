package com.gent.service;

import com.gent.controller.SitemapController;
import com.gent.dao.ICategoryDAO;
import com.gent.dao.IGoodDAO;
import com.gent.dto.GoodDTO;
import com.gent.model.Good;
import com.gent.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.gent.util.Constants.*;

/**
 * Created by daria on 30.09.2016.
 */
@Service
public class GoodService implements IGoodService {

    @Autowired
    private IGoodDAO goodDAO;

    @Autowired
    private ICategoryDAO categoryDAO;

    @Override
    public List<GoodDTO> getAllGoodsDTO() {
        return convertListToMiniDTO(goodDAO.getAllGoods());
    }

    @Override
    public List<Good> getAllGoods() {
        return goodDAO.getAllGoods();
    }


    @Override
    public Good getGoodById(int id) throws NotFoundException {
        Good good = goodDAO.getGoodById(id);
        if (null == good) {
            throw new NotFoundException("Not found good with id " + id);
        }
        return good;
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
    public List<GoodDTO> getListGoodsDTO(List<Integer> list) {
        return convertListToMiniDTO(goodDAO.getListGoods(list));
    }

    @Override
    public List<Good> getListGoods(List<Integer> list) {
        return goodDAO.getListGoods(list);
    }


    @Override
    public void changeStatus(int id, int status) {
        goodDAO.changeStatus(id, status);
    }

    @Override
    public List<GoodDTO> getGoodsByCategorie(int catId, int page) {
        return convertListToMiniDTO(goodDAO.getGoodsByCategorie(catId, page));
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

    @Override
    public List<GoodDTO> getListGoodsByCategories(List<Integer> categories, int page) {
        List<GoodDTO> goodDTOList = new ArrayList<>();
        for (Integer id : categories) {
            goodDTOList.addAll(convertListToMiniDTO(goodDAO.getGoodsByCategorie(id, page)));
        }
        return goodDTOList;
    }

    @Override
    public GoodDTO convertToMiniDTO(Good good, String lang) {
        GoodDTO goodDTO = new GoodDTO();
        goodDTO.setId(good.getId());
        if (lang.equals(UK_LANG)) {
            goodDTO.setName(good.getNameUa());
            goodDTO.setHref(DOMAIN + lang + SLASH + "good" + SLASH +
                    encode(goodDTO.getName().replaceAll(" ", "-")) + SLASH + goodDTO.getId());
            goodDTO.setAltHref(DOMAIN + RU_LANG + SLASH + "good" + SLASH +
                    encode(good.getNameRu().replaceAll(" ", "-")) + SLASH + goodDTO.getId());
        } else {
            goodDTO.setName(good.getNameRu());
            goodDTO.setHref(DOMAIN + lang + SLASH + "good" + SLASH +
                    encode(goodDTO.getName().replaceAll(" ", "-")) + SLASH + goodDTO.getId());
            goodDTO.setAltHref(DOMAIN + UK_LANG + SLASH + "good" + SLASH +
                    encode(good.getNameUa().replaceAll(" ", "-")) + SLASH + goodDTO.getId());
        }
        goodDTO.setDate(good.getData());
        return goodDTO;
    }

    @Override
    public GoodDTO convertToMiniDTO(Good good) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return convertToMiniDTO(good, lang);
    }

    @Override
    public List<GoodDTO> convertListToMiniDTO(List<Good> goodList) {
        List<GoodDTO> goodDTOList = new ArrayList<GoodDTO>(goodList.size());
        for (Good good : goodList) {
            goodDTOList.add(convertToMiniDTO(good));
        }
        return goodDTOList;
    }

    @Override
    public List<GoodDTO> convertListToMiniDTO(List<Good> goodList, String lang) {
        List<GoodDTO> goodDTOList = new ArrayList<GoodDTO>(goodList.size());
        for (Good good : goodList) {
            goodDTOList.add(convertToMiniDTO(good, lang));
        }
        return goodDTOList;
    }

    private String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
