package com.gent.dto;

import com.gent.model.Good;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.gent.util.Constants.UK_LANG;

/**
 * Created by daria on 22.07.2017.
 */
public class GoodDTO extends MiniGoodDTO implements Serializable {

    private String colorName;
    private int price;
    private String size;
    private int countImg;
    private String description;

    public GoodDTO() {
    }

    public static GoodDTO convertToDTO(Good good) {
        GoodDTO goodDTO = new GoodDTO();
        goodDTO.id = good.getId();
        goodDTO.firm = good.getFirm();
        goodDTO.price = good.getPrice();
        goodDTO.size = good.getSize();
        goodDTO.countImg = good.getCountImg();
        if (LocaleContextHolder.getLocale().getLanguage().equals(UK_LANG)) {
            goodDTO.colorName = good.getColor().getUaText();
            goodDTO.categoryName = good.getCategory().getUaText();
            goodDTO.description = good.getUaText();
        } else {
            goodDTO.colorName = good.getColor().getRuText();
            goodDTO.categoryName = good.getCategory().getRuText();
            goodDTO.description = good.getRuText();
        }
        return goodDTO;
    }

    public static List<GoodDTO> convertListToDTO(List<Good> goodList) {
        List<GoodDTO> goodDTOList = new ArrayList<GoodDTO>(goodList.size());
        for (Good good : goodList) {
            goodDTOList.add(convertToDTO(good));
        }
        return goodDTOList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
}
