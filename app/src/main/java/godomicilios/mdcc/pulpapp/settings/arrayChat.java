package godomicilios.mdcc.pulpapp.settings;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 26/08/2017.
 */

public class arrayChat {
    private Integer idSuc;
    private Integer idChat;
    public ArrayList<arrayChat> arrayChats;
    public arrayChat(Integer idSuc, Integer idChat){
        this.setIdSuc(idSuc);
        this.setIdChat(idChat);
    }
    public arrayChat(){}

    public Integer getIdSuc() {
        return idSuc;
    }

    public void setIdSuc(Integer idSuc) {
        this.idSuc = idSuc;
    }

    public Integer getIdChat() {
        return idChat;
    }

    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }
}
