package godomicilios.mdcc.godomiciliosc.settings;

import android.app.ProgressDialog;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Date;

/**
 * Created by PROGRAMACION5 on 24/03/2017.
 */

public class user {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String user;
    private String password;
    private Date date;
    private String addressWork;
    private Boolean aBoolean=false;
    private GoogleApiClient googleApiClient;
    private String URL ="https://godomicilios.co/webService/servicios.php?";
    public static GoogleAnalytics analytics;
    private ProgressDialog progressDialog;
    private String carCant;

    public static Tracker tracker;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddressWork() {
        return addressWork;
    }

    public void setAddressWork(String addressWork) {
        this.addressWork = addressWork;
    }

    public GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }
    public void logouts(){
        settings.user =new user();
        settings.address = new address();
        settings.stablishment = new stablishment();
        settings.rank =new rank();
        settings.product = new product();
        settings.spinner = new spinner();
        settings.newAddress = new newAddress();
        settings.update = new update();
        settings.subtotal = new subtotal();
        settings.drink = new drink();
        settings.elementsToProducts = new elementsToProducts();
        settings.ingredients = new ingredients();
        settings.addition = new addition();
        settings.coupon = new coupon();
        settings.shoppingCar= new shoppingCar();
        settings.order = new order();
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public String getCarCant() {
        Integer num=0;

        if(settings.shoppingCar.carFinal.size()>=1){
            for (int bb=0; bb<settings.shoppingCar.carFinal.size();bb++){
                num= num + settings.shoppingCar.carFinal.get(bb).getProductCars().size();
            }
        }

        return num.toString();
    }

    public void setCarCant(String carCant) {
        this.carCant = carCant;
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public void setProgressDialog(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }
}


