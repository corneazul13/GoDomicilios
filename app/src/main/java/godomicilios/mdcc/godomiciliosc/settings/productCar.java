package godomicilios.mdcc.godomiciliosc.settings;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 25/05/2017.
 */

public class productCar {

    private Integer idProduct;
    private String name;
    private Integer price;
    private String picture;
    private String obser;
    private Integer total;
    public ArrayList<additionCar> additionCars;
    public ArrayList<ingredientsCar> ingredientsCars;
    private drinkCar drinkCar;
    public ArrayList<productCar> productCars;

    public productCar (Integer idProduct, String name, Integer price, String picture,
                       String observ,Integer total, ArrayList<additionCar> additionCars,
                       ArrayList<ingredientsCar> ingredientsCars, drinkCar drinkCar){

        this.setIdProduct(idProduct);
        this.setName(name);
        this.setPrice(price);
        this.setPicture(picture);
        this.setObser(observ);
        this.setTotal(total);
        this.setAdditionCars(additionCars);
        this.setIngredientsCars(ingredientsCars);
        this.setDrinkCar(drinkCar);

    }
    public productCar(){}

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getObser() {
        return obser;
    }

    public void setObser(String obser) {
        this.obser = obser;
    }

    public ArrayList<additionCar> getAdditionCars() {
        return additionCars;
    }

    public void setAdditionCars(ArrayList<additionCar> additionCars) {
        this.additionCars = additionCars;
    }

    public ArrayList<ingredientsCar> getIngredientsCars() {
        return ingredientsCars;
    }

    public void setIngredientsCars(ArrayList<ingredientsCar> ingredientsCars) {
        this.ingredientsCars = ingredientsCars;
    }

    public ArrayList<productCar> getProductCars() {
        return productCars;
    }

    public void setProductCars(ArrayList<productCar> productCars) {
        this.productCars = productCars;
    }

    public godomicilios.mdcc.godomiciliosc.settings.drinkCar getDrinkCar() {
        return drinkCar;
    }

    public void setDrinkCar(godomicilios.mdcc.godomiciliosc.settings.drinkCar drinkCar) {
        this.drinkCar = drinkCar;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
