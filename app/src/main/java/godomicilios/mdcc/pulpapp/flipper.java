package godomicilios.mdcc.pulpapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import godomicilios.mdcc.pulpapp.settings.settings;

public class flipper extends AppCompatActivity {


        public float init_x;
        private ViewFlipper vf;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_flipper);

            settings.user.analytics = GoogleAnalytics.getInstance(this);
            settings.user.analytics.setLocalDispatchPeriod(1800);
            settings.user.tracker = settings.user.analytics.newTracker("UA-101326412-1");
            settings.user.tracker.enableExceptionReporting(true);
            settings.user.tracker.enableAdvertisingIdCollection(true);
            settings.user.tracker.enableAutoActivityTracking(true);

            vf = (ViewFlipper) findViewById(R.id.viewFlipper);

            LinearLayout food = (LinearLayout) findViewById(R.id.food);
            food.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    vf.showNext();
                }
            });

            LinearLayout beer = (LinearLayout) findViewById(R.id.beer);
            beer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    vf.showPrevious();
                }
            });

            vf.setOnTouchListener(new ListenerTouchViewFlipper());

        }

        public class ListenerTouchViewFlipper implements View.OnTouchListener{

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: //Cuando el usuario toca la pantalla por primera vez
                        init_x=event.getX();
                        return true;
                    case MotionEvent.ACTION_UP: //Cuando el usuario deja de presionar
                        float distance =init_x-event.getX();

                        if(distance>0)
                        {
                            vf.setInAnimation(inFromRightAnimation());
                            vf.setOutAnimation(outToLeftAnimation());
                            vf.showPrevious();
                        }

                        if(distance<0)
                        {
                            vf.setInAnimation(inFromLeftAnimation());
                            vf.setOutAnimation(outToRightAnimation());
                            vf.showNext();
                        }

                    default:
                        break;
                }

                return false;
            }

        }

        private Animation inFromRightAnimation() {

            Animation inFromRight = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT,  +1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
                    Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f );

            inFromRight.setDuration(500);
            inFromRight.setInterpolator(new AccelerateInterpolator());

            return inFromRight;

        }

        private Animation outToLeftAnimation() {
            Animation outtoLeft = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, -1.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f);
            outtoLeft.setDuration(500);
            outtoLeft.setInterpolator(new AccelerateInterpolator());
            return outtoLeft;
        }

        private Animation inFromLeftAnimation() {
            Animation inFromLeft = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, -1.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f);
            inFromLeft.setDuration(100);
            inFromLeft.setInterpolator(new AccelerateInterpolator());
            return inFromLeft;
        }

        private Animation outToRightAnimation() {
            Animation outtoRight = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, +1.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f);
            outtoRight.setDuration(100);
            outtoRight.setInterpolator(new AccelerateInterpolator());
            return outtoRight;
        }
    }

