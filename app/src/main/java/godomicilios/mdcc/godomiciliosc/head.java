package godomicilios.mdcc.godomiciliosc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.login.LoginManager;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

import godomicilios.mdcc.godomiciliosc.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.godomiciliosc.settings.product;
import godomicilios.mdcc.godomiciliosc.settings.rank;
import godomicilios.mdcc.godomiciliosc.settings.settings;
import godomicilios.mdcc.godomiciliosc.settings.stablishment;
import godomicilios.mdcc.godomiciliosc.settings.user;

public class head extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

   private Context context;
    private LinearLayout layout;
    private ScrollView scrollView;
    public float init_x;
    BigDecimal distanced;
    private ViewFlipper vf;
    Integer count=0;
    Spinner catego;
    Integer as =0;
    String url = "";
    Button time, prices, methodPay, promotions;
    TextView numberCar;
    public static final String MyPREFERENCES= "myPreferences";
    public static final String Status = "status";
    SharedPreferences sharedpreferences;
    CoordinatorLayout coordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context=this;
        layout = (LinearLayout)findViewById(R.id.li);
        vf = (ViewFlipper) findViewById(R.id.viewFlipper);
        LinearLayout beer = (LinearLayout) findViewById(R.id.beer);
        LinearLayout pharmacy =(LinearLayout) findViewById(R.id.pharmacy);
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
        settings.stablishment.setId(1);
        //DataBaseHelper MDB = new DataBaseHelper(getApplicationContext());
        addItemsOnSpinner2();
        addListenerOnButton();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);


        user.analytics = GoogleAnalytics.getInstance(this);
        user.analytics.setLocalDispatchPeriod(1800);
        user.tracker = user.analytics.newTracker("UA-101326412-1");
        user.tracker.enableExceptionReporting(true);
        user.tracker.enableAdvertisingIdCollection(true);
        user.tracker.enableAutoActivityTracking(true);


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setBackground(ContextCompat.getDrawable(context, R.drawable.corner_select));
                prices.setBackground(ContextCompat.getDrawable(context,R.drawable.corners));
                methodPay.setBackground(ContextCompat.getDrawable(context,R.drawable.corners));
                promotions.setBackground(ContextCompat.getDrawable(context,R.drawable.corners));
                try {
                    httpC("https://godomicilios.co/webService/servicios.php?svice=FILTRO_MEJOR_TIEMPO&metodo=json&lat="
                            +settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa=1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        prices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.corners));
                prices.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.corner_select));
                methodPay.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.corners));
                promotions.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.corners));
                try {
                    httpC("https://godomicilios.co/webService/servicios.php?svice=FILTRO_PRECIOS&metodo=json&lat="
                            +settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa=1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        methodPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.corners));
                prices.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.corners));
                methodPay.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.corner_select));
                promotions.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.corners));

            }
        });

        promotions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.corners));
                prices.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.corners));
                methodPay.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.corners));
                promotions.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.corner_select));
                try {
                    httpC("https://godomicilios.co/webService/servicios.php?svice=POSICIONAMIENTO_EMPRESAS&metodo=json&lat="
                            +settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa=1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //settings.handleSSLHandshake();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        settings.user.setGoogleApiClient(new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build());
        settings.order.setReloadActivity(1);

        pharmacy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent go = new Intent(head.this, headThree.class);
                startActivity(go);

            }
        });

        pet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent go = new Intent(head.this, headFour.class);
                startActivity(go);
            }
        });

        market.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent go = new Intent(head.this, headFive.class);
                startActivity(go);
            }
        });

        beer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent go = new Intent(head.this, headTwo.class);
                startActivity(go);
            }
        });

//        vf.setOnTouchListener(new head.ListenerTouchViewFlipper());


       /* try {
            httpC("https://godomicilios.co/webService/servicios.php?svice=EMPRESAS&metodo=json&lat="
                    +settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa=1");
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(!settings.user.getaBoolean()){
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_stablishm_drawer);
        }
    }

    @Override
    public void onBackPressed() {
        Intent go = new Intent(head.this, choose.class);
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
                        Intent go = new Intent(head.this, car.class);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.porfile) {
            Intent go = new Intent(head.this,porfile.class);
            startActivity(go);

        } else if (id == R.id.referred) {
            Intent go = new Intent(head.this,refer.class);
            startActivity(go);

        } else if (id == R.id.coupons) {
            Intent go = new Intent(head.this,coupons.class);
            startActivity(go);


        } else if (id == R.id.logout) {


            /*if(settings.user!= null&& settings.user.getPassword()!=null){
                DataBaseHelper MDB = new DataBaseHelper(getApplicationContext());

                MDB.modificarCONTACTO(settings.user.getUser()
                        , settings.user.getPassword(), 0);
            }*/
            logout2();

            Auth.GoogleSignInApi.signOut(settings.user.getGoogleApiClient()).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {

                    String n  = "status";
                    String ph  = settings.user.getPassword();
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(Status, n);
                    editor.apply();
                    logOut();
                    settings.user.logouts();
                    goLoginScreen();
                }
            });

        } else if (id == R.id.method) {
            Intent go = new Intent(head.this,payM.class);
            startActivity(go);


        } else if (id == R.id.car) {

            Intent go = new Intent(head.this, car.class);
            startActivity(go);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void httpC (String url) throws Exception{
        LinearLayout linear = (LinearLayout) findViewById(R.id.li);
        linear.removeAllViews();

        settings.stablishment.stablishments=null;


        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(head.this)));


        final ProgressDialog dialog = ProgressDialog.show(head.this, "",
                "Loading. Please wait...", true);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(JSONArray response) {
                        try{

                            settings.stablishment.stablishments= new ArrayList<>();

                            for(int i =0;i<response.length();i++) {

                                final JSONObject address = response.getJSONObject(i);

                                settings.stablishment.stablishments.add(new stablishment(1,
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
                                        Math.round(address.getInt("estrellas_sucursal")*2)));

                                LinearLayout linear = (LinearLayout) findViewById(R.id.li);
                                View child = View.inflate(head.this, R.layout.li, null);

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
                                    buttons.setBackgroundColor(ContextCompat.getColor(context,R.color.redLast));
                                    buttons.setText("CERRADO");
                                    main.setEnabled(false);
                                }

                            main.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String mensajee ="Un momento\n" +
                                            "Redirigiendo...";

                                    Toast toast1 =
                                            Toast.makeText(getApplicationContext(),
                                                    mensajee, Toast.LENGTH_SHORT);

                                    toast1.show();
                                    coordinator.setEnabled(false);
                                    settings.product.products=null;
                                    settings.product.products= new ArrayList<>();
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
                                                httpRank("https://godomicilios.co/webService/servicios.php?svice=CATALOGO&metodo=json&empId=" + settings.rank.getIdStablishment());
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        } catch(JSONException e){
                                            e.printStackTrace();
                                        }

                                }
                            });
                                 linear.addView(child); //attach to your item

                                String imgUrl ="http://godomicilios.co/admin/documentosVarios/"+address.getString("imagen_corporativa");
                                Picasso.with(head.this)
                                        .load(imgUrl)
                                        .into(im, new com.squareup.picasso.Callback() {
                                            @Override
                                            public void onSuccess() {
                                                //do smth when picture is loaded successfully
                                                String h="";
                                                if(Objects.equals(h, "")){}
                                            }

                                            @Override
                                            public void onError() {
                                                //do smth when there is picture loading error
                                                String h="";
                                                if(Objects.equals(h, "")){}
                                            }
                                        });

                                if(i%2==0){
                                    child.setBackgroundColor(ContextCompat.getColor(context,R.color.gray));
                                }
                                else{
                                    child.setBackgroundColor(Color.WHITE);
                                }
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
                    httpC("https://godomicilios.co/webService/servicios.php?svice=EMPRESAS&metodo=json&lat="+settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa=1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();

            }
        }
        );
            queue.add(jsonArrayRequest);
    }

    /*
*/

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

        double valor = Radius * c;
        String val = valor+"";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(2, RoundingMode.HALF_UP);
        distanced = big;
        return "Distancia: "+big.toString()+" Km";
    }

    public String duration (){

        Integer h = ((distanced.intValue() *60)/10)+1;
        return "Duración: "+h.toString()+" mins Aprox.";
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

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void httpRank (final String url) throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(head.this)));

        final ProgressDialog dialog = ProgressDialog.show(head.this, "",
                "Loading. Please wait...", true);
        settings.user.setProgressDialog(dialog);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{

                            settings.rank.ranks = new ArrayList<>();

//                            in = new ArrayList<>();

                            for(int i =0;i<response.length();i++) {

                                View childaa = View.inflate(head.this, R.layout.rank, null);
                                LinearLayout clickaa = (LinearLayout) childaa.findViewById(R.id.click);
                                TextView nameaa = (TextView) childaa.findViewById(R.id.name);
                                LinearLayout visibilityaa = (LinearLayout) childaa.findViewById(R.id.lii);

                                final JSONObject ranks = response.getJSONObject(i);

                                settings.rank.ranks.add(new rank(ranks.getInt("id_categoria"),
                                        ranks.getInt("empresa_id"),
                                        ranks.getString("nombre_categoria"),
                                        ranks.getInt("estado"),0)
                                        );
                            }
                            final JsonArrayRequest jsonArrayRequests= new JsonArrayRequest(JsonArrayRequest.Method.GET, "https://godomicilios.co/webService/servicios.php?svice=PRODUCTOS&metodo=json&empId="+settings.rank.getIdStablishment(), null,
                                    new Response.Listener<JSONArray>() {
                                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                                        @Override
                                        public void onResponse(JSONArray response) {
                                                try{
                                                for(int i = 0; i<response.length(); i++) {

                                                    final JSONObject product = response.getJSONObject(i);

                                                        settings.product.products.add(new product(
                                                                product.getInt("id_producto"),
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

                                                    Intent go = new Intent(head.this, stablishm.class);
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
                            jsonArrayRequests.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            queue.add(jsonArrayRequests);

                        }
                        catch (Exception e){

                            String mensajee ="Oops algo salió mal, intentalo nuevamente";

                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            mensajee, Toast.LENGTH_SHORT);

                            toast1.show();


                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String err = error.getMessage();
                try {
                    httpRank(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        );
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarijamitad));
                break;
            case 2:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                break;
            case 3:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                two.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarijamitad));
                break;
            case 4:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                two.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                break;
            case 5:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                two.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                three.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarijamitad));
                break;
            case 6:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                two.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                three.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                break;
            case 7:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                two.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                three.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                four.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarijamitad));
                break;
            case 8:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                two.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                three.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                four.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                break;
            case 9:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                two.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                three.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                four.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                five.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarijamitad));
                break;
            case 10:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                two.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                three.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                four.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                five.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianarojacompleta));
                break;
        }
    }

    public void categor (final String url, final Integer nu, final Class clas) throws Exception{

        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(head.this)));

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{
                            settings.categories = new ArrayList<>();
                            settings.categories.add("CATEGORIA");
                            if(response.length()<1){

                            }
                            else{
                                for(int i = 0; i<response.length(); i++) {

                                    final JSONObject product = response.getJSONObject(i);

                                    settings.categories.add(product.getString("nombre_categoria"));
                                }
                            }

                            Intent go = new Intent(head.this, clas);
                            startActivity(go);
                            head.this.finish();
                        }
                        catch (Exception ignored){
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

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, settings.categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catego.setAdapter(dataAdapter);
        catego.setOnItemSelectedListener(new head.addressSpinnerClassOne());
    }
    private class addressSpinnerClassOne implements AdapterView.OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
        {
            if (Objects.equals(settings.categories.get(position), "CATEGORIA"))
            {
                try {
                    httpC("https://godomicilios.co/webService/servicios.php?svice=EMPRESAS&metodo=json&lat="
                            +settings.order.getLatitude()+"&long="+settings.order.getLongitude()+"&tipo_empresa=1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    categorSpinner("https://godomicilios.co/webService/servicios.php?svice=FILTRO_CATEGORIA&metodo=json&lat="
                                    +settings.order.getLatitude()+"&long="+settings.order.getLongitude()+
                            "&tipo_empresa=1&categoria="+settings.categories.get(position).replace(" ","%20")
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
                null, CustomSSLSocketFactory.getSSLSocketFactory(head.this)));
        final ProgressDialog dialog = ProgressDialog.show(head.this, "",
                "Loading. Please wait...", true);

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{
                            settings.stablishment.stablishments= new ArrayList<>();

                            for(int i =0;i<response.length();i++) {

                                final JSONObject address = response.getJSONObject(i);

                                settings.stablishment.stablishments.add(new stablishment(1,
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
                                        Math.round(address.getInt("estrellas_sucursal")*2)));

                                View child = View.inflate(head.this, R.layout.li, null);

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
                                    buttons.setBackgroundColor(ContextCompat.getColor(context,R.color.redGo));
                                    buttons.setText("CERRADO");
                                    main.setEnabled(false);

                                }

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
                                                httpRank("https://godomicilios.co/webService/servicios.php?svice=CATALOGO&metodo=json&empId=" + settings.rank.getIdStablishment());
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        } catch(JSONException e){
                                            e.printStackTrace();
                                        }

                                    }
                                });
                                linear.addView(child); //attach to your item

                                Picasso.with(head.this)
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
                                    child.setBackgroundColor(ContextCompat.getColor(context,R.color.gray));
                                }
                                else{
                                    child.setBackgroundColor(Color.WHITE);
                                }


                                LinearLayout lm =(LinearLayout) findViewById(R.id.li);

                            }


                            dialog.dismiss();
                        }
                        catch (Exception ignored){
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

    private static Target getTarget(final String url){
        return new Target(){

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + url);
                        try {
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                            ostream.flush();
                            ostream.close();
                        } catch (IOException e) {
                            Log.e("IOException", e.getLocalizedMessage());
                        }
                    }
                }).start();

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
    }
    public  void logout2(){
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();
    }
    }

