package godomicilios.mdcc.godomiciliosc.settings;
import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;



public class stablishment {
    private Integer id;
    private Integer number;
    public Integer idBranch;
    public String name;
    public String address;
    public String price;
    public String status;
    public String image;
    public String nameBranch;
    public String minimum;
    private String distance;
    private String durattion;
    private Integer id_Stablish;
    private Integer stars;
    public ArrayList<stablishment> stablishments;

    public stablishment(Integer id, Integer idBranch, String name, String address, String price,
                   String status, String image, String nameBranch, String minimum, String distance,
                        String duration, Integer id_Stablish, Integer stars){
        this.setId(id);
        this.idBranch = idBranch;
        this.name = name;
        this.address = address;
        this.price = price;
        this.status = status;
        this.image = image;
        this.nameBranch = nameBranch;
        this.minimum = minimum;
        this.setDistance(distance);
        this.setDurattion(duration);
        this.setId_Stablish(id_Stablish);
        this.setStars(stars);

    }
    public stablishment(){}

    public Drawable LoadImageFromWebOperations(String strPhotoUrl) {
        try {
            InputStream is = (InputStream) new URL(strPhotoUrl).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            System.out.println("Exc=" + e);
            return null;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDurattion() {
        return durattion;
    }

    public void setDurattion(String durattion) {
        this.durattion = durattion;
    }

    public Integer getId_Stablish() {
        return id_Stablish;
    }

    public void setId_Stablish(Integer id_Stablish) {
        this.id_Stablish = id_Stablish;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
}
