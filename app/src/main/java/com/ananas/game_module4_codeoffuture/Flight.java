package com.ananas.game_module4_codeoffuture;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Flight {

    private int x = 0, y = 0;
    private int width, height;
    private int count = 0;
    private Bitmap flight1, flight2;
    private boolean isGoingUp = false;

    private boolean onCenter = false;


    public Flight(int screenX, int screenY, Resources resources) {

        flight1 = BitmapFactory.decodeResource(resources, R.drawable.plane_fly1);
        flight2 = BitmapFactory.decodeResource(resources, R.drawable.plane_fly2);

        width = flight1.getWidth() / 2;
        height = flight1.getHeight() / 2;

        width = (int) (width * 1920f / screenX);
        height = (int) (height * 1080f / screenX);

        flight1 = Bitmap.createScaledBitmap(flight1, width, height, false);
        flight2 = Bitmap.createScaledBitmap(flight2, width, height, false);

        y = screenY / 2;
        x = screenX / 21;

    }

    public Bitmap getFlight() {
        if (count == 0) {
            count++;
            return flight1;
        } else {
            count--;
            return flight2;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean getGoingUp() {
        return isGoingUp;
    }

    public void setGoingUp(boolean goingUp) {
        isGoingUp = goingUp;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public boolean isOnCenter() {
        return onCenter;
    }

    public void setOnCenter(boolean onCenter) {
        this.onCenter = onCenter;
    }


}
