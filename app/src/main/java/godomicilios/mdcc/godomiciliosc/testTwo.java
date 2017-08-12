package godomicilios.mdcc.godomiciliosc;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import godomicilios.mdcc.godomiciliosc.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.godomiciliosc.settings.ingredients;
import godomicilios.mdcc.godomiciliosc.settings.settings;
import me.omidh.liquidradiobutton.LiquidRadioButton;


public class testTwo extends AppCompatActivity
        {
            SeekBar seekBar;
            public float init_x;
            private ViewFlipper vf;
            Integer as=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink);
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
    public void nothing (){
       /* for(int namess=0;namess<names.size();namess++){
            //add names categor
            for(int opt=0;opt<countIngreOptio.size();opt++){
                //add optionals
            }
        }*/
    }
}
