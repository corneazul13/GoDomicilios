package godomicilios.mdcc.pulpapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.renderscript.Sampler;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import godomicilios.mdcc.pulpapp.settings.ImageConverter;
import godomicilios.mdcc.pulpapp.settings.canvas;


public class tester extends AppCompatActivity
        {
            TextView text1;
            Button moreTime;
            ImageView imageView, image;

            private static final String FORMAT = "%02d:%02d:%02d";

            int secondss , minutess;

            private LoginButton loginButton;
            private CallbackManager callbackManager;

            @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        /*moreTime = (Button) findViewById(R.id.moreTime);
        text1=(TextView)findViewById(R.id.textView1);
         imageView = (ImageView) findViewById(R.id.profile_image);

        moreTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count();
                moreTime.setVisibility(View.INVISIBLE);
            }
        });

        count();*/

                /*Picasso.with(test.this)
                        .load("http://godomicilios.co/admin/documentosVarios/2017_03_22_11_28_22_logokokoriko.png")
                        .into(imageView, new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {
                               *//* //do smth when picture is loaded successfully

                                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                                //imageView.setImageBitmap(canvas.getRoundedCornerBitmap(bitmap, true));

                                RoundedBitmapDrawable roundedDrawable =
                                        RoundedBitmapDrawableFactory.create(getResources(), bitmap);

                                roundedDrawable.setCornerRadius(bitmap.getHeight());
                                imageView.setImageDrawable(roundedDrawable);
*//*
                            }

                            @Override
                            public void onError() {
                                //do smth when there is picture loading error
                            }
                        });*/


                //Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.bannercomida);


                callbackManager = CallbackManager.Factory.create();

                //loginButton = (LoginButton) findViewById(R.id.loginButton);

                loginButton.setReadPermissions(Arrays.asList("public_profile", "user_friends"));

                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
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
                                            String email;
                                            String id = object.getString("id");
                                            if ( L < 3){
                                                String birthday = object.getString("birthday");
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
                        Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            private void goMainScreen() {
                Intent intent = new Intent(this, choose.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                callbackManager.onActivityResult(requestCode, resultCode, data);
            }


    public void count (){

        new CountDownTimer(300* 1000+1000, 1000) { // adjust the milli seconds here

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

                if(square){
                    if(bitmap.getWidth() < bitmap.getHeight()){
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
                canvas.drawCircle(160 , 160, 160, paint);

                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(bitmap, rect, rect, paint);

                return output;
            }

        }




