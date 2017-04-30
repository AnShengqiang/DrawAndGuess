package com.anshengqiang.android.drawandguess;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.anshengqiang.android.drawandguess.models.DoodleList;
import com.anshengqiang.android.drawandguess.models.MyPoint;
import com.anshengqiang.android.drawandguess.models.Points;

/**
 * Created by anshengqiang on 2017/4/13.
 */

public class SceneView extends View {
    private static final String TAG = "SceneView";

    private Handler mHandler;
    private DoodleList mDoodleList;
    private int LineListIndex = -1;
    private int mType = 0;
    private int mColor;
    private int mPenWidth = 6;
    private boolean isMultiPoint = false;

    public SceneView(Context context) {
        super(context);
        init();
        startThread();
    }

    public SceneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.SceneView,
                0, 0);
        try {
            mColor = a.getColor(R.styleable.SceneView_color, getResources().getColor(R.color.blue));
        } finally {
            a.recycle();
        }

        init();
        startThread();
    }

    private void init() {
        mDoodleList = new DoodleList();

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        invalidate();
                }
            }
        };
    }

    private void startThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                        Message message = new Message();
                        message.what = 1;
                        mHandler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int j = 0; j < mDoodleList.getDoodles().size(); j++) {
            Points points = mDoodleList.getDoodles().get(j);
            int size = points.getPoints().size();
            if (size == 1) {
                MyPoint myPoint = points.getPoints().get(0);

                Paint paint = new Paint();
                paint.setColor(myPoint.getColor());
                paint.setStrokeWidth(myPoint.getPenWidth());

                canvas.drawCircle(myPoint.x, myPoint.y, myPoint.getPenWidth() / 2, paint);
            } else if (size == 2) {
                MyPoint startPoint = points.getPoints().get(0);
                MyPoint endPoint = points.getPoints().get(1);

                Paint paint = new Paint();
                paint.setColor(startPoint.getColor());
                paint.setStrokeWidth(startPoint.getPenWidth());

                canvas.drawCircle(startPoint.x, startPoint.y, startPoint.getPenWidth() / 2, paint);
                canvas.drawCircle(endPoint.x, endPoint.y, endPoint.getPenWidth() / 2, paint);

                canvas.drawLine(startPoint.x,
                        startPoint.y,
                        endPoint.x,
                        endPoint.y,
                        paint);
            } else if (size == 3) {
                MyPoint startPoint = points.getPoints().get(0);
                MyPoint middlePoint = points.getPoints().get(1);
                MyPoint endPoint = points.getPoints().get(2);

                Paint paint = new Paint();
                paint.setColor(startPoint.getColor());

                canvas.drawCircle(startPoint.x, startPoint.y, startPoint.getPenWidth() / 2, paint);
                canvas.drawCircle(endPoint.x, endPoint.y, endPoint.getPenWidth() / 2, paint);

                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(startPoint.getPenWidth());

                Path path = new Path();
                path.moveTo(startPoint.x, startPoint.y);
                path.quadTo(middlePoint.x, middlePoint.y, endPoint.x, endPoint.y);

                canvas.drawPath(path, paint);
            } else if (size == 4){
                MyPoint startPoint = points.getPoints().get(0);
                MyPoint middlePoint = points.getPoints().get(1);
                MyPoint middlePoint2 = points.getPoints().get(3);
                MyPoint endPoint = points.getPoints().get(2);

                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(startPoint.getColor());
                paint.setStrokeWidth(startPoint.getPenWidth());

                Path path = new Path();
                path.moveTo(startPoint.x, startPoint.y);
                path.cubicTo(middlePoint.x, middlePoint.y, middlePoint2.x, middlePoint2.y, endPoint.x, endPoint.y);

                canvas.drawCircle(startPoint.x, startPoint.y, startPoint.getPenWidth()/2, paint);
                canvas.drawCircle(endPoint.x, endPoint.y, endPoint.getPenWidth()/2, paint);
                canvas.drawPath(path, paint);
            } else if (size > 4) {
                for (int i = 1; i < points.getPoints().size(); i++) {
                    MyPoint startPoint = points.getPoints().get(i - 1);
                    MyPoint endPoint = points.getPoints().get(i);

                    Paint paint = new Paint();
                    paint.setColor(startPoint.getColor());
                    paint.setStrokeWidth(startPoint.getPenWidth());

                    canvas.drawCircle(startPoint.x, startPoint.y, startPoint.getPenWidth() / 2, paint);
                    canvas.drawCircle(endPoint.x, endPoint.y, endPoint.getPenWidth() / 2, paint);
                    canvas.drawLine(startPoint.x,
                            startPoint.y,
                            endPoint.x,
                            endPoint.y,
                            paint);
                }
            }

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mType == 0) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDoodleList.getDoodles().add(new Points());
                    LineListIndex++;
                    isMultiPoint = false;
                    mDoodleList.getDoodles().get(LineListIndex).addPoint(
                            new MyPoint((int) event.getX(), (int) event.getY(), mColor, mPenWidth));
                    break;
                case MotionEvent.ACTION_MOVE:
                    mDoodleList.getDoodles().get(LineListIndex).addPoint(
                            new MyPoint((int) event.getX(), (int) event.getY(), mColor, mPenWidth));
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    break;
            }
        }

        if (mType == 1) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDoodleList.getDoodles().add(new Points());
                    LineListIndex++;
                    isMultiPoint = false;
                    mDoodleList.getDoodles().get(LineListIndex).addPoint(
                            new MyPoint((int) event.getX(), (int) event.getY(), mColor, mPenWidth));
                    break;

                case MotionEvent.ACTION_UP:
                    break;
                default:
                    break;
            }
        }

        if (mType == 2) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDoodleList.getDoodles().add(new Points());
                    LineListIndex++;
                    isMultiPoint = false;
                    mDoodleList.getDoodles().get(LineListIndex).addPoint(
                            new MyPoint((int) event.getX(), (int) event.getY(), mColor, mPenWidth));
                    mDoodleList.getDoodles().get(LineListIndex).addPoint(
                            new MyPoint((int) event.getX(), (int) event.getY(), mColor, mPenWidth));
                    break;

                case MotionEvent.ACTION_MOVE:
                    MyPoint point = new MyPoint((int) event.getX(), (int) event.getY(), mColor, mPenWidth);
                    mDoodleList.getDoodles().get(LineListIndex).getPoints().set(1, point);
                    break;

                case MotionEvent.ACTION_UP:
                    MyPoint upPoint = new MyPoint((int) event.getX(), (int) event.getY(), mColor, mPenWidth);
                    mDoodleList.getDoodles().get(LineListIndex).getPoints().set(1, upPoint);
                    break;

                default:
                    break;
            }
        }

        if (mType == 3) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDoodleList.getDoodles().add(new Points());
                    LineListIndex++;
                    isMultiPoint = false;
                    mDoodleList.getDoodles().get(LineListIndex).addPoint(
                            new MyPoint((int) event.getX(), (int) event.getY(), mColor, mPenWidth));
                    mDoodleList.getDoodles().get(LineListIndex).addPoint(
                            new MyPoint((int) event.getX(), (int) event.getY(), mColor, mPenWidth));
                    mDoodleList.getDoodles().get(LineListIndex).addPoint(
                            new MyPoint((int) event.getX(), (int) event.getY(), mColor, mPenWidth));
                    break;

                case MotionEvent.ACTION_MOVE:
                    int firstX = mDoodleList.getDoodles().get(LineListIndex).getPoints().get(0).x;
                    int firstY = mDoodleList.getDoodles().get(LineListIndex).getPoints().get(0).y;

                    int lastX = (int) event.getX();
                    int lastY = (int) event.getY();
                    int num = event.getPointerCount();
                    if (num > 1) {
                        isMultiPoint = true;
                        int middleX = (int) event.getX(1);
                        int middleY = (int) event.getY(1);
                        MyPoint middlePoint = new MyPoint(middleX, middleY, mColor, mPenWidth);
                        mDoodleList.getDoodles().get(LineListIndex).getPoints().set(1, middlePoint);
                        if (num == 3) {
                            int middleX2 = (int) event.getX(2);
                            int middleY2 = (int) event.getY(2);
                            MyPoint middlePoint2 = new MyPoint(middleX2, middleY2, mColor, mPenWidth);
                            if (mDoodleList.getDoodles().get(LineListIndex).getPoints().size() == 3){
                                mDoodleList.getDoodles().get(LineListIndex).getPoints().add(middlePoint);
                            }
                            mDoodleList.getDoodles().get(LineListIndex).getPoints().set(3, middlePoint2);
                        }
                    }

                    if (!isMultiPoint) {
                        MyPoint middlePoint = new MyPoint(lastX, firstY, mColor, mPenWidth);
                        mDoodleList.getDoodles().get(LineListIndex).getPoints().set(1, middlePoint);
                    }
                    MyPoint lastPoint = new MyPoint(lastX, lastY, mColor, mPenWidth);
                    mDoodleList.getDoodles().get(LineListIndex).getPoints().set(2, lastPoint);
                    break;

                default:
                    break;
            }
        }

        return true;
    }

    public DoodleList getDoodleList() {
        return mDoodleList;
    }

    public void setDoodleList(DoodleList d) {
        mDoodleList = d;
    }

    public int getLineListIndex() {
        return LineListIndex;
    }

    public void setLineListIndex(int lineListIndex) {
        LineListIndex = lineListIndex;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public int getLineWidth() {
        return mPenWidth;
    }

    public void setLineWidth(int width) {
        mPenWidth = width;
    }
}
