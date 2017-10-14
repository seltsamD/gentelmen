package com.gent.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by daria on 30.09.2016.
 */
@Entity
@Table(name = "tbGood")
public class Good implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Size(min = 2, max = 255)
    @Column(name = "Firm")
    private String firm;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "description_id", nullable = false)
    private Description description;

    @Min(1)
    @Column(name = "Price")
    private int price;

    @Size(min = 1, max = 50)
    @Column(name = "Size")
    private String size;

    @Min(1)
    @Column(name = "CountImg")
    private int countImg;

    @Column(name = "Status")
    private int status;

    @Size(min = 2, max = 70)
    @Column(name = "nameUa")
    private String nameUa;

    @Size(min = 2, max = 70)
    @Column(name = "nameRu")
    private String nameRu;

    @Column
    private Date data = new Date();


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

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

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Good good = (Good) o;

        if (price != good.price) return false;
        if (countImg != good.countImg) return false;
        if (status != good.status) return false;
        if (firm != null ? !firm.equals(good.firm) : good.firm != null) return false;
        if (size != null ? !size.equals(good.size) : good.size != null) return false;
        return data != null ? data.equals(good.data) : good.data == null;

    }

    @Override
    public int hashCode() {
        int result = firm != null ? firm.hashCode() : 0;
        result = 31 * result + price;
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + countImg;
        result = 31 * result + status;
        return result;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", firm='" + firm + '\'' +
                ", color=" + color +
                ", category=" + category +
                ", price=" + price +
                ", size='" + size + '\'' +
                ", countImg=" + countImg +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
