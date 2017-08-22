package godomicilios.mdcc.godomiciliosc;

import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import godomicilios.mdcc.godomiciliosc.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.godomiciliosc.settings.ingredients;
import godomicilios.mdcc.godomiciliosc.settings.optionalIngredients;
import godomicilios.mdcc.godomiciliosc.settings.settings;
import me.omidh.liquidradiobutton.LiquidRadioButton;


public class testTwo extends AppCompatActivity
        {
            SeekBar seekBar;
            public float init_x;
            private ViewFlipper vf;
            Integer as=0;
            Date now = new Date();
            String format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US).format(now);
            String format2 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US).format(now);
            String format3 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format(now);
            long dayD;
            long hourH;
            long minM;
            long segS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
        Date fechaI = null, fechaF = null;
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        int millis = now.get(Calendar.MILLISECOND);

        String total = day+"/"+month+"/"+year+" "+ hour+":" +minute+";"+second;
        try {
            fechaI = simpleDateFormat.parse("22/8/2017 18:44:38");
            //fechaF puede ser la fecha actual o tu puedes asignarala,
            //por ejemplo: fechaF = simpleDateFormat.parse("2/6/2016 15:40:42");
            fechaF = new Date(System.currentTimeMillis());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Milli "+getDiferencia(fechaI , fechaF));

        if (dayD==0&&hourH==0&&minM<5){
            minM = (minM*60)+(segS);
            double d = 300-minM;
            System.out.println("Milli "+d);
        }



        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        DateTime jodaTime = new DateTime();
        Date fechaI = null;

        try {
            fechaI = simpleDateFormat.parse("8/22/2017 18:00:12");
            //fechaF puede ser la fecha actual o tu puedes asignarala,
            //por ejemplo: fechaF = simpleDateFormat.parse("2/6/2016 15:40:42");
        } catch (ParseException e) {
            e.printStackTrace();
        }

      *//*  DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMddHHmmss");
        String h = "jodaTime = " + formatter.print(jodaTime);*//*

        System.out.println("Milli "+getTimeInMilliSeconds("Thu Aug 22 18:05:12 CDT 2017"));


*/


        /*String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        String month= String.valueOf(calendar.get(Calendar.MONTH));

        String year = String.valueOf(calendar.get(Calendar.YEAR));

        String hour = String.valueOf(calendar.get(Calendar.HOUR));

        String minute = String.valueOf(calendar.get(Calendar.MINUTE));

        String second = String.valueOf(calendar.get(Calendar.SECOND));

        String hourComplete =day+"-"+month+"-"+year+"/"+hour+":"+minute+":"+second;
        String hourCompleteTwo = format1;
        String hourCompleteThree = format2;
        String hourCompleteFour = format3;*/

        final LiquidRadioButton appCompatCheckBox = (LiquidRadioButton) findViewById(R.id.appCompatCheckBox);

        appCompatCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (as==1){
                    appCompatCheckBox.setChecked(false);
                    as=0;
                }
                else{
                    appCompatCheckBox.setChecked(true);
                    as=1;
                }
            }
        });
       /* vf=(ViewFlipper) findViewById(R.id.viewFlipper);
        vf.setOnTouchListener(new testTwo.ListenerTouchViewFlipper());
        DiscreteSeekBar discreteSeekBar1 = (DiscreteSeekBar) findViewById(R.id.seekBar);
        discreteSeekBar1.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                if(value>6){
                    return 100;
                }
                else{
                    return value * 10;
                }
            }
        });
        try {
            ingredients();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ingredients();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

            private class ListenerTouchViewFlipper implements View.OnTouchListener{

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN: //Cuando el usuario toca la pantalla por primera vez
                            init_x=event.getX();
                            return true;
                        case MotionEvent.ACTION_UP: //Cuando el usuario deja de presionar
                            float distance =init_x-event.getX();

                            if(distance>0)
                            {
                                vf.setInAnimation(inFromRightAnimation());
                                vf.setOutAnimation(outToLeftAnimation());
                                vf.showPrevious();
                            }

                            if(distance<0)
                            {
                                vf.setInAnimation(inFromLeftAnimation());
                                vf.setOutAnimation(outToRightAnimation());
                                vf.showNext();
                            }

                        default:
                            break;
                    }

                    return false;
                }
            }

            private Animation inFromRightAnimation() {

                Animation inFromRight = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT,  +1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
                        Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f );

                inFromRight.setDuration(500);
                inFromRight.setInterpolator(new AccelerateInterpolator());

                return inFromRight;

            }

            private Animation outToLeftAnimation() {
                Animation outtoLeft = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, -1.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f);
                outtoLeft.setDuration(500);
                outtoLeft.setInterpolator(new AccelerateInterpolator());
                return outtoLeft;
            }

            private Animation inFromLeftAnimation() {
                Animation inFromLeft = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, -1.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f);
                inFromLeft.setDuration(100);
                inFromLeft.setInterpolator(new AccelerateInterpolator());
                return inFromLeft;
            }

            private Animation outToRightAnimation() {
                Animation outtoRight = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, +1.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f);
                outtoRight.setDuration(100);
                outtoRight.setInterpolator(new AccelerateInterpolator());
                return outtoRight;
            }

            /*public void ingredients () throws Exception{
                String url="https://godomicilios.co/webService/servicios.php?svice=INGREDIENTES&metodo=json&proId=1";


                final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                        null, CustomSSLSocketFactory.getSSLSocketFactory(testTwo.this)));

                JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(JsonObjectRequest.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                            @Override
                            public void onResponse(final JSONObject response) {
                                try{
                                    settings.ingredients.ingredientses= new ArrayList<>();
                                    JSONArray obligatory = response.getJSONArray("OBLIGATORIO");
                                    for (int o=0;o<obligatory.length();o++){
                                        final JSONObject obligator = (JSONObject) obligatory.getJSONObject(o);
                                        settings.ingredients.ingredientses.add(new ingredients(
                                                obligator.getInt("id"),obligator.getString("nombre"),obligator.getString("tipo_txt"),1,obligator.getInt("ingrediente_id")
                                        ));
                                    }
                                    Integer drink=settings.ingredients.ingredientses.size();

                                    *//*JSONArray normal = response.getJSONArray("NORMAL");
                                    for (int n=0;n<obligatory.length();n++){
                                        final JSONObject norma = (JSONObject) normal.getJSONObject(n);
                                        settings.ingredients.ingredientses.add(new ingredients(
                                                norma.getInt("id"),norma.getString("nombre"),norma.getString("tipo_txt"),3,norma.getInt("ingrediente_id")
                                        ));
                                    }*//*
                                    //JSONObject optional = response.getJSONObject("OPCIONAL");

                                }

                                catch (Exception e){

                                    String mensajee ="Error vuelve a intentarlo";
                                    Toast toast1 =
                                            Toast.makeText(getApplicationContext(),
                                                    mensajee, Toast.LENGTH_SHORT);
                                    toast1.show();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        try {
                            settings.ingredients.ingredientses= new ArrayList<>();
                            ingredients();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                );
                queue.add(jsonObjectRequest);


            }*/
            public long getTimeInMilliSeconds(String deadline){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault());
                long back_timer = 0;
                try {
                    Calendar calendar_deadline = Calendar.getInstance();
                    Calendar calendar_datenow = Calendar.getInstance();

                    Date date = format.parse(deadline);
                    calendar_deadline.setTime(date);

                    System.out.println("tiempo convertido: " + calendar_deadline.getTimeInMillis());

                    back_timer =  calendar_datenow.getTimeInMillis()-calendar_deadline.getTimeInMillis();
                    System.out.println("back_timer: " + back_timer);
                } catch (Exception ex) {
                    Log.e("Challenge(setDeadline)", "Error: " + ex.getMessage());
                }
                return back_timer;
            }

            public String getDiferencia(Date fechaInicial, Date fechaFinal){

                long diferencia = fechaFinal.getTime() - fechaInicial.getTime();

                Log.i("MainActivity", "fechaInicial : " + fechaInicial);
                Log.i("MainActivity", "fechaFinal : " + fechaFinal);

                long segsMilli = 1000;
                long minsMilli = segsMilli * 60;
                long horasMilli = minsMilli * 60;
                long diasMilli = horasMilli * 24;

                long diasTranscurridos = diferencia / diasMilli;
                diferencia = diferencia % diasMilli;

                long horasTranscurridos = diferencia / horasMilli;
                diferencia = diferencia % horasMilli;

                long minutosTranscurridos = diferencia / minsMilli;
                diferencia = diferencia % minsMilli;

                long segsTranscurridos = diferencia / segsMilli;
                dayD = diasTranscurridos;
                hourH = horasTranscurridos;
                minM = minutosTranscurridos;
                segS = segsTranscurridos;

                return "diasTranscurridos: " + diasTranscurridos + " , horasTranscurridos: " + horasTranscurridos +
                        " , minutosTranscurridos: " + minutosTranscurridos + " , segsTranscurridos: " + segsTranscurridos;


            }
}
