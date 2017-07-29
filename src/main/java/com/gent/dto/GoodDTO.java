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
        goodDTO.setId(good.getId());
        goodDTO.setFirm(good.getFirm());
        goodDTO.setPrice(good.getPrice());
        goodDTO.setSize(good.getSize());
        goodDTO.setCountImg(good.getCountImg());
        if (LocaleContextHolder.getLocale().getLanguage().equals(UK_LANG)) {
            goodDTO.setColorName(good.getColor().getUaText());
            goodDTO.setCategoryName(good.getCategory().getUaText());
            goodDTO.setDescription(good.getUaText());
        } else {
            goodDTO.setColorName(good.getColor().getRuText());
            goodDTO.setCategoryName(good.getCategory().getRuText());
            goodDTO.setDescription(good.getRuText());
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
}
