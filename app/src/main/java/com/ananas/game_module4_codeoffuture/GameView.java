package com.ananas.game_module4_codeoffuture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;
    private Background background1, background2;
    private int screenX, screenY;
    private Paint paint;
    private float screenRationX, screenRationY;
    private Flight flight;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.screenX = screenX;
        this.screenY = screenY;

        screenRationX = 1920f / screenX;
        screenRationY = 1080f / screenY;

        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());

        background2.setX(screenX);

        flight = new Flight(screenX, screenY, getResources());
        paint = new Paint();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // нажатие на левую сторону экрана
                if (event.getX() < screenX / 2 )
                    flight.setGoingUp(true); // летит вверх
                else if (event.getX() >= screenX / 2) {
                flight.setOnCenter(true);
                }
                break;
            case MotionEvent.ACTION_MOVE: // движение по экрану

                break;
            case MotionEvent.ACTION_UP:
                flight.setGoingUp(false);
                flight.setOnCenter(false);
                break;
        }

        return true;
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();

        }
    }

    private void update() {
        background1.setX(background1.getX() - (int) (10 * screenRationX));
        background2.setX(background2.getX() - (int) (10 * screenRationX));

        if ((background1.getX() + background1.getBackground().getWidth()) <= 0)  background1.setX(screenX);
        if ((background2.getX() + background2.getBackground().getWidth()) <= 0)  background2.setX(screenX);


        if (flight.getGoingUp()) flight.setY(flight.getY() - (int)
                (30 * screenRationY)); // подъем, ось y направлена вниз
        else
            flight.setY(flight.getY() + (int) (30 * screenRationY)); // снижение, ось y направлена вниз

        if (flight.isOnCenter()) flight.setY(screenY / 2); // домашка

        if (flight.getY() < 0)
            flight.setY(0);
        else if (flight.getY() >= screenY - flight.getHeight()) // запрет на вылет за границы экрана
            flight.setY(screenY - flight.getHeight());
    }

    private void draw() {

        if (getHolder().getSurface().isValid()) {

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.getBackground(), background1.getX(), background1.getY(), paint);
            canvas.drawBitmap(background2.getBackground(), background2.getX(), background2.getY(), paint);

            canvas.drawBitmap(flight.getFlight(), flight.getX(), flight.getY(),paint);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resumeThread() {

        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pauseThread() {
        isPlaying = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
