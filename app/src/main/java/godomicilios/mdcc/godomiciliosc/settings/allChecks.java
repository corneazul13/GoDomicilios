package godomicilios.mdcc.godomiciliosc.settings;

import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 28/07/2017.
 */

public class allChecks {
    private android.support.v7.widget.AppCompatCheckBox[] checksDrink;
    private android.support.v7.widget.AppCompatCheckBox[] checkAddition;
    private android.support.v7.widget.AppCompatCheckBox[] checks;
    public ArrayList<EditText>ed;
    public ArrayList<allChecks> allCheckses;

    public allChecks (android.support.v7.widget.AppCompatCheckBox[] checksDrink,
                      android.support.v7.widget.AppCompatCheckBox[] checkAddition,
                      android.support.v7.widget.AppCompatCheckBox[] checks){
        this.setChecksDrink(checksDrink);
        this.setCheckAddition(checkAddition);
        this.setChecks(checks);
    }
    public allChecks(){}


    public android.support.v7.widget.AppCompatCheckBox[] getChecksDrink() {
        return checksDrink;
    }

    public void setChecksDrink(android.support.v7.widget.AppCompatCheckBox[] checksDrink) {
        this.checksDrink = checksDrink;
    }

    public android.support.v7.widget.AppCompatCheckBox[] getCheckAddition() {
        return checkAddition;
    }

    public void setCheckAddition(android.support.v7.widget.AppCompatCheckBox[] checkAddition) {
        this.checkAddition = checkAddition;
    }

    public android.support.v7.widget.AppCompatCheckBox[] getChecks() {
        return checks;
    }

    public void setChecks(android.support.v7.widget.AppCompatCheckBox[] checks) {
        this.checks = checks;
    }

    public ArrayList<EditText> getEd() {
        return ed;
    }

    public void setEd(ArrayList<EditText> ed) {
        this.ed = ed;
    }
}
