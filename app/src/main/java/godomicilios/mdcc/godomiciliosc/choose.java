package godomicilios.mdcc.godomiciliosc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.facebook.login.LoginManager;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.qintong.library.InsLoadingView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import godomicilios.mdcc.godomiciliosc.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.godomiciliosc.settings.coupon;
import godomicilios.mdcc.godomiciliosc.settings.settings;

public class choose extends AppCompatActivity {

    public static GoogleAnalytics analytics;

    private static Tracker tracker;

    ImageView restaurant, beer, med, pet, market, imageView10;
    public static int MILISEGUNDOS_ESPERA = 200;
    InsLoadingView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        restaurant = (ImageView) findViewById(R.id.restaurant);
        beer = (ImageView) findViewById(R.id.beer);
        med =(ImageView)findViewById(R.id.med);
        pet = (ImageView)findViewById(R.id.pet);
        market = (ImageView) findViewById(R.id.market);
        /*test = (InsLoadingView) findViewById(R.id.test);
        test.setStatus(InsLoadingView.Status.UNCLICKED);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setStatus(InsLoadingView.Status.LOADING);
                test.setStartColor(Color.YELLOW); //or your color
                test.setEndColor(Color.BLUE);
                test.setCircleDuration(2000);
                test.setRotateDuration(10000);
            }
        });
*/

        settings.shoppingCar.carFinal = new ArrayList<>();

        settings.user.analytics = GoogleAnalytics.getInstance(this);
        settings.user.analytics.setLocalDispatchPeriod(1800);
        settings.user.tracker = settings.user.analytics.newTracker("UA-101326412-1");
        settings.user.tracker.enableExceptionReporting(true);
        settings.user.tracker.enableAdvertisingIdCollection(true);
        settings.user.tracker.enableAutoActivityTracking(true);

        try {
            coupon("https://godomicilios.co/webService/servicios.php?svice=CUPONES_USR&metodo=json&usr_id="+ settings.user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.RotateOutDownRight)
                        .duration(600)
                        .repeat(0)
                        .playOn(restaurant);
                esperarYCerrar(restaurant,1);

            }
        });

        beer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YoYo.with(Techniques.RotateOutDownRight)
                        .duration(600)
                        .repeat(0)
                        .playOn(beer);
                esperarYCerrar(beer,2);


            }
        });
        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.RotateOutDownRight)
                        .duration(600)
                        .repeat(0)
                        .playOn(med);
                esperarYCerrar(med,3);

            }
        });
        pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.RotateOutDownRight)
                        .duration(600)
                        .repeat(0)
                        .playOn(pet);
                esperarYCerrar(pet,4);

            }
        });
        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.RotateOutDownRight)
                        .duration(600)
                        .repeat(0)
                        .playOn(market);
                esperarYCerrar(market,5);

            }
        });


        /*if (AccessToken.getCurrentAccessToken() == null) {
            goLoginScreen();
        }*/

    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, test.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    private void reloadActivity() {
        Intent refresh = new Intent(this, choose.class);
        startActivity(refresh);
        this.finish(); //
    }

    public void coupon (final String url) throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(choose.this)));

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{
                            settings.coupon.coupons= new ArrayList<>();


                            if(response.length()<1){

                            }
                            else{
                                for(int i =0;i<response.length();i++) {

                                    final JSONObject coupo = (JSONObject) response.getJSONObject(i);
                                    settings.coupon.coupons.add(new coupon(
                                            coupo.getInt("id_cupon"),
                                            coupo.getString("imagen_cupon"),
                                            coupo.getString("valor_cupon")
                                    ));
                                }
                            }
                        }

                        catch (Exception e){


                            String mensajee ="";

                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            mensajee, Toast.LENGTH_SHORT);

                            toast1.show();


                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        queue.add(jsonArrayRequest);


    }
    public void categor (final String url, final Integer nu, final Class clas) throws Exception{

        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(choose.this)));

                restaurant.setEnabled(false);
                beer.setEnabled(false);
                med.setEnabled(false);
                pet.setEnabled(false);
                market.setEnabled(false);


        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{
                            settings.categories = new ArrayList<String>();
                            settings.categories.add("CATEGORIA");
                            if(response.length()<1){

                            }
                            else{
                                for(int i = 0; i<response.length(); i++) {


                                    final JSONObject product = (JSONObject) response.getJSONObject(i);

                                    settings.categories.add(new String(
                                            product.getString("nombre_categoria")
                                    ));
                                }
                            }

                            Intent go = new Intent(choose.this, clas);
                            startActivity(go);
                            choose.this.finish();
                        }
                        catch (Exception e){
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    categor("https://godomicilios.co/webService/servicios.php?svice=CATALOGOS&metodo=json&lat="
                            +settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa="+ nu.toString()
                            , nu, clas);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        );
        queue.add(jsonArrayRequest);
    }


    public void esperarYCerrar(final ImageView ima, final Integer option ) {

        int milisegundos=MILISEGUNDOS_ESPERA;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
                switch (option){
                    case 1:
                        ima.setImageDrawable(getResources().getDrawable(R.drawable.arestaurantexdos));
                        settings.stablishment.setId(1);
                        try {
                            categor("https://godomicilios.co/webService/servicios.php?svice=CATALOGOS&metodo=json&lat="
                                    +settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa=1",1, head.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;
                    case 2:
                        ima.setImageDrawable(getResources().getDrawable(R.drawable.alicorxdos));
                        settings.stablishment.setId(2);
                        try {
                            categor("https://godomicilios.co/webService/servicios.php?svice=CATALOGOS&metodo=json&lat="
                                    +settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa=2",2, headTwo.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        ima.setImageDrawable(getResources().getDrawable(R.drawable.amedicinaxdos));
                        settings.stablishment.setId(3);

                        try {
                            categor("https://godomicilios.co/webService/servicios.php?svice=CATALOGOS&metodo=json&lat="
                                    +settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa=3",3, headThree.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        ima.setImageDrawable(getResources().getDrawable(R.drawable.amascotaxdos));
                        settings.stablishment.setId(4);
                        try {
                            categor("https://godomicilios.co/webService/servicios.php?svice=CATALOGOS&metodo=json&lat="
                                    +settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa=4",4, headFour.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 5:
                        ima.setImageDrawable(getResources().getDrawable(R.drawable.amercadoxdos));
                        settings.stablishment.setId(5);
                        try {
                            categor("https://godomicilios.co/webService/servicios.php?svice=CATALOGOS&metodo=json&lat="
                                    +settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa=5",5, headFive.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }

                YoYo.with(Techniques.RotateInUpRight)
                        .duration(600)
                        .repeat(0)
                        .playOn(ima);
            }
        }, milisegundos);
    }
}
