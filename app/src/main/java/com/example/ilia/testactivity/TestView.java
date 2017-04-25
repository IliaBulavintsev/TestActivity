package com.example.ilia.testactivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class TestView extends View {

    private int tapCount = 0;
    private TextPaint counterPaint;

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        counterPaint = new TextPaint();
        counterPaint.setColor(Color.BLACK);
        counterPaint.setTextSize(h / 4);
        counterPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            tapCount++;
            invalidate();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(Integer.toString(tapCount), canvas.getWidth()/2, canvas.getHeight()/2, counterPaint);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        SavedState state = new SavedState(super.onSaveInstanceState());
        state.count = tapCount;
        return state;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        tapCount = ss.count;
        invalidate();
    }

    private static class SavedState extends BaseSavedState{

        private int count;

        public SavedState(Parcel source) {
            super(source);
            count = source.readInt();
        }

        @TargetApi(Build.VERSION_CODES.N)
        public SavedState(Parcel source, ClassLoader loader) {
            super(source, loader);
            count = source.readInt();
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);

            out.writeInt(count);
        }
    }

    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {


        @Override
        public SavedState createFromParcel(Parcel source) {
            return new SavedState(source);
        }

        @Override
        public SavedState[] newArray(int size) {
            return new SavedState[size];
        }
    };

}
