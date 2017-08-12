package godomicilios.mdcc.godomiciliosc.settings;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 04/04/2017.
 */

public class rank {
    private Integer id;
    private Integer number;
    private Integer idCompany;
    private String name;
    private Integer status;
    private Integer idStablishment;
    private Integer select;
    private Integer click;
    public ArrayList <rank> ranks;

    public rank(Integer id, Integer idCompany, String name, Integer status, Integer select
                ){
        this.setId(id);
        this.setIdCompany(idCompany);
        this.setName(name);
        this.setStatus(status);
        this.setSelect(select);

    }

    public rank(){}


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getIdStablishment() {
        return idStablishment;
    }

    public void setIdStablishment(Integer idStablishment) {
        this.idStablishment = idStablishment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSelect() {
        return select;
    }

    public void setSelect(Integer select) {
        this.select = select;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

}
