package godomicilios.mdcc.pulpapp.settings;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 27/03/2017.
 */

public class address {
    private Integer id;
    private String city;
    private String saveAddress;
    private double latitude;
    private double longitude;
    private String address;
    private String aditional;
    private String type;
    public ArrayList<address> addresses;

    public address(Integer id, String city, double latitude, double longitude, String address,
                   String aditional, String type){
        this.setId(id);
        this.setCity(city);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setAddress(address);
        this.setAditional(aditional);
        this.setType(type);

    }
    public address(){}

    public String getSaveAddress() {
        return saveAddress;
    }

    public void setSaveAddress(String saveAddress) {
        this.saveAddress = saveAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAditional() {
        return aditional;
    }

    public void setAditional(String aditional) {
        this.aditional = aditional;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
