package com.gent.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by daria on 30.07.2017.
 */
@Entity
@Table(name = "Description")
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Size(min = 2, max = 1000)
    @Column(name = "ruText")
    private String ruText;

    @Size(min = 2, max = 1000)
    @Column(name = "uaText")
    private String uaText;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
