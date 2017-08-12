package godomicilios.mdcc.godomiciliosc;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import godomicilios.mdcc.godomiciliosc.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.godomiciliosc.settings.settings;
import godomicilios.mdcc.godomiciliosc.settings.temporalCar;

public class coupons extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Button go;
    EditText code;
    public static GoogleAnalytics analytics;
    private static Tracker tracker;
    TextView numberCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        go = (Button) findViewById(R.id.go);
        LinearLayout linear = (LinearLayout) findViewById(R.id.li);
        code = (EditText) findViewById(R.id.code);
        numberCar = (TextView) findViewById(R.id.numberCar);
        Integer car= settings.shoppingCar.carFinal.size();
        numberCar.setText(settings.user.getCarCant());

        settings.user.analytics = GoogleAnalytics.getInstance(this);
        settings.user.analytics.setLocalDispatchPeriod(1800);
        settings.user.tracker = settings.user.analytics.newTracker("UA-101326412-1");
        settings.user.tracker.enableExceptionReporting(true);
        settings.user.tracker.enableAdvertisingIdCollection(true);
        settings.user.tracker.enableAutoActivityTracking(true);

        //settings.handleSSLHandshake();

        for(int i =0;i<settings.coupon.coupons.size();i++){

            View child = View.inflate(coupons.this, R.layout.coupon, null);
            ImageView one = (ImageView) child.findViewById(R.id.one);
            TextView text = (TextView) child.findViewById(R.id.text);
            Picasso.with(coupons.this)
                    .load("http://godomicilios.co/"+settings.coupon.coupons.get(i).getImage())
                    .into(one, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            //do smth when picture is loaded successfully
                        }

                        @Override
                        public void onError() {
                            //do smth when there is picture loading error
                        }
                    });

            if(settings.couponActivate.getStatus()==1){
                final int finalI = i;
                one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer h= settings.temporalCar.getIdClick();
                        Integer f= Integer.parseInt(settings.coupon.coupons.get(finalI).getPrice());

                        settings.couponActivate.setStatus(0);
                        settings.temporalCar.temporalCars.set(h, new temporalCar(
                                settings.temporalCar.temporalCars.get(h).getIdStablish(),
                                settings.temporalCar.temporalCars.get(h).getIdClick(),
                                f , settings.temporalCar.temporalCars.get(h).getMethod(),
                                settings.temporalCar.temporalCars.get(h).getSubtotal(),
                                settings.temporalCar.temporalCars.get(h).getDomiPrice()
                        ));
                        Intent go = new Intent (coupons.this, car.class);
                        startActivity(go);
                        finish();

                    }
                });
            }

            text.setText("$"+settings.coupon.coupons.get(i).getPrice());
            linear.addView(child);
        }

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(code.getText().toString().equals("")){
                    String mensajee ="Debes digitar un código";
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    mensajee, Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else{
                    try {
                        httpCon("https://godomicilios.co/webService/servicios.php?svice=CODIGO_CUPON&metodo=json&usr_id=102&codigo="+
                                code.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.head, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.car:

                if(settings.shoppingCar.carFinal.size()>0){
                    Intent go = new Intent(coupons.this, car.class);
                    startActivity(go);
                }
                else{
                    String mensajee ="Lo sentimos!\n" +
                            "Aun no has agregado productos a la canasta";

                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    mensajee, Toast.LENGTH_SHORT);

                    toast1.show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.porfile) {
            Intent go = new Intent(coupons.this,porfile.class);
            startActivity(go);

        } else if (id == R.id.referred) {
            Intent go = new Intent(coupons.this,refer.class);
            startActivity(go);

        } else if (id == R.id.coupons) {
            Intent go = new Intent(coupons.this,coupons.class);
            startActivity(go);


        } else if (id == R.id.logout) {
            Auth.GoogleSignInApi.signOut(settings.user.getGoogleApiClient()).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {

                    logOut();
                    settings.user.logouts();
                    goLoginScreen();
                }
            });

        } else if (id == R.id.method) {
            Intent go = new Intent(coupons.this,payM.class);
            startActivity(go);


        } else if (id == R.id.car) {

            Intent go = new Intent(coupons.this, car.class);
            startActivity(go);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void httpCon (final String url) throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(coupons.this)));

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{

                            if(response.length()<1){

                            }
                            else {

                                for (int i = 0; i < response.length(); i++) {
                                    final String res = (String) response.getString(i);

                                    if (isNumeric(res)==true) {

                                        String mensajee = "Código Redimido con éxito";

                                        Toast toast1 =
                                                Toast.makeText(getApplicationContext(),
                                                        mensajee, Toast.LENGTH_SHORT);

                                        toast1.show();
                                        finish();
                                        Intent go = new Intent (coupons.this, choose.class);
                                        startActivity(go);

                                    } else {
                                        String mensajee = "Usted ya registró este código";

                                        Toast toast1 =
                                                Toast.makeText(getApplicationContext(),
                                                        mensajee, Toast.LENGTH_SHORT);
                                        toast1.show();
                                    }
                                }
                            }
                        }

                        catch (Exception e){


                            String mensajee ="no se pudo registrar tu cupon, vuelve a intentarlo";

                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            mensajee, Toast.LENGTH_SHORT);

                            toast1.show();


                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                String mensaje = "no se pudo registrar tu cupon, vuelve a intentarlo";
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                mensaje, Toast.LENGTH_SHORT);

                toast1.show();

            }
        }
        );
        queue.add(jsonArrayRequest);


    }
    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    public void logOut() {
        Auth.GoogleSignInApi.signOut(settings.user.getGoogleApiClient()).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goLogInScreen();
                } else {
                    Toast.makeText(getApplicationContext(), "no cerrar sesion", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goLogInScreen() {
        Intent intent = new Intent(this, Splash.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, Splash.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
