package com.example.catchtheplastics;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;


public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isplaying;
    private int screenX, screenY;
    public static float screenRatioX, screenRatioY;
    private Paint paint;
    private Swime swime;
    private Backround backround1, backround2;


    public GameView(Context context, int screenX, int screenY){
        super (context);
        this.screenX = screenX;
        this.screenY = screenY;
        //Burası anlaşılacak.
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;
        backround1 = new Backround(screenX, screenY, getResources());
        backround2 = new Backround(screenX, screenY, getResources());

        swime = new Swime(screenY, getResources());

        backround2.x = screenX;
        paint = new Paint();
    }
    @Override
    public void run() {
        //Şimdi run döngüsüne işlevsellik vereceğiz. Bir While döngüsü oluşturacağız.
        while(isplaying){
            //Birazdan görev atayacağımız while döngüsü içinde ki 3 metodu oluşturuyoruz.
            while (isplaying) {
                update();
                draw();
                sleep();
            }

        }
    }
    private void update(){
        backround1.x -= 10 * screenRatioX;
        backround2.x -= 10 * screenRatioY;
        if(backround1.x + backround1.backround.getWidth() < 0){
            backround1.x = screenX;
        }
        if(backround2.x + backround2.backround.getWidth() < 0){
            backround2.x = screenX;
        }
        if (swime.isGoingUp)
            swime.y -= 30 * screenRatioY;
        else
            swime.y += 30 * screenRatioY;
        if (swime.y < 0)
            swime.y = 0;
        if (swime.y > screenY - swime.height)
            swime.y = screenY - swime.height;

    }

    private void draw(){
        if(getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(backround1.backround, backround1.x, backround1.y, paint);
            canvas.drawBitmap(backround2.backround, backround2.x, backround2.y, paint);

            canvas.drawBitmap(swime.getSwime(),swime.x, swime.y, paint);

            getHolder().unlockCanvasAndPost(canvas);
        }

    }

    private void sleep(){
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Resum metodunu işlevlerini veriyoruz. Verdiğimiz işlevler ile run metodunu çağıracak.
    public void resume(){
        isplaying = true;
        thread = new Thread(this);
        thread.start();

    }
    //Şimdi ise bu metodmuza sadece durma metodunu vereceğiz.
    public void pause() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < screenX / 2) {
                    swime.isGoingUp = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                swime.isGoingUp = false;
                break;
        }
        return true;
    }
}
