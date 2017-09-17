package com.gent.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by daria on 06.08.2017.
 */
public class CategoryDTO {
    private int id;
    private String name;
    private String href;
    private List<CategoryDTO> child;
    private String altHref;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<CategoryDTO> getChild() {
        return child;
    }

    public void setChild(List<CategoryDTO> child) {
        this.child = child;
    }

    public String getAltHref() {
        return altHref;
    }

    public void setAltHref(String altHref) {
        this.altHref = altHref;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
