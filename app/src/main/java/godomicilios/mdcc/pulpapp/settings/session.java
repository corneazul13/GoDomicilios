package godomicilios.mdcc.pulpapp.settings;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by PROGRAMACION5 on 29/06/2017.
 */

public class session {

    private Integer id;
    private String user;
    private String pass;
    private Integer status;

    public session (Integer id, String user, String pass, Integer status){
        this.setId(id);
        this.setUser(user);
        this.setPass(pass);
        this.setStatus(status);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
