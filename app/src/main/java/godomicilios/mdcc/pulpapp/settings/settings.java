package godomicilios.mdcc.pulpapp.settings;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 24/03/2017.
 */

public class settings {
    public  static user user = new user();
    public static address address = new address();
    public static order order = new order();
    public static stablishment stablishment = new stablishment();
    public static rank rank = new rank();
    public  static product product = new product();
    public static spinner spinner = new spinner();
    public static newAddress newAddress  = new newAddress();
    public static update update = new update();
    public static subtotal subtotal = new subtotal();
    public static drink drink = new drink();
    public static elementsToProducts elementsToProducts = new elementsToProducts();
    public static ingredients ingredients =new ingredients();
    public static addition addition = new addition();
    public  static coupon coupon = new coupon();
    public static shoppingCar shoppingCar = new shoppingCar();
    public static additionCar additionCar = new additionCar();
    public  static ingredientsCar ingredientsCar = new ingredientsCar();
    public static productCar productCar = new productCar();
    public static StablishmentCar stablishmentCar = new StablishmentCar();
    public static drinkCar drinkCar = new drinkCar();
    public static ArrayList<String> categories = new ArrayList<>();
    public static methodPay methodPay = new methodPay();
    public static couponActivate couponActivate = new couponActivate();
    public static temporalCar temporalCar = new temporalCar();
    public static allChecks allChecks = new allChecks();
    public static  answerOrder answerOrder = new answerOrder();
    public static chats chats = new chats();
    public static optionalIngredients optionalIngredients = new optionalIngredients();
    public static check check= new check();
    public static subGlobalChecks subGlobalChecks = new subGlobalChecks();
    public static arrayChat arrayChat = new arrayChat();

    public static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    /*@SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }*/



}
