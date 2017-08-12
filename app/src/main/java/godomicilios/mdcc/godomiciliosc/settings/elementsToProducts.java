package godomicilios.mdcc.godomiciliosc.settings;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 27/04/2017.
 */

public class elementsToProducts {
    public ArrayList<drink> drinks;
    public ArrayList<elementsToProducts> elementsToProductses;
    private ArrayList<ingredients> ingredientses;


    public elementsToProducts(ArrayList<drink> drinks){
        this.drinks=settings.drink.drinks;
    }
    public elementsToProducts(){}

    public void replicate (){

    }

    public ArrayList<ingredients> getIngredientses() {
        return ingredientses;
    }

    public void setIngredientses(ArrayList<ingredients> ingredientses) {
        this.ingredientses = ingredientses;
    }
}
