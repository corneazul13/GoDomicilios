package godomicilios.mdcc.pulpapp.settings;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 05/04/2017.
 */

public class ingredients {
    public Integer id;
    public String name;
    public String status;
    public Integer type;
    public Integer ingId;
    public Integer max;
    public String categor;
    public ArrayList<ingredients> ingredientses;


    public ingredients(Integer id, String name,  String status, Integer type, Integer ingId, Integer max, String categor){

        this.id =id;
        this.name = name;
        this.status = status;
        this.type = type;
        this.ingId = ingId;
        this.max=max;
        this.categor=categor;
    }
    public ingredients(){}

    @Override
    public String toString() {
        return "ingredients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", type=" + type +
                ", ingId=" + ingId +
                ", max=" + max +
                ", categor='" + categor + '\'' +
                ", ingredientses=" + ingredientses +
                '}';
    }
}
