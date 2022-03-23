package com.example.catchtheplastics;

import static com.example.catchtheplastics.GameView.screenRatioX;
import static com.example.catchtheplastics.GameView.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Swime {
    public boolean isGoingUp = false;
    int x,y, width, height, wingCounter = 0;
    Bitmap swime1, swime2;


    Swime (int screenY, Resources res){
        swime1 = BitmapFactory.decodeResource(res, R.drawable.vehicle1);
        swime2 = BitmapFactory.decodeResource(res, R.drawable.vehicle2);

        width = swime1.getWidth();
        height = swime1.getHeight();

        width /= 4;
        height /=4;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        swime1 = Bitmap.createScaledBitmap(swime1, width, height, false);
        swime2 = Bitmap.createScaledBitmap(swime2, width, height, false);

        y = screenY / 2;
        x = (int) (64 * screenRatioX);


    }



    //Bu kısmı anlamadım.
    Bitmap getSwime () {

        if (wingCounter == 0) {
            wingCounter++;
            return swime1;
        }
        wingCounter--;

        return swime2;
    }

}
