package godomicilios.mdcc.pulpapp.settings;

import java.util.AbstractList;

/**
 * Created by PROGRAMACION5 on 30/06/2017.
 */

public class methodPay {
    private Integer id;
    private String name;
    private Integer status;
    private String image;
    public AbstractList<methodPay> methodPays;

    public methodPay(Integer id, String name, Integer status, String image){
        this.setId(id);
        this.setName(name);
        this.setStatus(status);
        this.setImage(image);
    }
    public methodPay(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
