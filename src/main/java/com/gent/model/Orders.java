package com.gent.model;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.List;

/**
 * Created by daria on 15.10.2016.
 */
@Entity
@Table(name= "Orders")
public class Orders implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @NotNull
    @Size(min=2, max=255)
    @Column(name = "Name")
    private String name;

    @NotNull
    @Size(min=2, max=255)
    @Column(name = "Phone")
    private String phone;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinColumn(name = "good_id", nullable = false)
    @JoinTable(name = "ORDERS_GOOD", joinColumns = { @JoinColumn(name = "orders_id") }, inverseJoinColumns = { @JoinColumn(name = "good_id") })
    private List<Good> listGood;


    @Size(min=2, max=500)
    @Column(name = "Info")
    private String info;

    @Column(name = "status") // 0-принятый, 1-отправленый, 2-выполненый, 3-отмененный
    private int status;

    @Column(name = "date")
    private LocalDateTime date;



    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Good> getListGood() {
        return listGood;
    }

    public void setListGood(List<Good> listGood) {
        this.listGood = listGood;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
