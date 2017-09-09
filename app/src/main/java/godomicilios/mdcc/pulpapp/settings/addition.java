package godomicilios.mdcc.pulpapp.settings;

import java.util.AbstractList;

/**
 * Created by PROGRAMACION5 on 28/04/2017.
 */

public class addition {
    private Integer id;
    private String name;
    private Integer price;
    private String picture;
    private Integer num=0;
    private Integer cantss;
    public AbstractList<addition>additions;

    public addition (Integer id, String name, Integer price, String picture, Integer cantss){
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setPicture(picture);
        this.setCantss(cantss);
    }
    public addition(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getCantss() {
        return cantss;
    }

    public void setCantss(Integer cantss) {
        this.cantss = cantss;
    }
}
