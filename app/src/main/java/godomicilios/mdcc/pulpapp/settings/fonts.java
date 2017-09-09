package godomicilios.mdcc.pulpapp.settings;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by PROGRAMACION5 on 23/03/2017.
 */

public class fonts {
    public static Typeface montserrat (Context context){
        return Typeface.createFromAsset(context.getAssets(),"fonts/MontserratBold.ttf");
    }
}
