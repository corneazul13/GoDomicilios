package godomicilios.mdcc.godomiciliosc;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import godomicilios.mdcc.godomiciliosc.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.godomiciliosc.settings.methodPay;
import godomicilios.mdcc.godomiciliosc.settings.settings;
import godomicilios.mdcc.godomiciliosc.settings.temporalCar;


public class car extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public static GoogleAnalytics analytics;

    private static Tracker tracker;

    LinearLayout li, princ, detail, show, up;
    TextView total;
    Button confrim, cancel;
    Integer tota =0;
    TextView numberCar;
    Integer totals = 0;
    ImageView cash, datapho, onLine;
    Integer view=0;
    String firstUrl = "https://godomicilios.co/webService/servicios.php?svice=COMPRAR_PRODUCTOS&metodo=json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        li = (LinearLayout) findViewById(R.id.li);
        total = (TextView) findViewById(R.id.total);
        confrim= (Button) findViewById(R.id.confirm);
        cancel = (Button) findViewById(R.id.cancel);
        numberCar = (TextView) findViewById(R.id.numberCar);
        Integer car= settings.shoppingCar.carFinal.size();
        numberCar.setText(settings.user.getCarCant());
        cash = (ImageView) findViewById(R.id.cash);
        datapho = (ImageView) findViewById(R.id.datapho);
        onLine = (ImageView) findViewById(R.id.onLine);
        show = (LinearLayout) findViewById(R.id.show);
        up = (LinearLayout) findViewById(R.id.up);
        show.setTranslationY(4000);
        if (settings.temporalCar.temporalCars==null){
            settings.temporalCar.temporalCars = new ArrayList<>();
        }


        settings.user.analytics = GoogleAnalytics.getInstance(this);
        settings.user.analytics.setLocalDispatchPeriod(1800);
        settings.user.tracker = settings.user.analytics.newTracker("UA-101326412-1");
        settings.user.tracker.enableExceptionReporting(true);
        settings.user.tracker.enableAdvertisingIdCollection(true);
        settings.user.tracker.enableAutoActivityTracking(true);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view ==1){
                    view =0;
                    AnimationSet set = new AnimationSet(true);
                    Animation animation;
                    animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                            0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
                            0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
                    animation.setDuration(1000);
                    set.addAnimation(animation);
                    LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);
                    show.setTranslationY(4000);
                    show.startAnimation(animation);
                    overridePendingTransition(R.anim.izquierdasale, R.anim.izquierdaentra);
                }
            }
        });

        confrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer sendCar=0;

                for (int g =0; g<settings.temporalCar.temporalCars.size();g++){
                    if(settings.temporalCar.temporalCars.get(g).getMethod().equals("none")){
                        Toast.makeText(getApplicationContext(), "Debes escoger un metodo de pago!", Toast.LENGTH_LONG).show();
                        sendCar =1;
                        break;
                    }
                }

                if(sendCar==0){
                        for (int p =0;p<settings.shoppingCar.carFinal.size();p++){
                            String id,idBranch, sale,domi, coupon, idPay = null, payApp,
                                    addressDomi, latit, longi, idStablish;

                            switch (settings.temporalCar.temporalCars.get(p).getMethod()){
                                case "EFECTIVO":
                                    idPay="&metodo_pago_id=1";
                                    break;
                                case "DATÁFONO":
                                    idPay="&metodo_pago_id=2";
                                    break;
                                case "OnLine":
                                    idPay="&metodo_pago_id=3";
                                    break;
                            }

                            id= "&id_usr="+settings.user.getId();
                            idBranch = "&sucId="+settings.shoppingCar.carFinal.get(p).getIdBranch().toString();
                            sale = "&ventaPedido="+settings.shoppingCar.carFinal.get(p).getSubtotal().toString();
                            domi = "&domicilio="+settings.shoppingCar.carFinal.get(p).getPrice();
                            coupon = "&desc_cupon="+settings.temporalCar.temporalCars.get(p).getCoupon().toString();
                            String h= settings.shoppingCar.carFinal.get(p).getPrice();
                            h=h.replace("$ ","");
                            Integer usrApp=Integer.parseInt(h)+settings.shoppingCar.carFinal.get(p).getSubtotal();
                            payApp = "&pago_usr_app="+usrApp;
                            addressDomi = "&direccionDomicilio="+settings.order.getAddress();
                            latit = "&latitud=" +Double.toString(settings.order.getLatitude());
                            longi = "&longitud="+Double.toString(settings.order.getLongitude());
                            idStablish = "&empId="+settings.shoppingCar.carFinal.get(p).getId().toString();

                            JSONArray carJson = new JSONArray();
                            JSONObject jsonObject = null;
                            String [] json= new String [settings.shoppingCar.carFinal.get(p).getProductCars().size()];
                            for (int o =0;o<settings.shoppingCar.carFinal.get(p).getProductCars().size();o++){
                                jsonObject= new JSONObject();
                                Integer [] ingr;
                                String [] adic;
                                adic = new String [settings.shoppingCar.carFinal.get(p).getProductCars().get(o).getAdditionCars().size()];
                                ingr = new Integer[settings.shoppingCar.carFinal.get(p).getProductCars().get(o).getIngredientsCars().size()];
                                Integer kj = settings.shoppingCar.carFinal.get(p).getProductCars().get(o).getAdditionCars().size();
                                for(int b=0;b<kj;b++){

                                    String vb=settings.shoppingCar.carFinal.get(p).getProductCars().get(o).getAdditionCars().get(b).id.toString();
                                    Integer cantt = settings.shoppingCar.carFinal.get(p).getProductCars().get(o).additionCars.get(b).cant+1;
                                    String bv=cantt.toString();
                                    if(b==kj-1){
                                        adic[b] = vb+"/"+bv;
                                    }
                                    else{
                                        adic[b] = vb+"/"+bv+",";
                                    }

                                }


                                if (settings.shoppingCar.carFinal.get(p).getProductCars().get(o).getIngredientsCars().size()>0){
                                    for(int b=0;b<settings.shoppingCar.carFinal.get(p).getProductCars().get(o).getIngredientsCars().size();b++){
                                        ingr[b] = settings.shoppingCar.carFinal.get(p).getProductCars().get(o).getIngredientsCars().get(b).getId();
                                    }
                                }

                                StringBuffer cadena = new StringBuffer();
                                for (int x=0;x<ingr.length;x++){
                                    cadena =cadena.append(ingr[x]);
                                }
                                StringBuffer   cadena2 = new StringBuffer();
                                for (int x=0;x<adic.length;x++){
                                    cadena2 =cadena2.append(adic[x]);
                                }

                                try {
                                    jsonObject.put("id_producto",
                                            settings.shoppingCar.carFinal.get(p).getProductCars().get(o).getIdProduct());
                                    jsonObject.put("venta_producto",
                                            settings.shoppingCar.carFinal.get(p).getSubtotal());
                                    jsonObject.put("bebida",
                                            settings.shoppingCar.carFinal.get(p).getProductCars().get(o).getDrinkCar().getId());
                                    jsonObject.put("array_ingredientes", cadena);
                                    jsonObject.put("array_adiciones", cadena2);
                                    jsonObject.put("observacion", settings.shoppingCar.carFinal.get(p).getProductCars().get(o).getObser());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                if(p==settings.shoppingCar.carFinal.get(p).getProductCars().size()-1){
                                    json[o] = jsonObject.toString();
                                }
                                else{
                                    json[o] =jsonObject.toString()+",";
                                }

                            }
                            StringBuffer carrr = new StringBuffer();
                            for (int x=0;x<json.length;x++){
                                carrr =carrr.append(json[x]);
                            }
                            
                            try {
                                sendCar(firstUrl+id+idBranch+sale+domi+idPay+coupon+payApp+addressDomi+latit+longi+idStablish+"&carrito=["+carrr+"]");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        Intent go = new Intent(car.this, status.class);
                        startActivity(go);
                        settings.couponActivate.setStatus(0);

                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(car.this, head.class);
                startActivity(go);
                settings.couponActivate.setStatus(0);
            }
        });

        for(int i = 0; i< settings.shoppingCar.carFinal.size(); i++)
        {
            /*View childs = View.inflate(car.this, R.layout.product_in_product, null);
            ImageView pictureProduct = (ImageView) childs.findViewById(R.id.pictureProduct);
            TextView name = (TextView) childs.findViewById(R.id.name);
            TextView unity = (TextView) childs.findViewById(R.id.unity);
            ImageView delete = (ImageView) childs.findViewById(R.id.delete);
            TextView seeDetail = (TextView) childs.findViewById(R.id.seeDetail);
            TextView update = (TextView) childs.findViewById(R.id.update);
            LinearLayout total = (LinearLayout) childs.findViewById(R.id.)*/

            Integer stId = settings.shoppingCar.carFinal.get(i).getId();
            if(settings.shoppingCar.carFinal.size()>settings.temporalCar.temporalCars.size()){
                settings.temporalCar.temporalCars.add(new temporalCar(
                        stId,i, 0, "none", 0, totals + settings.shoppingCar.carFinal.get(i).getSubtotal()
                ));
            }
            View child = View.inflate(car.this, R.layout.product_in_car, null);
            LinearLayout up = (LinearLayout) child.findViewById(R.id.up);
            TextView cant = (TextView) child.findViewById(R.id.cant);
            LinearLayout shows = (LinearLayout) findViewById(R.id.shows);
            ImageView imageOne = (ImageView) child.findViewById(R.id.imageOne);
            TextView name = (TextView) child.findViewById(R.id.name);
            TextView duration = (TextView) child.findViewById(R.id.duration);
            TextView distance = (TextView) child.findViewById(R.id.distance);
            TextView sub = (TextView) child.findViewById(R.id.sub);
            TextView re = (TextView) child.findViewById(R.id.re);
            TextView cou = (TextView) child.findViewById(R.id.textView12);
            final LinearLayout addCoupon = (LinearLayout) child.findViewById(R.id.addCoupon);
            LinearLayout me = (LinearLayout) child.findViewById(R.id.met);
            final TextView meth = (TextView) child.findViewById(R.id.meth);
            final ImageView image = (ImageView) child.findViewById(R.id.image);
            image.setId(i);
            LinearLayout color= (LinearLayout) child.findViewById(R.id.color);
            image.setId(i);
            Integer g=0;
            addCoupon.setId(i);

            if(settings.temporalCar.temporalCars.get(i).getCoupon()>=0){
                cou.setText("$ "+settings.temporalCar.temporalCars.get(i).getCoupon().toString());
            }
            addCoupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent go = new Intent (car.this, coupons.class);
                    startActivity(go);
                    settings.couponActivate.setStatus(1);
                    settings.temporalCar.setIdClick(addCoupon.getId());
                }
            });
            totals = totals + settings.shoppingCar.carFinal.get(i).getSubtotal();
            total.setText("$ "+totals.toString());
            g = settings.shoppingCar.carFinal.get(i).productCars.size();
                cant.setText(" "+g.toString()+" ");
            name.setText(settings.shoppingCar.carFinal.get(i).getName());
            sub.setText("$ "+settings.shoppingCar.carFinal.get(i).getSubtotal().toString());
            duration.setText(settings.shoppingCar.carFinal.get(i).getDuration());
            re.setText(settings.shoppingCar.carFinal.get(i).getPrice());
            distance.setText(settings.shoppingCar.carFinal.get(i).getDistance());

            final int finalI = i;
            cash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    meth.setText("  EFECTIVO");
                    if(view ==1){
                        view =0;
                        AnimationSet set = new AnimationSet(true);
                        Animation animation;
                        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                                0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
                                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
                        animation.setDuration(1000);
                        set.addAnimation(animation);
                        LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);
                        show.setTranslationY(4000);
                        show.startAnimation(animation);
                        overridePendingTransition(R.anim.izquierdasale, R.anim.izquierdaentra);
                        settings.temporalCar.temporalCars.set(finalI, new temporalCar(
                                settings.temporalCar.temporalCars.get(finalI).getIdStablish(),
                                settings.temporalCar.temporalCars.get(finalI).getIdClick(),
                                settings.temporalCar.temporalCars.get(finalI).getCoupon(),
                                "EFECTIVO",settings.temporalCar.temporalCars.get(finalI).getSubtotal(),
                                settings.temporalCar.temporalCars.get(finalI).getDomiPrice())
                        );
                    }
                }
            });

            datapho.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    meth.setText("  DATÁFONO");
                    if(view ==1){
                        view =0;
                        AnimationSet set = new AnimationSet(true);
                        Animation animation;
                        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                                0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
                                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
                        animation.setDuration(1000);
                        set.addAnimation(animation);
                        LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);
                        show.setTranslationY(4000);
                        show.startAnimation(animation);
                        overridePendingTransition(R.anim.izquierdasale, R.anim.izquierdaentra);
                        settings.temporalCar.temporalCars.set(finalI, new temporalCar(
                                settings.temporalCar.temporalCars.get(finalI).getIdStablish(),
                                settings.temporalCar.temporalCars.get(finalI).getIdClick(),
                                settings.temporalCar.temporalCars.get(finalI).getCoupon(),
                                "DATÁFONO",settings.temporalCar.temporalCars.get(finalI).getSubtotal(),
                                settings.temporalCar.temporalCars.get(finalI).getDomiPrice())
                        );
                    }
                }

            });

            onLine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    meth.setText("  OnLine");
                    if(view ==1){
                        view =0;
                        AnimationSet set = new AnimationSet(true);
                        Animation animation;
                        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                                0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
                                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
                        animation.setDuration(1000);
                        set.addAnimation(animation);
                        LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);
                        show.setTranslationY(4000);
                        show.startAnimation(animation);
                        overridePendingTransition(R.anim.izquierdasale, R.anim.izquierdaentra);
                        settings.temporalCar.temporalCars.set(finalI, new temporalCar(
                                settings.temporalCar.temporalCars.get(finalI).getIdStablish(),
                                settings.temporalCar.temporalCars.get(finalI).getIdClick(),
                                settings.temporalCar.temporalCars.get(finalI).getCoupon(),
                                "OnLine",settings.temporalCar.temporalCars.get(finalI).getSubtotal(),
                                settings.temporalCar.temporalCars.get(finalI).getDomiPrice())
                        );
                    }
                }

            });

            me.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        methodPay (settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getId_Company());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    AnimationSet set = new AnimationSet(true);
                    Animation animation;
                    animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                            1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    animation.setDuration(300);
                    set.addAnimation(animation);
                    LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);
                    show.setTranslationY(0);
                    show.startAnimation(animation);
                    view =1;
                    overridePendingTransition(R.anim.izquierdaentra, R.anim.izquierdasale);
                }
            });

            Picasso.with(car.this)
                    .load("http://godomicilios.co/admin/documentosVarios/"+settings.shoppingCar.carFinal.get(i).getImg())
                    .into(imageOne, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            //do smth when picture is loaded successfully
                        }

                        @Override
                        public void onError() {
                            //do smth when there is picture loading error
                        }
                    });

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Integer h =totals-settings.shoppingCar.carFinal.get(image.getId()).getSubtotal();
                    total.setText("$" + h.toString());
                    li.removeViewAt(image.getId());
                    settings.shoppingCar.carFinal.remove(image.getId());
                    numberCar.setText(settings.user.getCarCant());
                }
            });

            switch (settings.shoppingCar.carFinal.get(i).getIdColor()) {
                case 1:
                    up.setBackgroundColor(getResources().getColor(R.color.redGo));
                    cant.setBackgroundColor(getResources().getColor(R.color.redGoSelect));
                    color.setBackgroundColor(getResources().getColor(R.color.redGoSelect));
                    break;
                case 2:
                    up.setBackgroundColor(getResources().getColor(R.color.blacBeer));
                    cant.setBackgroundColor(getResources().getColor(R.color.blacBeerSelec));
                    color.setBackgroundColor(getResources().getColor(R.color.blacBeerSelec));
                    break;
                case 3:
                    up.setBackgroundColor(getResources().getColor(R.color.blueFarmacy));
                    cant.setBackgroundColor(getResources().getColor(R.color.blueFarmacySelect));
                    color.setBackgroundColor(getResources().getColor(R.color.blueFarmacySelect));
                    break;
                case 4:
                    up.setBackgroundColor(getResources().getColor(R.color.bluePet));
                    cant.setBackgroundColor(getResources().getColor(R.color.bluePetSelect));
                    color.setBackgroundColor(getResources().getColor(R.color.bluePetSelect));
                    break;
                case 5:
                    up.setBackgroundColor(getResources().getColor(R.color.yellowmarket));
                    cant.setBackgroundColor(getResources().getColor(R.color.yellowmarketSelect));
                    color.setBackgroundColor(getResources().getColor(R.color.yellowmarketSelect));
                    break;
            }


            /*cant.setText(" "+settings.shoppingCar.carFinal.get(i).getStablishmentCars().get().get(i).getCant().toString()+" ");


            Integer subs=settings.shoppingCar.shoppingCars.get(i).getSubtotal() ;
            sub.setText("$"+subs.toString());
            re.setText("$"+settings.shoppingCar.shoppingCars.get(i).getStablishment().price);
            }
            */
             li.addView(child);
        }

      /*  for(int i =0; i< settings.shoppingCar.shoppingCars.size();i++){
            if(settings.shoppingCar.shoppingCars == null){

            }
            else {
            //paint(i);
        }}*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(settings.user.getaBoolean()==false){
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_stablishm_drawer);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        settings.couponActivate.setStatus(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.head, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.porfile) {
            Intent go = new Intent(car.this,porfile.class);
            startActivity(go);
            settings.couponActivate.setStatus(0);

        } else if (id == R.id.referred) {
            Intent go = new Intent(car.this,refer.class);
            startActivity(go);
            settings.couponActivate.setStatus(0);

        } else if (id == R.id.coupons) {
            Intent go = new Intent(car.this,coupons.class);
            startActivity(go);
            settings.couponActivate.setStatus(0);

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
            Intent go = new Intent(car.this,payM.class);
            startActivity(go);
            settings.couponActivate.setStatus(0);


        } else if (id == R.id.car) {

            Intent go = new Intent(car.this, car.class);
            startActivity(go);
            settings.couponActivate.setStatus(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String addTo (Integer subtotal, Integer domi){

        tota = tota + subtotal + domi;
        return  tota.toString();
    }
    public void paint (Integer i){/*
        Integer put;
        Integer One;
        put = settings.shoppingCar.shoppingCars.get(settings.shoppingCar.getNum()-1).getStablishment().getId_Company();
        One = settings.shoppingCar.shoppingCars.get(i).getStablishment().getId_Company();

        for(int hh=-1; hh<settings.shoppingCar.mod.size();hh++){
            Integer hhNew;
            if(hh<0){
                hhNew=hh+1;
            }
            else{
                hhNew=hh;
            }


            if(settings.shoppingCar.mod.size()>0 && settings.shoppingCar.mod.get(i).equals(settings.shoppingCar.shoppingCars.get(hhNew).getStablishment().getId_Company())){
                Integer num, numTwo;
                num = settings.shoppingCar.shoppingCars.get(settings.shoppingCar.getNum()-1).getCant()+ settings.shoppingCar.shoppingCars.get(i).getCant();
                numTwo =settings.shoppingCar.shoppingCars.get(settings.shoppingCar.getNum()-1).getSubtotal()+ settings.shoppingCar.shoppingCars.get(i).getSubtotal() ;
                settings.shoppingCar.shoppingCars.set(settings.shoppingCar.getNum()-1, new shoppingCar(
                        settings.shoppingCar.shoppingCars.get(i).getStablishment(),
                        numTwo,
                        num));
            }
            else{
                settings.shoppingCar.mod.add(new Integer(One));
                addFinal(settings.shoppingCar.getNum()-1);
                hh = hh+1;
            }
        }*/
    }
    public void addFinal( int i){

                    View child = View.inflate(car.this, R.layout.product_in_car, null);
                    LinearLayout up = (LinearLayout) child.findViewById(R.id.up);
                    TextView cant = (TextView) child.findViewById(R.id.cant);
                    LinearLayout shows = (LinearLayout) findViewById(R.id.shows);
                    ImageView imageOne = (ImageView) child.findViewById(R.id.imageOne);
                    TextView name = (TextView) child.findViewById(R.id.name);
                    TextView duration = (TextView) child.findViewById(R.id.duration);
                    TextView distance = (TextView) child.findViewById(R.id.distance);
                    TextView sub = (TextView) child.findViewById(R.id.sub);
                    TextView re = (TextView) child.findViewById(R.id.re);
                    final ImageView image = (ImageView) child.findViewById(R.id.image);
                    LinearLayout color= (LinearLayout) child.findViewById(R.id.color);

                    /*total.setText("$ " + addTo(settings.shoppingCar.shoppingCars.get(i).getSubtotal(),
                            Integer.parseInt(settings.shoppingCar.shoppingCars.get(i).getStablishment().price)));*/
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
        settings.couponActivate.setStatus(0);
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, Splash.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        settings.couponActivate.setStatus(0);
    }
    public void methodPay (Integer in) throws Exception{

        String url = "https://godomicilios.co/webService/servicios.php?svice=METODOS_PAGO&metodo=json&empresaId="+
                in;


        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(car.this)));

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{
                            if(response.length()>0){
                                settings.methodPay.methodPays= new ArrayList<>();

                                for(int i =0;i<response.length();i++){
                                    final JSONObject method = (JSONObject) response.getJSONObject(i);

                                    settings.methodPay.methodPays.add( new methodPay(
                                            method.getInt("metodo_pago_id"),
                                            method.getString("detalle"),
                                            method.getInt("estado"),
                                            method.getString("img")
                                    ));

                                        if(settings.methodPay.methodPays.get(i).getStatus()==1){
                                            switch (settings.methodPay.methodPays.get(i).getName()){
                                                case ("Efectivo"):
                                                    Picasso.with(car.this)
                                                            .load("http://godomicilios.co/"+settings.methodPay.methodPays.get(i).getImage())
                                                            .into(cash, new com.squareup.picasso.Callback() {
                                                                @Override
                                                                public void onSuccess() {
                                                                    //do smth when picture is loaded successfully
                                                                }

                                                                @Override
                                                                public void onError() {
                                                                    //do smth when there is picture loading error
                                                                }
                                                            });
                                                    break;
                                                case ("Datafono"):
                                                    Picasso.with(car.this)
                                                            .load("http://godomicilios.co/"+settings.methodPay.methodPays.get(i).getImage())
                                                            .into(datapho, new com.squareup.picasso.Callback() {
                                                                @Override
                                                                public void onSuccess() {
                                                                    //do smth when picture is loaded successfully
                                                                }

                                                                @Override
                                                                public void onError() {
                                                                    //do smth when there is picture loading error
                                                                }
                                                            });
                                                    break;
                                                case ("Pago online"):
                                                    Picasso.with(car.this)
                                                            .load("http://godomicilios.co/"+settings.methodPay.methodPays.get(i).getImage())
                                                            .into(onLine, new com.squareup.picasso.Callback() {
                                                                @Override
                                                                public void onSuccess() {
                                                                    //do smth when picture is loaded successfully
                                                                }

                                                                @Override
                                                                public void onError() {
                                                                    //do smth when there is picture loading error
                                                                }
                                                            });
                                                    break;
                                            }
                                        }
                                        else{

                                            switch (settings.methodPay.methodPays.get(i).getName()){
                                                case ("Efectivo"):
                                                    cash.setEnabled(false);
                                                    break;
                                                case ("Datafono"):
                                                    datapho.setEnabled(false);
                                                    break;
                                                case ("Pago online"):
                                                    onLine.setEnabled(false);
                                                    break;
                                            }
                                        }
                                }
                            }
                            else {
                                settings.methodPay.methodPays=null;
                            }

                        }

                        catch (Exception e){

                            settings.methodPay.methodPays=null;
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        queue.add(jsonArrayRequest);


    }


    public void sendCar (String url) throws Exception{

        url = url.replace(" ", "%20");
        url = url.replace("#", "%23");
        url = url.replaceAll("\"","%22");
        url = url.replace("$%20","");
        url = url.replace("null","");
        url = url.replace("\\","");
        url = url.replace("},]","}]");

        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(car.this)));

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{

                            for(int m=0;m<response.length();m++){

                            }
                        }

                        catch (Exception e){

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        queue.add(jsonArrayRequest);


    }

            }



