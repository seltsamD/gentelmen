package com.gent.dto;

import com.gent.model.Good;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.gent.util.Constants.*;

/**
 * Created by daria on 22.07.2017.
 */
public class GoodDTOExtend extends GoodDTO implements Serializable {

    private String colorName;
    private int price;
    private String size;
    private int countImg;
    private String description;
    private String firm;
    private String categoryName;
    private int status;


    public GoodDTOExtend() {
    }

    public static GoodDTOExtend convertToDTO(Good good) {
        GoodDTOExtend goodDTOExtend = new GoodDTOExtend();
        goodDTOExtend.setId(good.getId());
        goodDTOExtend.setFirm(good.getFirm());
        goodDTOExtend.setPrice(good.getPrice());
        goodDTOExtend.setSize(good.getSize());
        goodDTOExtend.setCountImg(good.getCountImg());
        goodDTOExtend.setStatus(good.getStatus());
        if (LocaleContextHolder.getLocale().getLanguage().equals(UK_LANG)) {
            goodDTOExtend.setColorName(good.getColor().getUaText());
            goodDTOExtend.setCategoryName(good.getCategory().getUaText());
            goodDTOExtend.setDescription(good.getDescription().getUaText());
            goodDTOExtend.setName(good.getNameUa());
        } else {
            goodDTOExtend.setColorName(good.getColor().getRuText());
            goodDTOExtend.setCategoryName(good.getCategory().getRuText());
            goodDTOExtend.setDescription(good.getDescription().getRuText());
            goodDTOExtend.setName(good.getNameRu());
        }
        goodDTOExtend.href = DOMAIN + LocaleContextHolder.getLocale().getLanguage() + SLASH + "good" + SLASH +
                goodDTOExtend.getName().replaceAll(" ", "-") + SLASH + goodDTOExtend.id;
        return goodDTOExtend;
    }

    public static List<GoodDTOExtend> convertListToDTO(List<Good> goodList) {
        List<GoodDTOExtend> goodDTOExtendList = new ArrayList<GoodDTOExtend>(goodList.size());
        for (Good good : goodList) {
            goodDTOExtendList.add(convertToDTO(good));
        }
        return goodDTOExtendList;
    }


    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getCountImg() {
        return countImg;
    }

    public void setCountImg(int countImg) {
        this.countImg = countImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
