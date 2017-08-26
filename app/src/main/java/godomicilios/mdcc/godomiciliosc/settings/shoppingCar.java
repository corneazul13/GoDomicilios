package godomicilios.mdcc.godomiciliosc.settings;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 06/04/2017.
 */

public class shoppingCar {

    public ArrayList<StablishmentCar> carFinal;
    public String picture;
    public String name;
    public Integer idStablish;

    public ArrayList<StablishmentCar> getCarFinal() {
        return carFinal;
    }

    public void setCarFinal(ArrayList<StablishmentCar> carFinal) {
        this.carFinal = carFinal;
    }
}
