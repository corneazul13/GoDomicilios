package godomicilios.mdcc.pulpapp.settings;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 25/05/2017.
 */

public class additionCar {
    public Integer id;
    public Integer cant;
    public String name;
    public Integer price;
    public ArrayList<additionCar> additionCars;

    public additionCar (Integer id, Integer cant, String name, Integer price){
        this.id = id;
        this.cant = cant;
        this.name = name;
        this.price = price;
    }
    public additionCar (){}
}
