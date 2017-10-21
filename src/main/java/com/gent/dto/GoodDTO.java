package com.gent.dto;

import java.util.Date;

/**
 * Created by daria on 25.07.2017.
 */
public class GoodDTO {
    protected int id;
    protected String href;
    protected String name;
    protected String altHref;
    protected Date date;
    protected int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
