package com.anshengqiang.android.drawandguess.models;

import android.graphics.Point;

/**
 * Created by anshengqiang on 2017/4/15.
 */

public class MyPoint extends Point {
    private int color;
    private int penWidth;

    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MyPoint(int x, int y, int color, int penWidth) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.penWidth = penWidth;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getPenWidth() {
        return penWidth;
    }

    public void setPenWidth(int penWidth) {
        this.penWidth = penWidth;
    }
}
