package godomicilios.mdcc.godomiciliosc.settings;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 25/05/2017.
 */

public class StablishmentCar {
    public Integer id;
    private Integer idBranch;
    private Integer idColor;
    public String img;
    public String name;
    public String distance;
    public String duration;
    private String price;
    private String minimum;
    private Integer subtotal;
    private Integer idOrder;
    public ArrayList<productCar> productCars;
    public ArrayList<StablishmentCar> stablishmentCars;
    public StablishmentCar(Integer id,Integer idBranch, Integer idColor, String img, String name, String distance,
                           String duration, String price, String minimum, Integer subtotal,Integer idOrder,ArrayList<productCar> productCars){

        this.setId(id);
        this.setIdBranch(idBranch);
        this.setIdColor(idColor);
        this.setImg(img);
        this.setName(name);
        this.setDistance(distance);
        this.setDuration(duration);
        this.setPrice(price);
        this.setProductCars(productCars);
        this.setMinimum(minimum);
        this.setSubtotal(subtotal);
        this.setIdOrder(idOrder);
    }

    public  StablishmentCar (){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public ArrayList<productCar> getProductCars() {
        return productCars;
    }

    public void setProductCars(ArrayList<productCar> productCars) {
        this.productCars = productCars;
    }

    public ArrayList<StablishmentCar> getStablishmentCars() {
        return stablishmentCars;
    }

    public void setStablishmentCars(ArrayList<StablishmentCar> stablishmentCars) {
        this.stablishmentCars = stablishmentCars;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public Integer getIdColor() {
        return idColor;
    }

    public void setIdColor(Integer idColor) {
        this.idColor = idColor;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price ="$ "+ price;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(Integer idBranch) {
        this.idBranch = idBranch;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }
}
