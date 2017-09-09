package godomicilios.mdcc.pulpapp.settings;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 07/04/2017.
 */

public class spinner {
    public String city;
    public Integer id;
    public ArrayList<spinner> spinners;

    public spinner(String city, Integer id){
        this.city = city;
        this.id = id;
    }
    public spinner () {}
    public String[] spinnerCities (){
        String[] b = new String[settings.spinner.spinners.size()+1];

        for (int i =0; i< settings.spinner.spinners.size()+1;i++){

            if (i==0){
                b[0] = "Seleccione una opción";
            }
            else {
                b [i] = settings.spinner.spinners.get(i-1).city;

            }

        }
        return b;
    }
}
