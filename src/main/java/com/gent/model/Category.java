package com.gent.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by daria on 05.10.2016.
 */
@Entity
@Table(name= "Category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Size(min=2, max=255)
    @Column(name = "ruText")
    private String ruText;

    @Size(min=2, max=255)
    @Column(name = "uaText")
    private String uaText;

    @Column(name = "parent_id")
    private int parent;

    @Column(name = "level")
    private int level;

    @Column
    private Date data = new Date();


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != category.id) return false;
        if (parent != category.parent) return false;
        if (level != category.level) return false;
        if (ruText != null ? !ruText.equals(category.ruText) : category.ruText != null) return false;
        return uaText != null ? uaText.equals(category.uaText) : category.uaText == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ruText != null ? ruText.hashCode() : 0);
        result = 31 * result + (uaText != null ? uaText.hashCode() : 0);
        result = 31 * result + parent;
        result = 31 * result + level;
        return result;
    }


}
