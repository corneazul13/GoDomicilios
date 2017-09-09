package godomicilios.mdcc.pulpapp.settings;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by PROGRAMACION5 on 10/04/2017.
 */

public class newAddress {

    public double a,b;
    private Integer userId;
    private String city;
    private double latitude;
    private double longitude;
    private String address;
    private String addressAdd;
    private String type;
    private String icon;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        if (settings.user.getId()!=null){
            this.userId = settings.user.getId();
        }
    }

    public String getCity() {
        return Uri.encode(this.city);
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address.replace(" ", "%20");
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressAdd() {
        return addressAdd;
    }

    public void setAddressAdd(String addressAdd) {
        this.addressAdd = addressAdd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void getLocation (String url, Context context){
        final RequestQueue queue = Volley.newRequestQueue(context);


        final JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(JsonObjectRequest.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(JSONObject response) {
                        try{

                            final JSONArray product = (JSONArray) response.getJSONArray("results");


                            for(int i =0; i<product.length();i++){
                                final JSONObject one = (JSONObject) product.getJSONObject(i);
                                if(a ==0.0){
                                    for (int j =0;j<one.length();j++){
                                        final JSONObject geometry = (JSONObject) one.getJSONObject("geometry");
                                        if(a == 0.0){
                                            for(int k =0; k<geometry.length();k++){
                                                final JSONObject location = (JSONObject) geometry.getJSONObject("location");
                                                if(a == 0.0){
                                                    for(int l =0;l<location.length();l++){
                                                        a =location.getDouble("lat");
                                                        b = location.getDouble("lng");
                                                        l = location.length();
                                                        break;

                                                    }
                                                }
                                                else{
                                                    k=geometry.length();
                                                }

                                            }
                                        }
                                        else{
                                            j=one.length();
                                        }

                                    }
                                }
                                else{
                                    i=product.length();
                                }


                            }




                        }
                        catch (Exception e){

                            Exception b=e;
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyError a = error;
            }
        }
        );
        queue.add(jsonObjectRequest);

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
