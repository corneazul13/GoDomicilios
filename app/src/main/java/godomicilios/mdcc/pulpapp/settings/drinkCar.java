package godomicilios.mdcc.pulpapp.settings;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 25/05/2017.
 */

public class drinkCar {
    public Integer id;
    public String name;
    public String picture;
    public String length;
    public  ArrayList<drinkCar> drinkCars;

    public drinkCar(Integer id, String name, String picture, String length){
        this.setId(id);
        this.setName(name);
        this.setPicture(picture);
        this.setLength(length);
    }
    public drinkCar(){}

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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public ArrayList<drinkCar> getDrinkCars() {
        return drinkCars;
    }

    public void setDrinkCars(ArrayList<drinkCar> drinkCars) {
        this.drinkCars = drinkCars;
    }
}
