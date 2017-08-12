package godomicilios.mdcc.godomiciliosc;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import godomicilios.mdcc.godomiciliosc.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.godomiciliosc.settings.addition;
import godomicilios.mdcc.godomiciliosc.settings.additionCar;
import godomicilios.mdcc.godomiciliosc.settings.allChecks;
import godomicilios.mdcc.godomiciliosc.settings.drinkCar;
import godomicilios.mdcc.godomiciliosc.settings.ingredients;
import godomicilios.mdcc.godomiciliosc.settings.ingredientsCar;
import godomicilios.mdcc.godomiciliosc.settings.productCar;
import godomicilios.mdcc.godomiciliosc.settings.settings;

public class productm extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    ImageView profile_image, secondImg, imageViewDrink, imageViewIngredient, imageViewAddition, imageViewObsrv;
    TextView name, address, price, description, priceProduct, subtotal, back, number,productName,textxvi,priceMinimum;
    LinearLayout  li, imgDrink,imgIngredient, imgAddition;
    Button addProduct;
    int dd=0, aa=0, validatora =0, bb=0, validatorb=0, validatorc=0,cc=0,
    removeCount= settings.subtotal.getCant();
    Integer priceFirst= settings.subtotal.getPrice();
    Integer moreInt=0;
    Integer z=0;
    Integer multi;
    Integer countFinal=0;
    public float init_x;
    TextView numberCar;
    private ViewFlipper vf;
    Integer ingredientSize=settings.ingredients.ingredientses.size();
    Integer additionSize = settings.addition.additions.size();
    Integer drinkSize = settings.drink.drinks.size();
    Integer cantii = settings.subtotal.getCant();
    ArrayList<EditText>ed2 = new ArrayList<>();
    android.support.v7.widget.AppCompatCheckBox[] checksDrink2 = new android.support.v7.widget.AppCompatCheckBox[drinkSize];
    android.support.v7.widget.AppCompatCheckBox[] checkAddition2 = new android.support.v7.widget.AppCompatCheckBox[additionSize];
    android.support.v7.widget.AppCompatCheckBox[] checks2 = new android.support.v7.widget.AppCompatCheckBox[ingredientSize];
    Integer ingreConfirm = 1, additiConfirm=1, drinkConfirm=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        profile_image = (ImageView) findViewById(R.id.profile_image);
        secondImg  = (ImageView) findViewById(R.id.secondImg);
        productName = (TextView) findViewById(R.id.productName);
        priceProduct = (TextView) findViewById(R.id.priceProduct);
        description = (TextView) findViewById(R.id.description);
        imageViewDrink = (ImageView) findViewById(R.id.imageViewDrink);
        imgDrink = (LinearLayout) findViewById(R.id.imgDrink);
        imageViewIngredient = (ImageView) findViewById(R.id.imageViewIngredient);
        imgIngredient = (LinearLayout) findViewById(R.id.imgIngredient);
        imageViewAddition = (ImageView) findViewById(R.id.imageViewAddition);
        imgAddition= (LinearLayout) findViewById(R.id.imgAddition);
        imageViewObsrv = (ImageView) findViewById(R.id.imageViewObsrv);
        vf=(ViewFlipper) findViewById(R.id.viewFlipper);
        vf.setOnTouchListener(new productm.ListenerTouchViewFlipper());
        setImage("http://godomicilios.co/admin/img/productos/"+settings.subtotal.getPicture(), profile_image);
        setImage("http://godomicilios.co/admin/img/productos/"+settings.subtotal.getPicture(), secondImg);
        productName.setText(settings.subtotal.getName());
        description.setText(settings.subtotal.getDescription());
        priceProduct.setText("$" +settings.subtotal.getPrice());
        confirmeFirstImage(settings.stablishment.getId());
        showExists();
        View child = View.inflate(productm.this, R.layout.observation, null);
        vf.addView(child);

        /*secondImg = (ImageView) findViewById(R.id.secondImg);
        productPicture = (ImageView) findViewById(R.id.profile_image);
        name = (TextView) findViewById(R.id.name);
        address = (TextView) findViewById(R.id.address);
        price = (TextView) findViewById(R.id.price);
        back = (TextView) findViewById(R.id.back);
        number = (TextView) findViewById(R.id.number);
        productName = (TextView) findViewById(R.id.productName);
        more = (ImageView) findViewById(R.id.more);
        less = (ImageView) findViewById(R.id.less);
        li = (LinearLayout) findViewById(R.id.li);
        priceMinimum =(TextView) findViewById(R.id.priceMinimum);
        ones= (ImageView) findViewById(R.id.one);
        twos= (ImageView) findViewById(R.id.two);
        threes= (ImageView) findViewById(R.id.three);
        fours= (ImageView) findViewById(R.id.four);
        fives= (ImageView) findViewById(R.id.five);
        numberCar = (TextView) findViewById(R.id.numberCar);
        Integer car= settings.shoppingCar.carFinal.size();
        numberCar.setText(settings.user.getCarCant());
        final productCar pCar = new productCar();
        settings.subtotal.setFinalPrice(settings.subtotal.getPrice());
        settings.productCar.productCars = new ArrayList<>();
        settings.stablishmentCar.stablishmentCars = new ArrayList<>();
        settings.allChecks.ed = new ArrayList<>();


        settings.user.analytics = GoogleAnalytics.getInstance(this);
        settings.user.analytics.setLocalDispatchPeriod(1800);
        settings.user.tracker = settings.user.analytics.newTracker("UA-101326412-1");
        settings.user.tracker.enableExceptionReporting(true);
        settings.user.tracker.enableAdvertisingIdCollection(true);
        settings.user.tracker.enableAutoActivityTracking(true);

        stars(settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getStars(),
                ones, twos, threes, fours, fives);

        pCar.setIdProduct(settings.subtotal.getProductId()
                );

        if(settings.shoppingCar.carFinal==null){
            settings.shoppingCar.carFinal = new ArrayList<StablishmentCar>();
        }

        //settings.handleSSLHandshake();

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               for  (int h =0;h<settings.productCar.productCars.size();h++){

                       settings.productCar.productCars.set(h, new productCar(
                               settings.productCar.productCars.get(h).getIdProduct(),
                               settings.productCar.productCars.get(h).getName(),
                               settings.productCar.productCars.get(h).getPrice(),
                               settings.productCar.productCars.get(h).getPicture(),
                               settings.allChecks.getEd().get(h).getText().toString(),
                               settings.productCar.productCars.get(h).getTotal(),
                               settings.productCar.productCars.get(h).getAdditionCars(),
                               settings.productCar.productCars.get(h).getIngredientsCars(),
                               settings.productCar.productCars.get(h).getDrinkCar()));
                   }

                for (int h =0; h<settings.productCar.productCars.size();h++){
                    ArrayList<ingredientsCar> realIngredien = new ArrayList<ingredientsCar>();

                    for(int j=0;j<settings.productCar.productCars.get(h).getIngredientsCars().size();j++) {

                        if (settings.allChecks.allCheckses.get(h).getChecks()[h].isChecked()) {
                            realIngredien.add(new ingredientsCar(

                                    settings.productCar.productCars.get(h).getIngredientsCars().get(j).getId(),
                                    settings.productCar.productCars.get(h).getIngredientsCars().get(j).getName()
                            ));
                        }
                    }
                    settings.productCar.productCars.set(h, new productCar(
                                    settings.productCar.productCars.get(h).getIdProduct(),
                                    settings.productCar.productCars.get(h).getName(),
                                    settings.productCar.productCars.get(h).getPrice(),
                                    settings.productCar.productCars.get(h).getPicture(),
                                    settings.productCar.productCars.get(h).getObser(),
                                    settings.productCar.productCars.get(h).getTotal(),
                                    settings.productCar.productCars.get(h).getAdditionCars(),
                                    realIngredien,
                                    settings.productCar.productCars.get(h).getDrinkCar()
                            ));
                }

                for(int h =0; h<settings.productCar.productCars.size();h++){
                    ArrayList<additionCar> realAddition = new ArrayList<additionCar>();
                    Integer intAdditions = settings.productCar.productCars.get(h).getAdditionCars().size();
                    for(int j=0;j<intAdditions;j++){
                        Boolean boole = settings.allChecks.allCheckses.get(h).getCheckAddition()[j].isChecked();
                        if (boole==true){
                            realAddition.add(new additionCar(
                                    settings.productCar.productCars.get(h).getAdditionCars().get(j).id,
                                    settings.productCar.productCars.get(h).getAdditionCars().get(j).cant,
                                    settings.productCar.productCars.get(h).getAdditionCars().get(j).name,
                                    settings.productCar.productCars.get(h).getAdditionCars().get(j).price
                                    )
                            );
                        }
                    }
                    settings.productCar.productCars.set(h, new productCar(
                            settings.productCar.productCars.get(h).getIdProduct(),
                            settings.productCar.productCars.get(h).getName(),
                            settings.productCar.productCars.get(h).getPrice(),
                            settings.productCar.productCars.get(h).getPicture(),
                            settings.productCar.productCars.get(h).getObser(),
                            settings.productCar.productCars.get(h).getTotal(),
                            realAddition,
                            settings.productCar.productCars.get(h).getIngredientsCars(),
                            settings.productCar.productCars.get(h).getDrinkCar()
                    ));
                }

                Intent go = new Intent(productm.this, car.class);

                if(settings.shoppingCar.carFinal.size()>=1){
                for (int bb=0; bb<settings.shoppingCar.carFinal.size();bb++){

                    Integer firstOption = settings.shoppingCar.carFinal.get(bb).getIdBranch();
                    Integer secondOption = settings.stablishment.stablishments.get(settings.stablishment.getNumber()).idBranch;


                    if (firstOption==secondOption
                            ){

                        Integer sd = settings.productCar.getProductCars().size();

                            settings.shoppingCar.carFinal.get(bb).productCars.add(
                                    new productCar(
                                            settings.productCar.productCars.get(cc).getIdProduct(),
                                            settings.productCar.productCars.get(cc).getName(),
                                            settings.productCar.productCars.get(cc).getPrice(),
                                            settings.productCar.productCars.get(cc).getPicture(),
                                            settings.allChecks.allCheckses.get(cc).getEd().get(bb).getText().toString(),0,
                                            settings.productCar.productCars.get(cc).getAdditionCars(),
                                            settings.productCar.productCars.get(cc).getIngredientsCars(),
                                            settings.productCar.productCars.get(cc).getDrinkCar()
                                    )
                            );

                        break;
                    }

                    else{
                        if(bb==settings.shoppingCar.carFinal.size()-1){
                            settings.shoppingCar.carFinal.add(
                                    new StablishmentCar(
                                            settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getId_Stablish(),
                                            settings.stablishment.stablishments.get(settings.stablishment.getNumber()).idBranch,
                                            settings.stablishment.getId(),
                                            settings.stablishment.stablishments.get(settings.stablishment.getNumber()).image,
                                            settings.stablishment.stablishments.get(settings.stablishment.getNumber()).name,
                                            settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getDistance(),
                                            settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getDurattion(),
                                            settings.stablishment.stablishments.get(settings.stablishment.getNumber()).price,
                                            settings.stablishment.stablishments.get(settings.stablishment.getNumber()).minimum,
                                            Integer.parseInt(subtotal.getText().toString()),
                                            settings.productCar.getProductCars()
                                    ));
                            break;
                        }


                    }
                }}
                else {
                    settings.shoppingCar.carFinal.add(
                            new StablishmentCar(
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getId_Stablish(),
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).idBranch,
                                    settings.stablishment.getId(),
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).image,
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).name,
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getDistance(),
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getDurattion(),
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).price,
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).minimum,
                                    Integer.parseInt(subtotal.getText().toString()),
                                    settings.productCar.getProductCars()
                            ));
                }

                startActivity(go);
                productm.this.finish();
            }
        });

        switch (settings.stablishment.getId()){
            case 2:
                upss.setBackgroundColor(getResources().getColor(R.color.blacBeer));
                namess.setBackgroundColor(getResources().getColor(R.color.blacBeerSelec));
                moress.setBackgroundColor(getResources().getColor(R.color.blacBeer));
                productPicture.setImageDrawable(getResources().getDrawable(R.drawable.logonegro));
                textxvi.setTextColor(getResources().getColor(R.color.blacBeer));
                break;
            case 3:
                upss.setBackgroundColor(getResources().getColor(R.color.blueFarmacy));
                namess.setBackgroundColor(getResources().getColor(R.color.blueFarmacySelect));
                moress.setBackgroundColor(getResources().getColor(R.color.blueFarmacy));
                productPicture.setImageDrawable(getResources().getDrawable(R.drawable.logoverde));
                break;
            case 4:
                upss.setBackgroundColor(getResources().getColor(R.color.bluePet));
                namess.setBackgroundColor(getResources().getColor(R.color.bluePetSelect));
                moress.setBackgroundColor(getResources().getColor(R.color.bluePet));
                productPicture.setImageDrawable(getResources().getDrawable(R.drawable.logoazul));
                break;
            case 5:
                upss.setBackgroundColor(getResources().getColor(R.color.yellowmarket));
                namess.setBackgroundColor(getResources().getColor(R.color.yellowmarketSelect));
                moress.setBackgroundColor(getResources().getColor(R.color.yellowmarket));
                productPicture.setImageDrawable(getResources().getDrawable(R.drawable.logoamarillo));
                break;

        }
        Picasso.with(productm.this)
                .load("http://godomicilios.co/admin/img/productos/"+settings.subtotal.getPicture())
                .into(productPicture, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        //do smth when picture is loaded successfully
                    }

                    @Override
                    public void onError() {
                        //do smth when there is picture loading error
                    }
                });

        Picasso.with(productm.this)
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


        final View[] childs = {View.inflate(productm.this, R.layout.components, null)};



        z = settings.subtotal.getPrice() * settings.subtotal.getCant();
        subtotal.setText(z.toString());



        for(int i =0;i<settings.subtotal.getCant();i++){
            settings.allChecks.allCheckses = new ArrayList<>();
            settings.allChecks.allCheckses.add(new allChecks(checksDrink2, checkAddition2, checks2)
            );

            More(i);
        }

        //click
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(settings.subtotal.getCant()>1){

                    Integer prov = settings.subtotal.getCant();
                    prov = prov-1;
                    settings.subtotal.setCant(prov);
                    number.setText(prov.toString());
                    Integer a = settings.subtotal.getPrice();
                    Integer b = settings.subtotal.getFinalPrice();
                    Integer multi= b -a;
                    z=multi;
                    settings.subtotal.setFinalPrice(multi);
                     subtotal.setText(multi.toString());
                    Less();


                    //deleteView();
                }
               *//* for(int i=0; i<removeCount;i++){
                    li.removeView(childs[0]);
                }


                removeCount=removeCount-1;*//*
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(settings.subtotal.getCant() >= 1){
                    Integer prov= settings.subtotal.getCant();
                    prov = prov+1;
                    settings.subtotal.setCant(prov);
                    number.setText(prov.toString());
                    Integer a = settings.subtotal.getFinalPrice();
                    multi= a + settings.subtotal.getPrice();
                    subtotal.setText(multi.toString());
                    settings.subtotal.setFinalPrice(multi);
                    z=multi;
                    moreInt=1;
                    settings.allChecks.allCheckses.add(new allChecks(
                            checksDrink2, checkAddition2, checks2
                    ));
                    More(prov-1);
                    //showAny(1);

                }

                *//*for(int i=0;i<removeCount;i++)
                {

                    childs[i] = View.inflate(productm.this, R.layout.components, null);
                    li.addView(childs[i]);

                }


                removeCount=removeCount+1;*//*


            }
        });


        //Stablishment
        name.setText(settings.stablishment.stablishments.get(settings.stablishment.getNumber()).name);
        address.setText(settings.stablishment.stablishments.get(settings.stablishment.getNumber()).address);
        price.setText("$" + settings.stablishment.stablishments.get(settings.stablishment.getNumber()).price);
        priceMinimum.setText("$" + settings.stablishment.stablishments.get(settings.stablishment.getNumber()).minimum);
        description.setText(settings.subtotal.getDescription());
        productName.setText(settings.subtotal.getName());
        priceProduct.setText("$" +settings.subtotal.getPrice());
        number.setText(settings.subtotal.getCant().toString());


        //Product

        *//*for(int i =0; i<settings.subtotal.subtotals.size();i++){
            li.addView(child);
        }*//*



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



       *//* for(int i=0; i<settings.elementsToProducts.elementsToProductses.size();i++){

            final View child = View.inflate(productm.this, R.layout.rank, null);

            final LinearLayout click = (LinearLayout) child.findViewById(R.id.click);
            final LinearLayout lll = (LinearLayout) child.findViewById(R.id.lii);
            TextView name = (TextView) child.findViewById(R.id.name);
            final LinearLayout linears = (LinearLayout) child.findViewById(R.id.lii);

            if(i%2==0){
                click.setBackgroundColor(getResources().getColor(R.color.gray));

            }
            else{
                click.setBackgroundColor(Color.WHITE);

            }
            li.addView(child);

        }*//*



        if(settings.user.getaBoolean()==false){
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_stablishm_drawer);
        }
    */}

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
                    Intent go = new Intent(productm.this, car.class);
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
            Intent go = new Intent(productm.this,porfile.class);
            startActivity(go);

        } else if (id == R.id.referred) {
            Intent go = new Intent(productm.this,refer.class);
            startActivity(go);

        } else if (id == R.id.coupons) {
            Intent go = new Intent(productm.this,coupons.class);
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
            Intent go = new Intent(productm.this,payM.class);
            startActivity(go);


        } else if (id == R.id.car) {

            Intent go = new Intent(productm.this, car.class);
            startActivity(go);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String subtotal (){
        return "a";
    }

    /*public void firstDrink (final String url) throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{
                            settings.drink.drinks= new ArrayList<>();

                            for(int i =0;i<response.length();i++){

                                final JSONObject drink = (JSONObject) response.getJSONObject(i);

                                settings.drink.drinks.add( new drink(drink.getInt("id_bebida"),
                                        drink.getInt("valor"),
                                        drink.getInt("empresa_id"),
                                        drink.getString("nombre_bebida"),
                                        drink.getString("imagen"),
                                        drink.getString("tamano")));


                                if(settings.subtotal.getDrinkEdit()>0) {
                                    secondDrink("https://godomicilios.co/webService/servicios.php?" +
                                            "svice=BEBIDAS_X_PRECIO&metodo=json&valor_bebida=" +
                                            drink.getString("valor") + "&empId=" +
                                            drink.getString("empresa_id"));

                                }
                                else{
                                    drinks.setVisibility(View.VISIBLE);
                                }
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

                                    finish();
                                    startActivity(getIntent());

                                }
                            }
                            );
                            queue.add(jsonArrayRequest);


                        }

    public void secondDrink (final String url) throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{
                            settings.drink.drinks= new ArrayList<>();
                            settings.elementsToProducts.elementsToProductses = new ArrayList<>();


                            for(int i =0;i<response.length();i++){
                                final JSONObject drink = (JSONObject) response.getJSONObject(i);


                                 settings.drink.drinks.add( new drink(drink.getInt("id_bebida"),
                                        drink.getInt("valor"),
                                        drink.getInt("empresa_id"),
                                        drink.getString("nombre_bebida"),
                                        drink.getString("imagen"),
                                        drink.getString("tamano")));

                            }
                            drinks.setVisibility(View.VISIBLE);

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

                String mensaje = "Oops algo salió mal, intentalo nuevamente";
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                mensaje, Toast.LENGTH_SHORT);

                toast1.show();

            }
        }
        );
        queue.add(jsonArrayRequest);


    }
    public void ingredients (final String url) throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{
                            settings.ingredients.ingredientses= new ArrayList<>();

                            if(response.length()>0){

                                for(int i =0;i<response.length();i++){
                                    final JSONObject ingredients = (JSONObject) response.getJSONObject(i);


                                    settings.ingredients.ingredientses.add( new ingredients(
                                            ingredients.getInt("id_ingrediente"),
                                            ingredients.getString("nombre_ingrediente"),
                                            ingredients.getInt("estado_ingrediente"),
                                            ingredients.getInt("obligatorio")
                                    ));

                                    settings.ingredients.idProduct=ingredients.getInt("producto_id");

                                }
                                ingredient.setVisibility(View.VISIBLE);
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

                finish();
                startActivity(getIntent());

            }
        }
        );
        queue.add(jsonArrayRequest);


    }
    public void aditions (final String url) throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(JsonArrayRequest.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONArray response) {
                        try{
                            settings.addition.additions= new ArrayList<>();


                            if(response.length()<1){

                            }
                            else{
                                for(int i =0;i<response.length();i++){
                                    final JSONObject addition = (JSONObject) response.getJSONObject(i);


                                    settings.addition.additions.add( new addition(
                                            addition.getInt("id"),
                                            addition.getString("nombre_adicion"),
                                            addition.getInt("valor"),
                                            addition.getString("imagen_adicion")
                                    ));

                                }
                                settings.addition.setNum(1);
                                addition.setVisibility(View.VISIBLE);

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

                String mensaje = "Oops algo salió mal, intentalo nuevamente";
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                mensaje, Toast.LENGTH_SHORT);

                toast1.show();

            }
        }
        );
        queue.add(jsonArrayRequest);


    }*/

    public void showAny () {
    }

    public void deleteView(){
        li.removeViewAt(settings.subtotal.getCant());
    }

    /*public void query (){

        //drinks
        if (settings.subtotal.getDrinkType()!=0){
            try {
                firstDrink("https://godomicilios.co/webService/servicios.php?svice=BEBIDAS&metodo=json&tipo_bebida="+ settings.subtotal.getDrinkType());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        if(settings.stablishment.getId()==1){
            try {
                ingredients("https://godomicilios.co/webService/servicios.php?svice=INGREDIENTES&metodo=json&proId="+ settings.subtotal.getProductId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                aditions("https://godomicilios.co/webService/servicios.php?svice=ADICIONES&metodo=json&proId="+ settings.subtotal.getProductId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    public void More (Integer g){

        final android.support.v7.widget.AppCompatCheckBox[] checksDrink = new android.support.v7.widget.AppCompatCheckBox[drinkSize];
        android.support.v7.widget.AppCompatCheckBox[] checkAddition = new android.support.v7.widget.AppCompatCheckBox[additionSize];
        android.support.v7.widget.AppCompatCheckBox[] checks = new android.support.v7.widget.AppCompatCheckBox[ingredientSize];
        ArrayList<EditText> ed = new ArrayList<>();

        countFinal= countFinal+1;
        View child = View.inflate(productm.this, R.layout.components, null);

        final LinearLayout drinks = (LinearLayout) child.findViewById(R.id.drink);
        final LinearLayout a = (LinearLayout) child.findViewById(R.id.a);
        final LinearLayout ingredient = (LinearLayout) child.findViewById(R.id.ingredients);
        final LinearLayout b = (LinearLayout) child.findViewById(R.id.b);
        final LinearLayout c = (LinearLayout) child.findViewById(R.id.c);
        final LinearLayout addition = (LinearLayout) child.findViewById(R.id.additions);
        final LinearLayout all = (LinearLayout) child.findViewById(R.id.all);
        final EditText text = (EditText) child.findViewById(R.id.text);
        final LinearLayout coments = (LinearLayout) child.findViewById(R.id.linear);
        final LinearLayout d = (LinearLayout) child.findViewById(R.id.d);

        settings.ingredientsCar.ingredientsCars = new ArrayList<>();
        settings.additionCar.additionCars = new ArrayList<>();

        if(moreInt==0){
            ed.add(text);
            ed.get(g).setId(g);
            settings.allChecks.getEd().add(text);
        }
        else{
            ed.add(text);
            Integer jh= ed.size()-1;
            ed.get(jh).setId(jh);
            moreInt=0;
            settings.allChecks.getEd().add(text);
        }

        settings.allChecks.allCheckses.set(g, new allChecks(
                settings.allChecks.allCheckses.get(g).getChecksDrink(),
                settings.allChecks.allCheckses.get(g).getCheckAddition(),
                settings.allChecks.allCheckses.get(g).getChecks()));

        settings.productCar.productCars.add(new productCar(

                settings.subtotal.getProductId(),
                settings.subtotal.getName(),
                settings.subtotal.getPrice(),
                settings.subtotal.getPicture(),
                "",0,
                settings.additionCar.additionCars,
                settings.ingredientsCar.ingredientsCars,
                settings.drinkCar
        ));

        final Integer daa= settings.productCar.productCars.size()-1;

        if (settings.ingredients.ingredientses.size()>0){
            for (int k =0; k<settings.ingredients.ingredientses.size();k++){

                settings.ingredientsCar.ingredientsCars = new ArrayList<>();

                final View childIn = View.inflate(productm.this, R.layout.products_elements, null);
                TextView name = (TextView) childIn.findViewById(R.id.text);
                name.setText(settings.ingredients.ingredientses.get(k).name);
                LinearLayout p = (LinearLayout) childIn.findViewById(R.id.p);
                LinearLayout allAll = (LinearLayout) childIn.findViewById(R.id.allAll);
                boolean seleccionado = true;

                settings.additionCar.additionCars = new ArrayList<>();

                for(int ing=0;ing<settings.ingredients.ingredientses.size();ing++){

                    settings.ingredientsCar.ingredientsCars.add(new ingredientsCar(
                            settings.ingredients.ingredientses.get(ing).id,
                            settings.ingredients.ingredientses.get(ing).name
                    ));
                }

                settings.productCar.productCars.set(daa,new productCar(
                        settings.productCar.productCars.get(daa).getIdProduct(),
                        settings.productCar.productCars.get(daa).getName(),
                        settings.productCar.productCars.get(daa).getPrice(),
                        settings.productCar.productCars.get(daa).getPicture(),
                        settings.productCar.productCars.get(daa).getObser(),0,
                        settings.productCar.productCars.get(daa).getAdditionCars(),
                        settings.ingredientsCar.ingredientsCars,
                        settings.productCar.productCars.get(daa).getDrinkCar()
                        )
                );

                final android.support.v7.widget.AppCompatCheckBox nuevo_checkbox = (android.support.v7.widget.AppCompatCheckBox) childIn.findViewById(R.id.appCompatCheckBox);

                checks[k] = nuevo_checkbox;
                checks[k].setId(k);
                settings.allChecks.allCheckses.set(g, new allChecks(
                        settings.allChecks.allCheckses.get(g).getChecksDrink(),
                        settings.allChecks.allCheckses.get(g).getCheckAddition(),
                        checks));

                /*if(settings.ingredients.ingredientses.get(k).obligatory == 1){
                    nuevo_checkbox.isChecked();
                    nuevo_checkbox.setChecked(seleccionado);
                    int states[][] = {{android.R.attr.state_checked}, {}};
                    int colors[] = {getResources().getColor(R.color.greenCar),getResources().getColor(R.color.greenCar)};
                    CompoundButtonCompat.setButtonTintList(nuevo_checkbox, new ColorStateList(states, colors));
                    nuevo_checkbox.setEnabled(false);
                }
                else{
                    nuevo_checkbox.isChecked();
                    nuevo_checkbox.setChecked(seleccionado);
                }*/

                allAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                p.setVisibility(View.GONE);
                b.addView(childIn);
            }
            ingredient.setVisibility(View.VISIBLE);


        }

        if (settings.drink.drinks.size()> 0){

            for(int x=0;x<settings.drink.drinks.size();x++){

                settings.drinkCar.drinkCars = new ArrayList<>();

                settings.drinkCar.setId(settings.drink.drinks.get(x).getId());
                settings.drinkCar.setName(settings.drink.drinks.get(x).getName());
                settings.drinkCar.setPicture(settings.drink.drinks.get(x).getPicture());
                settings.drinkCar.setLength(settings.drink.drinks.get(x).getLenght());

                final View childa = View.inflate(productm.this, R.layout.products_elements, null);

                TextView name = (TextView) childa.findViewById(R.id.text);
                ImageView picture = (ImageView) childa.findViewById(R.id.image);
                TextView length = (TextView) childa.findViewById(R.id.length);
                LinearLayout allAll = (LinearLayout) childa.findViewById(R.id.allAll);
                final android.support.v7.widget.AppCompatCheckBox nuevo_checkbox = (android.support.v7.widget.AppCompatCheckBox) childa.findViewById(R.id.appCompatCheckBox);
                nuevo_checkbox.setId(x);
                checksDrink[x] = nuevo_checkbox;
                checksDrink[x].setId(x);
                settings.allChecks.allCheckses.set(g, new allChecks(
                        checksDrink,
                        settings.allChecks.allCheckses.get(g).getCheckAddition(),
                        settings.allChecks.allCheckses.get(g).getChecks()));

                boolean seleccionado = true;
                Picasso.with(productm.this)
                        .load("http://godomicilios.co/admin/img/bebidas/"+settings.drink.drinks.get(x).getPicture())
                        .into(picture, new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {
                                //do smth when picture is loaded successfully
                            }

                            @Override
                            public void onError() {
                                //do smth when there is picture loading error
                            }
                        });
                if(x==0){
                    settings.productCar.productCars.set(daa ,new productCar(
                                    settings.productCar.productCars.get(daa).getIdProduct(),
                                    settings.productCar.productCars.get(daa).getName(),
                                    settings.productCar.productCars.get(daa).getPrice(),
                                    settings.productCar.productCars.get(daa).getPicture(),
                                    settings.productCar.productCars.get(daa).getObser(),0,
                                    settings.productCar.productCars.get(daa).getAdditionCars(),
                                    settings.productCar.productCars.get(daa).getIngredientsCars(),
                                    new drinkCar(
                                            settings.drinkCar.getId(),
                                            settings.drinkCar.getName(),
                                            settings.drinkCar.getPicture(),
                                            settings.drinkCar.getLength()
                                    )
                            )
                    );
                }


                name.setText(settings.drink.drinks.get(x).getName());
                length.setText(settings.drink.drinks.get(x).getLenght());
                if(x==0){
                    int states[][] = {{android.R.attr.state_checked}, {}};
                    int colors[] = {ContextCompat.getColor(this,R.color.redGo),ContextCompat.getColor(this,R.color.redGo)};
                    CompoundButtonCompat.setButtonTintList(nuevo_checkbox, new ColorStateList(states, colors));
                    nuevo_checkbox.isChecked();
                    nuevo_checkbox.setChecked(true);
                }
                else{
                    int states[][] = {{android.R.attr.state_checked}, {}};
                    int colors[] = {ContextCompat.getColor(this,R.color.redGo),ContextCompat.getColor(this,R.color.redGo)};
                    CompoundButtonCompat.setButtonTintList(nuevo_checkbox, new ColorStateList(states, colors));
                    nuevo_checkbox.setEnabled(true);
                    nuevo_checkbox.setChecked(false);
                }
                allAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                nuevo_checkbox.setOnClickListener(new View.OnClickListener() {
                    Integer idCheck =nuevo_checkbox.getId();
                    @Override
                    public void onClick(View v) {
                        for (int vv=0;vv<settings.drink.drinks.size();vv++){
                            if(idCheck==vv){
                                Boolean booleen =checksDrink[vv].isSelected();
                                if(!booleen){
                                    checksDrink[vv].setChecked(true);
                                    settings.productCar.productCars.set(daa ,new productCar(
                                                    settings.productCar.productCars.get(daa).getIdProduct(),
                                                    settings.productCar.productCars.get(daa).getName(),
                                                    settings.productCar.productCars.get(daa).getPrice(),
                                                    settings.productCar.productCars.get(daa).getPicture(),
                                                    settings.productCar.productCars.get(daa).getObser(),0,
                                                    settings.productCar.productCars.get(daa).getAdditionCars(),
                                                    settings.productCar.productCars.get(daa).getIngredientsCars(),
                                                    new drinkCar(
                                                            settings.drink.drinks.get(idCheck).getId(),
                                                            settings.drink.drinks.get(idCheck).getName(),
                                                            settings.drink.drinks.get(idCheck).getPicture(),
                                                            settings.drink.drinks.get(idCheck).getLenght()
                                                    )
                                            )
                                    );
                                }
                            }
                            else{
                                checksDrink[vv].setChecked(false);
                            }
                        }
                    }
                });

                //length.setText(settings.drink.drinks.get(i).getLenght());
                a.addView(childa);
            }

            drinks.setVisibility(View.VISIBLE);
        }
        if(settings.addition.additions.size()> 0){
            if(settings.addition.additions.size()>0){

                for(int bb =0;bb<settings.addition.additions.size();bb++) {

                    settings.additionCar.additionCars = new ArrayList<>();

                    final View childAdd = View.inflate(productm.this, R.layout.additionss, null);
                    final android.support.v7.widget.AppCompatCheckBox appCompatCheckBox = (android.support.v7.widget.AppCompatCheckBox) childAdd.findViewById(R.id.appCompatCheckBox);
                    final ImageView image = (ImageView) childAdd.findViewById(R.id.image);
                    TextView name = (TextView) childAdd.findViewById(R.id.textOne);
                    TextView price = (TextView) childAdd.findViewById(R.id.textTwo);
                    LinearLayout allAll = (LinearLayout) childAdd.findViewById(R.id.allAll);
                    final ImageView more = (ImageView) childAdd.findViewById(R.id.more);
                    final ImageView less = (ImageView) childAdd.findViewById(R.id.less);
                    final TextView numberaa = (TextView) childAdd.findViewById(R.id.number);
                    appCompatCheckBox.setId(bb);
                    more.setId(bb);
                    less.setId(bb);
                    more.setEnabled(false);
                    less.setEnabled(false);
                    checkAddition[bb] = appCompatCheckBox;
                    checkAddition[bb].setId(bb);
                    settings.allChecks.allCheckses.set(g, new allChecks(
                            settings.allChecks.allCheckses.get(g).getChecksDrink(),
                            checkAddition,
                            settings.allChecks.allCheckses.get(g).getChecks()));

                    for (int ad = 0; ad < settings.addition.additions.size(); ad++) {

                    settings.additionCar.additionCars.add(new additionCar(
                            settings.addition.additions.get(ad).getId(),
                            settings.addition.additions.get(ad).getCantss(),
                            settings.addition.additions.get(ad).getName(),
                            settings.addition.additions.get(ad).getPrice()
                    ));
                }

                    settings.productCar.productCars.set(daa,new productCar(
                                    settings.productCar.productCars.get(daa).getIdProduct(),
                                    settings.productCar.productCars.get(daa).getName(),
                                    settings.productCar.productCars.get(daa).getPrice(),
                                    settings.productCar.productCars.get(daa).getPicture(),
                                    settings.productCar.productCars.get(daa).getObser(),0,
                                    settings.additionCar.additionCars,
                                    settings.productCar.productCars.get(daa).getIngredientsCars(),
                                    settings.productCar.productCars.get(daa).getDrinkCar()
                            )
                    );

                    Picasso.with(productm.this)
                            .load("http://godomicilios.co/admin/img/adiciones/"+settings.addition.additions.get(bb).getPicture())
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

                    name.setText(settings.addition.additions.get(bb).getName());
                    price.setText(settings.addition.additions.get(bb).getPrice().toString());

                    appCompatCheckBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer One = settings.subtotal.getFinalPrice();
                            Integer Two = settings.addition.additions.get(
                                    appCompatCheckBox.getId()
                            ).getPrice();
                            Integer idCheck =appCompatCheckBox.getId();

                            if(appCompatCheckBox.isChecked()){

                                Integer Total= One+Two;
                                settings.subtotal.setFinalPrice(Total);
                                subtotal.setText(Total.toString());
                                settings.addition.additions.set(idCheck,
                                        new addition(settings.addition.additions.get(idCheck).getId(),
                                                settings.addition.additions.get(idCheck).getName(),
                                                settings.addition.additions.get(idCheck).getPrice(),
                                                settings.addition.additions.get(idCheck).getPicture(),
                                                1));
                                numberaa.setText(settings.addition.additions.get(idCheck).getCantss().toString());
                                more.setEnabled(true);


                                int states[][] = {{android.R.attr.state_checked}, {}};
                                int colors[] = {ContextCompat.getColor(context,R.color.redGo),ContextCompat.getColor(context,R.color.black)};
                                CompoundButtonCompat.setButtonTintList(appCompatCheckBox, new ColorStateList(states, colors));
                                appCompatCheckBox.isChecked();
                                appCompatCheckBox.setChecked(true);

                                if(settings.addition.additions.get(idCheck).getCantss()>=2){
                                    appCompatCheckBox.setEnabled(false);
                                }


                            }
                            else{
                                Integer Total=settings.subtotal.getFinalPrice()-settings.addition.additions.get(appCompatCheckBox.getId()).getPrice();
                                settings.subtotal.setFinalPrice(Total);
                                subtotal.setText(Total.toString());
                                more.setEnabled(false);
                                settings.addition.additions.set(idCheck,
                                        new addition(settings.addition.additions.get(idCheck).getId(),
                                                settings.addition.additions.get(idCheck).getName(),
                                                settings.addition.additions.get(idCheck).getPrice(),
                                                settings.addition.additions.get(idCheck).getPicture(),
                                                0));

                                if(!numberaa.getText().equals("-")){
                                    numberaa.setText("-");
                                }

                            }
                        }
                    });


                    c.addView(childAdd);
                    allAll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    more.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Integer nn=more.getId();

                            if(settings.addition.additions.get(more.getId()).getCantss() >= 1){

                                settings.addition.additions.set(more.getId(),
                                        new addition(settings.addition.additions.get(more.getId()).getId(),
                                                settings.addition.additions.get(more.getId()).getName(),
                                                settings.addition.additions.get(more.getId()).getPrice(),
                                                settings.addition.additions.get(more.getId()).getPicture(),
                                                settings.addition.additions.get(more.getId()).getCantss()+1));
                                numberaa.setText(settings.addition.additions.get(more.getId()).getCantss().toString());
                                Integer a = settings.subtotal.getFinalPrice();
                                multi=settings.addition.additions.get(nn).getPrice()+a;
                                subtotal.setText(multi.toString());
                                settings.subtotal.setFinalPrice(multi);
                                less.setEnabled(true);
                                appCompatCheckBox.setEnabled(false);
                                //showAny(1);
                            }

                        }
                    });
                    less.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer nn=less.getId();

                            if(settings.addition.additions.get(nn).getCantss()>2){

                                settings.addition.additions.set(more.getId(),
                                        new addition(settings.addition.additions.get(nn).getId(),
                                                settings.addition.additions.get(nn).getName(),
                                                settings.addition.additions.get(nn).getPrice(),
                                                settings.addition.additions.get(nn).getPicture(),
                                                settings.addition.additions.get(nn).getCantss()-1));
                                numberaa.setText(settings.addition.additions.get(nn).getCantss().toString());
                                Integer a = settings.subtotal.getFinalPrice();
                                Integer b = settings.addition.additions.get(nn).getPrice();
                                Integer multi= a - b;
                                subtotal.setText(multi.toString());
                                settings.subtotal.setFinalPrice(multi);

                                //deleteView();
                            }
                            else{
                                if(settings.addition.additions.get(nn).getCantss() == 2){
                                    settings.addition.additions.set(more.getId(),
                                            new addition(settings.addition.additions.get(nn).getId(),
                                                    settings.addition.additions.get(nn).getName(),
                                                    settings.addition.additions.get(nn).getPrice(),
                                                    settings.addition.additions.get(nn).getPicture(),
                                                    settings.addition.additions.get(nn).getCantss()-1));
                                    numberaa.setText("1");
                                    Integer a = settings.subtotal.getFinalPrice();
                                    Integer b = settings.addition.additions.get(nn).getPrice();
                                    Integer multi= a - b;
                                    subtotal.setText(multi.toString());
                                    settings.subtotal.setFinalPrice(multi);
                                    less.setEnabled(false);
                                    appCompatCheckBox.setEnabled(true);
                                }
                                else{
                                    if(settings.addition.additions.get(nn).getCantss()== 1){
                                        appCompatCheckBox.setChecked(false);
                                        appCompatCheckBox.setEnabled(true);
                                        numberaa.setText("-");
                                        settings.addition.additions.set(more.getId(),
                                                new addition(settings.addition.additions.get(nn).getId(),
                                                        settings.addition.additions.get(nn).getName(),
                                                        settings.addition.additions.get(nn).getPrice(),
                                                        settings.addition.additions.get(nn).getPicture(),
                                                        settings.addition.additions.get(nn).getCantss()-1));
                                        Integer a = settings.subtotal.getFinalPrice();
                                        Integer b = settings.addition.additions.get(nn).getPrice();
                                        Integer multi= a - b;
                                        subtotal.setText(multi.toString());
                                        settings.subtotal.setFinalPrice(multi);
                                    }


                                }

                            }
                        }
                    });

                }
                addition.setVisibility(View.VISIBLE);
            }}


            /*if(settings.drink.drinks != null){
            if (settings.drink.drinks.size() >0){

                for (int j= 0;j<settings.drink.drinks.size();j++){
                    final View childa = View.inflate(productm.this, R.layout.products_elements, null);

                    TextView name = (TextView) child.findViewById(R.id.text);
                    ImageView picture = (ImageView) child.findViewById(R.id.image);
                    TextView length = (TextView) child.findViewById(R.id.length);
                    new settings.DownloadImageTask(picture)
                            .execute("https://godomicilios.co/admin/img/bebidas/"+settings.drink.drinks.get(i).getPicture());

                    name.setText(settings.drink.drinks.get(i).getName());
                    //length.setText(settings.drink.drinks.get(i).getLenght());
                    a.addView(childa);

                        drinks.setVisibility(View.VISIBLE);



                }
                }
            }*/

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  /*  if(validatorc==0){
                        for(int i =0;i<settings.addition.additions.size();i++){

                            ViewGroup viewGroup = all;
                            final View child = View.inflate(productm.this, R.layout.additionss,null);

                            TextView name = (TextView) child.findViewById(R.id.textOne);
                            ImageView picture = (ImageView) child.findViewById(R.id.image);
                            TextView price = (TextView) child.findViewById(R.id.textTwo);

                            new settings.DownloadImageTask(picture)
                                    .execute("https://godomicilios.co/admin/img/adiciones/"+settings.addition.additions.get(i).getPicture());

                            name.setText(settings.addition.additions.get(i).getName());
                            price.setText(settings.addition.additions.get(i).getPrice().toString());

                            c.addView(child);
                            validatorc =1;
                        }

                    }
                    if(a.getVisibility()==View.VISIBLE){
                        a.setVisibility(View.GONE);
                    }
                    if(b.getVisibility()==View.VISIBLE){
                        b.setVisibility(View.GONE);
                    }
                    if(d.getVisibility()==View.VISIBLE){
                        d.setVisibility(View.GONE);
                    }*/

                if(cc==0){
                    c.setVisibility(View.VISIBLE);
                    cc=1;
                }
                else{
                    c.setVisibility(View.GONE);
                    cc=0;

                }
            }
        });

        ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    /*if(validatorb ==0){
                        for(int i =0;i<settings.ingredients.ingredientses.size();i++){

                            final View child = View.inflate(productm.this, R.layout.products_elements, null);

                            TextView name = (TextView) child.findViewById(R.id.text);
                            ImageView picture = (ImageView) child.findViewById(R.id.image);
                            TextView length = (TextView) child.findViewById(R.id.length);
                            picture.setVisibility(View.GONE);

                            name.setText(settings.ingredients.ingredientses.get(i).name);

                            b.addView(child);}
                        validatorb =1;
                    }

                    if(a.getVisibility()==View.VISIBLE){
                        a.setVisibility(View.GONE);
                    }
                    if(c.getVisibility()==View.VISIBLE){
                        c.setVisibility(View.GONE);
                    }
                    if(d.getVisibility()==View.VISIBLE){
                        d.setVisibility(View.GONE);
                    }*/
                if(bb==0){
                    b.setVisibility(View.VISIBLE);
                    bb=1;
                }
                else{
                    b.setVisibility(View.GONE);
                    bb=0;

                }
            }
        });






        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    /*if(validatora ==0){
                        for(int i =0;i<settings.drink.drinks.size();i++){

                            final View child = View.inflate(productm.this, R.layout.products_elements, null);

                            TextView name = (TextView) child.findViewById(R.id.text);
                            ImageView picture = (ImageView) child.findViewById(R.id.image);
                            TextView length = (TextView) child.findViewById(R.id.length);

                            new settings.DownloadImageTask(picture)
                                    .execute("https://godomicilios.co/admin/img/bebidas/"+settings.drink.drinks.get(i).getPicture());


                            name.setText(settings.drink.drinks.get(i).getName());
                            length.setText(settings.drink.drinks.get(i).getLenght());
                            a.addView(child);}
                        validatora =1;


                    }*/

                if(aa==0){
                    a.setVisibility(View.VISIBLE);
                    aa=1;
                }
                else{
                    a.setVisibility(View.GONE);
                    aa=0;
                }
            }
        });

        //query();




        coments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   /* if(a.getVisibility()==View.VISIBLE){
                        a.setVisibility(View.GONE);
                    }
                    if(b.getVisibility()==View.VISIBLE){
                        b.setVisibility(View.GONE);
                    }
                    if(c.getVisibility()==View.VISIBLE){
                        c.setVisibility(View.GONE);
                    }*/
                if (dd == 0) {
                    d.setVisibility(View.VISIBLE);
                    dd = 1;
                } else {
                    d.setVisibility(View.GONE);
                    dd = 0;
                }
            }
        });

       /*     if (number == 1 && settings.stablishment.getId()==1) {
                if (settings.ingredients.ingredientses.size() >= 1 && settings.ingredients.idProduct == settings.subtotal.getProductId()) {
                    ingredient.setVisibility(View.VISIBLE);
                }

                if (settings.drink.drinks.size() >= 1 ) {
                    drinks.setVisibility(View.VISIBLE);
                }

                if (settings.addition.additions.size() >= 1 ) {
                    addition.setVisibility(View.VISIBLE);
                }
            }*/

        li.addView(child);
    }
    public void Less(){
        li.removeViewAt(countFinal-1);
        countFinal= countFinal-1;
    }

    public void stars (Integer i, ImageView one, ImageView two, ImageView three,
                       ImageView four, ImageView five){
        switch (i){
            case 1:
                one.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.medianablancamitad));
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
    public void setImage (String img, ImageView image){
        Picasso.with(productm.this)
                .load(img)
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
    }

    public void confirmeFirstImage(Integer integer){
        Integer sizeDrink =settings.drink.drinks.size();
        if (sizeDrink> 0){
            imageViewDrink.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_bebida_lleno));
            View child = View.inflate(productm.this, R.layout.drink, null);
            vf.addView(child);
        }
        else{
            imgDrink.setVisibility(View.GONE);
            drinkConfirm=0;
            if (settings.ingredients.ingredientses.size()> 0){
                imageViewIngredient.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_ingredientes_lleno));
            }
            else{
                ingreConfirm=0;
                imgIngredient.setVisibility(View.GONE);
                if (settings.addition.additions.size()>0){
                    imageViewAddition.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_adiciones_lleno));
                }
                else{
                    additiConfirm=0;
                    imgAddition.setVisibility(View.GONE);
                    switch (integer){
                        case 1:
                            imageViewObsrv.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_adiciones_lleno));
                            break;
                        case 2:
                            imageViewObsrv.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_observaciones_lleno_beer));
                            break;
                        case 3:
                            imageViewObsrv.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_observaciones_lleno_farmacia));
                            break;
                        case 4:imageViewObsrv.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_observaciones_lleno_mascotas));
                            break;
                        case 5:imageViewObsrv.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_observaciones_lleno_mercado));
                    }

                }

            }
        }

    }
    public void showExists (){
        Integer sizeIngre=settings.ingredients.ingredientses.size();
        Integer sizeAddi = settings.addition.additions.size();

        if (sizeIngre<1&&imgIngredient.getVisibility() ==View.VISIBLE){
            imgIngredient.setVisibility(View.GONE);
            ingreConfirm=0;
            View child = View.inflate(productm.this, R.layout.ingradients, null);
            vf.addView(child);
        }
        if (sizeAddi<1&&imgAddition.getVisibility() ==View.VISIBLE){
            imgAddition.setVisibility(View.GONE);
            additiConfirm=0;
            View child = View.inflate(productm.this, R.layout.additionss, null);
            vf.addView(child);
        }
    }

    private class ListenerTouchViewFlipper implements View.OnTouchListener{

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
                        //vf.setOutAnimation(outToRightAnimation());
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
        outtoLeft.setDuration(100);
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

    /*public void ingredients () throws Exception{
        String url="https://godomicilios.co/webService/servicios.php?svice=INGREDIENTES&metodo=json&proId=1";


        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(productm.this)));

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(JsonObjectRequest.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(final JSONObject response) {
                        try{
                            settings.ingredients.ingredientses= new ArrayList<>();
                            JSONArray obligatory = response.getJSONArray("OBLIGATORIO");
                            for (int o=0;o<obligatory.length();o++){
                                final JSONObject obligator = (JSONObject) obligatory.getJSONObject(o);
                                settings.ingredients.ingredientses.add(new ingredients(
                                        obligator.getInt("id"),obligator.getString("nombre"),obligator.getString("tipo_txt"),1,obligator.getInt("ingrediente_id"),0
                                ));
                            }


                            JSONArray normal = response.getJSONArray("NORMAL");

                            for (int n=0;n<normal.length();n++){
                                final JSONObject norma = (JSONObject) normal.getJSONObject(n);
                                settings.ingredients.ingredientses.add(new ingredients(
                                        norma.getInt("id"),norma.getString("nombre"),norma.getString("tipo_txt"),3,norma.getInt("ingrediente_id"),0
                                ));
                            }
                            JSONObject optional = response.getJSONObject("OPCIONAL");
                            Iterator<String>iterator=optional.keys();
                            ArrayList<JSONObject> objectArray=new ArrayList<>();
                            int i = 0;
                            for (iterator= optional.keys(); iterator.hasNext(); i++) {
                                String s = iterator.next();
                                JSONObject j =optional.getJSONObject(s);
                                JSONArray array = j.getJSONArray("opciones");
                                Integer maxi = j.getInt("cantidad_max");
                                for(int cou =0;cou<array.length();cou++){
                                    final JSONObject opti = (JSONObject) array.getJSONObject(cou);
                                    settings.ingredients.ingredientses.add(new ingredients(
                                            opti.getInt("id"), opti.getString("nombre"), opti.getString("tipo_txt"),3, opti.getInt("ingrediente_id"),maxi
                                    ));
                                }
                                objectArray.add(j);
                            }

                            Integer drink=settings.ingredients.ingredientses.size();
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
                    ingredients();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        );
        queue.add(jsonObjectRequest);


    }*/


}
