package com.example.catchtheplastics;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Backround {
    int x = 0, y = 0;

    Bitmap backround;
    Backround(int screenX, int screenY, Resources res){

        backround = BitmapFactory.decodeResource(res, R.drawable.backround);
        backround = Bitmap.createScaledBitmap(backround, screenX, screenY, false);

    }
}
