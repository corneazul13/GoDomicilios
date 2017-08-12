package godomicilios.mdcc.godomiciliosc.settings;

import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 05/04/2017.
 */

public class product {
    public Integer id;
    public Integer idCompany;
    public Integer idRank;
    public String name;
    public String description;
    public Integer price;
    private String picture;
    public LinearLayout linear;
    public Integer visible;
    public Integer num;
    public Integer col;
    public Integer cant;
    private Integer stablishSelection;
    private Integer confirm;
    public Integer drinkType;
    public Integer drinkEdit;
    public ArrayList<product> products;

    public product(Integer id, Integer idCompany, Integer idRank, String name,
                   String description, Integer price, String picture, Integer visible
                   , Integer num, Integer col, Integer cant, Integer drinkType,
                   Integer drinkEdit){

        this.id =id;
        this.idCompany = idCompany;
        this.idRank =idRank;
        this.name = name;
        this.description = description;
        this.price = price;
        this.setPicture(picture);
        this.visible = visible;
        this.num = num;
        this.col = col;
        this.cant=cant;
        this.drinkType= drinkType;
        this.drinkEdit= drinkEdit;



    }
    public  product(){}

    public Integer getStablishSelection() {
        return stablishSelection;
    }

    public void setStablishSelection(Integer stablishSelection) {
        this.stablishSelection = stablishSelection;
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public String getPicture() {
        String h = picture.replace(" ", "%20");
        return h;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
