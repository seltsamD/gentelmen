package com.gent.model;



import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by daria on 30.09.2016.
 */
@Entity
@Table(name= "tbGood")
public class Good implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Size(min=2, max=255)
    @Column(name = "Firm")
    private String firm;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Min(1)
    @Column(name = "Price")
    private int price;

    @Size(min=1, max=50)
    @Column(name = "Size")
    private String size;

    @Min(1)
    @Column(name = "CountImg")
    private int countImg;

    @Column(name = "Status")
    private int status;

    @Size(min=2, max=1000)
    @Column(name = "ruText")
    private String ruText;

    @Size(min=2, max=1000)
    @Column(name = "uaText")
    private String uaText;


    public void setStatus(int status) {
        this.status = status;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public String getRuText() {
        return ruText;
    }

    public void setRuText(String ruText) {
        this.ruText = ruText;
    }

    public String getUaText() {
        return uaText;
    }

    public void setUaText(String uaText) {
        this.uaText = uaText;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", firm= " + firm +
                ", color= " + color.getUaText() +
                ", category= "  + category.getUaText() +
                ", price= " + price +
                ", size= " + size +
                ", countImg= " + countImg +
                ", status= " + status +
                ", \nruText= " + ruText +
                ", \nuaText= " + uaText +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Good good = (Good) o;

        if (id != good.id) return false;
        if (price != good.price) return false;
        if (countImg != good.countImg) return false;
        if (status != good.status) return false;
        if (!firm.equals(good.firm)) return false;
        if (color.getId() != good.color.getId()) return false;
        if (category.getId() != good.category.getId()) return false;
        if (!size.equals(good.size)) return false;
        if (!ruText.equals(good.ruText)) return false;
        return uaText.equals(good.uaText);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firm.hashCode();
        result = 31 * result + price;
        result = 31 * result + size.hashCode();
        result = 31 * result + countImg;
        result = 31 * result + ruText.hashCode();
        result = 31 * result + uaText.hashCode();
        return result;
    }
}
