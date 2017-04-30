package com.anshengqiang.android.drawandguess.models;

import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anshengqiang on 2017/4/13.
 */

public class Points {

    private static final String TAG = "Points";

    private List<MyPoint> points;

    public Points(){
        points = new ArrayList<>();
    }

    public List<MyPoint> getPoints(){
        return points;
    }

    public void addPoint(MyPoint point){
        points.add(point);
    }

    public void deleteLastPoint(){
        points.remove(points.size() - 1);
    }

    public void clearPoint(){
        points.clear();
    }
}
