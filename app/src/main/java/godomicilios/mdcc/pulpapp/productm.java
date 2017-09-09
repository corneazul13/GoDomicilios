package godomicilios.mdcc.pulpapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.ActionBar;
import android.text.InputType;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;
import com.tt.whorlviewlibrary.WhorlView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.refactor.lib.colordialog.ColorDialog;
import godomicilios.mdcc.pulpapp.settings.CustomSSLSocketFactory;
import godomicilios.mdcc.pulpapp.settings.StablishmentCar;
import godomicilios.mdcc.pulpapp.settings.addition;
import godomicilios.mdcc.pulpapp.settings.additionCar;
import godomicilios.mdcc.pulpapp.settings.allChecks;
import godomicilios.mdcc.pulpapp.settings.check;
import godomicilios.mdcc.pulpapp.settings.drink;
import godomicilios.mdcc.pulpapp.settings.drinkCar;
import godomicilios.mdcc.pulpapp.settings.ingredients;
import godomicilios.mdcc.pulpapp.settings.ingredientsCar;
import godomicilios.mdcc.pulpapp.settings.optionalIngredients;
import godomicilios.mdcc.pulpapp.settings.productCar;
import godomicilios.mdcc.pulpapp.settings.settings;
import godomicilios.mdcc.pulpapp.settings.subGlobalChecks;
import me.omidh.liquidradiobutton.LiquidRadioButton;

public class productm extends AppCompatActivity {

    Context context;
    ImageView profile_image, secondImg, imageViewDrink, imageViewIngredient, imageViewAddition, imageViewObsrv, img_banner_options, more, less;
    TextView name, address, description, priceProduct, subtotal, number,productName, lbl_title_layout;
    LinearLayout  li, imgDrink,imgIngredient, imgAddition;
    Button add, buttonDrink, buttonIngre, buttonAddi;
    int dd=0, aa=0, validatora =0, bb=0, validatorb=0, validatorc=0,cc=0,
    removeCount= settings.subtotal.getCant();
    Integer priceFirst= settings.subtotal.getPrice(), moreInt=0,z=0, multi, countFinal=0;
    public float init_x;
    EditText text;
    Integer ingredientSize, additionSize , drinkSize;
    ArrayList<EditText>ed2 = new ArrayList<>();
    me.omidh.liquidradiobutton.LiquidRadioButton[] checksDrink2 ;
    android.support.v7.widget.AppCompatCheckBox[] checkAddition2;
    me.omidh.liquidradiobutton.LiquidRadioButton[] checks2 ;
    Integer ingreConfirm = 1, additiConfirm=1, drinkConfirm=1;
    ArrayList<ingredients> countIngreNormal, countIngreOptio, countIngreObli;
    ArrayList<check> otherIngre=new ArrayList<>();
    LinearLayout showDrink, showIngredients, showadditions,showObservations;
    Integer daa, test;
    LinearLayout showDrin, showIngredient, showAddition, showObservatio, actual;
    ArrayList<check> checksTemp= new ArrayList<>();
    ArrayList<ingredientsCar> tempIng = new ArrayList<>();
    ArrayList<productCar> productCars = new ArrayList<>();
    productCar actualProduct;
    int rN;
    ArrayList<ingredients> temporal ;
    LinearLayout showCom;
    WhorlView progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        sethomeToolbar(toolbar);
        context = this;
        progress = (WhorlView) this.findViewById(R.id.progressBar2);
        progress.start();
        Integer click = settings.product.clickId;
        query(settings.product.products.get(click).drinkType,
                settings.product.products.get(click).id,
                settings.product.products.get(click).drinkEdit);
        showCom = (LinearLayout) findViewById(R.id.showCom);
        showDrin= (LinearLayout) findViewById(R.id.showDrin);
        showIngredient = (LinearLayout) findViewById(R.id.showIngredient);
        showAddition = (LinearLayout) findViewById(R.id.showAddition);
        showObservatio = (LinearLayout) findViewById(R.id.showObservatio);
        buttonDrink = (Button) findViewById(R.id.buttonDrink);
        buttonIngre = (Button) findViewById(R.id.buttonIngre);
        buttonAddi = (Button) findViewById(R.id.buttonAddi);
        more=(ImageView) findViewById(R.id.more);
        less = (ImageView) findViewById(R.id.less);
        text=(EditText) findViewById(R.id.text);
        add=(Button) findViewById(R.id.add);
        number=(TextView) findViewById(R.id.number);
        profile_image = (ImageView) findViewById(R.id.profile_image);
        productName = (TextView) findViewById(R.id.productName);
        lbl_title_layout = (TextView)findViewById(R.id.lbl_title_layout);
        priceProduct = (TextView) findViewById(R.id.priceProduct);
        description = (TextView) findViewById(R.id.description);
        imageViewDrink = (ImageView) findViewById(R.id.imageViewDrink);
        imgDrink = (LinearLayout) findViewById(R.id.imgDrink);
        imageViewIngredient = (ImageView) findViewById(R.id.imageViewIngredient);
        imgIngredient = (LinearLayout) findViewById(R.id.imgIngredient);
        imageViewAddition = (ImageView) findViewById(R.id.imageViewAddition);
        img_banner_options = (ImageView) findViewById(R.id.img_banner_options);
        imgAddition= (LinearLayout) findViewById(R.id.imgAddition);
        imageViewObsrv = (ImageView) findViewById(R.id.imageViewObsrv);
        setImage("http://godomicilios.co/admin/img/productos/"+settings.subtotal.getPicture(), profile_image);
        productName.setText(settings.subtotal.getName());
        description.setText(settings.subtotal.getDescription());
        priceProduct.setText("$" +settings.subtotal.getPrice());

        showadditions = (LinearLayout) findViewById(R.id.showAdditions);
        showDrink = (LinearLayout) findViewById(R.id.showDrink);

        less.setEnabled(false);
        settings.allChecks.allCheckses = new ArrayList<>();

        rN= new Integer(0);

        if(settings.shoppingCar.carFinal==null){
            settings.shoppingCar.carFinal = new ArrayList<>();
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (settings.subtotal.getFinalPrice()==0){
                    settings.subtotal.setFinalPrice(settings.subtotal.getPrice());
                }
                settings.subtotal.setFinalPrice(settings.subtotal.getPrice());
                Integer tempTotal = settings.productCar.productCars.get(daa).getTotal();
                settings.productCar.productCars.set(daa,new productCar(
                                settings.productCar.productCars.get(daa).getIdProduct(),
                                settings.productCar.productCars.get(daa).getName(),
                                settings.productCar.productCars.get(daa).getPrice(),
                                settings.productCar.productCars.get(daa).getPicture(),
                                text.getText().toString(),tempTotal
                                ,
                                settings.productCar.productCars.get(daa).getAdditionCars(),
                                settings.productCar.productCars.get(daa).getIngredientsCars(),
                                settings.productCar.productCars.get(daa).getDrinkCar()
                ));

                if(settings.shoppingCar.carFinal.size()>=1){
                    for (int bb=0; bb<settings.shoppingCar.carFinal.size();bb++){

                        Integer firstOption = settings.shoppingCar.carFinal.get(bb).getIdBranch();
                        Integer secondOption = settings.stablishment.stablishments.get(settings.stablishment.getNumber()).idBranch;


                        if (firstOption==secondOption
                                ){
                            Integer sd=0 ;
                            for (int i = 0;i<productCars.size();i++){
                                sd = sd+productCars.get(i).getTotal();

                                settings.shoppingCar.carFinal.get(bb).productCars.add(
                                        new productCar(
                                                productCars.get(i).getIdProduct(),
                                                productCars.get(i).getName(),
                                                productCars.get(i).getPrice(),
                                                productCars.get(i).getPicture(),
                                                productCars.get(i).getObser(),
                                                productCars.get(i).getTotal(),
                                                productCars.get(i).getAdditionCars(),
                                                productCars.get(i).getIngredientsCars(),
                                                productCars.get(i).getDrinkCar()
                                        )
                                );

                                break;
                            }
                        }

                        else{
                            Integer subtotalN=0;
                            for (int j =0;j<settings.productCar.productCars.size();j++){
                                subtotalN = settings.productCar.productCars.get(j).getTotal();
                            }
                            if(bb==settings.shoppingCar.carFinal.size()-1){
                                settings.shoppingCar.carFinal.add(
                                        new StablishmentCar(
                                                settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getId_Company(),
                                                settings.stablishment.stablishments.get(settings.stablishment.getNumber()).idBranch,
                                                settings.stablishment.getId(),
                                                settings.stablishment.stablishments.get(settings.stablishment.getNumber()).image,
                                                settings.stablishment.stablishments.get(settings.stablishment.getNumber()).name,
                                                settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getDistance(),
                                                settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getDurattion(),
                                                settings.stablishment.stablishments.get(settings.stablishment.getNumber()).price,
                                                settings.stablishment.stablishments.get(settings.stablishment.getNumber()).minimum,
                                                Integer.parseInt(subtotal.getText().toString()),subtotalN,
                                                productCars
                                        ));
                                break;
                            }
                        }
                    }}
                else {
                    Integer subtotalN =0;
                    for (int j =0;j<settings.productCar.productCars.size();j++){
                        subtotalN = settings.productCar.productCars.get(j).getTotal();
                    }
                    settings.shoppingCar.carFinal.add(
                            new StablishmentCar(
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getId_Company(),
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).idBranch,
                                    settings.stablishment.getId(),
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).image,
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).name,
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getDistance(),
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getDurattion(),
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).price,
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).minimum,
                                    subtotalN,0,
                                    productCars
                            ));
                }
                Intent go = new Intent(productm.this, car.class);
                startActivity(go);
                productm.this.finish();
            }
        });


        buttonDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ingreConfirm==0){
                    if(additiConfirm==0){
                        next(showDrin,showObservatio);
                        actual=showObservatio;
                        YoYo.with(Techniques.Swing).duration(300).playOn(v);
                        Picasso.with(context).load(R.drawable.option_observations).centerInside().fit().into(img_banner_options);
                        YoYo.with(Techniques.FadeIn).duration(300).playOn(img_banner_options);
                        //Changes
                        showObservations.setVisibility(View.VISIBLE);
                        showObservatio.setVisibility(View.VISIBLE);
                        lbl_title_layout.setText("Observaciones");
                        Picasso.with(context).load(R.drawable.icon_observaciones_lleno).centerInside().fit().into(imageViewObsrv);
                        Picasso.with(context).load(R.drawable.icon_bebida_normal).centerInside().fit().into(imageViewDrink);
                        Picasso.with(context).load(R.drawable.icon_ingredientes_normal).centerInside().fit().into(imageViewIngredient);
                        Picasso.with(context).load(R.drawable.icon_addition_normal).centerInside().fit().into(imageViewAddition);
                    }
                    else{
                        next(showDrin, showAddition);
                        actual = showAddition;
                        actual = showAddition;

                       YoYo.with(Techniques.Swing).duration(300).playOn(v);
                        Picasso.with(context).load(R.drawable.option_additions).centerInside().fit().into(img_banner_options);
                        YoYo.with(Techniques.FadeIn).duration(300).playOn(img_banner_options);
                        //Changes
                        showadditions.setVisibility(View.VISIBLE);
                        showAddition.setVisibility(View.VISIBLE);
                        lbl_title_layout.setText("Adiciones");
                        Picasso.with(context).load(R.drawable.icon_adiciones_lleno).centerInside().fit().into(imageViewAddition);
                        Picasso.with(context).load(R.drawable.icon_bebida_normal).centerInside().fit().into(imageViewDrink);
                        Picasso.with(context).load(R.drawable.icon_ingredientes_normal).centerInside().fit().into(imageViewIngredient);
                        Picasso.with(context).load(R.drawable.icon_observaciones_normal).centerInside().fit().into(imageViewObsrv);
                    }

                }
                else{
                    next(showDrin, showIngredient);
                    actual=showIngredient;
                    YoYo.with(Techniques.Swing).duration(300).playOn(v);
                    Picasso.with(context).load(R.drawable.option_ingredients).centerInside().fit().into(img_banner_options);
                    YoYo.with(Techniques.FadeIn).duration(300).playOn(img_banner_options);
                    showIngredient.setVisibility(View.VISIBLE);
                    showIngredients.setVisibility(View.VISIBLE);
                    //Changes
                    lbl_title_layout.setText("Ingredientes");
                    Picasso.with(context).load(R.drawable.icon_ingredientes_lleno).centerInside().fit().into(imageViewIngredient);
                    Picasso.with(context).load(R.drawable.icon_bebida_normal).centerInside().fit().into(imageViewDrink);
                    Picasso.with(context).load(R.drawable.icon_addition_normal).centerInside().fit().into(imageViewAddition);
                    Picasso.with(context).load(R.drawable.icon_observaciones_normal).centerInside().fit().into(imageViewObsrv);
                }

            }
        });
        buttonIngre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer si = settings.subGlobalChecks.subGlobalCheckses.size();
                for (int i =0;i<si;i++){
                    Integer in =settings.subGlobalChecks.subGlobalCheckses.get(i).count;
                    if(in>0){
                        if(i==si-1){
                            productCars.set(daa,new productCar(
                                    settings.productCar.productCars.get(daa).getIdProduct(),
                                    settings.productCar.productCars.get(daa).getName(),
                                    settings.productCar.productCars.get(daa).getPrice(),
                                    settings.productCar.productCars.get(daa).getPicture(),
                                    settings.productCar.productCars.get(daa).getObser(),0,
                                    settings.productCar.productCars.get(daa).getAdditionCars(),
                                    tempIng,
                                    settings.productCar.productCars.get(daa).getDrinkCar()
                            ));
                            actualProduct = new productCar(
                                    settings.productCar.productCars.get(daa).getIdProduct(),
                                    settings.productCar.productCars.get(daa).getName(),
                                    settings.productCar.productCars.get(daa).getPrice(),
                                    settings.productCar.productCars.get(daa).getPicture(),
                                    settings.productCar.productCars.get(daa).getObser(),0,
                                    settings.productCar.productCars.get(daa).getAdditionCars(),
                                    tempIng,
                                    settings.productCar.productCars.get(daa).getDrinkCar()
                            );

                            settings.productCar.productCars.set(daa,new productCar(
                                            settings.productCar.productCars.get(daa).getIdProduct(),
                                            settings.productCar.productCars.get(daa).getName(),
                                            settings.productCar.productCars.get(daa).getPrice(),
                                            settings.productCar.productCars.get(daa).getPicture(),
                                            settings.productCar.productCars.get(daa).getObser(),0,
                                            settings.productCar.productCars.get(daa).getAdditionCars(),
                                            tempIng,
                                            settings.productCar.productCars.get(daa).getDrinkCar()
                                    )
                            );
                            if(additiConfirm==0){
                                next(showIngredient,showObservatio);
                                actual=showObservatio;
                                YoYo.with(Techniques.Swing).duration(300).playOn(v);
                                Picasso.with(context).load(R.drawable.option_observations).centerInside().fit().into(img_banner_options);
                                YoYo.with(Techniques.FadeIn).duration(300).playOn(img_banner_options);
                                //Changes
                                showObservatio.setVisibility(View.VISIBLE);
                                showObservations.setVisibility(View.VISIBLE);
                                lbl_title_layout.setText("Observaciones");
                                Picasso.with(context).load(R.drawable.icon_observaciones_lleno).centerInside().fit().into(imageViewObsrv);
                                Picasso.with(context).load(R.drawable.icon_bebida_normal).centerInside().fit().into(imageViewDrink);
                                Picasso.with(context).load(R.drawable.icon_ingredientes_normal).centerInside().fit().into(imageViewIngredient);
                                Picasso.with(context).load(R.drawable.icon_addition_normal).centerInside().fit().into(imageViewAddition);
                            }
                            else{
                                next(showIngredient,showAddition);
                                actual=showAddition;

                                YoYo.with(Techniques.Swing).duration(300).playOn(v);
                                Picasso.with(context).load(R.drawable.option_additions).centerInside().fit().into(img_banner_options);
                                YoYo.with(Techniques.FadeIn).duration(300).playOn(img_banner_options);
                                //Changes
                                showadditions.setVisibility(View.VISIBLE);
                                showAddition.setVisibility(View.VISIBLE);
                                lbl_title_layout.setText("Adiciones");
                                Picasso.with(context).load(R.drawable.icon_adiciones_lleno).centerInside().fit().into(imageViewAddition);
                                Picasso.with(context).load(R.drawable.icon_bebida_normal).centerInside().fit().into(imageViewDrink);
                                Picasso.with(context).load(R.drawable.icon_ingredientes_normal).centerInside().fit().into(imageViewIngredient);
                                Picasso.with(context).load(R.drawable.icon_observaciones_normal).centerInside().fit().into(imageViewObsrv);

                            }

                        }
                    }
                    else{
                        String mensajee ="Faltan ingredientes opcionales por escoger!";

                        Toast toast1 =
                                Toast.makeText(productm.this,
                                        mensajee, Toast.LENGTH_SHORT);

                        toast1.show();
                        break;
                    }
                }

            }
        });
        buttonAddi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                next(showAddition,showObservatio);
                for(int h =0; h<settings.productCar.productCars.size();h++){
                    ArrayList<additionCar> realAddition = new ArrayList<additionCar>();
                    Integer intAdditions = settings.addition.additions.size();
                    Integer totalPrice = settings.productCar.productCars.get(h).getPrice();
                    for(int j=0;j<intAdditions;j++){
                        Integer priceAdd = settings.addition.additions.get(j).getPrice();
                        Integer cantAdd = settings.addition.additions.get(j).getCantss();
                        Integer idAdd = settings.addition.additions.get(j).getId();
                        String nameAdd = settings.addition.additions.get(j).getName();
                        if (cantAdd>0){
                            Integer totalAdditionsP = cantAdd*priceAdd;
                            totalPrice = totalPrice+totalAdditionsP;
                            realAddition.add(new additionCar(
                                            idAdd, cantAdd, nameAdd, priceAdd
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
                            totalPrice, realAddition,
                            settings.productCar.productCars.get(h).getIngredientsCars(),
                            settings.productCar.productCars.get(h).getDrinkCar()
                    ));
                    productCars.set(h, new productCar(
                            settings.productCar.productCars.get(h).getIdProduct(),
                            settings.productCar.productCars.get(h).getName(),
                            settings.productCar.productCars.get(h).getPrice(),
                            settings.productCar.productCars.get(h).getPicture(),
                            settings.productCar.productCars.get(h).getObser(),
                            totalPrice, realAddition,
                            settings.productCar.productCars.get(h).getIngredientsCars(),
                            settings.productCar.productCars.get(h).getDrinkCar()
                    ));
                }
                actual=showObservatio;
                YoYo.with(Techniques.Swing).duration(300).playOn(v);
                Picasso.with(context).load(R.drawable.option_observations).centerInside().fit().into(img_banner_options);
                YoYo.with(Techniques.FadeIn).duration(300).playOn(img_banner_options);
                showObservations.setVisibility(View.VISIBLE);
                showObservatio.setVisibility(View.VISIBLE);
                //Changes
                lbl_title_layout.setText("Observaciones");
                Picasso.with(context).load(R.drawable.icon_observaciones_lleno).centerInside().fit().into(imageViewObsrv);
                Picasso.with(context).load(R.drawable.icon_bebida_normal).centerInside().fit().into(imageViewDrink);
                Picasso.with(context).load(R.drawable.icon_ingredientes_normal).centerInside().fit().into(imageViewIngredient);
                Picasso.with(context).load(R.drawable.icon_addition_normal).centerInside().fit().into(imageViewAddition);
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actualProduct.getIngredientsCars()== null){
                    ArrayList<ingredientsCar> in = new ArrayList<>();
                    actualProduct.setIngredientsCars(in);
                }
                if (actualProduct.getAdditionCars()==null){
                    ArrayList<additionCar> ad = new ArrayList<>();
                    actualProduct.setAdditionCars(ad);
                }
                if(actualProduct.getDrinkCar().id==null){
                    drinkCar d = new drinkCar(-1,"","","");
                    actualProduct.setDrinkCar(d);
                }
                productCars.add(actualProduct);
                Integer newNumber= 1+Integer.parseInt(number.getText().toString());
                number.setText(newNumber.toString());
                less.setEnabled(true);

            }
        });
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer newNumber= Integer.parseInt(number.getText().toString())-1;
                if(newNumber ==1){
                    less.setEnabled(false);
                }
                number.setText(newNumber.toString());

            }
        });

        ArrayList<additionCar> ad = new ArrayList<>();
        ArrayList<ingredientsCar> ic = new ArrayList<>();
        drinkCar dc = new drinkCar(-1,"","","");
        settings.productCar.productCars = new ArrayList<>();
        productCars.add(new productCar(

                settings.subtotal.getProductId(),
                settings.subtotal.getName(),
                settings.subtotal.getPrice(),
                settings.subtotal.getPicture(),
                "", 0, ad, ic,dc));
        actualProduct = new productCar(
                settings.subtotal.getProductId(),
                settings.subtotal.getName(),
                settings.subtotal.getPrice(),
                settings.subtotal.getPicture(),
                "", 0, ad, ic, dc
        );


        settings.productCar.productCars.add(new productCar(

                settings.subtotal.getProductId(),
                settings.subtotal.getName(),
                settings.subtotal.getPrice(),
                settings.subtotal.getPicture(),
                "",0, ad, ic, settings.drinkCar
        ));

        //clicksDetails();

        imageViewAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actual!=showAddition){
                    if(actual==showDrin || actual==showIngredient){
                        //next(actual,showAddition);


                    }
                    else{
                        prevous(actual,showAddition);
                        actual=showAddition;

                        YoYo.with(Techniques.Swing).duration(300).playOn(v);
                        Picasso.with(context).load(R.drawable.option_additions).centerInside().fit().into(img_banner_options);
                        YoYo.with(Techniques.FadeIn).duration(300).playOn(img_banner_options);
                        //Changes
                        lbl_title_layout.setText("Adiciones");
                        Picasso.with(context).load(R.drawable.icon_adiciones_lleno).centerInside().fit().into(imageViewAddition);
                        Picasso.with(context).load(R.drawable.icon_bebida_normal).centerInside().fit().into(imageViewDrink);
                        Picasso.with(context).load(R.drawable.icon_ingredientes_normal).centerInside().fit().into(imageViewIngredient);
                        Picasso.with(context).load(R.drawable.icon_observaciones_normal).centerInside().fit().into(imageViewObsrv);
                    }


                }

            }
        });
        imageViewIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(actual!=showIngredient){

                    if(actual==showDrin){
                        //next(actual,showIngredients);

                    }
                    else{
                        prevous(actual, showIngredient);
                        actual=showIngredient;
                        YoYo.with(Techniques.Swing).duration(300).playOn(v);
                        Picasso.with(context).load(R.drawable.option_ingredients).centerInside().fit().into(img_banner_options);
                        YoYo.with(Techniques.FadeIn).duration(300).playOn(img_banner_options);
                        //Changes
                        lbl_title_layout.setText("Ingredientes");
                        Picasso.with(context).load(R.drawable.icon_ingredientes_lleno).centerInside().fit().into(imageViewIngredient);
                        Picasso.with(context).load(R.drawable.icon_bebida_normal).centerInside().fit().into(imageViewDrink);
                        Picasso.with(context).load(R.drawable.icon_addition_normal).centerInside().fit().into(imageViewAddition);
                        Picasso.with(context).load(R.drawable.icon_observaciones_normal).centerInside().fit().into(imageViewObsrv);
                    }


                }

            }
        });
        imageViewObsrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    /*if(actual!=showObservatio){prevous(actual,showObservatio);

                        YoYo.with(Techniques.Swing).duration(300).playOn(v);
                        Picasso.with(context).load(R.drawable.option_observations).centerInside().fit().into(img_banner_options);
                        YoYo.with(Techniques.FadeIn).duration(300).playOn(img_banner_options);
                        //Changes
                        lbl_title_layout.setText("Observaciones");
                        Picasso.with(context).load(R.drawable.icon_observaciones_lleno).centerInside().fit().into(imageViewObsrv);
                        Picasso.with(context).load(R.drawable.icon_bebida_normal).centerInside().fit().into(imageViewDrink);
                        Picasso.with(context).load(R.drawable.icon_ingredientes_normal).centerInside().fit().into(imageViewIngredient);
                        Picasso.with(context).load(R.drawable.icon_addition_normal).centerInside().fit().into(imageViewAddition);
                    }

                actual=showObservatio;*/
            }
        });
        imageViewDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(actual!=showDrin){
                   next(actual,showDrin);
                actual=showDrin;
                   YoYo.with(Techniques.Swing).duration(300).playOn(v);
                   Picasso.with(context).load(R.drawable.option_drink).centerInside().fit().into(img_banner_options);
                   YoYo.with(Techniques.FadeIn).duration(300).playOn(img_banner_options);
                   //Changes
                   lbl_title_layout.setText("Bebidas");
                   Picasso.with(context).load(R.drawable.icon_bebida_lleno).centerInside().fit().into(imageViewDrink);
                   Picasso.with(context).load(R.drawable.icon_ingredientes_normal).centerInside().fit().into(imageViewIngredient);
                   Picasso.with(context).load(R.drawable.icon_addition_normal).centerInside().fit().into(imageViewAddition);
                   Picasso.with(context).load(R.drawable.icon_observaciones_normal).centerInside().fit().into(imageViewObsrv);


               }
            }
        });

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
        recicler = (LinearLayout) findViewById(R.id.recicler);
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
                                            settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getId_Company(),
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
                                    settings.stablishment.stablishments.get(settings.stablishment.getNumber()).getId_Company(),
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
                    recicler.removeView(childs[0]);
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
                    recicler.addView(childs[i]);

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
            recicler.addView(child);
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
            recicler.addView(child);

        }*//*



        if(settings.user.getaBoolean()==false){
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_stablishm_drawer);
        }
    */}

    @Override
    public void onBackPressed() {
        backAdver();
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

        android.support.v7.widget.AppCompatCheckBox[] checkAddition = new android.support.v7.widget.AppCompatCheckBox[additionSize];

        me.omidh.liquidradiobutton.LiquidRadioButton[] checks = new me.omidh.liquidradiobutton.LiquidRadioButton[ingredientSize];
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




        settings.ingredients.ingredientses = new ArrayList<>();

        if (settings.ingredients.ingredientses.size()>0){
            for (int k =0; k<settings.ingredients.ingredientses.size();k++){

                settings.ingredientsCar.ingredientsCars = new ArrayList<>();

                final View childIn = View.inflate(productm.this, R.layout.drink, null);
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
                for (int obli=0;obli<countIngreObli.size();obli++){

                }
                for (int norma=0;norma<countIngreNormal.size();norma++){


                }
                ArrayList<String>names = getNamesOptional();
                for(int namess=0;namess<names.size();namess++){
                    //add names categor
                    for(int opt=0;opt<countIngreOptio.size();opt++){

                        //add optionals
                    }
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

                final me.omidh.liquidradiobutton.LiquidRadioButton nuevo_checkbox = (me.omidh.liquidradiobutton.LiquidRadioButton) childIn.findViewById(R.id.appCompatCheckBox);

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
                    final View childa = View.inflate(productm.this, R.layout.drink, null);

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

                            final View child = View.inflate(productm.this, R.layout.drink, null);

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

                            final View child = View.inflate(productm.this, R.layout.drink, null);

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
            actual=showDrin;

            imageViewDrink.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_bebida_lleno));
            img_banner_options.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.option_drink));
            lbl_title_layout.setText("Bebidas");
            //putDrink();

            showadditions.setVisibility(View.GONE);
            showIngredients.setVisibility(View.GONE);
            showObservations.setVisibility(View.GONE);

        }
        else{
            imgDrink.setVisibility(View.GONE);
            showDrink.setVisibility(View.GONE);
            showDrin.setVisibility(View.GONE);
            drinkConfirm=0;
            if (settings.ingredients.ingredientses.size()> 0){
                imageViewIngredient.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_ingredientes_lleno));
                actual=showIngredient;
                img_banner_options.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.option_ingredients));
                lbl_title_layout.setText("Ingredientes");
                if (showIngredients.getVisibility()==View.GONE){showIngredients.setVisibility(View.VISIBLE);}
                showAddition.setVisibility(View.GONE);
                showObservatio.setVisibility(View.GONE);

            }
            else{
                ingreConfirm=0;
                imgIngredient.setVisibility(View.GONE);
                showIngredients.setVisibility(View.GONE);
                showIngredient.setVisibility(View.GONE);
                if (settings.addition.additions.size()>0){
                    imageViewAddition.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_adiciones_lleno));
                    img_banner_options.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.option_additions));
                    actual=showAddition;
                    lbl_title_layout.setText("Adiciones");
                    if (showadditions.getVisibility()==View.GONE){showadditions.setVisibility(View.VISIBLE);}
                    showObservatio.setVisibility(View.GONE);
                }
                else{
                    additiConfirm=0;
                    showAddition.setVisibility(View.GONE);
                    showadditions.setVisibility(View.GONE);
                    imageViewObsrv.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_observaciones_lleno));
                    actual=showObservatio;
                    showObservatio.setVisibility(View.VISIBLE);
                    showObservations.setVisibility(View.VISIBLE);
                    img_banner_options.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.option_observations));
                    lbl_title_layout.setText("Observaciones");

                    /*additiConfirm=0;
                    showAddition.setVisibility(View.GONE);
                    showadditions.setVisibility(View.GONE);
                    imgAddition.setVisibility(View.GONE);
                    if (showObservations.getVisibility()==View.GONE){showObservations.setVisibility(View.VISIBLE);}
                    img_banner_options.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.option_observations));
                    lbl_title_layout.setText("Observaciones");
                    actual=showObservatio;
                    showObservations.setVisibility(View.VISIBLE);
                    showObservatio.setVisibility(View.VISIBLE);

                    lbl_title_layout.setText("Observaciones");
                    Picasso.with(context).load(R.drawable.icon_observaciones_lleno).centerInside().fit().into(imageViewObsrv);
                    Picasso.with(context).load(R.drawable.icon_bebida_normal).centerInside().fit().into(imageViewDrink);
                    Picasso.with(context).load(R.drawable.icon_ingredientes_normal).centerInside().fit().into(imageViewIngredient);
                    Picasso.with(context).load(R.drawable.icon_addition_normal).centerInside().fit().into(imageViewAddition);
                    switch (integer){
                        case 1:
                            imageViewObsrv.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_observaciones_lleno));
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
                    }*/

                }

            }
        }

    }
    public void showExists (){
        Integer sizeIngre=settings.ingredients.ingredientses.size();
        Integer sizeAddi = settings.addition.additions.size();

        if (sizeIngre<1){
            imgIngredient.setVisibility(View.GONE);
            showIngredient.setVisibility(View.GONE);
           showIngredients.setVisibility(View.GONE);
            ingreConfirm=0;
        }
        if (sizeAddi<1){
            imgAddition.setVisibility(View.GONE);
            showAddition.setVisibility(View.GONE);
            additiConfirm=0;
            showadditions.setVisibility(View.GONE);
        }
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

    public void move (){
        countIngreObli = new ArrayList<>();
        countIngreNormal = new ArrayList<>();
        countIngreOptio = new ArrayList<>();
        ArrayList<ingredients> in = settings.ingredients.ingredientses;
        for(int i = 0;i<in.size();i++){
            ingredients inOnly = new ingredients(in.get(i).id, in.get(i).name, in.get(i).status, in.get(i).type,in.get(i).ingId, in.get(i).max,in.get(i).categor);
            switch (in.get(i).type){
                case 1:
                    countIngreObli.add(inOnly);
                    break;
                case 2:
                    countIngreNormal.add(inOnly);
                    break;
                case 3:
                    countIngreOptio.add(inOnly);
                    break;
            }
        }
    }

    public void sethomeToolbar (Toolbar toolbar){

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            toolbar.setContentInsetStartWithNavigation(0);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backAdver();
                }
            });
        }
    }

    public ArrayList<String>getNamesOptional(){
        ArrayList<String> names =new ArrayList<>();
        for (int opti=0;opti<countIngreOptio.size();opti++){

            String cate =countIngreOptio.get(opti).categor;
            if(opti==0){
                names.add(cate);
            }
            for(int nam=0;nam<names.size();nam++){
                if(!countIngreOptio.get(opti).categor.equals(cate)){
                    names.add(cate);
                }
            }
        }
        return names;
    }

    /*public void clicksDetails() {
        clickDrinks();
        clickIngredients();
        clickAdditions();
        clickObservations();
    }

    public void clickDrinks() {
        imageViewDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drinkMethod(view);
            }
        });
    }

    public void clickIngredients() {
         imageViewIngredient.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 YoYo.with(Techniques.Swing).duration(300).playOn(view);
                 Picasso.with(context).load(R.drawable.option_ingredients).centerInside().fit().into(img_banner_options);
                 YoYo.with(Techniques.FadeIn).duration(300).playOn(img_banner_options);
                 //Changes
                 lbl_title_layout.setText("Ingredientes");
                 Picasso.with(context).load(R.drawable.icon_ingredientes_lleno).centerInside().fit().into(imageViewIngredient);
                 Picasso.with(context).load(R.drawable.icon_bebida_normal).centerInside().fit().into(imageViewDrink);
                 Picasso.with(context).load(R.drawable.icon_addition_normal).centerInside().fit().into(imageViewAddition);
                 Picasso.with(context).load(R.drawable.icon_observaciones_normal).centerInside().fit().into(imageViewObsrv);
             }
         });
     }

     public void clickAdditions() {
         imageViewAddition.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 YoYo.with(Techniques.Swing).duration(300).playOn(view);
                 Picasso.with(context).load(R.drawable.option_additions).centerInside().fit().into(img_banner_options);
                 YoYo.with(Techniques.FadeIn).duration(300).playOn(img_banner_options);
                 //Changes
                 lbl_title_layout.setText("Adiciones");
                 Picasso.with(context).load(R.drawable.icon_adiciones_lleno).centerInside().fit().into(imageViewAddition);
                 Picasso.with(context).load(R.drawable.icon_bebida_normal).centerInside().fit().into(imageViewDrink);
                 Picasso.with(context).load(R.drawable.icon_ingredientes_normal).centerInside().fit().into(imageViewIngredient);
                 Picasso.with(context).load(R.drawable.icon_observaciones_normal).centerInside().fit().into(imageViewObsrv);
             }
         });
     }

     public void clickObservations() {
         imageViewObsrv.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 YoYo.with(Techniques.Swing).duration(300).playOn(view);
                 Picasso.with(context).load(R.drawable.option_observations).centerInside().fit().into(img_banner_options);
                 YoYo.with(Techniques.FadeIn).duration(300).playOn(img_banner_options);
                 //Changes
                 lbl_title_layout.setText("Observaciones");
                 Picasso.with(context).load(R.drawable.icon_observaciones_lleno).centerInside().fit().into(imageViewObsrv);
                 Picasso.with(context).load(R.drawable.icon_bebida_normal).centerInside().fit().into(imageViewDrink);
                 Picasso.with(context).load(R.drawable.icon_ingredientes_normal).centerInside().fit().into(imageViewIngredient);
                 Picasso.with(context).load(R.drawable.icon_addition_normal).centerInside().fit().into(imageViewAddition);
             }
         });
     }*/


    public void putDrink(){

        settings.allChecks.allCheckses.add(new allChecks(
                checksDrink2, checkAddition2, checks2
        ));
        final me.omidh.liquidradiobutton.LiquidRadioButton[] checksDrink = new me.omidh.liquidradiobutton.LiquidRadioButton[drinkSize];
        if (settings.drink.drinks.size()> 0){

            for(int x=0;x<settings.drink.drinks.size();x++){

                settings.drinkCar.drinkCars = new ArrayList<>();

                settings.drinkCar.setId(settings.drink.drinks.get(x).getId());
                settings.drinkCar.setName(settings.drink.drinks.get(x).getName());
                settings.drinkCar.setPicture(settings.drink.drinks.get(x).getPicture());
                settings.drinkCar.setLength(settings.drink.drinks.get(x).getLenght());

                final View childa = View.inflate(productm.this, R.layout.drink, null);

                TextView name = (TextView) childa.findViewById(R.id.text);
                ImageView picture = (ImageView) childa.findViewById(R.id.image);
                TextView length = (TextView) childa.findViewById(R.id.length);
                LinearLayout allAll = (LinearLayout) childa.findViewById(R.id.allAll);
                final me.omidh.liquidradiobutton.LiquidRadioButton nuevo_checkbox = (me.omidh.liquidradiobutton.LiquidRadioButton) childa.findViewById(R.id.appCompatCheckBox);
                nuevo_checkbox.setId(x);
                checksDrink[x] = nuevo_checkbox;
                checksDrink[x].setId(x);
                settings.allChecks.allCheckses.set(0, new allChecks(
                        checksDrink,
                        settings.allChecks.allCheckses.get(0).getCheckAddition(),
                        settings.allChecks.allCheckses.get(0).getChecks()));

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
                    productCars.set(daa ,new productCar(
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
                    ));
                    actualProduct = new productCar(settings.productCar.productCars.get(daa).getIdProduct(),
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
                            ));

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
                    nuevo_checkbox.setChecked(true);
                }
                else{
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
                showDrink.addView(childa);
            }
        }
    }
    public void putIngredients(){
        settings.ingredientsCar.ingredientsCars = new ArrayList<>();
        me.omidh.liquidradiobutton.LiquidRadioButton[] checks = new me.omidh.liquidradiobutton.LiquidRadioButton[ingredientSize];
        if (settings.ingredients.ingredientses.size()>0){
            settings.ingredientsCar.ingredientsCars = new ArrayList<>();
            final View childIn = View.inflate(productm.this, R.layout.ingradients, null);
            LinearLayout liObli =(LinearLayout) childIn.findViewById(R.id.liObli);
            LinearLayout liNor =(LinearLayout) childIn.findViewById(R.id.liIngredients);
            LinearLayout liOpt =(LinearLayout) childIn.findViewById(R.id.liOptional);
            for (int k =0; k<settings.ingredients.ingredientses.size();k++){


                if(settings.ingredients.ingredientses.get(k).type==1){
                    String t = settings.ingredients.ingredientses.get(k).name;
                    int id = settings.ingredients.ingredientses.get(k).id;
                    LiquidRadioButton radio = (LiquidRadioButton)View.inflate(context, R.layout.obliga, null);
                    radio.setText(t);
                    radio.setId(id);
                    tempIng.add(new ingredientsCar(id,t));

                    /*final View childOb = View.inflate(productm.this, R.layout.obliga, null);
                    TextView text = (TextView) childOb.findViewById(R.id.text);
                    text.setText(t);*/
                    liObli.addView(radio);
                }
                if (settings.ingredients.ingredientses.get(k).type==2){
                    final String t = settings.ingredients.ingredientses.get(k).name;
                    final int id = settings.ingredients.ingredientses.get(k).id;
                    final LiquidRadioButton radio = (LiquidRadioButton)View.inflate(context, R.layout.template_radio_button, null);
                    radio.setText(t);
                    radio.setId(otherIngre.size());
                    otherIngre.add(new check(0,0,0,0,radio));
                    radio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (otherIngre.get(radio.getId()).in==0){
                                radio.setChecked(true);
                                otherIngre.set(radio.getId(),new check(1,0,0,0,otherIngre.get(radio.getId()).liquidRadioButtons));
                                tempIng.add(new ingredientsCar(id,t));
                            }
                            else{
                                radio.setChecked(false);
                                otherIngre.set(radio.getId(),new check(0,0,0,0,otherIngre.get(radio.getId()).liquidRadioButtons));
                                removeNormal(id);
                                tempIng.remove(rN);
                            }
                        }
                    });

                    /*final View childOb = View.inflate(productm.this, R.layout.normal, null);
                    TextView text = (TextView) childOb.findViewById(R.id.text);
                    text.setText(t);*/
                    liNor.addView(radio);
                }

                    /*int id = settings.ingredients.ingredientses.get(k).id;
                    LiquidRadioButton radio = (LiquidRadioButton)View.inflate(context, R.layout.template_radio_button, null);
                    radio.setText(t);
                    radio.setId(id);

                    *//*final View childOb = View.inflate(productm.this, R.layout.optional, null);
                    TextView text = (TextView) childOb.findViewById(R.id.text);
                    text.setText(t);*//*
                    liOpt.addView(radio);
                }
*/


                //p.setVisibility(View.GONE);

            }

            showIngredients.addView(childIn);

            callOptionals(liOpt);
        }
    }
    public void removeNormal(Integer id){
        for(int i=0;i<tempIng.size();i++){
            int idTemp=tempIng.get(i).getId();
            if(idTemp==id){
                rN =i;
            }
        }
    }
    public void callOptionals(LinearLayout linear){
        settings.subGlobalChecks.subGlobalCheckses= new ArrayList<>();
        for (int i =0; i<settings.optionalIngredients.optionalIngredientses.size();i++){
            final View childIn = View.inflate(productm.this, R.layout.optional, null);
            String conti = "  Mínimo 1 máximo "+ settings.optionalIngredients.optionalIngredientses.get(i).cant.toString()+" ingrediente(s)";
            final Integer can = settings.optionalIngredients.optionalIngredientses.get(i).cant;
            settings.subGlobalChecks.subGlobalCheckses.add(new subGlobalChecks(i,0,can));
            final Integer[] coun = {settings.optionalIngredients.optionalIngredientses.get(i).coun};
            TextView text=(TextView)childIn.findViewById(R.id.text);
            LinearLayout layout_ingredients = (LinearLayout) childIn.findViewById(R.id.layout_ingredients);
            text.setText(settings.optionalIngredients.optionalIngredientses.get(i).name+conti);
            final ArrayList<check> checks= new ArrayList<>();
            final List<LiquidRadioButton> liquidRadioButtons= new ArrayList<>();
            for (int j =0; j<settings.optionalIngredients.optionalIngredientses.get(i).ingredientses.size();j++){
                final String t = settings.optionalIngredients.optionalIngredientses.get(i).ingredientses.get(j).name;
                final int id = settings.optionalIngredients.optionalIngredientses.get(i).ingredientses.get(j).id;
                final LiquidRadioButton radio = (LiquidRadioButton)View.inflate(context, R.layout.template_radio_button, null);

                radio.setText(t);
                radio.setId(j);

                layout_ingredients.addView(radio);
                checks.add(new check(0,j,can,0, radio));
                checksTemp.add(new check(0,j,can,0,radio));
                liquidRadioButtons.add(radio);

                final int finalJ = i;
                radio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checks.get(radio.getId()).in==0){
                            if(can> coun[0]){
                                settings.subGlobalChecks.subGlobalCheckses.set(finalJ,new subGlobalChecks(
                                        settings.subGlobalChecks.subGlobalCheckses.get(finalJ).id,
                                        settings.subGlobalChecks.subGlobalCheckses.get(finalJ).count+1,
                                        settings.subGlobalChecks.subGlobalCheckses.get(finalJ).cant
                                ));
                                radio.setChecked(true);
                                checksTemp.set(radio.getId(),new check(1,checks.get(radio.getId()).categor,checks.get(radio.getId()).cant,checks.get(radio.getId()).count+1,checks.get(radio.getId()).liquidRadioButtons));
                                checks.set(radio.getId(),new check(1,checks.get(radio.getId()).categor,checks.get(radio.getId()).cant,checks.get(radio.getId()).count+1,checks.get(radio.getId()).liquidRadioButtons));
                                coun[0] = coun[0] +1;
                                tempIng.add(new ingredientsCar(id,t));
                            }
                            else {
                                radio.setChecked(false);
                                String mensajee ="no puedes escoger mas ingredientes";

                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(),
                                                mensajee, Toast.LENGTH_SHORT);

                                toast1.show();


                            }


                        }
                        else {
                            settings.subGlobalChecks.subGlobalCheckses.set(finalJ,new subGlobalChecks(
                                    settings.subGlobalChecks.subGlobalCheckses.get(finalJ).id,
                                    settings.subGlobalChecks.subGlobalCheckses.get(finalJ).count-1,
                                    settings.subGlobalChecks.subGlobalCheckses.get(finalJ).cant
                            ));
                            radio.setChecked(false);
                            removeNormal(id);
                            Integer a=rN;
                            tempIng.remove(a);
                            checksTemp.set(radio.getId(),new check(0,checks.get(radio.getId()).categor,checks.get(radio.getId()).cant,checks.get(radio.getId()).count-1,checks.get(radio.getId()).liquidRadioButtons));
                            checks.set(radio.getId(),new check(0,checks.get(radio.getId()).categor,checks.get(radio.getId()).cant,checks.get(radio.getId()).count-1,checks.get(radio.getId()).liquidRadioButtons));
                            coun[0] = coun[0] -1;

                        }
                    }
                });

            }
            settings.optionalIngredients.optionalIngredientses.set(i, new optionalIngredients(settings.optionalIngredients.optionalIngredientses.get(i).id,settings.optionalIngredients.optionalIngredientses.get(i).name, settings.optionalIngredients.optionalIngredientses.get(i).cant,
                    settings.optionalIngredients.optionalIngredientses.get(i).coun, checks,settings.optionalIngredients.optionalIngredientses.get(i).ingredientses));
            linear.addView(childIn);

        }
    }

    /*TextView name = (TextView) childIn.findViewById(R.id.text);
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
                for (int obli=0;obli<countIngreObli.size();obli++){

                }
                for (int norma=0;norma<countIngreNormal.size();norma++){


                }
                ArrayList<String>names = getNamesOptional();
                for(int namess=0;namess<names.size();namess++){
                    //add names categor
                    for(int opt=0;opt<countIngreOptio.size();opt++){

                        //add optionals
                    }
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

                final me.omidh.liquidradiobutton.LiquidRadioButton nuevo_checkbox = (me.omidh.liquidradiobutton.LiquidRadioButton) childIn.findViewById(R.id.appCompatCheckBox);

                checks[k] = nuevo_checkbox;
                checks[k].setId(k);
                settings.allChecks.allCheckses.set(0, new allChecks(
                        settings.allChecks.allCheckses.get(0).getChecksDrink(),
                        settings.allChecks.allCheckses.get(0).getCheckAddition(),
                        checks));

                *//*if(settings.ingredients.ingredientses.get(k).obligatory == 1){
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
                }*//*

                allAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });*/
/*
                public void drinkMethod(View view){
                    //Changes
                    lbl_title_layout.setText("Bebidas");
                    Picasso.with(context).load(R.drawable.icon_bebida_lleno).centerInside().fit().into(imageViewDrink);
                    Picasso.with(context).load(R.drawable.icon_ingredientes_normal).centerInside().fit().into(imageViewIngredient);
                    Picasso.with(context).load(R.drawable.icon_addition_normal).centerInside().fit().into(imageViewAddition);
                    Picasso.with(context).load(R.drawable.icon_observaciones_normal).centerInside().fit().into(imageViewObsrv);

                  *//*  YoYo.with(Techniques.Swing).duration(300).playOn(view);
                    Picasso.with(context).load(R.drawable.option_drink).centerInside().fit().into(img_banner_options);
                    YoYo.with(Techniques.FadeIn).duration(300).playOn(img_banner_options);
*//*
                }*/

    public void backAdver(){


        ColorDialog dialog = new ColorDialog(context);
        dialog.setTitle("Deseas volver sin completar el pedido?");
        dialog.setTitleTextColor(ContextCompat.getColor(context, R.color.black));
        dialog.setColor(ContextCompat.getColor(context, R.color.white));
        dialog.setContentImage(ContextCompat.getDrawable(context, R.drawable.alert));
        dialog.setContentText("");
        dialog.setPositiveListener(getString(R.string.back), new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    dialog.onBackPressed();
                }*/
                finish();
            }
        }).setNegativeListener(getString(R.string.cancel), new ColorDialog.OnNegativeListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                dialog.dismiss();
            }
        }).show();
        settings.drink.drinks = new ArrayList<>();
    }

    public void prevous(LinearLayout gone, LinearLayout visible){
        /*YoYo.with(Techniques.FadeOutRight)
                .duration(500)
                .repeat(0)
                .playOn(now);

        YoYo.with(Techniques.FadeInRight)
                .duration(500)
                .repeat(0)
                .playOn(linTwo);*/
        visible.setVisibility(View.VISIBLE);
        gone.setVisibility(View.GONE);
    }
    public void next(LinearLayout gone, LinearLayout visible){
       /* YoYo.with(Techniques.FadeOutLeft)
                .duration(500)
                .repeat(0)
                .playOn(lin);
        YoYo.with(Techniques.FadeInLeft)
                .duration(500)
                .repeat(0)
                .playOn(linTwo);*/
       visible.setVisibility(View.VISIBLE);
        gone.setVisibility(View.GONE);
    }
    public void showAdditions (){
        android.support.v7.widget.AppCompatCheckBox[] checkAddition = new android.support.v7.widget.AppCompatCheckBox[additionSize];
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
                    settings.allChecks.allCheckses.set(0, new allChecks(
                            settings.allChecks.allCheckses.get(0).getChecksDrink(),
                            checkAddition,
                            settings.allChecks.allCheckses.get(0).getChecks()));

                    for (int ad = 0; ad < settings.addition.additions.size(); ad++) {

                        settings.additionCar.additionCars.add(new additionCar(
                                settings.addition.additions.get(ad).getId(),
                                settings.addition.additions.get(ad).getCantss(),
                                settings.addition.additions.get(ad).getName(),
                                settings.addition.additions.get(ad).getPrice()
                        ));
                    }
                    ArrayList<additionCar> atemp = new ArrayList<>();
                    productCars.set(daa,new productCar(
                            settings.productCar.productCars.get(daa).getIdProduct(),
                            settings.productCar.productCars.get(daa).getName(),
                            settings.productCar.productCars.get(daa).getPrice(),
                            settings.productCar.productCars.get(daa).getPicture(),
                            settings.productCar.productCars.get(daa).getObser(),0,
                            atemp,
                            settings.productCar.productCars.get(daa).getIngredientsCars(),
                            settings.productCar.productCars.get(daa).getDrinkCar()
                    ));
                    actualProduct = new productCar(settings.productCar.productCars.get(daa).getIdProduct(),
                            settings.productCar.productCars.get(daa).getName(),
                            settings.productCar.productCars.get(daa).getPrice(),
                            settings.productCar.productCars.get(daa).getPicture(),
                            settings.productCar.productCars.get(daa).getObser(),0,
                            atemp,
                            settings.productCar.productCars.get(daa).getIngredientsCars(),
                            settings.productCar.productCars.get(daa).getDrinkCar());

                    settings.productCar.productCars.set(daa,new productCar(
                                    settings.productCar.productCars.get(daa).getIdProduct(),
                                    settings.productCar.productCars.get(daa).getName(),
                                    settings.productCar.productCars.get(daa).getPrice(),
                                    settings.productCar.productCars.get(daa).getPicture(),
                                    settings.productCar.productCars.get(daa).getObser(),0,
                                    atemp,
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


                    showadditions.addView(childAdd);
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
                                        settings.subtotal.setFinalPrice(multi);
                                    }
                                }

                            }
                        }
                    });

                }
            }
    }

    public void query (Integer complement, Integer productId, Integer edi){
        settings.addition.additions= new ArrayList<>();
        settings.ingredients.ingredientses= new ArrayList<>();

        //drinks

        final ProgressDialog dialog = ProgressDialog.show(productm.this, "",
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
            //Cerrar loadding
            test = settings.optionalIngredients.optionalIngredientses.size();
            showIngredients = (LinearLayout) findViewById(R.id.showIngredients);
            showObservations = (LinearLayout) findViewById(R.id.showObservation);
            ingredientSize=settings.ingredients.ingredientses.size();
            additionSize = settings.addition.additions.size();
            drinkSize = settings.drink.drinks.size();
            checksDrink2 = new me.omidh.liquidradiobutton.LiquidRadioButton[drinkSize];
            checkAddition2 = new android.support.v7.widget.AppCompatCheckBox[additionSize];
            checks2 = new me.omidh.liquidradiobutton.LiquidRadioButton[ingredientSize];
            confirmeFirstImage(settings.stablishment.getId());
            daa=settings.productCar.productCars.size()-1;
            putDrink();
            putIngredients();
            showAdditions();
            showExists();


            move();
            progress.setVisibility(View.GONE);
            showCom.setVisibility(View.VISIBLE);
        }
    }

    public void firstDrink (final String url, final Integer drinkE, final Integer edi, final Integer productId) throws Exception{

        if (drinkE!=0) {


            final RequestQueue queue = Volley.newRequestQueue(this, new HurlStack(
                    null, CustomSSLSocketFactory.getSSLSocketFactory(productm.this)));

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
            jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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
                null, CustomSSLSocketFactory.getSSLSocketFactory(productm.this)));

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
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsonArrayRequest);


    }
    public void ingredients (final String url, final  Integer productId) throws Exception{


        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(productm.this)));

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
                                organizeCategor();
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
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsonObjectRequest);


    }
    public void aditions (final String url, final Integer productId) throws Exception{

        final RequestQueue queue = Volley.newRequestQueue(this,new HurlStack(
                null, CustomSSLSocketFactory.getSSLSocketFactory(productm.this)));

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
                            //cerrar dialog

                            confirmCant();

                            test = settings.optionalIngredients.optionalIngredientses.size();
                            showIngredients = (LinearLayout) findViewById(R.id.showIngredients);
                            showObservations = (LinearLayout) findViewById(R.id.showObservation);
                            ingredientSize=settings.ingredients.ingredientses.size();
                            additionSize = settings.addition.additions.size();
                            drinkSize = settings.drink.drinks.size();
                            checksDrink2 = new me.omidh.liquidradiobutton.LiquidRadioButton[drinkSize];
                            checkAddition2 = new android.support.v7.widget.AppCompatCheckBox[additionSize];
                            checks2 = new me.omidh.liquidradiobutton.LiquidRadioButton[ingredientSize];
                            confirmeFirstImage(settings.stablishment.getId());
                            daa=settings.productCar.productCars.size()-1;
                            putDrink();
                            putIngredients();
                            showAdditions();
                            showExists();


                            move();
                            progress.setVisibility(View.GONE);
                            showCom.setVisibility(View.VISIBLE);

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
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsonArrayRequest);


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
        ArrayList<ingredients> ingr= new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<check> liquidRadioButtons = new ArrayList<>();
        temporal = new ArrayList<>();

        settings.optionalIngredients.optionalIngredientses = new ArrayList<>();
        for (int i =0;i<settings.ingredients.ingredientses.size();i++){

            Integer id = settings.ingredients.ingredientses.get(i).id;
            String category = settings.ingredients.ingredientses.get(i).categor;
            Integer cant = settings.ingredients.ingredientses.get(i).max;
            if(!category.equals("none")&&names.size()==0){
                names.add(category);
                settings.optionalIngredients.optionalIngredientses.add(new optionalIngredients(id,category,cant,0,liquidRadioButtons, ingr));
            }

            if(names.size()>0){
                if(!names.get(names.size()-1).equals(category)&&!category.equals("none")){
                    names.add(category);
                    settings.optionalIngredients.optionalIngredientses.add(new optionalIngredients(id,category,cant,0,liquidRadioButtons, ingr));
                }
            }


        }
        minim();



    }
    public void organizeIngredients(){
        ArrayList<ingredients> finalIngre=new ArrayList<>();
        ArrayList<check> liquidRadioButtons= new ArrayList<>();
        for (int j =0; j<settings.optionalIngredients.optionalIngredientses.size();j++){
            Integer id = settings.optionalIngredients.optionalIngredientses.get(j).id;
            String name = settings.optionalIngredients.optionalIngredientses.get(j).name;
            Integer cant = settings.optionalIngredients.optionalIngredientses.get(j).cant;
            ArrayList<ingredients> temp = new ArrayList<>();
            for (int i =0;i<temporal.size();i++){
                String nameTwo=temporal.get(i).categor;
                if(name.equals(nameTwo)){
                    ingredients ingredien= new ingredients(temporal.get(i).id, temporal.get(i).name, temporal.get(i).status, temporal.get(i).type,temporal.get(i).ingId,temporal.get(i).max,temporal.get(i).categor);
                    temp.add(ingredien);
                }
            }
            settings.optionalIngredients.optionalIngredientses.set(j,new optionalIngredients(id,name,cant,0,liquidRadioButtons,temp));


        }
    }
    public void minim (){

        for (int i =0;i<settings.ingredients.ingredientses.size();i++){
            ArrayList<ingredients>te=settings.ingredients.ingredientses;
            ingredients ingredien= new ingredients(te.get(i).id, te.get(i).name, te.get(i).status, te.get(i).type,te.get(i).ingId,te.get(i).max,te.get(i).categor);
            if(settings.ingredients.ingredientses.get(i).categor!="none"){

                temporal.add(ingredien);
            }
        }
        organizeIngredients();
    }
    public void showComponents(){}
}

