package godomicilios.mdcc.godomiciliosc.settings;

import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 01/08/2017.
 */

public class answerOrder {
    private Integer id;
    private CountDownTimer countDownTimer;
    private TextView text;
    public ArrayList<answerOrder> answerOrders;

    public answerOrder (Integer id, CountDownTimer countDownTimer, TextView text){
        this.setId(id);
        this.setCountDownTimer(countDownTimer);
        this.setText(text);
    }
    public answerOrder(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CountDownTimer getCountDownTimer() {
        return countDownTimer;
    }

    public void setCountDownTimer(CountDownTimer countDownTimer) {
        this.countDownTimer = countDownTimer;
    }

    public TextView getText() {
        return text;
    }

    public void setText(TextView text) {
        this.text = text;
    }
}
