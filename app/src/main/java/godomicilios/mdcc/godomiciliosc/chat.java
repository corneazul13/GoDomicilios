package godomicilios.mdcc.godomiciliosc;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import godomicilios.mdcc.godomiciliosc.R;
import godomicilios.mdcc.godomiciliosc.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.godomiciliosc.settings.chats;
import godomicilios.mdcc.godomiciliosc.settings.settings;
import godomicilios.mdcc.godomiciliosc.settings.stablishment;

public class chat extends AppCompatActivity {

    LinearLayout li;
    ImageView send;
    EditText searchedTxt;
    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
    String temporal="";
    Integer chatid =17, total =0;
    final Handler handler = new Handler();
    String urlChat= "https://godomicilios.co/webService/servicios.php?svice=ENVIAR_MSJ&metodo=json&datos=";
    String urlNew= "https://godomicilios.co/webService/servicios.php?svice=CONVERSACION&metodo=json&datos=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        li = (LinearLayout) findViewById(R.id.li);
        send = (ImageView) findViewById(R.id.send);
        searchedTxt = (EditText) findViewById(R.id.searchedTxt);
        settings.chats.chatses = new ArrayList<>();

 /*       try {
            iniChat();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        handler.postDelayed(new Runnable() {
            public void run() {
                    try {
                        newMess(urlNew+chatid);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                handler.postDelayed(this, 5000);
            }
        }, 5000);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!searchedTxt.getText().toString().replace(" ","").equals("")){
                    temporal = searchedTxt.getText().toString();
                    settings.chats.chatses.add(new chats(searchedTxt.getText().toString(),
                            "CLIENTE"));

                    View child = View.inflate(chat.this, R.layout.chat_out, null);
                    TextView text = (TextView) child.findViewById(R.id.text);
                    text.setText(temporal);
                    li.addView(child);
                    String urlEncoded = Uri.encode(temporal, ALLOWED_URI_CHARS);
                    searchedTxt.setText("");
          /*          JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("chatId", chatid);
                        jsonObject.put("usrId", 102);
                        jsonObject.put("sucId", 12);
                        jsonObject.put("msj", urlEncoded);
                        jsonObject.put("envia", "CLIENTE");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        sendM(urlChat+jsonObject.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
*/
                }
            }
        });

    }

    public void iniChat () throws Exception{
        final LinearLayout linear = (LinearLayout) findViewById(R.id.li);
        linear.removeAllViews();
        String url = "https://godomicilios.co/webService/servicios.php?svice=INICIAR_CHAT&metodo=json&usrId=102&sucId=2";

        settings.stablishment.stablishments=null;

        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(chat.this)));
        final ProgressDialog dialog = ProgressDialog.show(chat.this, "",
                "Loading. Please wait...", true);

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(JsonObjectRequest.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONObject response) {
                        try{
                            chatid=response.getInt("id");

                            dialog.dismiss();
                        }
                        catch (Exception e){
                        }
                        dialog.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String h = error.getCause().getMessage();
                if(h == ""){
                    try {
                        iniChat();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                dialog.dismiss();
            }
        }
        );
        queue.add(jsonObjectRequest);
    }

    public void sendM (final String url) throws Exception{
        final LinearLayout linear = (LinearLayout) findViewById(R.id.li);
        linear.removeAllViews();

        settings.stablishment.stablishments=null;

        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(chat.this)));
        final ProgressDialog dialog = ProgressDialog.show(chat.this, "",
                "Loading. Please wait...", true);

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(JsonObjectRequest.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONObject response) {
                        try{

                            dialog.dismiss();

                        }
                        catch (Exception e){
                        }
                        dialog.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    sendM(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                dialog.dismiss();
            }
        }
        );
        queue.add(jsonObjectRequest);
    }
    public void newMess (String url) throws Exception{
        final LinearLayout linear = (LinearLayout) findViewById(R.id.li);
        linear.removeAllViews();

        settings.stablishment.stablishments=null;

        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(chat.this)));


        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{
                            Integer count=response.length();
                            ArrayList<chats> server = new ArrayList<>();
                            for (int i = 0;i<count;i++){
                                final JSONObject chat = (JSONObject) response.getJSONObject(i);
                                settings.chats.chatses.add(new chats(
                                   chat.getString("mensaje"),
                                        chat.getString("quien_envia")
                                ));
                                Integer newTotal=0;

                                for (chats countServer:server){
                                    Integer validator=0;
                                    for(chats old:settings.chats.chatses){
                                        String idServer=countServer.getMessage();
                                        String idOld=countServer.getMessage();
                                        if(idOld.equals(idServer)){
                                            validator=1;
                                            break;
                                        }
                                    }
                                    if(validator==0){

                                        settings.chats.chatses.add(countServer);
                                        newTotal=1;
                                    }
                                    if(newTotal==1){
                                        Integer cou = settings.chats.chatses.size();
                                        View childs = View.inflate(chat.this, R.layout.chat_in, null);
                                        TextView text = (TextView) childs.findViewById(R.id.text);
                                        text.setText(settings.chats.chatses.get(cou-1).getMessage());
                                        li.addView(childs);
                                    }
                                }
                            }
                        }
                        catch (Exception e){
                        }
                        String errors = "";

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String errors = error.getMessage();
            }
        }
        );
        queue.add(jsonArrayRequest);
    }

}
