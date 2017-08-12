package godomicilios.mdcc.godomiciliosc.settings;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 25/04/2017.
 */

public class drink {
    private Integer id;
    private Integer price;
    private Integer idCompany;
    private String name;
    private String picture;
    private String lenght;
    public ArrayList<drink> drinks;

    public drink (Integer id, Integer price, Integer idCompany, String name,
                  String picture, String lenght){

        this.id=id;
        this.price=price;
        this.idCompany=idCompany;
        this.name = name;
        this.picture = picture;
        this.lenght = lenght;

    }

    public drink () {

    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLenght() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght = lenght;
    }

}
