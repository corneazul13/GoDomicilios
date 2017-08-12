package godomicilios.mdcc.godomiciliosc.settings;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 08/06/2017.
 */

public class categories {
    private ArrayList<String> ca;

    public categories (ArrayList<String> ca){
        this.setCa(ca);
    }
    public categories (){}

    public ArrayList<String> getCa() {
        return ca;
    }

    public void setCa(ArrayList<String> ca) {
        this.ca = ca;
    }
}
