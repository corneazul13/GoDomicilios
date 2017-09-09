package godomicilios.mdcc.pulpapp.settings;

import java.util.ArrayList;

import me.omidh.liquidradiobutton.LiquidRadioButton;

/**
 * Created by PROGRAMACION5 on 15/08/2017.
 */

public class check {
    public Integer in;
    public Integer categor;
    public Integer cant;
    public Integer count;
    public LiquidRadioButton liquidRadioButtons;
    public check(Integer in,Integer categor,Integer cant,Integer count, LiquidRadioButton liquidRadioButtons){
        this.in=in;
        this.liquidRadioButtons=liquidRadioButtons;
        this.categor=categor;
        this.count=count;
        this.cant=cant;
    }
    public check(){}

}
