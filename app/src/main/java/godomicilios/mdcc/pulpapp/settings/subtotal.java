package godomicilios.mdcc.pulpapp.settings;

import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 25/04/2017.
 */

public class subtotal {
    private Integer id;
    private String name;
    private Integer price;
    private String description;
    private Integer cant;
    private String picture;
    private Integer productId;
    private Integer drinkType;
    private Integer drinkEdit;
    private Integer finalPrice=0;
    private ArrayList<LinearLayout> components;
    public ArrayList<subtotal> subtotals;

    public subtotal(Integer id, String name, Integer price, String description,
                    Integer cant, ArrayList<LinearLayout> components){

        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setDescription(description);
        this.setCant(cant);
        this.components=components;
    }
     public subtotal(){}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCant() {
        return cant;
    }

    public void setCant(Integer cant) {
        this.cant = cant;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(Integer drinkType) {
        this.drinkType = drinkType;
    }

    public Integer getDrinkEdit() {
        return drinkEdit;
    }

    public void setDrinkEdit(Integer drinkEdit) {
        this.drinkEdit = drinkEdit;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }
}
