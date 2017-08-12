package godomicilios.mdcc.godomiciliosc.settings;

/**
 * Created by PROGRAMACION5 on 28/03/2017.
 */

public class order {
    private double latitude;
    private double longitude;
    private String address;
    private Integer reloadActivity=0;

    public Integer getReloadActivity() {
        return reloadActivity;
    }

    public void setReloadActivity(Integer reloadActivity) {
        this.reloadActivity = reloadActivity;
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
}
