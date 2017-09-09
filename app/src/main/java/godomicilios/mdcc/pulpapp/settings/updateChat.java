package godomicilios.mdcc.pulpapp.settings;

/**
 * Created by PROGRAMACION5 on 25/08/2017.
 */

public class updateChat {
    private boolean confirm;
    private int onClass;
    public updateChat(boolean confirm, int onClass){
        this.confirm=confirm;
        this.onClass=onClass;
    }

    public boolean isConfirm() {
        return confirm;
    }



    public int getOnClass() {
        return onClass;
    }


}
