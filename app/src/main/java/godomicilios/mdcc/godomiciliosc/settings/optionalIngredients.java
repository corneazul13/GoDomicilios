package godomicilios.mdcc.godomiciliosc.settings;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 12/08/2017.
 */

public class optionalIngredients {
    public String name;
    public Integer count;
    public ArrayList<ingredients> ingredientses;
    public ArrayList<optionalIngredients> optionalIngredientses;

    public optionalIngredients(String name,Integer count, ArrayList<ingredients> ingredientses){

        this.name = name;
        this.count=count;
        this.ingredientses=ingredientses;
    }
    public optionalIngredients(){}
}