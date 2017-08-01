package com.gent.dto;

import com.gent.model.Good;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.List;

import static com.gent.util.Constants.*;

/**
 * Created by daria on 25.07.2017.
 */
public class GoodDTO {
    protected int id;

    protected String href;

    protected String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static GoodDTO convertToMiniDTO(Good good) {
        GoodDTO goodDTO = new GoodDTO();
        goodDTO.setId(good.getId());
        if (LocaleContextHolder.getLocale().getLanguage().equals(UK_LANG)) {
            goodDTO.setName(good.getNameUa());
        } else {
            goodDTO.setName(good.getNameRu());
        }
        goodDTO.href = DOMAIN + LocaleContextHolder.getLocale().getLanguage() + SLASH + "good" + SLASH +
                goodDTO.getName().replaceAll(" ", "-") + SLASH + goodDTO.id;

        return goodDTO;
    }

    public static List<GoodDTO> convertListToMiniDTO(List<Good> goodList) {
        List<GoodDTO> goodDTOList = new ArrayList<GoodDTO>(goodList.size());
        for (Good good : goodList) {
            goodDTOList.add(convertToMiniDTO(good));
        }
        return goodDTOList;
    }

}
