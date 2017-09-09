    package godomicilios.mdcc.pulpapp.settings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by PROGRAMACION5 on 04/07/2017.
 */

public class canvas extends View {

    ImageView imageBitmap;

    public canvas(Context context) {
        super(context);
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, boolean square) {

        int width = 0;
        int height = 0;

        if(square){
            if(bitmap.getWidth() < bitmap.getHeight()){
                width = 300;
                height = 300;
            } else {
                width = 300;
                height = 300;
            }
        } else {
            height = bitmap.getHeight();
            width = bitmap.getWidth();
        }

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, height);
        final RectF rectF = new RectF(rect);
        final float roundPx = 90;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(150, 150, 150, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
    public static Bitmap getRoundedCorner( boolean square) {
        int width = 0;
        int height = 0;


        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, height);
        final RectF rectF = new RectF(rect);
        final float roundPx = 90;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(180, 180, 180, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(null, rect, rect, paint);

        return output;
    }


    @Override

    protected void onDraw(Canvas canvas) {
        Paint pincel = new Paint();
        pincel.setStrokeWidth(5);
        pincel.setStyle(Paint.Style.FILL_AND_STROKE);


        canvas.drawCircle(180, 150, 100, pincel);

    }



}