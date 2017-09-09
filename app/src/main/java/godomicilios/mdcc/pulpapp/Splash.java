package godomicilios.mdcc.pulpapp;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import godomicilios.mdcc.pulpapp.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.pulpapp.settings.settings;
import godomicilios.mdcc.pulpapp.settings.spinner;

/**
 * Created by PROGRAMACION5 first 08/03/2017.
 */


public class Splash extends AppCompatActivity {
    public static final String MyPREFERENCES= "myPreferences";
    public static final String User = "user";
    public static final String Password = "password";
    public static final String Status = "status";
    public static final String VALIDATOR = "validator";
    public static final String QUALIFY = "qualify";
    public static final String IDUSER= "iduser";
    public static final String REFERID ="referid";
    public static final String IDTOKEN = "idtoken";
    SharedPreferences sharedpreferences;
    private static final long SPLASH_SCREEN_DELAY = 7000;
    String idToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_screen);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        idToken = FirebaseInstanceId.getInstance().getToken();

        YoYo.with(Techniques.Shake)
                .duration(500)
                .repeat(2)
                .playOn(findViewById(R.id.imageView));


        if(isOnline(Splash.this)==true){

            if (sharedpreferences.getAll().size()>0 ){
                try {
                    httpCities();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        // Start the next activity
                        Intent mainIntent = new Intent().setClass(
                                Splash.this, MainActivity.class);
                        startActivity(mainIntent);

                        // Close the activity so the user won't able to go back this
                        // activity pressing Back button
                        finish();
                    }
                };
                // Simulate a long loading process first application startup.
                Timer timer = new Timer();
                timer.schedule(task, SPLASH_SCREEN_DELAY);
            }

        }

        else{
            String mensajee ="Oops revisa tu conexión a internet!";

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            mensajee, Toast.LENGTH_SHORT);

            toast1.show();
        }
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    public void httpCities () throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(Splash.this)));


        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, settings.user.getURL()+"svice=CIUDADES&metodo=json", null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(JSONArray response) {
                        try{

                            settings.spinner.spinners= new ArrayList<>();

                            for(int ii =0;ii<response.length();ii++){

                                final JSONObject spinners =(JSONObject) response.getJSONObject(ii);

                                settings.spinner.spinners.add(new spinner(spinners.getString("ciudad"),spinners.getInt("id_ciudad")));
                                String h = sharedpreferences.getString(Status, "");
                                String i = sharedpreferences.getString(User, "");
                                String j = sharedpreferences.getString(Password, "");
                                String k = sharedpreferences.getString(VALIDATOR, "");
                                String l = sharedpreferences.getString(QUALIFY, "");

                                if (k.equals("trues")){
                                    Intent go = new Intent(Splash.this, status.class);
                                    go.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(go);
                                }

                                else if(h.equals("a")){

                                    Intent go = new Intent(Splash.this, showAddress.class);
                                    go.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(go);
                                }
                                else if(!h.equals("a")){
                                    Intent intent = new Intent(Splash.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }

                            }
                            settings.user.setIdToken(idToken);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(IDTOKEN,idToken);
                            editor.commit();

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
            }
        }
        );
        queue.add(jsonArrayRequest);
    }


}
