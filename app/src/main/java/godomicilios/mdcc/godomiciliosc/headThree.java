package godomicilios.mdcc.godomiciliosc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import godomicilios.mdcc.godomiciliosc.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.godomiciliosc.settings.product;
import godomicilios.mdcc.godomiciliosc.settings.rank;
import godomicilios.mdcc.godomiciliosc.settings.settings;
import godomicilios.mdcc.godomiciliosc.settings.stablishment;

public class headThree extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener  {


    private LinearLayout layout;
    private ScrollView scrollView;
    public float init_x;
    BigDecimal distanced;
    private ViewFlipper vf;
    Integer count=0;
    String url = "";
    Spinner catego;
    Button time, prices, methodPay, promotions;
    TextView numberCar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_three);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LinearLayout beer = (LinearLayout) findViewById(R.id.beer);
        LinearLayout food =(LinearLayout) findViewById(R.id.food);
        LinearLayout pet =(LinearLayout) findViewById(R.id.pet);
        LinearLayout market = (LinearLayout) findViewById(R.id.market);
        final Spinner catego = (Spinner) findViewById(R.id.catego);
        time = (Button) findViewById(R.id.time);
        prices = (Button) findViewById(R.id.prices);
        methodPay = (Button) findViewById(R.id.methodPay);
        promotions = (Button) findViewById(R.id.promotions);
        numberCar = (TextView) findViewById(R.id.numberCar);
        Integer car= settings.shoppingCar.carFinal.size();
        numberCar.setText(settings.user.getCarCant());
        settings.stablishment.setId(3);
        addItemsOnSpinner2();
        addListenerOnButton();

        settings.user.analytics = GoogleAnalytics.getInstance(this);
        settings.user.analytics.setLocalDispatchPeriod(1800);
        settings.user.tracker = settings.user.analytics.newTracker("UA-101326412-1");
        settings.user.tracker.enableExceptionReporting(true);
        settings.user.tracker.enableAdvertisingIdCollection(true);
        settings.user.tracker.enableAutoActivityTracking(true);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_three_select));
                prices.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_three));
                methodPay.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_three));
                promotions.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_three));
                try {
                    httpC("https://godomicilios.co/webService/servicios.php?svice=FILTRO_MEJOR_TIEMPO&metodo=json&lat="
                            +settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa=3");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        prices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_three));
                prices.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_three_select));
                methodPay.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_three));
                promotions.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_three));
                try {
                    httpC("https://godomicilios.co/webService/servicios.php?svice=FILTRO_PRECIOS&metodo=json&lat="
                            +settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa=3");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        methodPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_three));
                prices.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_three));
                methodPay.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_three_select));
                promotions.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_three));
            }
        });

        promotions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_three));
                prices.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_three));
                methodPay.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_three));
                promotions.setBackgroundDrawable(getResources().getDrawable(R.drawable.corners_three_select));
                try {
                    httpC("https://godomicilios.co/webService/servicios.php?svice=POSICIONAMIENTO_EMPRESAS&metodo=json&lat="
                            +settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa=3");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        settings.product.products= new ArrayList<>();


        //settings.handleSSLHandshake();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        settings.user.setGoogleApiClient(new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build());
        settings.order.setReloadActivity(1);


        for (int i =0;i<5;i++){

        }


        food.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent go = new Intent(headThree.this, head.class);
                startActivity(go);

            }
        });

        pet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent go = new Intent(headThree.this, headFour.class);
                startActivity(go);

            }
        });

        market.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent go = new Intent(headThree.this, headFive.class);
                startActivity(go);

            }
        });


        beer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent go = new Intent(headThree.this, headTwo.class);
                startActivity(go);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(settings.user.getaBoolean()==false){
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_stablishm_drawer);
        }

    }

    @Override
    public void onBackPressed() {
        Intent go = new Intent(headThree.this, choose.class);
        startActivity(go);
        this.finish();
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
                    Intent go = new Intent(headThree.this, car.class);
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
            Intent go = new Intent(headThree.this,porfile.class);
            startActivity(go);

        } else if (id == R.id.referred) {
            Intent go = new Intent(headThree.this,refer.class);
            startActivity(go);

        } else if (id == R.id.coupons) {
            Intent go = new Intent(headThree.this,coupons.class);
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
            Intent go = new Intent(headThree.this,payM.class);
            startActivity(go);


        } else if (id == R.id.car) {

            Intent go = new Intent(headThree.this, car.class);
            startActivity(go);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void httpC (String url) throws Exception{

        final LinearLayout linear = (LinearLayout) findViewById(R.id.li);
        linear.removeAllViews();

        settings.stablishment.stablishments=null;
        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(headThree.this)));


        final ProgressDialog dialog = ProgressDialog.show(headThree.this, "",
                "Loading. Please wait...", true);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(JSONArray response) {
                        try{

                            settings.stablishment.stablishments= new ArrayList<>();

                            for(int i =0;i<response.length();i++) {

                                final JSONObject address = (JSONObject) response.getJSONObject(i);

                                settings.stablishment.stablishments.add(new stablishment(3,
                                        address.getInt("id_sucursal"),
                                        address.getString("nombre"),
                                        address.getString("direccion_mapa"),
                                        address.getString("csto_domicilio"),
                                        address.getString("estado"),
                                        address.getString("imagen_corporativa"),
                                        address.getString("nombre_sucursal"),
                                        address.getString("pedido_minimo"),
                                        CalculationByDistance(address.getDouble("latitud"),
                                                settings.order.getLatitude(),
                                                address.getDouble("longitud"),
                                                settings.order.getLongitude()),
                                        duration(), address.getInt("empresa_id"),
                                        Math.round(address.getInt("estrellas_sucursal")*2),
                                        address.getString("categorias_products")
                                ));

                                LinearLayout linear = (LinearLayout) findViewById(R.id.li);
                                View child = View.inflate(headThree.this, R.layout.li, null);

                                ImageView im = (ImageView) child.findViewById(R.id.imageRestaurant);
                                TextView name = (TextView) child.findViewById(R.id.name);
                                TextView branch = (TextView) child.findViewById(R.id.branch);
                                TextView addressbranch = (TextView) child.findViewById(R.id.addressbranch);
                                TextView price = (TextView) child.findViewById(R.id.price);
                                ImageView one1 = (ImageView) child.findViewById(R.id.one);
                                ImageView two1 = (ImageView) child.findViewById(R.id.two);
                                ImageView three1 = (ImageView) child.findViewById(R.id.three);
                                ImageView four1 = (ImageView) child.findViewById(R.id.four);
                                ImageView five1 = (ImageView) child.findViewById(R.id.five);
                                LinearLayout d = (LinearLayout) child.findViewById(R.id.d);
                                final Button buttons = (Button) child.findViewById(R.id.buttons);
                                final LinearLayout main = (LinearLayout)child.findViewById(R.id.main);
                                Integer cantStars=address.getInt("estrellas_sucursal") ;
                                Integer cantt = Math.round(cantStars*2);

                                stars(cantt,one1, two1, three1, four1, five1);
                                name.setText(address.getString("nombre"));
                                one1.setImageDrawable(getResources().getDrawable(R.drawable.estrellaverdevacia));
                                two1.setImageDrawable(getResources().getDrawable(R.drawable.estrellaverdevacia));
                                three1.setImageDrawable(getResources().getDrawable(R.drawable.estrellaverdevacia));
                                four1.setImageDrawable(getResources().getDrawable(R.drawable.estrellaverdevacia));
                                five1.setImageDrawable(getResources().getDrawable(R.drawable.estrellaverdevacia));
                                name.setTextColor(getResources().getColor(R.color.blueFarmacy));
                                d.setBackgroundColor(getResources().getColor(R.color.blueFarmacy));

                                branch.setText(address.getString("nombre_sucursal"));
                                addressbranch.setText(address.getString("direccion_mapa"));
                                price.setText("Pedido mínimo $" + address.getString("csto_domicilio"));

                                main.setId(i);

                                if (address.getString("estadoEstablecimiento").equals("ABIERTO")) {

                                } else{
                                    buttons.setBackgroundColor(getResources().getColor(R.color.redLast));
                                    buttons.setText("CERRADO");
                                    main.setEnabled(false);

                                }
                                settings.product.products = new ArrayList<>();

                                main.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        try {
                                            settings.rank.setIdStablishment(address.getInt("empresa_id"));
                                            settings.stablishment.setNumber(main.getId());
                                            if (settings.product.getStablishSelection() == null) {

                                                settings.product.setStablishSelection(main.getId());
                                                settings.product.setConfirm(0);

                                            } else {
                                                if (settings.product.getStablishSelection() == main.getId()) {
                                                    settings.product.setConfirm(1);
                                                } else {
                                                    settings.product.setStablishSelection(main.getId());
                                                    settings.product.setConfirm(0);
                                                }
                                            }

                                            try {
                                                httpRank("https://godomicilios.co/webService/servicios.php?svice=CATALOGO&metodo=json&categorias=" + settings.stablishment.stablishments.get(main.getId()).getProductRank(),main.getId());
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        } catch(JSONException e){
                                            e.printStackTrace();
                                        }

                                    }
                                });
                                linear.addView(child); //attach to your item
                                Picasso.with(headThree.this)
                                        .load("http://godomicilios.co/admin/documentosVarios/"+address.getString("imagen_corporativa"))
                                        .into(im, new com.squareup.picasso.Callback() {
                                            @Override
                                            public void onSuccess() {
                                                //do smth when picture is loaded successfully
                                            }

                                            @Override
                                            public void onError() {
                                                //do smth when there is picture loading error
                                            }
                                        });

                                if(i%2==0){
                                    child.setBackgroundColor(getResources().getColor(R.color.gray));
                                }
                                else{
                                    child.setBackgroundColor(Color.WHITE);
                                }
                                LinearLayout lm =(LinearLayout) findViewById(R.id.li);
                            }
                            dialog.dismiss();
                        }
                        catch (Exception e){

                            String mensajee ="No hay establecimientos cerca";

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

                try {
                    httpC("https://godomicilios.co/webService/servicios.php?svice=EMPRESAS&metodo=json&lat="+settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa=3");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();


            }
        }
        );
        queue.add(jsonArrayRequest);
    }
    public String CalculationByDistance(double lat1, double lat2, double lon1, double lon2 ) {
        int Radius = 6371;// radio de la tierra en  kilómetros
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        double total= Radius * c;

        double valor = total;
        String val = valor+"";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(2, RoundingMode.HALF_UP);
        distanced = big;
        return "Distancia: "+big.toString()+" Km";
    }

    public String duration (){

        Integer h = ((Integer.valueOf(distanced.intValue())*60)/10);
        return "Duración: "+h.toString()+" mins Aprox.";
    }

    public void httpRank (final String url,final Integer id) throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(headThree.this)));



        final ProgressDialog dialog = ProgressDialog.show(headThree.this, "",
                "Loading. Please wait...", true);
        settings.user.setProgressDialog(dialog);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{

                            settings.rank.ranks = new ArrayList<>();
                            settings.product.products= new ArrayList<>();
//                            in = new ArrayList<>();

                            for(int i =0;i<response.length();i++) {

                                View childaa = View.inflate(headThree.this, R.layout.rank, null);
                                LinearLayout clickaa = (LinearLayout) childaa.findViewById(R.id.click);
                                TextView nameaa = (TextView) childaa.findViewById(R.id.name);
                                LinearLayout visibilityaa = (LinearLayout) childaa.findViewById(R.id.lii);

                                final JSONObject ranks = (JSONObject) response.getJSONObject(i);

                                settings.rank.ranks.add(new rank(ranks.getInt("id_categoria"),
                                        ranks.getInt("empresa_id"),
                                        ranks.getString("nombre_categoria"),
                                        ranks.getInt("estado"),0)
                                );
                            }
                            final JsonArrayRequest jsonArrayRequests= new JsonArrayRequest(JsonArrayRequest.Method.GET, "https://godomicilios.co/webService/servicios.php?svice=PRODUCTOS&metodo=json&sucId="
                                    +settings.stablishment.stablishments.get(id).getId()+"&empId="+settings.stablishment.stablishments.get(id).getId_Stablish(), null,
                                    new Response.Listener<JSONArray>() {
                                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                                        @Override
                                        public void onResponse(JSONArray response) {
                                            try{
                                                for(int i = 0; i<response.length(); i++) {



                                                    final JSONObject product = (JSONObject) response.getJSONObject(i);

                                                    settings.product.products.add(new product(product.getInt("id_producto"),
                                                            product.getInt("empre"),
                                                            product.getInt("categoria_id"),
                                                            product.getString("nombre_producto"),
                                                            product.getString("descripcion_producto"),
                                                            product.getInt("valor_producto"),
                                                            pictureValidator(product.getString("foto_producto")),
                                                            0,0,
                                                            0, 0,
                                                            product.getInt("tipo_bebida"),
                                                            product.getInt("bebida_modificable")
                                                    ));
                                                }

                                                Intent go = new Intent(headThree.this, stablishm.class);
                                                startActivity(go);

                                            }
                                            catch (Exception e){

                                                String mensajee ="No hay productos disponibles";

                                                Toast toast1 =
                                                        Toast.makeText(getApplicationContext(),
                                                                mensajee, Toast.LENGTH_SHORT);

                                                toast1.show();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    String mensaje = "Oops algo salió mal, intentalo nuevamente";
                                    Toast toast1 =
                                            Toast.makeText(getApplicationContext(),
                                                    mensaje, Toast.LENGTH_SHORT);

                                    toast1.show();
                                }
                            }
                            );
                            queue.add(jsonArrayRequests);
                            dialog.dismiss();


                        }
                        catch (Exception e){

                            String mensajee ="No hay establecimientos cerca";

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

    public String pictureValidator (String url){
        if(url.length()<3){
            return "a";
        }
        else{
            return url;
        }
    }

    public void stars (Integer i, ImageView one, ImageView two, ImageView three,
                       ImageView four, ImageView five){
        switch (i){
            case 1:
                one.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdemitad));
                break;
            case 2:
                one.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                break;
            case 3:
                one.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                two.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdemitad));
                break;
            case 4:
                one.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                two.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                break;
            case 5:
                one.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                two.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                three.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdemitad));
                break;
            case 6:
                one.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                two.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                three.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                break;
            case 7:
                one.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                two.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                three.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                four.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdemitad));
                break;
            case 8:
                one.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                two.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                three.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                four.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                break;
            case 9:
                one.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                two.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                three.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                four.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                five.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdemitad));
                break;
            case 10:
                one.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                two.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                three.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                four.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                five.setImageDrawable(getResources().getDrawable(R.drawable.medianaverdecompleta));
                break;
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void categor (final String url, final Integer nu, final Class clas) throws Exception{

        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(headThree.this)));

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

                            Intent go = new Intent(headThree.this, clas);
                            startActivity(go);
                            headThree.this.finish();
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

    public void addItemsOnSpinner2() {

        catego = (Spinner) findViewById(R.id.catego);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, settings.categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catego.setAdapter(dataAdapter);
        catego.setOnItemSelectedListener(new headThree.addressSpinnerClassOne());
    }
    class addressSpinnerClassOne implements AdapterView.OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
        {
            if (settings.categories.get(position)=="CATEGORIA")
            {
                try {
                    httpC("https://godomicilios.co/webService/servicios.php?svice=EMPRESAS&metodo=json&lat="
                            +settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa=3");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    categorSpinner("https://godomicilios.co/webService/servicios.php?svice=FILTRO_CATEGORIA&metodo=json&lat="
                            + settings.order.getLatitude() + "&long=" + settings.order.getLongitude() +
                            "&tipo_empresa=3&categoria=" + settings.categories.get(position).replace(" ", "%20")
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    public void addListenerOnButton() {

        catego = (Spinner) findViewById(R.id.catego);


    }

    public void categorSpinner (final String url) throws Exception{
        final LinearLayout linear = (LinearLayout) findViewById(R.id.li);
        linear.removeAllViews();

        settings.stablishment.stablishments=null;

        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(headThree.this)));
        final ProgressDialog dialog = ProgressDialog.show(headThree.this, "",
                "Loading. Please wait...", true);

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{
                            settings.stablishment.stablishments= new ArrayList<>();

                            for(int i =0;i<response.length();i++) {

                                final JSONObject address = (JSONObject) response.getJSONObject(i);

                                settings.stablishment.stablishments.add(new stablishment(3,
                                        address.getInt("id_sucursal"),
                                        address.getString("nombre"),
                                        address.getString("direccion_mapa"),
                                        address.getString("csto_domicilio"),
                                        address.getString("estado"),
                                        address.getString("imagen_corporativa"),
                                        address.getString("nombre_sucursal"),
                                        address.getString("pedido_minimo"),
                                        CalculationByDistance(address.getDouble("latitud"),
                                                settings.order.getLatitude(),
                                                address.getDouble("longitud"),
                                                settings.order.getLongitude()),
                                        duration(),address.getInt("empresa_id"),
                                        Math.round(address.getInt("estrellas_sucursal")*2),
                                        address.getString("categorias_products")));


                                View child = View.inflate(headThree.this, R.layout.li, null);

                                ImageView im = (ImageView) child.findViewById(R.id.imageRestaurant);
                                TextView name = (TextView) child.findViewById(R.id.name);
                                TextView branch = (TextView) child.findViewById(R.id.branch);
                                TextView addressbranch = (TextView) child.findViewById(R.id.addressbranch);
                                TextView price = (TextView) child.findViewById(R.id.price);
                                ImageView one1 = (ImageView) child.findViewById(R.id.one);
                                ImageView two1 = (ImageView) child.findViewById(R.id.two);
                                ImageView three1 = (ImageView) child.findViewById(R.id.three);
                                ImageView four1 = (ImageView) child.findViewById(R.id.four);
                                ImageView five1 = (ImageView) child.findViewById(R.id.five);
                                final Button buttons = (Button) child.findViewById(R.id.buttons);
                                final LinearLayout main = (LinearLayout)child.findViewById(R.id.main);
                                Integer cantStars=address.getInt("estrellas_sucursal") ;
                                Integer cantt = Math.round(cantStars*2);

                                stars(cantt,one1, two1, three1, four1, five1);
                                name.setText(address.getString("nombre"));
                                branch.setText(address.getString("nombre_sucursal"));
                                addressbranch.setText(address.getString("direccion_mapa"));
                                price.setText("Pedido mínimo $" + address.getString("csto_domicilio"));

                                main.setId(i);

                                if (address.getString("estadoEstablecimiento").equals("ABIERTO")) {

                                } else{
                                    buttons.setBackgroundColor(getResources().getColor(R.color.redGo));
                                    buttons.setText("CERRADO");
                                    main.setEnabled(false);

                                }

                                buttons.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        try {
                                            settings.rank.setIdStablishment(address.getInt("empresa_id"));
                                            settings.stablishment.setNumber(main.getId());
                                            if (settings.product.getStablishSelection() == null) {

                                                settings.product.setStablishSelection(main.getId());
                                                settings.product.setConfirm(0);

                                            } else {
                                                if (settings.product.getStablishSelection() == main.getId()) {
                                                    settings.product.setConfirm(1);
                                                } else {
                                                    settings.product.setStablishSelection(main.getId());
                                                    settings.product.setConfirm(0);
                                                }
                                            }

                                            try {
                                                httpRank("https://godomicilios.co/webService/servicios.php?svice=CATALOGO&metodo=json&empId=" + settings.stablishment.stablishments.get(main.getId()).getProductRank(), main.getId());
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        } catch(JSONException e){
                                            e.printStackTrace();
                                        }

                                    }
                                });

                                main.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        try {
                                            settings.rank.setIdStablishment(address.getInt("empresa_id"));
                                            settings.stablishment.setNumber(main.getId());
                                            if (settings.product.getStablishSelection() == null) {

                                                settings.product.setStablishSelection(main.getId());
                                                settings.product.setConfirm(0);

                                            } else {
                                                if (settings.product.getStablishSelection() == main.getId()) {
                                                    settings.product.setConfirm(1);
                                                } else {
                                                    settings.product.setStablishSelection(main.getId());
                                                    settings.product.setConfirm(0);
                                                }
                                            }

                                            try {
                                                httpRank("https://godomicilios.co/webService/servicios.php?svice=CATALOGO&metodo=json&categorias=" + settings.stablishment.stablishments.get(main.getId()).getProductRank(),main.getId());
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        } catch(JSONException e){
                                            e.printStackTrace();
                                        }

                                    }
                                });
                                linear.addView(child); //attach to your item

                                Picasso.with(headThree.this)
                                        .load("http://godomicilios.co/admin/documentosVarios/"+address.getString("imagen_corporativa"))
                                        .into(im, new com.squareup.picasso.Callback() {
                                            @Override
                                            public void onSuccess() {
                                                //do smth when picture is loaded successfully
                                            }

                                            @Override
                                            public void onError() {
                                                //do smth when there is picture loading error
                                            }
                                        });


                                if(i%2==0){
                                    child.setBackgroundColor(getResources().getColor(R.color.gray));
                                }
                                else{
                                    child.setBackgroundColor(Color.WHITE);
                                }


                                LinearLayout lm =(LinearLayout) findViewById(R.id.li);

                            }


                            dialog.dismiss();
                        }
                        catch (Exception e){
                        }
                        dialog.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dialog.dismiss();
            }
        }
        );
        queue.add(jsonArrayRequest);
    }

}
