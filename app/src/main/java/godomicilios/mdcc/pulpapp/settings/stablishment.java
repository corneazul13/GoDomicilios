package godomicilios.mdcc.pulpapp.settings;
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
    private Integer id_Company;
    private Integer stars;
    private String productRank;
    public ArrayList<stablishment> stablishments;

    public stablishment(Integer id, int number, Integer idBranch, String name, String address, String price,
                        String status, String image, String nameBranch, String minimum, String distance,
                        String duration, Integer id_Company, Integer stars, String productRank){
        this.setId(id);
        this.number = number;
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
        this.setId_Company(id_Company);
        this.setStars(stars);
        this.setProductRank(productRank);

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

    public Integer getId_Company() {
        return id_Company;
    }

    public void setId_Company(Integer id_Company) {
        this.id_Company = id_Company;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getProductRank() {
        return productRank;
    }

    public void setProductRank(String productRank) {
        this.productRank = productRank;
    }

    @Override
    public String toString() {
        return "stablishment{" +
                "id=" + id +
                ", number=" + number +
                ", idBranch=" + idBranch +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", price='" + price + '\'' +
                ", status='" + status + '\'' +
                ", image='" + image + '\'' +
                ", nameBranch='" + nameBranch + '\'' +
                ", minimum='" + minimum + '\'' +
                ", distance='" + distance + '\'' +
                ", durattion='" + durattion + '\'' +
                ", id_Company=" + id_Company +
                ", stars=" + stars +
                ", productRank='" + productRank + '\'' +
                ", stablishments=" + stablishments +
                '}';
    }
}
