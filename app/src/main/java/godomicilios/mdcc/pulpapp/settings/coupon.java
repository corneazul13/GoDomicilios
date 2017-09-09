package godomicilios.mdcc.pulpapp.settings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 09/05/2017.
 */

public class coupon {
    private Integer id;
    private String image;
    private String price;
    private Context context;
    private ImageView imageView;
    private LinearLayout linearLayout;
    private Bitmap[] bitmaps;
    public ArrayList<coupon> coupons;

    public coupon(Integer id, String image,
                  String price
//ImageView imageView, LinearLayout linearLayout
){
        this.setId(id);
        this.setImage(image);
        this.setPrice(price);
        /*this.setImageView(imageView);
        this.setLinearLayout(linearLayout);
*/
    }
    public coupon(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public Bitmap[] getImageView(Context c) {

        for(int i=0; i< coupons.size();i++){

            final ImageView an = new ImageView(c);
            new settings.DownloadImageTask(an)
                    .execute("https://godomicilios.co/"+
                            coupons.get(i).image);
            BitmapDrawable drawable = (BitmapDrawable) an.getDrawable();
            Bitmap bitmap = drawable.getBitmap();


            bitmaps[i]=bitmap;


        }
        return bitmaps;
    }

    public void setImageView(Bitmap[] image) {
        this.bitmaps = image;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    public void allCoupons (){
        for (int i =0;i<coupons.size(); i++){

            ImageView an = coupons.get(i).getImageView();

            new settings.DownloadImageTask(an)
                    .execute("https://godomicilios.co/"+
                            coupons.get(i).image);
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }
}
