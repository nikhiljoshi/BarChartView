package com.niks.chartview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by nikhil on 30/1/18.
 */


public class ChartView extends View {
    private static final String TAG = "ChartView";
    public int mWidth;
    public int mHeight;
    public static int mPaddingRight = 30;
    public static int mPaddingLeft = 3*mPaddingRight;
    public static int mPaddingBottom = 50;
    public Point mPoint = new Point();
    public Point mYPoint = new Point();
    public Point mXPoint = new Point();
    public int mYBlankLength = 30;
    public int mYLength;
    public int mXLength;
    public Paint mPaint = new Paint();
    public int mIncreaseNumber = 1;
    public ArrayList<String> mYNameList =  new ArrayList();
    private ArrayList<Double> mYValueList =  new ArrayList();

    private ArrayList<String> mIndexList =  new ArrayList();
    private ArrayList<Integer> mValueList =  new ArrayList();

    private int mCurrentHeight = -mPaddingBottom;


    private int mBarColor = Color.WHITE;

    public ChartView(Context context) {
        super(context);
        initXY();
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initXY();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = this.getWidth();
        mHeight = this.getHeight();
        mPoint.x = mPaddingLeft;
        mPoint.y = -mPaddingBottom;
        mYPoint.x = mPaddingLeft;
        mYPoint.y = -mHeight;
        mXPoint.x = mWidth -mPaddingRight;
        mXPoint.y = -mPaddingBottom;

        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(3);
        mPaint.setTextSize((int) (7f * getResources().getDisplayMetrics().scaledDensity + 0.5f));

        mYLength = mHeight -mPaddingBottom - mYBlankLength;
        mXLength = mXPoint.x - mPaddingLeft;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(0,mHeight);

        mPaint.setStrokeWidth(3);

        int perYLength = mYLength / mYNameList.size();
        int perXLength = mXLength / ((mIndexList.size()*2)+1);


        mPaint.setStrokeWidth(perXLength);
        mPaint.setColor(mBarColor);
        for(int i = 0; i< mValueList.size(); i++){

            if(mValueList.get(i)>=mYValueList.get(0)){
                if(-((mValueList.get(i)-1)*perYLength+perYLength)-mPaddingBottom<= mCurrentHeight){
                    canvas.drawLine(((i+1)*2*perXLength)+mPaddingLeft,-mPaddingBottom,
                            ((i+1)*2*perXLength)+mPaddingLeft, (float) mCurrentHeight,mPaint);
                    refreshData();
                }else{
                    canvas.drawLine(((i+1)*2*perXLength)+mPaddingLeft,-mPaddingBottom,
                            ((i+1)*2*perXLength)+mPaddingLeft,
                            (float) -((mValueList.get(i)-1)*perYLength+perYLength)-mPaddingBottom,mPaint);
                }

            }else{
                double percent = mValueList.get(i) / mYValueList.get(0);
                Log.d(TAG,"percent"+percent);
                if(-(percent*perYLength)-mPaddingBottom<= mCurrentHeight){
                    canvas.drawLine(((i + 1) * 2 * perXLength) + mPaddingLeft, -mPaddingBottom,
                            ((i + 1) * 2 * perXLength) + mPaddingLeft, (float) mCurrentHeight, mPaint);
                    refreshData();
                }else {
                    canvas.drawLine(((i + 1) * 2 * perXLength) + mPaddingLeft, -mPaddingBottom,
                            ((i + 1) * 2 * perXLength) + mPaddingLeft,
                            (float)-(percent*perYLength)-mPaddingBottom, mPaint);
                }
            }

        }


    }

    public int getBarColor() {
        return mBarColor;
    }

    public void setBarColor(int mBarColor) {
        this.mBarColor = mBarColor;
    }


    private void initXY() {
        Log.d(TAG,"initXY");

        mYNameList.add("1");
        mYNameList.add("2");
        mYNameList.add("3");
        mYNameList.add("4");
        mYNameList.add("5");

        mYValueList.add(0,1.0);
        mYValueList.add(1,2.0);
        mYValueList.add(2,3.0);
        mYValueList.add(3,4.0);
        mYValueList.add(4,5.0);


       /*
         Commented mock data Part as we are adding data from Network
       mIndexList.add("1");
        mIndexList.add("2");
        mIndexList.add("3");
        mIndexList.add("4");
        mIndexList.add("5");
        mIndexList.add("6");
        mIndexList.add("7");
        mIndexList.add("8");
        mIndexList.add("9");
        mIndexList.add("10");

        mValueList.add(4);
        mValueList.add(2);
        mValueList.add(1);
        mValueList.add(3);
        mValueList.add(5);
        mValueList.add(3);
        mValueList.add(2);
        mValueList.add(1);
        mValueList.add(5);
        mValueList.add(3);*/


        refreshData();
    }



    public void refreshData(){

        postDelayed(new Runnable() {
            @Override
            public void run() {
                mCurrentHeight +=(-mIncreaseNumber);
            }
        },100);
        invalidate();

    }


    public ArrayList<String> getIndexList() {
        return mIndexList;
    }

    public void setIndexList(String index) {
        this.mIndexList.add(index);
    }

    public ArrayList<Integer> getValueList() {
        return mValueList;
    }

    public void setValueList(int value) {
        this.mValueList.add(value);

    }

    public void clear()
    {
        mIndexList.clear();
        mValueList.clear();
    }

}
