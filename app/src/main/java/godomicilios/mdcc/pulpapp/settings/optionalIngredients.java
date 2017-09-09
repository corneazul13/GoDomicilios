package godomicilios.mdcc.pulpapp.settings;

import java.lang.reflect.Array;
import java.util.ArrayList;

import me.omidh.liquidradiobutton.LiquidRadioButton;

/**
 * Created by PROGRAMACION5 on 12/08/2017.
 */

public class optionalIngredients {
    public Integer id;
    public String name;
    public Integer cant;
    public Integer coun;
    public ArrayList<ingredients> ingredientses;
    public ArrayList<check> checks;
    public ArrayList<optionalIngredients> optionalIngredientses;

    public optionalIngredients(Integer id, String name, Integer cant,Integer count, ArrayList<check> checks, ArrayList<ingredients> ingredientses){

        this.id=id;
        this.name = name;
        this.cant= cant;
        this.coun=count;
        this.checks= checks;
        this.ingredientses=ingredientses;
    }
    public optionalIngredients(){}
}
