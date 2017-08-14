package godomicilios.mdcc.godomiciliosc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.developers.smartytoast.SmartyToast;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import godomicilios.mdcc.godomiciliosc.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.godomiciliosc.settings.addition;
import godomicilios.mdcc.godomiciliosc.settings.drink;
import godomicilios.mdcc.godomiciliosc.settings.ingredients;
import godomicilios.mdcc.godomiciliosc.settings.methodPay;
import godomicilios.mdcc.godomiciliosc.settings.optionalIngredients;
import godomicilios.mdcc.godomiciliosc.settings.product;
import godomicilios.mdcc.godomiciliosc.settings.rank;
import godomicilios.mdcc.godomiciliosc.settings.settings;
import godomicilios.mdcc.godomiciliosc.settings.user;

public class stablishm extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Context context;

    ImageView image, ones, twos, threes, fours, fives;
    TextView name, address, price, three, priceMinimum;
    ArrayList <Integer >in;
    LinearLayout one, two, com, lic, far, mas, mer;
    Integer count=0, ing=0, drin=0, addit=0;
    Integer comp =0, proId=0, edii=0 ;
    TextView numberCar;
    View include;
    DecimalFormat formatea = new DecimalFormat("###.###");
    ArrayList<ingredients> ingr;
    ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stablishm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        image = (ImageView) findViewById(R.id.image);
        name = (TextView) findViewById(R.id.name);
        address = (TextView) findViewById(R.id.address);
        price = (TextView) findViewById(R.id.price);
        one = (LinearLayout) findViewById(R.id.one);
        two = (LinearLayout) findViewById(R.id.two);
        lic = (LinearLayout) findViewById(R.id.lic);
        far = (LinearLayout) findViewById(R.id.far);
        mas = (LinearLayout) findViewById(R.id.mas);
        mer = (LinearLayout) findViewById(R.id.mer);
        com = (LinearLayout) findViewById(R.id.com);
        ones= (ImageView) findViewById(R.id.ones);
        twos= (ImageView) findViewById(R.id.twos);
        threes= (ImageView) findViewById(R.id.threess);
        settings.user.getProgressDialog().dismiss();
        fours= (ImageView) findViewById(R.id.fours);
        fives= (ImageView) findViewById(R.id.fives);
        priceMinimum = (TextView) findViewById(R.id.priceMinimum);
        numberCar = (TextView) findViewById(R.id.numberCar);
        Integer car= settings.shoppingCar.carFinal.size();
        numberCar.setText(settings.user.getCarCant());
        user.analytics = GoogleAnalytics.getInstance(this);
        user.analytics.setLocalDispatchPeriod(1800);
        user.tracker = user.analytics.newTracker("UA-101326412-1");
        user.tracker.enableExceptionReporting(true);
        user.tracker.enableAdvertisingIdCollection(true);
        user.tracker.enableAutoActivityTracking(true);
        include = findViewById(R.id.include);

        settings.ingredients.ingredientses = null;
        settings.addition.additions = null;
        settings.drink.drinks = null;

        //setStars
        stars(settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getStars(),
                ones,
                twos,
                threes,
                fours,
                fives);

        //settings.handleSSLHandshake();
        switch (settings.stablishment.getId()){
            case 1:
                com.setVisibility(View.VISIBLE);
                break;
            case 2:
                two.setBackgroundColor(ContextCompat.getColor(context,R.color.blacBeer));
                image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logonegro));
                lic.setVisibility(View.VISIBLE);
                break;
            case 3:
                two.setBackgroundColor(ContextCompat.getColor(context,R.color.blueFarmacy));
                image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logoverde));
                far.setVisibility(View.VISIBLE);
                break;
            case 4:
                two.setBackgroundColor(ContextCompat.getColor(context,R.color.bluePet));
                image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logoazul));
                mas.setVisibility(View.VISIBLE);
                break;
            case 5:
                two.setBackgroundColor(ContextCompat.getColor(context,R.color.yellowmarket));
                image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logoamarillo));
                mer.setVisibility(View.VISIBLE);
                break;

        }

        price.setText("$" + formatea.format(Integer.parseInt(settings.stablishment.stablishments.get(settings.stablishment.getNumber()).price)));
        priceMinimum.setText("$" + formatea.format(Integer.parseInt(settings.stablishment.stablishments.get(settings.stablishment.getNumber()).minimum)));

        address.setText(settings.stablishment.stablishments.get(settings.stablishment.getNumber()).address);

        name.setText(settings.stablishment.stablishments.get(settings.stablishment.getNumber()).name);


        Picasso.with(stablishm.this)
                .load("http://godomicilios.co/admin/documentosVarios/"+settings.stablishment.stablishments.get(settings.stablishment.getNumber()).image)
                .into(image, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        //do smth when picture is loaded successfully
                    }

                    @Override
                    public void onError() {
                        //do smth when there is picture loading error
                    }
                });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

            putView();
        //dialog.dismiss();

        if(!settings.user.getaBoolean()){
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
                    Intent go = new Intent(stablishm.this, car.class);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.porfile) {
            Intent go = new Intent(stablishm.this,porfile.class);
            startActivity(go);

        } else if (id == R.id.referred) {
            Intent go = new Intent(stablishm.this,refer.class);
            startActivity(go);

        } else if (id == R.id.coupons) {
            Intent go = new Intent(stablishm.this,coupons.class);
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
            Intent go = new Intent(stablishm.this,payM.class);
            startActivity(go);


        } else if (id == R.id.car) {

            Intent go = new Intent(stablishm.this, car.class);
            startActivity(go);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public Integer count (int i, int len){
        Integer count=0;
        if(settings.product.products.size()==len){

            count=settings.product.products.get(i).cant;
            return count;
        }
        else{
            return count;
        }
    }

    public String pictureValidator (String url){
        if(url.length()<3){
            return "a";
        }
        else{
            return url;
        }
    }

    public Integer cantOne (Integer num){
        if(num<1){
            return 1;
        }
        else{
            return num;
        }
    }
    public void putView (){
        in = new ArrayList<>();

        for(int k = 0; k< settings.rank.ranks.size(); k++){

            in.add(0);

            final LinearLayout linear = (LinearLayout) findViewById(R.id.li);
            final View child = View.inflate(stablishm.this, R.layout.rank, null);
            final TextView nameshowd = (TextView) child.findViewById(R.id.name);
            final LinearLayout lll = (LinearLayout) child.findViewById(R.id.lii);
            lll.setVisibility(View.GONE);
            lll.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
            final LinearLayout click = (LinearLayout) child.findViewById(R.id.click);
            final ScrollView scroll = (ScrollView) child.findViewById(R.id.scroll);
            final ImageView image = (ImageView) child.findViewById(R.id.image);

            final Integer textcolor = nameshowd.getCurrentTextColor();

            animationsFalse(lll);
            nameshowd.setText(settings.rank.ranks.get(k).getName());
            switch (settings.stablishment.getId()){
                case 1:
                    animationsFalse(lll);
                    break;
                case 2:
                    image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flecha_d_rlic));
                    animationsFalse(lll);
                    break;
                case 3:
                    image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flecha_d_rfarm));
                    animationsFalse(lll);
                    break;
                case 4:
                    image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flecha_d_rmas));
                    animationsFalse(lll);
                    break;
                case 5:
                    image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flecha_d_rmer));
                    animationsFalse(lll);
                    break;

            }
                linear.addView(child);

            if(k%2==0){
                click.setBackgroundColor(ContextCompat.getColor(context,R.color.gray));

                }
            else{
                click.setBackgroundColor(Color.WHITE);
                /*settings.product.products.set(k,new product(settings.product.products.get(k).id,
                        settings.product.products.get(k).idCompany,
                        settings.product.products.get(k).idRank,
                        settings.product.products.get(k).name,
                        settings.product.products.get(k).description,
                        settings.product.products.get(k).price,
                        settings.product.products.get(k).picture,
                        settings.product.products.get(k).visible,
                        0, Color.WHITE,settings.product.products.get(k).cant,
                        settings.product.products.get(k).drinkType,
                        settings.product.products.get(k).drinkEdit));*/
            }


            /*settings.product.products.set(k,new product(settings.product.products.get(k).id,
                    settings.product.products.get(k).idCompany,
                    settings.product.products.get(k).idRank,
                    settings.product.products.get(k).name,
                    settings.product.products.get(k).description,
                    settings.product.products.get(k).price,
                    settings.product.products.get(k).picture,
                    settings.product.products.get(k).visible,
                    0,
                    settings.product.products.get(k).col,
                    settings.product.products.get(k).cant,
                    settings.product.products.get(k).drinkType,
                    settings.product.products.get(k).drinkEdit)
            );*/



            for(int l =0;l<settings.product.products.size();l++){
                click.setId(k);

                if(Objects.equals(settings.product.products.get(l).idRank, settings.rank.ranks.get(k).getId())){


                    final View childs = View.inflate(stablishm.this, R.layout.products, null);
                    final ImageView im = (ImageView) childs.findViewById(R.id.picture);
                    final ImageView img = (ImageView) childs.findViewById(R.id.imageView12);
                    TextView nameshow = (TextView) childs.findViewById(R.id.name);
                    TextView price = (TextView) childs.findViewById(R.id.price);
                    childs.setId(l);

                    settings.product.products.set(l,new product(settings.product.products.get(l).id,
                            settings.product.products.get(l).idCompany,
                            settings.product.products.get(l).idRank,
                            settings.product.products.get(l).name,
                            settings.product.products.get(l).description,
                            settings.product.products.get(l).price,
                            settings.product.products.get(l).getPicture(),
                            settings.product.products.get(l).visible,
                            0,
                            settings.product.products.get(l).col,
                            settings.product.products.get(l).cant,
                            settings.product.products.get(l).drinkType,
                            settings.product.products.get(l).drinkEdit)
                    );


                    switch (settings.stablishment.getId()){
                        case 2:
                            im.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logonegro));
                            img.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flecha_d_rlic));
                            break;
                        case 3:
                            im.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logoverde));
                            img.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flecha_d_rfarm));
                            break;
                        case 4:
                            im.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logoazul));
                            img.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flecha_d_rmas));
                            break;
                        case 5:
                            im.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logoamarillo));
                            img.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flecha_d_rmer));
                            break;

                    }

                    childs.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            include.setEnabled(false);
                            String mensajee = "un momento por favor...";

                            SmartyToast.makeText(getApplicationContext(), mensajee,SmartyToast.LENGTH_LONG,SmartyToast.DONE);

                            settings.subtotal.setCant(cantOne(settings.product.products.get(childs.getId()).cant));
                            settings.subtotal.setDescription(settings.product.products.get(childs.getId()).description);
                            settings.subtotal.setName(settings.product.products.get(childs.getId()).name);
                            settings.subtotal.setPicture(settings.product.products.get(childs.getId()).getPicture());
                            settings.subtotal.setPrice(settings.product.products.get(childs.getId()).price);
                            settings.subtotal.setProductId(settings.product.products.get(childs.getId()).id);
                            settings.subtotal.setDrinkType(settings.product.products.get(childs.getId()).drinkType);
                            settings.subtotal.setDrinkEdit(settings.product.products.get(childs.getId()).drinkEdit);

                            comp =settings.product.products.get(childs.getId()).drinkType;
                            proId=settings.product.products.get(childs.getId()).id;
                            edii=settings.product.products.get(childs.getId()).drinkEdit ;
                            query(settings.product.products.get(childs.getId()).drinkType,
                                    settings.product.products.get(childs.getId()).id,
                                    settings.product.products.get(childs.getId()).drinkEdit);

                        }
                    });

                    settings.product.products.set(l,new product(settings.product.products.get(l).id,
                            settings.product.products.get(l).idCompany,
                            settings.product.products.get(l).idRank,
                            settings.product.products.get(l).name,
                            settings.product.products.get(l).description,
                            settings.product.products.get(l).price,
                            settings.product.products.get(l).getPicture(),
                            settings.product.products.get(l).visible,
                            0,
                            settings.product.products.get(l).col,
                            settings.product.products.get(k).cant,
                            settings.product.products.get(l).drinkType,
                            settings.product.products.get(l).drinkEdit));
                    lll.addView(childs);
                    final int finalI = settings.rank.ranks.size();
                    click.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            for (int j = 0; j< settings.rank.ranks.size(); j++){

                                if(j == click.getId() ){

                                    if (settings.rank.ranks.get(click.getId()).getSelect().equals(1)){

//                                        lll.setVisibility(View.GONE);
                                        settings.rank.ranks.set(click.getId(), new rank(
                                                settings.rank.ranks.get(click.getId()).getId(),
                                                settings.rank.ranks.get(click.getId()).getIdCompany(),
                                                settings.rank.ranks.get(click.getId()).getName(),
                                                settings.rank.ranks.get(click.getId()).getStatus(),
                                                0));
                                        //nameshowd.setTextColor(getResources().getColor(R.color.tertary));
                                        switch (settings.stablishment.getId()){
                                            case 1:
                                                image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flechadr));
                                                click.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
                                                nameshowd.setTextColor(ContextCompat.getColor(context,R.color.tertary));
                                                lll.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
                                                if(settings.product.products.size()>1){
                                                    animationsFalse(lll);
                                                }
                                                else{
                                                    animationsTrue(lll);
                                                    if(lll.getVisibility()== View.VISIBLE){
                                                        lll.setVisibility(View.GONE);
                                                    }
                                                }
                                                if(click.getId()%2==0){

                                                    click.setBackgroundColor(
                                                            getResources().getColor(R.color.gray));

//                                            childs.setBackgroundColor(getResources().getColor(R.color.gray));
                                                }
                                                else{
                                                    click.setBackgroundColor(
                                                            Color.WHITE
                                                    );
//                                            childs.setBackgroundColor(Color.WHITE);
                                                }
                                                break;
                                            case 2:
                                                image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flecha_d_rlic));
                                                click.setBackgroundColor(ContextCompat.getColor(context,R.color.blacBeerSelec));
                                                nameshowd.setTextColor(ContextCompat.getColor(context,R.color.tertary));
                                                lll.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
                                                animationsFalse(lll);
                                                if(click.getId()%2==0){

                                                    click.setBackgroundColor(
                                                            ContextCompat.getColor(context,R.color.gray));

//                                            childs.setBackgroundColor(getResources().getColor(R.color.gray));
                                                }
                                                else{
                                                    click.setBackgroundColor(
                                                            Color.WHITE
                                                    );
//                                            childs.setBackgroundColor(Color.WHITE);
                                                }
                                                break;
                                            case 3:
                                                image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flecha_d_rfarm));
                                                click.setBackgroundColor(ContextCompat.getColor(context,R.color.blueFarmacySelect));
                                                nameshowd.setTextColor(ContextCompat.getColor(context,R.color.tertary));
                                                lll.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
                                                animationsFalse(lll);
                                                if(click.getId()%2==0){

                                                    click.setBackgroundColor(
                                                            getResources().getColor(R.color.gray));

//                                            childs.setBackgroundColor(getResources().getColor(R.color.gray));
                                                }
                                                else{
                                                    click.setBackgroundColor(
                                                            Color.WHITE
                                                    );
//                                            childs.setBackgroundColor(Color.WHITE);
                                                }

                                                break;
                                            case 4:
                                                image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flecha_d_rmas));
                                                click.setBackgroundColor(ContextCompat.getColor(context,R.color.bluePetSelect));
                                                nameshowd.setTextColor(ContextCompat.getColor(context,R.color.tertary));
                                                lll.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
                                                animationsFalse(lll);
                                                if(click.getId()%2==0){

                                                    click.setBackgroundColor(
                                                            getResources().getColor(R.color.gray));

//                                            childs.setBackgroundColor(getResources().getColor(R.color.gray));
                                                }
                                                else{
                                                    click.setBackgroundColor(
                                                            Color.WHITE
                                                    );
//                                            childs.setBackgroundColor(Color.WHITE);
                                                }

                                                break;
                                            case 5:
                                                image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flecha_d_rmer));
                                                click.setBackgroundColor(ContextCompat.getColor(context,R.color.yellowmarketSelect));
                                                nameshowd.setTextColor(ContextCompat.getColor(context,R.color.tertary));
                                                lll.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
                                                animationsFalse(lll);
                                                if(click.getId()%2==0){

                                                    click.setBackgroundColor(
                                                            getResources().getColor(R.color.gray));

//                                            childs.setBackgroundColor(getResources().getColor(R.color.gray));
                                                }
                                                else{
                                                    click.setBackgroundColor(
                                                            Color.WHITE
                                                    );
//                                            childs.setBackgroundColor(Color.WHITE);
                                                }
                                                break;

                                        }
                                        break;
                                    }
                                    else {
                                        //
                                        //lll.setBackgroundColor(getResources().getColor(R.color.greenCar));
                                        settings.rank.ranks.set(click.getId(), new rank(
                                                settings.rank.ranks.get(click.getId()).getId(),
                                                settings.rank.ranks.get(click.getId()).getIdCompany(),
                                                settings.rank.ranks.get(click.getId()).getName(),
                                                settings.rank.ranks.get(click.getId()).getStatus(),
                                                1));

                                        switch (settings.stablishment.getId()){
                                            case 1:
                                                image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flechaarb));
                                                click.setBackgroundColor(ContextCompat.getColor(context,R.color.redGoSelect));
                                                nameshowd.setTextColor(Color.WHITE);
                                                lll.setVisibility(View.VISIBLE);
                                                break;
                                            case 2:
                                                image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flechaarb));
                                                click.setBackgroundColor(ContextCompat.getColor(context,R.color.blacBeerSelec));
                                                nameshowd.setTextColor(Color.WHITE);
                                                lll.setVisibility(View.VISIBLE);
                                                break;
                                            case 3:
                                                image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flechaarb));
                                                click.setBackgroundColor(ContextCompat.getColor(context,R.color.blueFarmacySelect));
                                                nameshowd.setTextColor(Color.WHITE);
                                                lll.setVisibility(View.VISIBLE);
                                                break;
                                            case 4:
                                                image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flechaarb));
                                                click.setBackgroundColor(ContextCompat.getColor(context,R.color.bluePetSelect));
                                                nameshowd.setTextColor(Color.WHITE);
                                                lll.setVisibility(View.VISIBLE);
                                                break;
                                            case 5:
                                                image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flechaarb));
                                                click.setBackgroundColor(ContextCompat.getColor(context,R.color.yellowmarketSelect));
                                                nameshowd.setTextColor(Color.WHITE);
                                                lll.setVisibility(View.VISIBLE);
                                                break;

                                        }

                                        //in.set(finalI,1);
                                        //  settings.product.products.get(j).linear.setBackgroundColor(getResources().getColor(R.color.redGo));

                                    }

                                }
                                else{
                                       //settings.product.products.get(j).linear.setBackgroundColor(getResources().getColor(R.color.gray));
                                        //in.set(j,0);


                                    //lll.setVisibility(View.GONE);
                                    in.set(click.getId(),0);

                                    if(click.getId()%2==0){

                                        click.setBackgroundColor(
                                                ContextCompat.getColor(context,R.color.gray));

//                                            childs.setBackgroundColor(getResources().getColor(R.color.gray));
                                    }
                                    else{
                                        click.setBackgroundColor(
                                                Color.WHITE
                                        );
//                                            childs.setBackgroundColor(Color.WHITE);
                                    }
                                    nameshowd.setTextColor(ContextCompat.getColor(context,R.color.tertary));
                                    switch (settings.stablishment.getId()){
                                        case 1:
                                            image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flechaarb));

                                            click.setBackgroundColor(ContextCompat.getColor(context,R.color.redGoSelect));
                                            nameshowd.setTextColor(Color.WHITE);
                                            animationsTrue(lll);
                                            break;
                                        case 2:
                                            image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flechaarb));
                                            click.setBackgroundColor(ContextCompat.getColor(context,R.color.blacBeerSelec));
                                            nameshowd.setTextColor(Color.WHITE);
                                            animationsTrue(lll);
                                            break;
                                        case 3:
                                            image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flechaarb));
                                            click.setBackgroundColor(ContextCompat.getColor(context,R.color.blueFarmacySelect));
                                            nameshowd.setTextColor(Color.WHITE);
                                            animationsTrue(lll);
                                            break;
                                        case 4:
                                            image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flechaarb));
                                            click.setBackgroundColor(ContextCompat.getColor(context,R.color.bluePetSelect));
                                            nameshowd.setTextColor(Color.WHITE);
                                            animationsTrue(lll);
                                            break;
                                        case 5:
                                            image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.flechaarb));
                                            click.setBackgroundColor(ContextCompat.getColor(context,R.color.yellowmarketSelect));
                                            nameshowd.setTextColor(Color.WHITE);
                                            animationsTrue(lll);
                                            break;

                                    }
                                    /*else{

                                        switch (settings.stablishment.getId()){

                                            case 1:
                                                image.setImageDrawable(getResources().getDrawable(R.drawable.flechadr));
                                                click.setBackgroundColor(getResources().getColor(R.color.gray));
                                                break;
                                            case 2:
                                                image.setImageDrawable(getResources().getDrawable(R.drawable.flecha_d_rlic));
                                                click.setBackgroundColor(getResources().getColor(R.color.blacBeerSelec));
                                                break;
                                            case 3:
                                                image.setImageDrawable(getResources().getDrawable(R.drawable.flecha_d_rfarm));
                                                click.setBackgroundColor(getResources().getColor(R.color.blueFarmacySelect));
                                                break;
                                            case 4:
                                                image.setImageDrawable(getResources().getDrawable(R.drawable.flecha_d_rmas));
                                                click.setBackgroundColor(getResources().getColor(R.color.bluePetSelect));
                                                break;
                                            case 5:
                                                image.setImageDrawable(getResources().getDrawable(R.drawable.flecha_d_rmer));
                                                click.setBackgroundColor(getResources().getColor(R.color.yellowmarketSelect));
                                                break;

                                        }
                                    }*/

                                }

                            }

                        }
                    });

                    nameshow.setText(settings.product.products.get(l).name);
                    price.setText("$" + formatea.format(settings.product.products.get(l).price));

                    if(settings.product.products.get(l).getPicture().equals("a")){
                        switch (settings.stablishment.getId()){
                            case 1:
                                im.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logorojo));
                                break;
                            case 2:
                                im.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logonegro));
                                break;
                            case 3:
                                im.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logoverde));
                                break;
                            case 4:
                                im.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logoazul));
                                break;
                            case 5:
                                im.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logoamarillo));
                                break;

                        }
                    }
                    else {

                        Picasso.with(stablishm.this)
                                .load("http://godomicilios.co/admin/img/productos/"+settings.product.products.get(l).getPicture())
                                .into(im, new com.squareup.picasso.Callback() {
                                    @Override
                                    public void onSuccess() {
                                        //do smth when picture is loaded successfully
                                    }

                                    @Override
                                    public void onError() {
                                        //do smth when there is picture loading error
                                    }
                                });
                    }
/*
                    if(l%2==0){

                        lll.setBackgroundColor(
                                getResources().getColor(R.color.gray));

                        childs.setBackgroundColor(getResources().getColor(R.color.gray));
                    }
                    else{
                        lll.setBackgroundColor(
                                Color.WHITE
                        );
                        childs.setBackgroundColor(Color.WHITE);
                    }*/
                }

            }


        }


    }
    public void animationsTrue (LinearLayout lll){
        TranslateAnimation animate = new TranslateAnimation(lll.getHeight(),0,lll.getWidth(),0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        lll.startAnimation(animate);
        lll.setVisibility(View.VISIBLE);
    }
    public void animationsFalse (LinearLayout view){
        TranslateAnimation animate = new TranslateAnimation(0,view.getWidth(),0,0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
        view.setVisibility(View.GONE);
    }


    public void firstDrink (final String url, final Integer drinkE, final Integer edi, final Integer productId) throws Exception{

        if (drinkE!=0) {


            final RequestQueue queue = Volley.newRequestQueue(this, new HurlStack(
                    null, CustomSSLSocketFactory.getSSLSocketFactory(stablishm.this)));

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                        @Override
                        public void onResponse(final JSONArray response) {
                            try {

                                if (response.length() > 0) {
                                    for (int i = 0; i < response.length(); i++) {
                                        settings.drink.drinks = new ArrayList<>();

                                        final JSONObject drink = response.getJSONObject(i);

                                        settings.drink.drinks.add(new drink(drink.getInt("id_bebida"),
                                                drink.getInt("valor"),
                                                drink.getInt("empresa_id"),
                                                drink.getString("nombre_bebida"),
                                                drink.getString("imagen"),
                                                drink.getString("tamano")));


                                        if (edi > 0) {
                                            secondDrink("https://godomicilios.co/webService/servicios.php?" +
                                                    "svice=BEBIDAS_X_PRECIO&metodo=json&valor_bebida=" +
                                                    drink.getString("valor") + "&empId=" +
                                                    drink.getString("empresa_id"), productId);

                                        } else {
                                                try {
                                                    settings.ingredients.ingredientses= new ArrayList<>();
                                                    ingredients("https://godomicilios.co/webService/servicios.php?svice=INGREDIENTES&metodo=json&proId="+ productId, productId);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }


                                        }
                                    }
                                } else {
                                    try {
                                        settings.ingredients.ingredientses= new ArrayList<>();
                                        ingredients("https://godomicilios.co/webService/servicios.php?svice=INGREDIENTES&metodo=json&proId="+ productId, productId);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (Exception e) {


                                String mensajee = "No hay productos disponibles";

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
                        firstDrink("https://godomicilios.co/webService/servicios.php?svice=BEBIDAS&metodo=json&tipo_bebida="+ drinkE, drinkE, edi , productId);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            );
            queue.add(jsonArrayRequest);
        }
        else {
            try {
                settings.ingredients.ingredientses= new ArrayList<>();
                ingredients("https://godomicilios.co/webService/servicios.php?svice=INGREDIENTES&metodo=json&proId="+ productId, productId);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    public void secondDrink (final String url, final Integer productId) throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(stablishm.this)));

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{

                            settings.drink.drinks= new ArrayList<>();

                            settings.elementsToProducts.elementsToProductses = new ArrayList<>();

                            for(int i =0;i<response.length();i++){
                                final JSONObject drink = response.getJSONObject(i);

                                settings.drink.drinks.add( new drink(drink.getInt("id_bebida"),
                                        drink.getInt("valor"),
                                        drink.getInt("empresa_id"),
                                        drink.getString("nombre_bebida"),
                                        drink.getString("imagen"),
                                        drink.getString("tamano")));
                            }
                            try {
                                settings.ingredients.ingredientses= new ArrayList<>();
                                ingredients("https://godomicilios.co/webService/servicios.php?svice=INGREDIENTES&metodo=json&proId="+ productId, productId);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        catch (Exception e){

                            String mensajee ="No hay productos disponibles";

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
                    secondDrink(url, productId);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        );
        queue.add(jsonArrayRequest);


    }
    public void ingredients (final String url, final  Integer productId) throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(stablishm.this)));

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(JsonObjectRequest.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONObject response) {
                        try{

                            if(response.length()>0){
                                settings.ingredients.ingredientses= new ArrayList<>();
                                JSONArray obligatory = response.getJSONArray("OBLIGATORIO");
                                for (int o=0;o<obligatory.length();o++){
                                    final JSONObject obligator = obligatory.getJSONObject(o);
                                    settings.ingredients.ingredientses.add(new ingredients(
                                            obligator.getInt("id"),obligator.getString("nombre"),obligator.getString("tipo_txt"),1,obligator.getInt("ingrediente_id"),0,"none"
                                    ));
                                }

                                JSONArray normal = response.getJSONArray("NORMAL");

                                for (int n=0;n<normal.length();n++){
                                    final JSONObject norma = normal.getJSONObject(n);
                                    settings.ingredients.ingredientses.add(new ingredients(
                                            norma.getInt("id"),norma.getString("nombre"),norma.getString("tipo_txt"),2,norma.getInt("ingrediente_id"),0,"none"
                                    ));
                                }
                                if(!response.isNull("OPCIONAL")){
                                    JSONObject optional = response.getJSONObject("OPCIONAL");
                                    Iterator<String> iterator;
                                    optional.keys();
                                    ArrayList<JSONObject> objectArray=new ArrayList<>();
                                    int i = 0;
                                    for (iterator= optional.keys(); iterator.hasNext(); i++) {
                                        String s = iterator.next();
                                        JSONObject j =optional.getJSONObject(s);
                                        JSONArray array = j.getJSONArray("opciones");
                                        Integer maxi = j.getInt("cantidad_max");
                                        for(int cou =0;cou<array.length();cou++){
                                            final JSONObject opti = array.getJSONObject(cou);
                                            settings.ingredients.ingredientses.add(new ingredients(
                                                    opti.getInt("id"), opti.getString("nombre"), opti.getString("tipo_txt"),3, opti.getInt("ingrediente_id"),maxi,s
                                            ));
                                        }
                                        objectArray.add(j);
                                    }

                                    Integer drink=settings.ingredients.ingredientses.size();
                                }
                                else {
                                    if(settings.ingredients.ingredientses==null){
                                        settings.ingredients.ingredientses= new ArrayList<>();
                                    }

                                }
                                //organize();
                                try {
                                    settings.addition.additions= new ArrayList<>();
                                    aditions("https://godomicilios.co/webService/servicios.php?svice=ADICIONES&metodo=json&proId="+ productId, productId);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                }

                        }

                        catch (Exception e){


                            String mensajee ="Error vuelve a intentarlo";
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
                    settings.ingredients.ingredientses= new ArrayList<>();
                    ingredients("https://godomicilios.co/webService/servicios.php?svice=INGREDIENTES&metodo=json&proId="+ productId, productId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        );
        queue.add(jsonObjectRequest);


    }
    public void aditions (final String url, final Integer productId) throws Exception{

        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(stablishm.this)));

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{


                            if(response.length()<1){
                                settings.addition.additions= new ArrayList<>();
                            }

                            else{
                                settings.addition.additions= new ArrayList<>();
                                for(int i =0;i<response.length();i++){
                                    final JSONObject addition = response.getJSONObject(i);

                                    settings.addition.additions.add( new addition(
                                            addition.getInt("id"),
                                            addition.getString("nombre_adicion"),
                                            addition.getInt("valor"),
                                            addition.getString("imagen_adicion"),0
                                    ));

                                }
                            }

                            Intent go = new Intent(stablishm.this, productm.class);
                            confirmCant();
                            startActivity(go);
                        }

                        catch (Exception e){

                            String mensajee ="No hay productos disponibles";
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
                    settings.addition.additions= new ArrayList<>();
                    aditions("https://godomicilios.co/webService/servicios.php?svice=ADICIONES&metodo=json&proId="+ productId, productId);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        );
        queue.add(jsonArrayRequest);


    }
    public void query (Integer complement, Integer productId, Integer edi){
        settings.addition.additions= new ArrayList<>();
        settings.ingredients.ingredientses= new ArrayList<>();

        //drinks

        final ProgressDialog dialog = ProgressDialog.show(stablishm.this, "",
                "Loading. Please wait...", true);

        if(settings.stablishment.getId()==1){

            try {
                firstDrink("https://godomicilios.co/webService/servicios.php?svice=BEBIDAS&metodo=json&tipo_bebida="+ complement, complement, edi , productId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            dialog.dismiss();

        }
        else{
            //Intent
            Intent go = new Intent(stablishm.this, productm.class);
            confirmCant();
            startActivity(go);
            dialog.dismiss();
        }
    }

    public void stars (Integer i, ImageView one, ImageView two, ImageView three,
                       ImageView four, ImageView five){
        switch (i){
            case 1:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancamitad));
                break;
            case 2:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                break;
            case 3:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                two.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancamitad));
                break;
            case 4:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                two.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                break;
            case 5:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                two.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                three.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancamitad));
                break;
            case 6:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                two.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                three.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                break;
            case 7:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                two.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                three.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                four.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancamitad));
                break;
            case 8:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                two.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                three.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                four.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                break;
            case 9:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                two.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                three.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                four.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                five.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancamitad));
                break;
            case 10:
                one.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                two.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                three.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                four.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                five.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.medianablancacompleta));
                break;
        }
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

    public void confirmCant (){
        if(settings.ingredients.ingredientses==null){
            settings.ingredients.ingredientses= new ArrayList<>();
        }
         if (settings.addition.additions==null){
            settings.addition.additions= new ArrayList<>();
        }
        if(settings.drink.drinks==null){
            settings.drink.drinks= new ArrayList<>();
        }
    }
    public void organizeCategor(){
         ingr= new ArrayList<>();
         names = new ArrayList<>();
        settings.optionalIngredients.optionalIngredientses = new ArrayList<>();
        for (int i =0;i<settings.ingredients.ingredientses.size();i++){
            String category = settings.ingredients.ingredientses.get(i).categor;
            ArrayList<ingredients> ingre = settings.ingredients.ingredientses;
            if(i==0&&!category.equals("")){
                names.add(category);
                settings.optionalIngredients.optionalIngredientses.add(new optionalIngredients(category, ingr));
            }
            for(int j=0; j<names.size();j++){
                if(!names.get(j).equals(category)&&!category.equals("")){
                    names.add(category);
                    settings.optionalIngredients.optionalIngredientses.add(new optionalIngredients(category, ingr));
                }
            }
        }


    }
    public void organizeIngredients(){
        ArrayList<ingredients> finalIngre=new ArrayList<>();
        for (int i =0;i<settings.ingredients.ingredientses.size();i++){
            ArrayList<ingredients> temporal = settings.ingredients.ingredientses;
            String categor = settings.ingredients.ingredientses.get(i).categor;
            ingredients ingredien= new ingredients(temporal.get(i).id, temporal.get(i).name, temporal.get(i).status, temporal.get(i).type,temporal.get(i).ingId,temporal.get(i).max,temporal.get(i).categor);
            for (int j =0; j<names.size();j++){
                if(categor.equals(names.get(j))){
                    finalIngre.add(ingredien);
                }
            }
        }
    }

}

