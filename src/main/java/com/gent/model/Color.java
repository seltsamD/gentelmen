package com.gent.model;



import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by daria on 05.10.2016.
 */
@Entity
@Table(name= "Color")
public class Color implements Serializable{

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Color color = (Color) o;

        if (id != color.id) return false;
        if (ruText != null ? !ruText.equals(color.ruText) : color.ruText != null) return false;
        return uaText != null ? uaText.equals(color.uaText) : color.uaText == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ruText != null ? ruText.hashCode() : 0);
        result = 31 * result + (uaText != null ? uaText.hashCode() : 0);
        return result;
    }
}
