package godomicilios.mdcc.pulpapp.settings;

import java.util.ArrayList;
import java.util.List;

import me.omidh.liquidradiobutton.LiquidRadioButton;

/**
 * Created by PROGRAMACION5 on 17/08/2017.
 */

public class subGlobalChecks {
    public Integer id;
    public Integer count;
    public Integer cant;
    public ArrayList<subGlobalChecks> subGlobalCheckses;

    public subGlobalChecks(Integer id,Integer count, Integer cant){
        this.id=id;
        this.count=count;
        this.cant=cant;
    }
    public subGlobalChecks(){}
}
