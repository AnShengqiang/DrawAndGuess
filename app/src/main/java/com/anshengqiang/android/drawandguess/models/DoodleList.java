package com.anshengqiang.android.drawandguess.models;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anshengqiang on 2017/4/13.
 */

public class DoodleList {

    private List<Points> mDoodleList;

    public DoodleList(){
        mDoodleList = new ArrayList<>();
    }

    public List<Points> getDoodles() {
        return mDoodleList;
    }

    public void deleteLast(){
        mDoodleList.remove(mDoodleList.size() - 1);
    }

    public void clear(){
        mDoodleList.clear();
    }
}
