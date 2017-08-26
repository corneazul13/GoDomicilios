package godomicilios.mdcc.godomiciliosc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.easing.linear.Linear;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import godomicilios.mdcc.godomiciliosc.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.godomiciliosc.settings.answerOrder;
import godomicilios.mdcc.godomiciliosc.settings.arrayChat;
import godomicilios.mdcc.godomiciliosc.settings.canvas;
import godomicilios.mdcc.godomiciliosc.settings.settings;
import godomicilios.mdcc.godomiciliosc.settings.shoppingCar;

import static android.R.attr.format;

public class status extends AppCompatActivity {
    LinearLayout li;
    private static final String FORMAT = "%02d:%02d:%02d";

    int secondss, minutess;
    Integer cancelBtn=0;

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    final Handler handler = new Handler();
    String firstUrl = "https://godomicilios.co/webService/servicios.php?svice=RESPUESTA_PEDIDO&metodo=json&pedidoId=";
    public static final String MyPREFERENCES = "myPreferences";
    public static final String VALIDATOR = "validator";
    public static final String CANT = "cant";
    public static final String PICTURES = "pictures";
    public static final String DATE= "date";
    public static final String CHATS = "chats";
    public static final shoppingCar SHOPPING_CAR = settings.shoppingCar;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("HH", Locale.US);
    String hour = format.format(new Date());
    SharedPreferences sharedpreferences;
    ArrayList<Long> times = new ArrayList<>();
    String chatsIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        li = (LinearLayout) findViewById(R.id.li);
        settings.answerOrder.answerOrders = new ArrayList<>();
        settings.arrayChat.arrayChats = new ArrayList<>();

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        /*SharedPreferences.Editor editors = sharedpreferences.edit();
        editors.putString(VALIDATOR,"false");
        editors.commit();*/

        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);



        if (sharedpreferences.getAll().size() > 0 && sharedpreferences.getString(VALIDATOR, "").equals("true")) {
            String chats =sharedpreferences.getString(CHATS, "");


            String pictures = sharedpreferences.getString(PICTURES, "");
            String numString = sharedpreferences.getString(CANT, "");
            String dataTime = sharedpreferences.getString(DATE, "");
            Integer num = Integer.parseInt(numString);
            if(!chats.equals("")){
                for (int g = 0; g < num; g++) {
                    showAll(g, getPictures(pictures));
                }

                setChatId(getChats(chats));
            }




        } else {
            if (settings.shoppingCar.carFinal != null && settings.shoppingCar.carFinal.size() > 0) {
                Integer f = settings.shoppingCar.carFinal.size();
                ArrayList<String> nothing = new ArrayList<>();
                ArrayList<String> strings = new ArrayList<>();

                    for (int h = 0; h < f; h++) {

                        showAll(h, nothing);
                        String newString= "http://godomicilios.co/admin/documentosVarios/" + settings.shoppingCar.carFinal.get(h).getImg();
                        strings.add(new String(newString));
                    }
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(VALIDATOR, "true");
                    editor.putString(CANT, f.toString());
                    editor.putString(PICTURES, picturestoString(strings));
                    editor.commit();
                }
            }
        handler.postDelayed(new Runnable() {
            public void run() {

                for(int fi=0;fi<settings.shoppingCar.carFinal.size();fi++){

                    try {
                        https(firstUrl+settings.shoppingCar.carFinal.get(fi).getIdOrder());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                handler.postDelayed(this, 5000);
            }
        }, 5000);
        }


    private void cancel(Integer nu) {
        if (settings.answerOrder.answerOrders.get(nu).getCountDownTimer() != null) {
            if(cancelBtn==0){
                li.removeViewAt(nu);
                qual(nu);
            }
            settings.answerOrder.answerOrders.get(nu).getCountDownTimer().cancel();
            CountDownTimer c = null;
            settings.answerOrder.answerOrders.get(nu).setCountDownTimer(c);
            settings.answerOrder.answerOrders.get(nu).getText().setText(" " + String.format("%2d", minutess)
                    + ":" + String.format("%02d", secondss));

            if(settings.shoppingCar.carFinal!=null){
                li.removeViewAt(nu);
                settings.shoppingCar.carFinal.remove(nu);
                Integer f = settings.shoppingCar.carFinal.size();
                ArrayList<String> nothing = new ArrayList<>();
                ArrayList<String> strings = new ArrayList<>();

                for (int h = 0; h < f; h++) {

                    showAll(h, nothing);
                    String newString= "http://godomicilios.co/admin/documentosVarios/" + settings.shoppingCar.carFinal.get(h).getImg();
                    strings.add(new String(newString));
                }
                if (sharedpreferences.getAll().size() < 1) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(VALIDATOR, "true");
                    editor.putString(CANT, f.toString());
                    editor.putString(PICTURES, picturestoString(strings));
                    editor.commit();}
                count(settings.shoppingCar.carFinal.size(), strings);
            }
            else{

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(VALIDATOR, "false");
                    editor.commit();

            }
        }
    }

    public void count(final TextView text1, final Button moreTime) {

        new CountDownTimer(300 * 1000 + 1000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;

                text1.setText(" " + String.format("%2d", minutes)
                        + ":" + String.format("%02d", seconds));

            }

            public void onFinish() {
                moreTime.setVisibility(View.VISIBLE);
                text1.setText(" " + String.format("%2d", minutess)
                        + ":" + String.format("%02d", secondss));
            }
        }.start();


    }

    public Bitmap getRoundedCornerBitmap(Bitmap bitmap, boolean square) {
        int width = 0;
        int height = 0;

        if (square) {
            if (bitmap.getWidth() < bitmap.getHeight()) {
                width = 320;
                height = 320;
            } else {
                width = 320;
                height = 320;
            }
        } else {
            height = bitmap.getHeight();
            width = bitmap.getWidth();
        }

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = getResources().getColor(R.color.grayCircle);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, height);
        final RectF rectF = new RectF(rect);
        final float roundPx = 90;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(160, 160, 160, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public void https(final String url) throws Exception {


        final RequestQueue queue = Volley.newRequestQueue(this, new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(status.this)));

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try {
                            for (int i = 0;i<response.length();i++){
                                final JSONObject object = response.getJSONObject(i);
                                Integer sta =object.getInt("estado_venta");
                                if (sta.equals(2)){

                                }
                                else if (sta.equals(3)){

                                }
                            }

                        } catch (Exception e) {
                            String er = e.getMessage();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String er = error.getMessage();
            }
        }
        );
        queue.add(jsonArrayRequest);
    }

    public void showAll(Integer h, ArrayList<String> pictures) {

        View child = View.inflate(status.this, R.layout.activity_test, null);
        final Button moreTime = (Button) child.findViewById(R.id.moreTime);
        final TextView text1 = (TextView) child.findViewById(R.id.textView1);
        final ImageView imageView = (ImageView) child.findViewById(R.id.profile_image);
        final LinearLayout blue = (LinearLayout) child.findViewById(R.id.blue);
        final Button cancel = (Button) child.findViewById(R.id.button4);
        final LinearLayout goChat = (LinearLayout) child.findViewById(R.id.goChat);
        goChat.setId(h);

        goChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.shoppingCar.picture=settings.shoppingCar.carFinal.get(goChat.getId()).getImg();
                settings.shoppingCar.name = settings.shoppingCar.carFinal.get(goChat.getId()).getName();
                Intent go = new Intent(status.this, chat.class);
                startActivity(go);
            }
        });
        cancel.setId(h);
        settings.answerOrder.answerOrders.add(new answerOrder(
                h, new CountDownTimer(3000 * 1000 + 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        }, moreTime
        ));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel(cancel.getId());
                cancelBtn = 1;
            }
        });

        moreTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count(text1, moreTime);
                moreTime.setVisibility(View.INVISIBLE);
            }
        });

        String example ;
        if (pictures.size()<1) {
            example = "http://godomicilios.co/admin/documentosVarios/" + settings.shoppingCar.carFinal.get(h).getImg();
        } else {
            example = pictures.get(h);
        }


        Picasso.with(status.this)

                .load(example)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        //do smth when picture is loaded successfully
                    }

                    @Override
                    public void onError() {
                        //do smth when there is picture loading error
                    }
                });
        CountDownTimer count = new CountDownTimer(30 * 1000 + 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;

                text1.setText(" " + String.format("%2d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            @Override
            public void onFinish() {
                moreTime.setVisibility(View.VISIBLE);
                text1.setText(" " + String.format("%2d", minutess)
                        + ":" + String.format("%02d", secondss));

            }
        };
        count.start();
        settings.answerOrder.answerOrders.set(h, new answerOrder(settings.answerOrder.answerOrders.get(h).getId(),
                count, settings.answerOrder.answerOrders.get(h).getText()));
        li.addView(child);

    }

    public ArrayList<String> getPictures(String pictures) {
        ArrayList<String> pics = new ArrayList<>();
        pictures.split(",");
        String picturesList[] = pictures.split(",");
        for (String pic : picturesList) {
            pics.add(pic);
        }
        return pics;

    }

    public String picturestoString(ArrayList<String> strings) {
        String all = "";

        for (int i = 0; i < strings.size(); i++) {
            String one = strings.get(i);
            if (i == 0) {
                all = one + ",";
            }
            all = all + one + ",";
        }
        return all;
    }
    public void qual (Integer i){

    }
    public void count (Integer h, ArrayList<String> strings){
        if(h==0){
            if (sharedpreferences.getAll().size() < 1) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(VALIDATOR, "false");
                editor.putString(CANT, h.toString());
                editor.putString(PICTURES, picturestoString(strings));
                editor.commit();
            }
        }

    }
    public ArrayList<String> getChats(String pictures) {
        ArrayList<String> pics = new ArrayList<>();
        pictures.split(",");
        String picturesList[] = pictures.split(",");
        for (String pic : picturesList) {
            pics.add(pic);
        }
        return pics;

    }

    public void  setChatId(ArrayList<String> strings) {
        ArrayList<String> pics = new ArrayList<>();

        for (int i = 0; i < strings.size(); i++) {
            String picturesList[] = strings.get(i).split(":");
            settings.arrayChat.arrayChats.add(new arrayChat(Integer.parseInt(picturesList[0]), Integer.parseInt(picturesList[1])));
        }
    }

}
