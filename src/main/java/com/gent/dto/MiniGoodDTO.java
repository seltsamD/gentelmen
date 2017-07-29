package com.gent.dto;

import com.gent.model.Good;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.List;

import static com.gent.util.Constants.*;

/**
 * Created by daria on 25.07.2017.
 */
public class MiniGoodDTO {
    protected int id;
    protected String firm;
    protected String categoryName;
    protected String href;

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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public static MiniGoodDTO convertToMiniDTO(Good good) {
        MiniGoodDTO goodDTO = new MiniGoodDTO();
        goodDTO.setId(good.getId());
        goodDTO.setFirm(good.getFirm());
        String colorName = null;
        if (LocaleContextHolder.getLocale().getLanguage().equals(UK_LANG)) {
            goodDTO.setCategoryName(good.getCategory().getUaText());
            colorName = good.getColor().getUaText();
        } else {
            goodDTO.setCategoryName(good.getCategory().getRuText());
            colorName = good.getColor().getRuText();
        }
        goodDTO.href = DOMAIN + LocaleContextHolder.getLocale().getLanguage() + SLASH + "good" + SLASH + goodDTO.categoryName.replaceAll(" ", "-") +
                "-" + good.getFirm().replaceAll(" ", "-") + "-" + colorName.replaceAll(" ", "-") + SLASH + goodDTO.id;

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
