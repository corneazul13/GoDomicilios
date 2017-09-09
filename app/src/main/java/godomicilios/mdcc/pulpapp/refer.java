package godomicilios.mdcc.pulpapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import org.json.JSONArray;
import org.json.JSONObject;

import godomicilios.mdcc.pulpapp.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.pulpapp.settings.settings;

public class refer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public static GoogleAnalytics analytics;

    private static Tracker tracker;

    TextView phone;
    Button cancel, confirm;
    EditText code;
    TextView numberCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        phone = (TextView) findViewById(R.id.phone);
        cancel = (Button) findViewById(R.id.cancel);
        confirm = (Button) findViewById(R.id.confirm);
        code= (EditText) findViewById(R.id.code);
        phone.setText(settings.user.getPhone().toString());
        numberCar = (TextView) findViewById(R.id.numberCar);
        Integer car= settings.shoppingCar.carFinal.size();
        numberCar.setText(settings.user.getCarCant());
        settings.user.analytics = GoogleAnalytics.getInstance(this);
        settings.user.analytics.setLocalDispatchPeriod(1800);
        settings.user.tracker = settings.user.analytics.newTracker("UA-101326412-1");
        settings.user.tracker.enableExceptionReporting(true);
        settings.user.tracker.enableAdvertisingIdCollection(true);
        settings.user.tracker.enableAutoActivityTracking(true);

        try {
            httpCon("https://godomicilios.co/webService/servicios.php?svice=VERIFICAR_REFERIDO" +
                    "&metodo=json&usrId=" + settings.user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(refer.this, choose.class);
                startActivity(go);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    httpC("https://godomicilios.co/webService/servicios.php?" +
                            "svice=INSERTAR_REFERIDO&metodo=json&codigo="+
                            code.getText().toString()+"&usrId="+ settings.user.getId());
                } catch (Exception e) {
                    e.printStackTrace();
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
                    Intent go = new Intent(refer.this, car.class);
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
            Intent go = new Intent(refer.this,porfile.class);
            startActivity(go);

        } else if (id == R.id.referred) {
            Intent go = new Intent(refer.this,refer.class);
            startActivity(go);

        } else if (id == R.id.coupons) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void httpC (String url) throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this, new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(refer.this)));


        final ProgressDialog dialog = ProgressDialog.show(refer.this, "",
                "Loading. Please wait...", true);
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(JsonObjectRequest.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(JSONObject response) {
                        try{

                            if(response.getInt("insert")==0){
                                String abc = response.getString("error");

                                switch (abc){
                                    case "Ya tienes un referido registrado":
                                        Toast toast1 =
                                                Toast.makeText(getApplicationContext(),
                                                        response.getString("error"), Toast.LENGTH_SHORT);

                                        toast1.show();
                                        break;
                                    case "No puedes introducir tu propio código de referencia":
                                        Toast toast2 =
                                                Toast.makeText(getApplicationContext(),
                                                        response.getString("error"), Toast.LENGTH_SHORT);

                                        toast2.show();
                                        break;
                                }

                            }
                            else{
                                response.getInt("ganancia");
                            }

                           dialog.dismiss();}
                        catch (Exception e){

                            String mensajee ="No se pudo insrtar el código de referencia";

                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            mensajee, Toast.LENGTH_SHORT);

                            toast1.show();
                            dialog.dismiss();


                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                finish();
                startActivity(getIntent());
                dialog.dismiss();


            }
        }
        );
        queue.add(jsonObjectRequest);
    }


    public void httpCon (String url) throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this,  new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(refer.this)));


        final ProgressDialog dialog = ProgressDialog.show(refer.this, "",
                "Loading. Please wait...", true);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            if(response.length()>=1) {
                                for (int i = 0; i < response.length(); i++) {

                                    final JSONObject one = (JSONObject) response.getJSONObject(i);
                                    if(response.getString(i).isEmpty()){
                                        //are  coupons
                                        for (int j = 0; j < one.getJSONObject("gananciaReferido").length(); j++) {
                                            final JSONObject two = (JSONObject) one.getJSONObject("gananciaReferido");

                                            two.getString("cupon" + j + 1);

                                        }
                                    }
                                    else{
                                        //are points
                                        String points = one.getString("gananciaReferido");
                                        code.setText(points);

                                    }
                                }
                            }
                                else {

                            }


                            dialog.dismiss();


                        }
                        catch (Exception e){

                            String mensajee ="Oops ocurrio un error";

                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            mensajee, Toast.LENGTH_SHORT);

                            toast1.show();
                            dialog.dismiss();


                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                finish();
                startActivity(getIntent());
                dialog.dismiss();


            }
        }
        );
        queue.add(jsonArrayRequest);
    }
}
