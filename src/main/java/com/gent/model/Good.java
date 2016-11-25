package com.gent.model;



import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by daria on 30.09.2016.
 */
@Entity
@Table(name= "tbGood")
public class Good implements Serializable{
    private static final long serialVersionUID = 1L;
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
                ", firm='" + firm + '\'' +
                ", color=" + color.getRuText() +
                ", category=" + category.getRuText() +
                ", price=" + price +
                ", size='" + size + '\'' +
                ", countImg=" + countImg +
                ", status=" + status +
                ", description=" + ruText + " "+ uaText +
                '}';
    }
}
