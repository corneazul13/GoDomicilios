package godomicilios.mdcc.pulpapp;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import godomicilios.mdcc.pulpapp.settings.AnalyticsApplication;
import godomicilios.mdcc.pulpapp.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.pulpapp.settings.MaterialDialog;
import godomicilios.mdcc.pulpapp.settings.settings;
import godomicilios.mdcc.pulpapp.settings.spinner;

import static android.R.attr.data;
import static godomicilios.mdcc.pulpapp.Splash.isOnline;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener{

    TextView text1, non;
    EditText user, pss,name, users, repeat, surname, pass,phone, mail;
    LinearLayout ln1, ln2, ln3, sg, newDown, laux, real;
    ImageView imb1, imb3, imb2;
    Button bregistro, login, signin;
    String email, firstname, lastname, userw, passw;
    LinearLayout laux1;
    InputStream inputStream = null;
    String result = "";
    Integer ddd = 0;
    Integer ok =0, statusw, idw;
    String url;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private GoogleApiClient googleApiClient;
    public static final int SIGN_IN_CODE = 777;
    Boolean internet = false;
    public static final String MyPREFERENCES= "myPreferences";
    public static final String User = "user";
    public static final String Password = "password";
    public static final String Status = "status";
    public static final String VALIDATOR = "validator";
    public static final String QUALIFY = "qualify";
    public static final String IDUSER= "iduser";
    public static final String REFERID ="referid";
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //DataBaseHelper MDB = new DataBaseHelper(getApplicationContext());
        users  =(EditText) findViewById(R.id.users);
        mail =(EditText)findViewById(R.id.email);
        repeat = (EditText) findViewById(R.id.repeat);
        surname = (EditText) findViewById(R.id.surname);
        name = (EditText) findViewById(R.id.names);
        pass = (EditText) findViewById(R.id.pass);
        phone = (EditText) findViewById(R.id.phone);
        user= (EditText) findViewById(R.id.user);
        login= (Button) findViewById(R.id.login);
        pss=(EditText) findViewById(R.id.pss);
        text1=(TextView)findViewById(R.id.textView3);
        ln1=(LinearLayout)findViewById(R.id.layanimated1);
        ln2=(LinearLayout)findViewById(R.id.layanimated2);
        ln3=(LinearLayout)findViewById(R.id.layanimated3);
        imb1=(ImageView)findViewById(R.id.imageButton);
        imb2=(ImageView)findViewById(R.id.imageButton2);
        imb3=(ImageView)findViewById(R.id.imageButton3);
        laux1=(LinearLayout)findViewById(R.id.newUp);
        real= (LinearLayout) findViewById(R.id.realLinear);
        laux  = (LinearLayout) findViewById(R.id.layhidden);
        newDown =(LinearLayout) findViewById(R.id.newDown);
        sg = (LinearLayout)findViewById(R.id.signin);
        sg.setTranslationY(4000);
        laux.setTranslationY(4000);
        bregistro=(Button)findViewById(R.id.bregistro);
        signin = (Button)findViewById(R.id.signind);
        non = (TextView) findViewById(R.id.non);
        non.setPaintFlags(non.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (sharedpreferences.getAll().size()>0 ){

            String h = sharedpreferences.getString(Status, "");
            String i = sharedpreferences.getString(User, "");
            String j = sharedpreferences.getString(Password, "");
            String k = sharedpreferences.getString(VALIDATOR, "");
            String l = sharedpreferences.getString(QUALIFY, "");
            if (k.equals("true")){
                Intent go = new Intent(MainActivity.this, status.class);
                startActivity(go);
            }

            else if(h.equals("a")){

                loginUser loginUser = new loginUser();
                loginUser.execute();
            }}

/*
        if (MDB.recuperarCONTACTO(1).getStatus()!=null&& MDB.recuperarCONTACTO(1).getStatus()==1){

            idw=MDB.recuperarCONTACTO(1).getId();
            userw=MDB.recuperarCONTACTO(1).getUser();
            passw=MDB.recuperarCONTACTO(1).getPass();
            statusw=MDB.recuperarCONTACTO(1).getStatus();
            if(statusw==1){
                try {
                    httpC ("https://godomicilios.co/webService/servicios.php?svice=LOGIN&metodo=json&usr="
                            +userw+"&pwd="+passw);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }*/

        non.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(MainActivity.this, showAddress.class);
                startActivity(go);
            }
        });
        settings.user.analytics = GoogleAnalytics.getInstance(this);
        settings.user.analytics.setLocalDispatchPeriod(1800);
        settings.user.tracker = settings.user.analytics.newTracker("UA-101326412-1");
        settings.user.tracker.enableExceptionReporting(true);
        settings.user.tracker.enableAdvertisingIdCollection(true);
        settings.user.tracker.enableAutoActivityTracking(true);

        imb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile", "user_friends"));
            }
        });

        FacebookSdk.sdkInitialize(this.getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Log.v("LoginActivity", response.toString());

                                        // Application code
                                        try {
                                            Integer L =object.length();
                                            boolean emails = object.isNull("email");
                                            String id = object.getString("id");
                                            String name = object.getString("name");
                                            if ( L <= 3){


                                                try {
                                                    loginWithFacebook("https://godomicilios.co/webService/servicios.php?svice=REG_USR_REDES&metodo=json&correo="+
                                                            id+"&nombres="+name+"&apellidos="+"&tipo=FACEBOOK");
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                } catch (GeneralSecurityException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            else {
                                                String email = object.getString("email");
                                                try {
                                                    loginWithFacebook("https://godomicilios.co/webService/servicios.php?svice=REG_USR_REDES&metodo=josn&correo="+
                                                            email+"&nombres="+name+"&apellidos="+"&tipo=FACEBOOK");
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                } catch (GeneralSecurityException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        // 01/31/1980 format
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday");
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "Inicio Cancelado", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });



       /* try {
            http("https://godomicilios.co/webService/servicios.php?" +
                    "svice=CUPONES_USR&metodo=json&usr_id=102");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        //settings.handleSSLHandshake();

        real.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        try {
            httpCities();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //signin
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a;


                if (name.getText().toString().equals("")){

                    a = "Digita tu nombre";
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    a, Toast.LENGTH_LONG);

                    toast1.show();
                }

                else if (surname.getText().toString().equals("")){
                    a = "Digita tus apellidos";
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    a, Toast.LENGTH_LONG);

                    toast1.show();
                }
                else if (phone.getText().toString().equals("")){
                    a = "Digita un numero telefónico";
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    a, Toast.LENGTH_LONG);

                    toast1.show();
                }
                else if (mail.getText().toString().equals("")){
                    a = "Digita tu correo electrónico";
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    a, Toast.LENGTH_LONG);

                    toast1.show();
                }
                else if (users.getText().toString().equals("")){
                    a = "Digita un usuario";
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    a, Toast.LENGTH_LONG);

                    toast1.show();
                }
                else if (pass.getText().toString().equals("")){
                    a = "Digita una contraseña";
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    a, Toast.LENGTH_LONG);

                    toast1.show();
                }
                else if (repeat.getText().toString().equals("")){
                    a = "Repite la contraseña";
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    a, Toast.LENGTH_LONG);

                    toast1.show();
                }
                else if (! pass.getText().toString().equals(repeat.getText().toString())){
                    a = "Las contraseñas no coinciden";
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    a, Toast.LENGTH_LONG);

                    toast1.show();
                }
                else if (ddd==0){
                    try {
                        httpSignin ("https://godomicilios.co/webService/servicios.php?svice=REGISTRAR_USR&metodo=json&nombres="
                                +name.getText().toString()+"&apellidos="+surname.getText().toString()
                                +"&cel="+phone.getText().toString()+"&correo="+mail.getText().toString()+
                                "&usr="+ users.getText().toString()+"&pwd="+pass.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pass.setText("");
                    repeat.setText("");
                }
            }
        });

        //login Facebook


        /*imb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackManager = CallbackManager.Factory.create();
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile", "email"));
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // Facebook Email address
                        AccessToken accessToken = loginResult.getAccessToken();
                        Profile profile = Profile.getCurrentProfile();

                        // Facebook Email address
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,
                                            GraphResponse response) {
                                        Log.v("LoginActivity Response ", response.toString());

                                        try {
                                            firstname = object.getString("name");

                                            email = object.getString("email");
                                            Log.v("Email = ", " " + email);
                                            Toast.makeText(getApplicationContext(), "Name " + firstname, Toast.LENGTH_LONG).show();


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender, birthday");
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), "No se Ha podido inciar sesion", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(getApplicationContext(), "Error desconocido al inciar sesion. "+exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(user.getText().toString().equals("")){
                    Toast toast1=
                            Toast.makeText(getApplicationContext(),
                                    "Digita un nombre de usuario",Toast.LENGTH_SHORT);
                    toast1.show();
//                    user.setBackgroundResource(R.drawable.validatora);
                }
                else if(pss.getText().toString().equals("")){
                    Toast toast1=
                            Toast.makeText(getApplicationContext(),
                                    "Digita una contraseña",Toast.LENGTH_SHORT);
                    toast1.show();
//                    pss.setBackgroundResource(R.drawable.validatora);
                }
                else if(ok ==0){


                    try {
                        httpC ("https://godomicilios.co/webService/servicios.php?svice=LOGIN&metodo=json&usr="+user.getText().toString()+"&pwd="+pss.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }
        });

        imb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Abrir();
            }
        });
        laux1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnimationSet set = new AnimationSet(true);
                Animation animation;
                animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                animation.setDuration(800);
                set.addAnimation(animation);
                LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);
                laux.setTranslationY(4000);
                laux.setLayoutAnimation(controller);
                laux.startAnimation(animation);

            }
        });

        newDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AnimationSet set = new AnimationSet(true);
                Animation animation;
                animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                animation.setDuration(800);
                set.addAnimation(animation);
                LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);
                laux.setTranslationY(4000);
                laux.setLayoutAnimation(controller);
                laux.startAnimation(animation);

            }
        });

        bregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnimationSet set = new AnimationSet(true);
                Animation animation;
                animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                animation.setDuration(500);
                set.addAnimation(animation);
                LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);
                sg.setTranslationY(0);
                sg.startAnimation(animation);
                laux.setTranslationY(4000);
                laux.startAnimation(animation);


                overridePendingTransition(R.anim.izquierdaentra, R.anim.izquierdasale);
            }
        });


        sg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnimationSet set = new AnimationSet(true);
                Animation animation;
                animation = new TranslateAnimation(Animation.START_ON_FIRST_FRAME, 1.1f, Animation.START_ON_FIRST_FRAME, 0.1f, Animation.START_ON_FIRST_FRAME, 1.1f, Animation.START_ON_FIRST_FRAME, 0.1f);
                animation.setDuration(1000);
                set.addAnimation(animation);
                LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);
                sg.setTranslationY(4000);
                sg.setLayoutAnimation(controller);
                sg.startAnimation(animation);

            }
        });
        //text1.setTypeface(fonts.montserrat(this));

        //transicion de los 3 botones del inicio

        Animation transparencia;
        transparencia = AnimationUtils.loadAnimation(this, R.anim.transparencia);
        transparencia.reset();
        ln1.startAnimation(transparencia);
        ln2.startAnimation(transparencia);
        ln3.startAnimation(transparencia);

        if(isOnline(MainActivity.this) ==false){

            String mensajee ="Oops revisa tu conexión a internet!";

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            mensajee, Toast.LENGTH_SHORT);

            toast1.show();
        }
        else {

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            googleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        }

        imb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN_CODE);
            }
        });
    }

    public void Abrir(){
        AnimationSet set = new AnimationSet(true);
        Animation animation;
        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(1000);
        set.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);
        laux.setTranslationY(0);
        laux.setLayoutAnimation(controller);
        laux.startAnimation(animation);
    }

    public void httpC (String url) throws Exception{

    final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
            null, CustomSSLSocketFactory.getSSLSocketFactory(MainActivity.this)));

        final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "",
            "Loading. Please wait...", true);
    JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try{

                        if(response.length()== 0){
                            String mensajee ="Usuario o Contraseña incorrectos, intente de nuevo!";

                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            mensajee, Toast.LENGTH_SHORT);

                            toast1.show();
                            user.setHintTextColor(getResources().getColor(R.color.redGo));
                            pss.setHintTextColor(getResources().getColor(R.color.redGo));
                            dialog.dismiss();

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
                                settings.user.setaBoolean(true);
                            }

                                String n  = settings.user.getUser();
                                String ph  = settings.user.getPassword();
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString(User, n);
                                editor.putString(Password, pss.getText().toString());
                                editor.putString(Status,"a");
                                editor.putString(IDUSER,settings.user.getId().toString());
                                editor.putString(REFERID,settings.user.getPhone());
                                editor.commit();



                            /*DataBaseHelper MDB = new DataBaseHelper(getApplicationContext());


                            MDB.insertarCONTACTO(1, settings.user.getUser(), settings.user.getPassword());*/

                            Intent go = new Intent(MainActivity.this, showAddress.class);
                            String mensajee ="Bienvenido (a)  "+ settings.user.getName();

                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            mensajee, Toast.LENGTH_SHORT);

                            toast1.show();
                            go.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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

            String err = error.getCause().getMessage();

            if (err.equals("javax.net.ssl.SSLProtocolException: SSL handshake aborted: ssl=0x74614008: Failure in SSL library, usually a protocol error\n" +
                    "error:14077410:SSL routines:SSL23_GET_SERVER_HELLO:sslv3 alert handshake failure (external/openssl/ssl/s23_clnt.c:741 0x71872d74:0x00000000)")){
                String mensajee ="Oops, Revisa tu conexión a internet!";

                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                mensajee, Toast.LENGTH_SHORT);

                toast1.show();
            }
            else{
                try {
                    httpCities();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            dialog.dismiss();


        }
    }
    );
        queue.add(jsonArrayRequest);
}

    public void httpSignin (String url) throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(MainActivity.this)));


        final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "",
                "Loading. Please wait...", true);
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(JsonObjectRequest.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            int z = response.length();

                            if(response.length()==1){

                                if(response.getString("registro")== "true"){
                                    String b = "Usuario creado con exito!";
                                    Toast toast1 =
                                            Toast.makeText(getApplicationContext(),
                                                    b, Toast.LENGTH_LONG);

                                    toast1.show();
                                    sg.setTranslationY(4000);
                                    Abrir();


                                    dialog.dismiss();
                                }

                            }
                            else{
                                String hola = new String(response.getString("dato"));

                                String b =hola.replaceAll("<br> -"," ");
                                String c= b.replaceAll("</b>"," ");
                                String a =c.replaceAll("<b>"," ");
                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(),
                                                a, Toast.LENGTH_LONG);

                                toast1.show();
                                dialog.dismiss();
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

                finish();
                startActivity(getIntent());

                dialog.dismiss();


            }
        }
        );
        queue.add(jsonObjectRequest);
    }

    public void httpCities () throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(MainActivity.this)));


        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, settings.user.getURL()+"svice=CIUDADES&metodo=json", null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(JSONArray response) {
                        try{

                            settings.spinner.spinners= new ArrayList<>();

                            for(int i =0;i<response.length();i++){

                                final JSONObject spinners =(JSONObject) response.getJSONObject(i);

                                settings.spinner.spinners.add(new spinner(spinners.getString("ciudad"),spinners.getInt("id_ciudad")));

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

                try {
                    httpCities();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        );
        queue.add(jsonArrayRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode, data);

        if (requestCode == SIGN_IN_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            try {
                handleSignInResult(result);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
        else{
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void handleSignInResult(GoogleSignInResult result) throws IOException, GeneralSecurityException {
        Integer h=0;
        if (result.isSuccess()) {

            GoogleSignInAccount account = result.getSignInAccount();
            String k = sharedpreferences.getString(VALIDATOR, "");
            if (!k.equals("true")){
                String mensajee ="Redirigiendo...";

                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                mensajee, Toast.LENGTH_SHORT);

                toast1.show();
                loginWithGoogle("https://godomicilios.co/webService/servicios.php?svice=REG_USR_REDES&metodo=json&correo="+
                        account.getEmail()+"&nombres="+account.
                        getGivenName()+"&apellidos="+account.getFamilyName() +"&tipo=GOOGLE");
            }

        }
    }

    private void goMainScreen() {



        Intent intent = new Intent(this, testTwo.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);    }

    public void autenticationGoogle (GoogleSignInResult result){

//        GoogleSignInAccount account = result.getSignInAccount();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    public void loginWithGoogle (String url) throws IOException, GeneralSecurityException {

        url = url.replace(" ", "%20");

        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(MainActivity.this)));


        final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "",
                "Loading. Please wait...", true);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            if (response.length()==0){

                            }

                            for(int i =0;i<response.length();i++){
                                final JSONObject user = (JSONObject) response.getJSONObject(1);

                                settings.user.setId(user.getInt("id_usr"));
                                settings.user.setName(user.getString("nombres"));
                                settings.user.setSurname(user.getString("apellidos"));
                                settings.user.setEmail(user.getString("correo"));
                                settings.user.setPhone(user.getString("cod_referencia"));
                                settings.user.setUser(user.getString("usr"));
                                settings.user.setPassword(user.getString("pwd"));
                                settings.user.setaBoolean(true);

                            }
                                String n  = settings.user.getUser();
                                String ph  = settings.user.getPassword();
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString(User, n);
                                editor.putString(Status,"a");
                                editor.putString(IDUSER,settings.user.getId().toString());
                                editor.putString(REFERID,settings.user.getPhone());
                                editor.commit();

                            Intent go = new Intent(MainActivity.this, showAddress.class);
                            String mensajee ="Bienvenido (a)  "+ settings.user.getName();

                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            mensajee, Toast.LENGTH_SHORT);

                            toast1.show();
                            go.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(go);
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

                String mensajee ="Oops ocurrio un error"+" "+error.toString();

                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                mensajee, Toast.LENGTH_SHORT);

                toast1.show();
                dialog.dismiss();


            }
        }
        );
        queue.add(jsonArrayRequest);

    }
    public void loginWithFacebook (String url) throws IOException, GeneralSecurityException {

        url = url.replace(" ", "%20");
        url = url.replaceAll("\"", "");

        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(MainActivity.this)));


        final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "",
                "Loading. Please wait...", true);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            if (response.length()==0){

                            }

                            for(int i =0;i<response.length();i++){
                                final JSONObject user = (JSONObject) response.getJSONObject(1);

                                settings.user.setId(user.getInt("id_usr"));
                                settings.user.setName(user.getString("nombres"));
                                settings.user.setSurname(user.getString("apellidos"));
                                settings.user.setEmail(user.getString("correo"));
                                settings.user.setPhone(user.getString("cod_referencia"));
                                settings.user.setUser(user.getString("usr"));
                                settings.user.setPassword(user.getString("pwd"));
                                settings.user.setaBoolean(true);

                            }
                            String n  = settings.user.getUser();
                            String ph  = settings.user.getPassword();
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(User, n);
                            editor.putString(Status,"a");
                            editor.putString(IDUSER,settings.user.getId().toString());
                            editor.putString(REFERID,settings.user.getPhone());
                            editor.commit();

                            Intent go = new Intent(MainActivity.this, showAddress.class);
                            String mensajee ="Bienvenido (a)  "+ settings.user.getName();

                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            mensajee, Toast.LENGTH_SHORT);

                            toast1.show();
                            go.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(go);

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
    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            try {
                handleSignInResult(result);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    try {
                        handleSignInResult(googleSignInResult);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    private class loginUser extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String i = sharedpreferences.getString(User, "");
            String j = sharedpreferences.getString(Password, "");

            try {
                    httpC ("https://godomicilios.co/webService/servicios.php?svice=LOGIN&metodo=json&usr="+
                            i+"&pwd="+j);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            return "Executed";
        }}
}