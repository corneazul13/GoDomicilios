package godomicilios.mdcc.godomiciliosc.settings;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 06/07/2017.
 */

public class temporalCar {
    private Integer idStablish;
    private Integer idClick;
    private Integer coupon;
    private String method;
    private Integer subtotal;
    private Integer domiPrice;
    public ArrayList<temporalCar>temporalCars;

    public temporalCar (Integer idStablish, Integer idClick,Integer coupon, String method, Integer subtotal, Integer domiPrice){
        this.setIdStablish(idStablish);
        this.setIdClick(idClick);
        this.setCoupon(coupon);
        this.setMethod(method);
        this.setSubtotal(subtotal);
        this.setDomiPrice(domiPrice);
    }
    public  temporalCar(){}

    public Integer getCoupon() {
        return coupon;
    }

    public void setCoupon(Integer coupon) {
        this.coupon = coupon;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getDomiPrice() {
        return domiPrice;
    }

    public void setDomiPrice(Integer domiPrice) {
        this.domiPrice = domiPrice;
    }

    public Integer getIdStablish() {
        return idStablish;
    }

    public void setIdStablish(Integer idStablish) {
        this.idStablish = idStablish;
    }

    public Integer getIdClick() {
        return idClick;
    }

    public void setIdClick(Integer idClick) {
        this.idClick = idClick;
    }
}
