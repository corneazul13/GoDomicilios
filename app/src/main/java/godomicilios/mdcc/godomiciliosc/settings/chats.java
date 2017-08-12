package godomicilios.mdcc.godomiciliosc.settings;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 09/08/2017.
 */

public class chats {
    private String message;
    private String person;
    public ArrayList<godomicilios.mdcc.godomiciliosc.settings.chats> chatses;
    public chats(String message, String person){
        this.setMessage(message);
        this.setPerson(person);
    }
    public chats(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
