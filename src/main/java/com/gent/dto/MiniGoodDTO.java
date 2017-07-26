package com.gent.dto;

import com.gent.model.Good;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.List;

import static com.gent.util.Constants.UK_LANG;

/**
 * Created by daria on 25.07.2017.
 */
public class MiniGoodDTO {
    protected int id;
    protected String firm;
    protected String categoryName;

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public static MiniGoodDTO convertToMiniDTO(Good good) {
        MiniGoodDTO goodDTO = new MiniGoodDTO();
        goodDTO.id = good.getId();
        goodDTO.firm = good.getFirm();
        if (LocaleContextHolder.getLocale().getLanguage().equals(UK_LANG)) {
            goodDTO.categoryName = good.getCategory().getUaText();
        } else {
            goodDTO.categoryName = good.getCategory().getRuText();
        }
        return goodDTO;
    }

    public static List<MiniGoodDTO> convertListToMiniDTO(List<Good> goodList) {
        List<MiniGoodDTO> goodDTOList = new ArrayList<MiniGoodDTO>(goodList.size());
        for (Good good : goodList) {
            goodDTOList.add(convertToMiniDTO(good));
        }
        return goodDTOList;
    }

}
