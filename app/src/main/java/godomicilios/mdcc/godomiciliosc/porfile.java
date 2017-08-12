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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import godomicilios.mdcc.godomiciliosc.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.godomiciliosc.settings.settings;

public class porfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static GoogleAnalytics analytics;

    private static Tracker tracker;


    JSONArray dir1, dir2, dir3, dir4, dirAdicional, fir, la, lo, dir_id;
    ArrayList<EditText> edi1, edi2, edi3;
    final Integer[] en = {-1};
    EditText name,surname,email,phone,edit1, edit2, edit3;
    LinearLayout food, l, up, beer, pharmacy, pet, market;
    Button send;
    boolean nnow= false;
    TextView show;
    Spinner spinnerTrhee;
    int count =0;
    String firstForm;
    double aa,bb;
    String realUrl;
    TextView numberCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porfile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        show = (TextView) findViewById(R.id.show);
        name = (EditText) findViewById(R.id.name);
        surname=(EditText) findViewById(R.id.surname);
        phone =(EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        food = (LinearLayout) findViewById(R.id.food);
        beer = (LinearLayout) findViewById(R.id.beer);
        pharmacy = (LinearLayout) findViewById(R.id.pharmacy);
        pet = (LinearLayout) findViewById(R.id.pet);
        market = (LinearLayout) findViewById(R.id.market);
        l = (LinearLayout) findViewById(R.id.l);
        up = (LinearLayout) findViewById(R.id.up);
        send = (Button) findViewById(R.id.send);
        numberCar = (TextView) findViewById(R.id.numberCar);
        Integer car= settings.shoppingCar.carFinal.size();
        numberCar.setText(settings.user.getCarCant());
        edi1 = new ArrayList<EditText>();
        edi2 = new ArrayList<EditText>();
        edi3 = new ArrayList<EditText>();
        settings.user.analytics = GoogleAnalytics.getInstance(this);
        settings.user.analytics.setLocalDispatchPeriod(1800);
        settings.user.tracker = settings.user.analytics.newTracker("UA-101326412-1");
        settings.user.tracker.enableExceptionReporting(true);
        settings.user.tracker.enableAdvertisingIdCollection(true);
        settings.user.tracker.enableAutoActivityTracking(true);

        pharmacy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent go = new Intent(porfile.this, headThree.class);
                startActivity(go);
                porfile.this.finish();

            }
        });

        pet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent go = new Intent(porfile.this, headFour.class);
                startActivity(go);
                porfile.this.finish();

            }
        });

        market.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent go = new Intent(porfile.this, headFive.class);
                startActivity(go);
                porfile.this.finish();

            }
        });


        beer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent go = new Intent(porfile.this, headTwo.class);
                startActivity(go);
                porfile.this.finish();

            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(porfile.this, choose.class);
                String a = "Usuario editado con Éxito";
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                a, Toast.LENGTH_LONG);

                toast1.show();
                startActivity(go);

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dir1 = new JSONArray();
                dir2  = new JSONArray();
                dir3 = new JSONArray();
                dir4 = new JSONArray();
                dir_id = new JSONArray();
                la = new JSONArray();
                lo = new JSONArray();
                dirAdicional= new JSONArray();
                fir = new JSONArray();

                if(settings.address.addresses.size()>0){

                    for(int i=0;i<settings.address.addresses.size();i++){

                        String address2 = settings.address.addresses.get(i).getAddress();
                        String[] address2ComoArray = address2.split(" ");

                        dir_id.put(settings.address.addresses.get(i).getId());

                        dir1.put(address2ComoArray[0].toString());

                        dir2.put(address2ComoArray[1].toString());

                        dir3.put(address2ComoArray[3].toString());

                        dir4.put(address2ComoArray[4].toString());

                        dirAdicional.put(settings.address.addresses.get(i).getAditional());
                        fir.put(address2ComoArray[0]);
                    }
                    for (int g =0; g<settings.address.addresses.size();g++){

                        String  hg = "";

                            View child = View.inflate(porfile.this, R.layout.update_address, null);
                            EditText editOne = (EditText) child.findViewById(R.id.editOne);
                            editOne.setId(g);
                            edi1.add(editOne);
                            hg = editOne.getText().toString();
                            EditText editTwo = (EditText) child.findViewById(R.id.editTwo);
                            editTwo.setId(g);
                            edi2.add(editTwo);
                            hg = editTwo.getText().toString();
                            EditText editThree = (EditText) child.findViewById(R.id.editTrhee);
                            editThree.setId(g);
                            edi3.add(editThree);
                            hg = editThree.getText().toString();

                            try {
                                try {
                                    getLatitudeLongitude(editOne.getText().toString(), editTwo.getText().toString(),
                                            editThree.getText().toString(), settings.address.addresses.get(g).getCity(),
                                            fir.get(g).toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (GeneralSecurityException e) {
                                    e.printStackTrace();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                    }
                }
    }
});
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count ==0){
                    count =1;
                    animar(true);
                    l.setVisibility(View.VISIBLE);
                }
                else {
                    animar(false);
                    count=0;
//                    l.setVisibility(View.GONE);
                }
            }
        });


        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(porfile.this, head.class);
                startActivity(go);
            }
        });

        String names = settings.user.getName();
        String phones = settings.user.getPhone().toString();

        surname.setText(settings.user.getSurname().toLowerCase());
        name.setText(names.toLowerCase());
        if(phones.equals("")){
            phone.setHint("Ingresa un nùmero contacto");
            phone.setEnabled(true);
            phone.setTextColor(Color.BLACK);
            phone.setBackgroundColor(Color.WHITE);
        }
        else{phone.setText(phones);}

        email.setText(settings.user.getEmail());

        for(int i =0; i<settings.address.addresses.size();i++){
            List<String> list = new ArrayList<String>();
            List<String> address = new ArrayList<String>();
            String address2 = settings.address.addresses.get(i).getAddress();
            String[] address2ComoArray = address2.split(" ");

            View child = View.inflate(porfile.this, R.layout.update_address, null);
            Spinner spinnerTrhee = (Spinner) child.findViewById(R.id.spinnerTrhee);
            TextView type = (TextView) child.findViewById(R.id.type);
            EditText editOne = (EditText) child.findViewById(R.id.editOne);
            editOne.setId(i);
            edi1.add(editOne);
            EditText editTwo = (EditText) child.findViewById(R.id.editTwo);
            editTwo.setId(i);
            edi2.add(editTwo);
            EditText editThree = (EditText) child.findViewById(R.id.editTrhee);
            editThree.setId(i);
            edi3.add(editThree);

            type.setText(settings.address.addresses.get(i).getType());

            for (int j = 0; j < address2ComoArray.length; j++) {

                    switch (j){
                        case 1:
                            editOne.setText(address2ComoArray[1].toString());
                            break;
                        case 3:
                            editTwo.setText(address2ComoArray[3].toString());
                            break;
                        case 4:
                            editThree.setText(address2ComoArray[4].toString());
                            break;
                    }
            }

            list.add("Carrera");
            list.add("Calle");
            list.add("Transversal");
            list.add("Diagonal");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerTrhee.setAdapter(dataAdapter);
        /*addressOne = dataAdapter.toString();
        if (dataAdapter.toString()=="OTRO"){
            *//*
        }*/
            spinnerTrhee.setOnItemSelectedListener(new addressSpinnerClassThree());

            LinearLayout.LayoutParams paramsT = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsT.weight = 4 ;
            paramsT.setMargins(10,15,10,15);

            LinearLayout ll = new LinearLayout(porfile.this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            //Add button to LinearLayout
            ll.addView(child);

            //Add button to LinearLayout defined in XML

            l.addView(ll);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
                    Intent go = new Intent(porfile.this, car.class);
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
            Intent go = new Intent(porfile.this,porfile.class);
            startActivity(go);

        } else if (id == R.id.referred) {
            Intent go = new Intent(porfile.this,refer.class);
            startActivity(go);

        } else if (id == R.id.coupons) {
            Intent go = new Intent(porfile.this,coupons.class);
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
            Intent go = new Intent(porfile.this,payM.class);
            startActivity(go);


        } else if (id == R.id.car) {

            Intent go = new Intent(porfile.this, car.class);
            startActivity(go);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void spinnerTrhee(Spinner spinnerTrhee) {

    }
    class addressSpinnerClassThree implements AdapterView.OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id, List<String> list
)
        {
             firstForm = list.get(position);
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void animar(boolean mostrar)
    {
        AnimationSet set = new AnimationSet(true);
        Animation animation = null;
        int z =0;
        if (mostrar)
        {
            //desde la esquina inferior derecha a la superior izquierda
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

            animation.setDuration(500);
            set.addAnimation(animation);
            LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);

            l.setLayoutAnimation(controller);
            l.startAnimation(animation);
        }
        else
        {    //desde la esquina superior izquierda a la esquina inferior derecha
            animation= AnimationUtils.loadAnimation(this, R.anim.hide);
            l.startAnimation(animation);
            if (count==1){
                l.setVisibility(View.GONE);
            }
        }
    }

    public void httpC (String url) throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this, new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(porfile.this)));


        final ProgressDialog dialog = ProgressDialog.show(porfile.this, "",
                "Loading. Please wait...", true);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{

                            if(response.length()== 0){
                                String mensajee ="Usuario o Contraseña incorrectos, intente de nuevo!";

                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(),
                                                mensajee, Toast.LENGTH_SHORT);
                               }
                            else {

                                for(int i =0;i<response.length();i++){
                                    settings.user.setId(response.getJSONObject(i).getInt("id_usr"));
                                    settings.user.setName(response.getJSONObject(i).getString("nombres"));
                                    settings.user.setSurname(response.getJSONObject(i).getString("apellidos"));
                                    settings.user.setEmail(response.getJSONObject(i).getString("correo"));
                                    settings.user.setPhone(response.getJSONObject(i).getString("cod_referencia"));
                                    settings.user.setUser(response.getJSONObject(i).getString("usr"));
                                    settings.user.setPassword(response.getJSONObject(i).getString("pwd"));
                                }

                                Intent go = new Intent(porfile.this, showAddress.class);
                                String mensajee ="Bienvenido (a)  "+ settings.user.getName();

                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(),
                                                mensajee, Toast.LENGTH_SHORT);
                                toast1.show();
                                startActivity(go);
                            }
                        }
                        catch (Exception e){

                            String mensajee ="Credenciales inválidas, vuelve a intentarlo";

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

                String mensaje = "Oops algo salió mal, intentalo nuevamente";
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                mensaje, Toast.LENGTH_SHORT);

                toast1.show();
                dialog.dismiss();
            }
        }
        );
        queue.add(jsonArrayRequest);
    }

    private void animar(String mostrar)
    {
        AnimationSet set = new AnimationSet(true);
        Animation animation = null;
        if (mostrar.equals("aparecer")) {
            animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);}

        if (mostrar.equals("desaparecer")) {
            animation = new TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0.1f,
                    Animation.RELATIVE_TO_SELF, 0.1f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);}

//duración en milisegundosanimation.setDuration(500);
        set.addAnimation(animation);
        LayoutAnimationController controller =

                new LayoutAnimationController(set, 0.25f);

        l.setLayoutAnimation(controller);
        l.startAnimation(animation);
    }

    public void update (JSONObject url) throws IOException, JSONException, GeneralSecurityException {


        String b =url.toString();
        String a = "https://godomicilios.co/webService/servicios.php?svice=MOD_USR&metodo=json&datos=";
        realUrl = a + url;
        realUrl = realUrl.replaceAll(" ", "");
        //realUrl = realUrl.replace("\"", "");
        final RequestQueue queue = Volley.newRequestQueue(this, new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(porfile.this)));

        final ProgressDialog dialog = ProgressDialog.show(porfile.this, "",
                "Loading. Please wait...", true);


        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, realUrl, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(JSONArray response) {
                        try{

                            String a = "Usuario modificado con Èxito!";
                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            a, Toast.LENGTH_LONG);
                            finish();
                            startActivity(getIntent());

                        }
                        catch (Exception e){
                            String a = "Ha ocurrido un error, Vuelve a intentarlo";
                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            a, Toast.LENGTH_LONG);

                            toast1.show();
                            dialog.dismiss();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String a = "Ha ocurrido un error, Vuelve a intentarlo";
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                a, Toast.LENGTH_LONG);

                toast1.show();
                dialog.dismiss();
            }
        }
        );
        queue.add(jsonArrayRequest);

    }
    public void getLatitudeLongitude (String one,String two, String three, String ci, String firstFor) throws IOException, GeneralSecurityException {
        la.put(new Double(0.0));
        lo.put(new Double(0.0));

        String service ="https://maps.google.com/maps/api/geocode/json?address=";
        String post ="&sensor=false";

        String url1 =service+firstFor+one+"%20"+two+"%20"+three+ci+post;
        url1 = stripAccents(url1);

        final RequestQueue queue = Volley.newRequestQueue(porfile.this, new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(porfile.this)));

        final ProgressDialog dialog = ProgressDialog.show(porfile.this, "",
                "Loading. Please wait...", true);
        final JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(JsonObjectRequest.Method.GET, url1, null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            settings.update.updates= new ArrayList<>();

                            final JSONArray product = (JSONArray) response.getJSONArray("results");
                            en[0] = en[0] +1;

                            for(int i =0; i<product.length();i++){
                                if(nnow ==true){
                                    break;
                                }

                                final JSONObject one = (JSONObject) product.getJSONObject(i);
                                if(aa==0.0){
                                    for (int j =0;j<one.length();j++){
                                        if(nnow ==true){
                                            break;
                                        }
                                        final JSONObject geometry = (JSONObject) one.getJSONObject("geometry");
                                        if(aa== 0.0){
                                            for(int k =0; k<geometry.length();k++){
                                                final JSONObject location = (JSONObject) geometry.getJSONObject("location");
                                                if(aa== 0.0){
                                                    Double laa = (Double) location.getDouble("lat");
                                                    Double loo = (Double) location.getDouble("lng");
                                                        la.put(en[0], (Object) laa);
                                                        lo.put(en[0], (Object) loo);

                                                    if(en[0]==la.length()-1){
                                                        nnow= true;
                                                        try {
                                                            JSONObject jsonObject = new JSONObject();
                                                            try {
                                                                jsonObject.put("nombre", name.getText().toString());
                                                                jsonObject.put("correo", email.getText().toString());
                                                                jsonObject.put("id_usr", settings.user.getId());
                                                                jsonObject.put("guardar", "modificar");
                                                                jsonObject.put("apellidos", surname.getText().toString());
                                                                jsonObject.put("tel", phone.getText().toString());
                                                                jsonObject.put("lat", la);
                                                                jsonObject.put("long", lo);
                                                                jsonObject.put("dir_id", dir_id );
                                                                jsonObject.put("dir1", dir1);
                                                                jsonObject.put("dir2", dir2);
                                                                jsonObject.put("dir3", dir3);
                                                                jsonObject.put("dir4", dir4);
                                                                jsonObject.put("dirAdicional", dirAdicional);
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                            update(
                                                                    jsonObject
                                                            );
                                                        } catch (UnsupportedEncodingException e) {
                                                            e.printStackTrace();
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                else{
                                                    k=geometry.length();
                                                }

                                            }
                                        }
                                        else{
                                            j=one.length();
                                        }

                                    }
                                }
                                else{
                                    i=product.length();
                                }


                            }


                            dialog.dismiss();


                        }
                        catch (Exception e){


                            dialog.dismiss();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                dialog.dismiss();
            }
        }
        );
        queue.add(jsonObjectRequest);

    }
    public void newurl (String name,String apellidos,String tel,String correo,
                        String id_usr, ArrayList<String> lat, ArrayList<String> dir_id,
                        ArrayList<String> dir1, ArrayList<String> dir2,ArrayList<String> dir3,
                        ArrayList<String> dir4, ArrayList<String> dirAdicional){
        String first = "http://192.168.0.113/goDomicilios/webService/servicios.php?svice=MOD_USR&metodo=json&";

        realUrl = first+"nombre="+name+"&apellidos="+apellidos+"&tel="+tel+"&correo="+correo+
                "&id_usr="+id_usr+"&lat="+lat+"&dir_id="+dir_id+"&dir1="+dir1+"&dir2="+dir2+
                "&dir3="+dir3+"&dir4="+dir4+"&dirAdicional="+dirAdicional;

    }
    private static final String ORIGINAL
            = "ÁáÉéÍíÓóÚúÑñÜü";
    private static final String REPLACEMENT
            = "AaEeIiOoUuNnUu";
    public static String stripAccents(String str) {

        if (str == null) {
            return null;
        }
        char[] array = str.toCharArray();
        for (int index = 0; index < array.length; index++) {
            int pos = ORIGINAL.indexOf(array[index]);
            if (pos > -1) {
                array[index] = REPLACEMENT.charAt(pos);
            }
        }
        return new String(array);
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





