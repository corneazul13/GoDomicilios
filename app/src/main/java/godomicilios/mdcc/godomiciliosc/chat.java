package godomicilios.mdcc.godomiciliosc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import godomicilios.mdcc.godomiciliosc.adapter.chatAdapter;
import godomicilios.mdcc.godomiciliosc.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.godomiciliosc.settings.StationBuss;
import godomicilios.mdcc.godomiciliosc.settings.arrayChat;
import godomicilios.mdcc.godomiciliosc.settings.chats;
import godomicilios.mdcc.godomiciliosc.settings.settings;
import godomicilios.mdcc.godomiciliosc.settings.updateChat;


public class chat extends AppCompatActivity {

    RecyclerView recicler;
    ImageView send;
    EditText searchedTxt;
    TextView textName;
    CircleImageView pictures;
    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
    public static final String MyPREFERENCES = "myPreferences";
    String temporal="";
    Integer chatid , total =0;
    final Handler handler = new Handler();
    String urlChat= "https://godomicilios.co/webService/servicios.php?svice=ENVIAR_MSJ&metodo=json&datos=";
    String urlNew= "https://godomicilios.co/webService/servicios.php?svice=CONVERSACION&metodo=json&chat_id=";
    SharedPreferences sharedpreferences;
    Integer f=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recicler = (RecyclerView) findViewById(R.id.recicler);
        send = (ImageView) findViewById(R.id.send);
        searchedTxt = (EditText) findViewById(R.id.searchedTxt);
        pictures = (CircleImageView) findViewById(R.id.porfile_image);
        textName = (TextView) findViewById(R.id.nameStablish);
        settings.chats.chatses = new ArrayList<>();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        textName.setText(settings.shoppingCar.name);

            for(int i =0;i<settings.arrayChat.arrayChats.size();i++){
                Integer idS = settings.shoppingCar.idStablish;
                if(settings.arrayChat.arrayChats.get(i).getIdSuc().equals(idS)){
                    chatid = idS;
                    f=1;
                }
            }
            if(f.equals(0)){
                try {
                    iniChat("https://godomicilios.co/webService/servicios.php?svice=INICIAR_CHAT&metodo=json&usrId="+settings.user.getId()+"&sucId="+settings.shoppingCar.idStablish, settings.shoppingCar.idStablish );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        try {
            newMess(urlNew+chatid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String example = settings.shoppingCar.picture ;

        Picasso.with(chat.this)

                .load(example)
                .into(pictures, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        //do smth when picture is loaded successfully
                    }

                    @Override
                    public void onError() {
                        //do smth when there is picture loading error
                    }
                });




 /*       try {
            iniChat();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*handler.postDelayed(new Runnable() {
            public void run() {
                    try {
                        newMess(urlNew+chatid);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                handler.postDelayed(this, 5000);
            }
        }, 5000);*/

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text =searchedTxt.getText().toString().trim();

                if (!text.isEmpty()){
                    temporal = searchedTxt.getText().toString();
                    settings.chats.chatses.add(new chats(searchedTxt.getText().toString(),
                            "CLIENTE"));
                    updateView(settings.chats.chatses);

                    String urlEncoded = Uri.encode(temporal, ALLOWED_URI_CHARS);
                    searchedTxt.setText("");
                   JSONObject jsonObject = new JSONObject();
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


                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        StationBuss.getBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        StationBuss.getBus().unregister(this);
    }

    @Subscribe
    public void updateView (updateChat update){
        boolean confirm = update.isConfirm();
        int onClass = update.getOnClass();
        if(confirm){
            if(onClass==1){
                updateView(settings.chats.chatses);
            }
        }
    }

    public void iniChat (final String url, final Integer hh) throws Exception{
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
                            chatid=response.getInt("id");
                            settings.arrayChat.arrayChats.add(new arrayChat(
                                    hh, chatid
                            ));

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
                        iniChat(url, hh);
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

        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(chat.this)));

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(JsonObjectRequest.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONObject response) {
                        try{

                            Integer h =response.getInt("insert");

                            if(h.equals(1)){

                            }
                            StationBuss.getBus().post(new updateChat(true,1));

                        }
                        catch (Exception e){
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    sendM(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        );
        queue.add(jsonObjectRequest);
    }
    public void newMess (String url) throws Exception{
        final LinearLayout linear = (LinearLayout) findViewById(R.id.li);
        //linear.removeAllViews();

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
                            for (int i = 0;i<count;i++) {
                                final JSONObject chat = (JSONObject) response.getJSONObject(i);
                                settings.chats.chatses.add(new chats(
                                        chat.getString("mensaje"),
                                        chat.getString("quien_envia")
                                ));
                            }
                            updateView(settings.chats.chatses);
                        }
                        catch (Exception e){
                            String error = e.getMessage();
                        }


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

    public void updateView (ArrayList<chats>  chats){
        if(!chats.isEmpty()){
            chatAdapter adapter = new chatAdapter(chat.this,chats);
            recicler.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
            recicler.setAdapter(adapter);
            recicler.getItemAnimator().setAddDuration(300);
            recicler.scrollToPosition(adapter.getItemCount()-1);
        }
    }

    public ArrayList<String> getIdsChats(String chats) {
        ArrayList<String> pics = new ArrayList<>();
        chats.split(",");
        String picturesList[] = chats.split(",");
        for (String pic : picturesList) {
            pics.add(pic);
        }
        return pics;

    }


}
