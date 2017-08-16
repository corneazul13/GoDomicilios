package godomicilios.mdcc.godomiciliosc.settings;

import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 28/07/2017.
 */

public class allChecks {
    private me.omidh.liquidradiobutton.LiquidRadioButton[] checksDrink;
    private android.support.v7.widget.AppCompatCheckBox[] checkAddition;
    private me.omidh.liquidradiobutton.LiquidRadioButton[] checks;
    public ArrayList<EditText>ed;
    public ArrayList<allChecks> allCheckses;

    public allChecks (me.omidh.liquidradiobutton.LiquidRadioButton[] checksDrink,
                      android.support.v7.widget.AppCompatCheckBox[] checkAddition,
                      me.omidh.liquidradiobutton.LiquidRadioButton[] checks){
        this.setChecksDrink(checksDrink);
        this.setCheckAddition(checkAddition);
        this.setChecks(checks);
    }
    public allChecks(){}


    public me.omidh.liquidradiobutton.LiquidRadioButton[] getChecksDrink() {
        return checksDrink;
    }

    public void setChecksDrink(me.omidh.liquidradiobutton.LiquidRadioButton[] checksDrink) {
        this.checksDrink = checksDrink;
    }

    public android.support.v7.widget.AppCompatCheckBox[] getCheckAddition() {
        return checkAddition;
    }

    public void setCheckAddition(android.support.v7.widget.AppCompatCheckBox[] checkAddition) {
        this.checkAddition = checkAddition;
    }

    public me.omidh.liquidradiobutton.LiquidRadioButton[] getChecks() {
        return checks;
    }

    public void setChecks(me.omidh.liquidradiobutton.LiquidRadioButton[] checks) {
        this.checks = checks;
    }

    public ArrayList<EditText> getEd() {
        return ed;
    }

    public void setEd(ArrayList<EditText> ed) {
        this.ed = ed;
    }
}
