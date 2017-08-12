package godomicilios.mdcc.godomiciliosc.settings;

import java.util.AbstractList;

/**
 * Created by PROGRAMACION5 on 19/04/2017.
 */

public class update {
    public double lat;
    public double lon;
    public AbstractList<update> updates;

    public  update (double lat, double lon){
        this.lat=lat;
        this.lon = lon;
    }
    public update(){}

}
