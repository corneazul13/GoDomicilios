package godomicilios.mdcc.pulpapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
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
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import godomicilios.mdcc.pulpapp.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.pulpapp.settings.address;
import godomicilios.mdcc.pulpapp.settings.settings;

import static android.R.attr.path;

public class showAddress extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

        SharedPreferences sharedpreferences;
        String[] listO = settings.spinner.spinnerCities();
        String url, cityOne = "a", otherForm, firstForm;
        double aa,bb;
        String urlOne = "https://godomicilios.co/webService/servicios.php?svice=GUARDAR_DIRECCION";
        String service =  "http://maps.google.com/maps/api/geocode/json?address=";
        String post = "&sensor=false";
        Button location, button2, send, cancel;
        LinearLayout a, one;
        EditText type, ones, two, three, addressAdd;
        TextView textView26;
        List<String> list = new ArrayList<String>();
        List<String> list2 = new ArrayList<>();
        Spinner city,addresss,spinnerTrhee;
        Integer number =0;

        List<String> listOne =
                new ArrayList<String>(Arrays.asList(listO));
        Integer as =0;
        private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
        private static final int MY_ACCESS_FINE_LOCATION = 1 ;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_address);
            final LinearLayout lm =(LinearLayout) findViewById(R.id.li);
            addressAdd = (EditText) findViewById(R.id.addressAdd);
            textView26 = (TextView) findViewById(R.id.textView26);
            location = (Button) findViewById(R.id.location);
            button2 = (Button) findViewById(R.id.button2);
            type = (EditText) findViewById(R.id.type);
            send = (Button) findViewById(R.id.saveAdrress);
            cancel = (Button) findViewById(R.id.cancel);
            a = (LinearLayout) findViewById(R.id.a);
            a.setTranslationY(4000);
            a.setVisibility(View.VISIBLE);
            one = (LinearLayout) findViewById(R.id.one);
            float o = one.getWeightSum();
            city = (Spinner) findViewById(R.id.city);
            addresss = (Spinner) findViewById(R.id.address);
            ones = (EditText)findViewById(R.id.editOne);
            two = (EditText) findViewById(R.id.editTwo);
            three = (EditText) findViewById(R.id.editTrhee);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a.setTranslationY(4000);
                }
            });
            sharedpreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);

            if (sharedpreferences.getAll().size()>0 ){

                String h = sharedpreferences.getString("iduser", "");
                String l = sharedpreferences.getString("referid", "");
                Integer idUs=Integer.parseInt(h);
                settings.user.setId(idUs);
                settings.user.setPhone(l);
                settings.user.setaBoolean(true);

            }

            settings.user.analytics = GoogleAnalytics.getInstance(this);
            settings.user.analytics.setLocalDispatchPeriod(1800);
            settings.user.tracker = settings.user.analytics.newTracker("UA-101326412-1");
            settings.user.tracker.enableExceptionReporting(true);
            settings.user.tracker.enableAdvertisingIdCollection(true);
            settings.user.tracker.enableAutoActivityTracking(true);

            //settings.handleSSLHandshake();
            spinnerTrhee = (Spinner) findViewById(R.id.spinnerTrhee);

            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(settings.newAddress.getCity() ==""){
                        String mensajee ="selecciona una ciudad";
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        mensajee, Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                    else if (settings.newAddress.getType()==""){
                        String mensajee ="selecciona un tipo de dirección";
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        mensajee, Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                    else if(type.getText().toString().equals("") && cityOne =="b"){
                        String mensajee ="digita el nuevo tipo de dirección";
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        mensajee, Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                    else if(ones.getText().toString().equals("")){
                        String mensajee ="digita el primer número de la dirección";
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        mensajee, Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                    else if(two.getText().toString().equals("")){
                        String mensajee ="digita el segundo número de la dirección";
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        mensajee, Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                    else if(three.getText().toString().equals("")){
                        String mensajee ="digita el tercer número de la dirección";
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        mensajee, Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                    else if(number ==0){}

                        String url1 =service+firstForm+ones.getText().toString()
                                +"%20"+two.getText().toString()+"%20"+three.getText().toString()
                                +settings.newAddress.getCity()+post;
                        final String addressA =firstForm+"%20"+ ones.getText().toString()
                                +"%20%23%20"+two.getText().toString()+"%20"+three.getText().toString();

                        RequestQueue queue = null;

                            queue = Volley.newRequestQueue(showAddress.this, null);

                        final ProgressDialog dialog = ProgressDialog.show(showAddress.this, "",
                                "Loading. Please wait...", true);
                        final JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(JsonObjectRequest.Method.GET, url1, null,
                                new Response.Listener<JSONObject>() {
                                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try{
                                            final JSONArray product = (JSONArray) response.getJSONArray("results");

                                            for(int i =0; i<product.length();i++){
                                                final JSONObject one = (JSONObject) product.getJSONObject(i);
                                                if(aa==0.0){
                                                    for (int j =0;j<one.length();j++){
                                                        final JSONObject geometry = (JSONObject) one.getJSONObject("geometry");
                                                        if(aa== 0.0){
                                                            for(int k =0; k<geometry.length();k++){
                                                                final JSONObject location = (JSONObject) geometry.getJSONObject("location");
                                                                if(aa== 0.0){
                                                                    for(int l =0;l<location.length();l++){
                                                                        aa =location.getDouble("lat");
                                                                        settings.order.setLatitude(aa);
                                                                        bb = location.getDouble("lng");
                                                                        settings.order.setLongitude(bb);
                                                                        l = location.length();
                                                                        break;
                                                                    }
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

                                            } if(cityOne=="a"){
                                                url = settings.newAddress.getType();
                                            }
                                            else {
                                                url= type.getText().toString();
                                            }
                                            dialog.dismiss();

                                            if(settings.user.getaBoolean()==true){
                                                sendLocation(
                                                        urlOne+"&longitud="+bb+"&latitud="+aa+
                                                                "&ciudad="+settings.newAddress.getCity()
                                                                +"&iconUbicacion="+settings.newAddress.getIcon()
                                                                +"&tipoDirec="+settings.newAddress.getType()
                                                                +"&direccionAdicional="+addressAdd.getText().toString()
                                                                +"&direccion="+addressA
                                                                +"&usr_id="+settings.user.getId()+"&tipoDirTxt="
                                                                +url
                                                );
                                            }
                                            else{
                                                settings.order.setLatitude(aa);
                                                settings.order.setLongitude(bb);
                                                settings.order.setAddress(firstForm+ones.getText().toString()
                                                        +"%20"+two.getText().toString()+"%20"+three.getText().toString());
                                                Intent go = new Intent(showAddress.this, choose.class);
                                                startActivity(go);
                                            }

                                        }
                                        catch (Exception e){
                                            dialog.dismiss();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                startActivity(getIntent());
                                finish();
                                dialog.dismiss();
                            }
                        }
                        );
                        queue.add(jsonObjectRequest);
                    }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animationOther();
                }
            });

            location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityCompat.requestPermissions(showAddress.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                    ActivityCompat.requestPermissions(showAddress.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1);
                    LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Localizacion Local = new Localizacion();
                    Local.setReport(showAddress.this);


                    if (checkLocationPermission()) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
                            (LocationListener) Local);

                    Intent go = new Intent(showAddress.this, choose.class);
                    startActivity(go);
                }
            });

            if(settings.user.getaBoolean()==true){
                try {
                    httpC("https://godomicilios.co/webService/servicios.php?svice=DIRECCIONES&metodo=json&usr="+settings.user.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        public void httpC (String url) throws Exception{

            final RequestQueue queue = Volley.newRequestQueue(this, new HurlStack(
                    null, CustomSSLSocketFactory.getSSLSocketFactory(showAddress.this)));
            JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                        @Override
                        public void onResponse(JSONArray response) {
                            try{
                                if (response.length()==0){
                                    settings.address.addresses = new ArrayList<>();
                                    if (settings.user.getaBoolean()==true){
                                        animationOther();
                                    }
                                    else{
                                        Intent go = new Intent(showAddress.this, choose.class);
                                        startActivity(go);
                                    }
                                }
                                else{
                                settings.address.addresses = new ArrayList<>();
                                    for(int i =0;i<response.length();i++){
                                        final JSONObject address =(JSONObject) response.getJSONObject(i);
                                        settings.address.addresses.add(new address(address.getInt("id_direccion"),
                                                address.getString("ciudad"),address.getDouble("latitud"),
                                                address.getDouble("longitud"),address.getString("direccion"),
                                                address.getString("direccion_adicional"),address.getString("tipo_direccion")));

                                        LinearLayout.LayoutParams paramsT = new LinearLayout.LayoutParams(
                                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                                LinearLayout.LayoutParams.WRAP_CONTENT);
                                        paramsT.weight = 6 ;
                                        paramsT.setMargins(10,20,10,20);

                                        LinearLayout.LayoutParams paramsB = new LinearLayout.LayoutParams(
                                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                                LinearLayout.LayoutParams.WRAP_CONTENT);
                                        paramsB.weight = 2 ;
                                        paramsB.height =100;
                                        paramsB.setMargins(10,25,10,25);

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                LinearLayout.LayoutParams.MATCH_PARENT,
                                                LinearLayout.LayoutParams.WRAP_CONTENT);

                                        LinearLayout ll = new LinearLayout(showAddress.this);
                                        ll.setOrientation(LinearLayout.HORIZONTAL);

                                        final TextView type = new TextView(showAddress.this);
                                        final TextView addr = new TextView(showAddress.this);
                                        final Button btn = new Button(showAddress.this);

                                        // Give button an ID

                                        btn.setLayoutParams(paramsT);
                                        btn.setId(i);
                                        btn.setText("Pedir Aquí".toUpperCase());
                                        btn.setTextSize(13);
                                        btn.setTextColor(Color.WHITE);
                                        btn.setBackgroundColor(getResources().getColor(R.color.redGo));

                                        type.setText(address.getString("tipo_direccion"));
                                        type.setLayoutParams(paramsB);
                                        type.setTextColor(Color.GRAY);
                                        type.setTextSize(13);

                                        addr.setText(address.getString("direccion"));
                                        addr.setLayoutParams(paramsT);
                                        addr.setTextColor(Color.GRAY);

                                        final View v = new View(showAddress.this);

                                        //Give separator Line
                                        v.setBackgroundResource(R.drawable.line);

                                        // set the layoutParams first the button
                                        v.setLayoutParams(params);
                                        final int index = i;
                                        btn.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {

                                                settings.order.setLatitude(settings.address.addresses.get(btn.getId()).getLatitude());
                                                settings.order.setLongitude(settings.address.addresses.get(btn.getId()).getLongitude());
                                                settings.order.setAddress(settings.address.addresses.get(btn.getId()).getAddress());
                                                Intent go = new Intent(showAddress.this, choose.class);
                                                startActivity(go);
                                            }
                                        });

                                        //Add button to LinearLayout
                                        ll.addView(type);
                                        ll.addView(addr);
                                        ll.addView(btn);

                                        //Add button to LinearLayout defined in XML
                                        LinearLayout lm =(LinearLayout) findViewById(R.id.li);

                                        lm.addView(ll);
                                        lm.addView(v);
                                    }

                            }}
                            catch (Exception e){
                                String mensajee ="No hay direcciones asociadas, registra una";
                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(),
                                                mensajee, Toast.LENGTH_SHORT);
                                toast1.show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                    startActivity(getIntent());
                    finish();
                }
            }
            );
            queue.add(jsonArrayRequest);
        }

        public void setLocation(Location loc) {
            //Obtener la direccion de la calle a partir de la latitud y la longitud
           /* if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
                try {
                    Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                    List<Address> list = geocoder.getFromLocation(
                            loc.getLatitude(), loc.getLongitude(), 1);
                    if (!list.isEmpty()) {
                        Address DirCalle = list.get(0);
                        String mensajee ="Mi direccion es: \n"
                                + DirCalle.getAddressLine(0);

                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        mensajee, Toast.LENGTH_SHORT);

                        toast1.show();

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        /* Aqui empieza la Clase Localizacion */
        public class Localizacion implements LocationListener {
            showAddress showAddress;

            public showAddress getreport() {
                return showAddress;
            }

            public void setReport(showAddress showAddress) {
                this.showAddress = showAddress;
            }

            @Override
            public void onLocationChanged(Location loc) {
                // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
                // debido a la deteccion de un cambio de ubicacion

                loc.getLatitude();
                loc.getLongitude();

                settings.order.setLatitude(loc.getLatitude());
                settings.order.setLongitude(loc.getLongitude());
                // SERVICIO PAR

                this.showAddress.setLocation(loc);
            }

            @Override
            public void onProviderDisabled(String provider) {
                // Este metodo se ejecuta cuando el GPS es desactivado

                String Text = "GPS Desactivado";
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                Text, Toast.LENGTH_SHORT);

                toast1.show();
            }

            @Override
            public void onProviderEnabled(String provider) {
                // Este metodo se ejecuta cuando el GPS es activado
                String Text = "GPS Activado";
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                Text, Toast.LENGTH_SHORT);
                toast1.show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // Este metodo se ejecuta cada vez que se detecta un cambio en el
                // status del proveedor de localizacion (GPS)
                // Los diferentes Status son:
                // OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
                // TEMPORARILY_UNAVAILABLE -> Temporalmente no disponible pero se
                // espera que este disponible en breve
                // AVAILABLE -> Disponible
            }



        }/* Fin de la clase localizacion */



        public void addItemsOnSpinner2() {

            city = (Spinner) findViewById(R.id.city);

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, listOne);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            city.setAdapter(dataAdapter);
            city.setOnItemSelectedListener(new addressSpinnerClassOne());
        }

        public void spinnerTwo() {

            addresss = (Spinner) findViewById(R.id.address);
            list.add("Seleccione una opción");
            list.add("APARTAMENTO");
            list.add("CASA");
            list.add("TRABAJO");
            list.add("UNIVERSIDAD");
            list.add("OTRO");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            addresss.setAdapter(dataAdapter);
            addresss.setOnItemSelectedListener(new addressSpinnerClassTwo());
        }
        public void spinnerTrhee() {

            spinnerTrhee = (Spinner) findViewById(R.id.spinnerTrhee);

            list2.add("Carrera");
            list2.add("Calle");
            list2.add("Transversal");
            list2.add("Diagonal");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list2);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerTrhee.setAdapter(dataAdapter);
            /*addressOne = dataAdapter.toString();
            if (dataAdapter.toString()=="OTRO"){
                *//*


            }*/
            spinnerTrhee.setOnItemSelectedListener(new addressSpinnerClassThree());


        }



        // get the selected dropdown list value
        public void addListenerOnButton() {

            city = (Spinner) findViewById(R.id.city);



        }
        public void addListenerOnButton2() {

            addresss = (Spinner) findViewById(R.id.address);



        }
        public void addListenerOnButton3() {

            addresss = (Spinner) findViewById(R.id.address);


        }

        class addressSpinnerClassOne implements AdapterView.OnItemSelectedListener
        {
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
            {
                if (listOne.get(position)=="Seleccione una opción")
                {
                   settings.newAddress.setCity("");
                }
                else {
                    settings.newAddress.setCity(listOne.get(position));
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }

        class addressSpinnerClassTwo implements AdapterView.OnItemSelectedListener
        {
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
            {
                if(list.get(position)=="OTRO"){

                    LinearLayout.LayoutParams paramsT = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    paramsT.setMargins(0,10,0,0);


                    textView26.setLayoutParams(paramsT);

                   type.setVisibility(View.VISIBLE);
                    otherForm = type.getText().toString();
                    settings.newAddress.setIcon("fa%20fa-star%20fa-fw");

                    cityOne = "b";
                                        }
                else {
                    if (type.getVisibility()==View.VISIBLE) {
                        LinearLayout.LayoutParams paramsT = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        paramsT.setMargins(0, 30, 0, 0);


                        textView26.setLayoutParams(paramsT);
                        type.setVisibility(View.GONE);
                    }
                    else{
                        switch (list.get(position)){
                            case "APARTAMENTO":
                                settings.newAddress.setType("APARTAMENTO");
                                settings.newAddress.setIcon("title=\"fa%20fa-building%20fa-fw");
                                cityOne="a";
                                break;
                            case ("CASA"):
                                settings.newAddress.setType("CASA");
                                settings.newAddress.setIcon("fa%20fa%20fa-home%20fa-fw");
                                cityOne="a";
                                break;
                            case ("TRABAJO"):
                                settings.newAddress.setType("TRABAJO");
                                settings.newAddress.setIcon("fa%20fa%20fa-laptop%20fa-fw");
                                cityOne="a";
                                break;
                            case ("UNIVERSIDAD"):
                                settings.newAddress.setType("UNIVERSIDAD");
                                settings.newAddress.setIcon("fa%20fa-university%20fa-fw");
                                cityOne="a";
                        }
                    }

                    }

                }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }

        class addressSpinnerClassThree implements AdapterView.OnItemSelectedListener
        {
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
            {


                firstForm = list2.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }



        public void sendLocation (final String url) throws IOException, GeneralSecurityException {

            final String urlEncoded = Uri.encode(url, ALLOWED_URI_CHARS);


            final RequestQueue queue = Volley.newRequestQueue(this, new HurlStack(
                    null, CustomSSLSocketFactory.getSSLSocketFactory(showAddress.this)));


            final JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, urlEncoded, null,
                    new Response.Listener<JSONArray>() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                        @Override
                        public void onResponse(JSONArray response) {
                            try{

                                startActivity(getIntent());
                                finish();

                            }
                            catch (Exception e){

                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    try {
                        sendLocation(urlEncoded);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
            );
            queue.add(jsonArrayRequest);
        }


        public void getLocation (String url){


        }

        public void all (){

                if (number ==0) {

                    try {
                        sendLocation("http://godomicilios.co/webService/servicios.php?svice=GUARDAR_DIRECCION&longitud=&latitud=&ciudad=Bogot%C3%A1&iconUbicacion=fa%20fa-building%20fa-fw&tipoDirec=APARTAMENTO&dir1=Carrera&dir2=35&dir3=63a&dir4=11&direccionAdicional=of%2012&guardarUbicacion=ok&direccion=Carrera%2035%20%23%2063a%2011&usr_id=102&tipoDirTxt=APARTAMENTO"

                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    }
                }
        }


        public void animationOther (){


            AnimationSet set = new AnimationSet(true);
            Animation animation;
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
            animation.setDuration(1000);
            set.addAnimation(animation);
            LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);
            a.setTranslationY(0);
            a.setLayoutAnimation(controller);
            a.startAnimation(animation);

            if (as==0){
                addItemsOnSpinner2();
                addListenerOnButton();
                spinnerTwo();
                addListenerOnButton2();
                spinnerTrhee();
                addListenerOnButton3();
                as=1;
            }

        }
        public boolean checkLocationPermission() {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {

                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    return true;

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            1);

                    // MY_PERMISSIONS_REQUEST_FINE_LOCATION is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                    return true;
                }
            }
            return true;
        }
        //public void  (){}

    }
