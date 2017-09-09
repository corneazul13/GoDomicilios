package godomicilios.mdcc.pulpapp.settings;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 25/05/2017.
 */

public class ingredientsCar {
    public Integer id;
    public String name;
    public ArrayList<ingredientsCar> ingredientsCars;

    public ingredientsCar (Integer id, String name){
        this.setId(id);
        this.setName(name);
    }
    public ingredientsCar (){}

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

    public ArrayList<ingredientsCar> getIngredientsCars() {
        return ingredientsCars;
    }

    public void setIngredientsCars(ArrayList<ingredientsCar> ingredientsCars) {
        this.ingredientsCars = ingredientsCars;
    }
}
