package com.lsm.splashview.viewfilipper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.lsm.splashview.MainActivity;
import com.lsm.splashview.R;

public class ViewFillipperActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private ViewFlipper viewFlipper;
    private Button btn;
    private LinearLayout linearLayout;
    private GestureDetector gestureDetector;
    private int index = 0;//当前是第几屏
    private int childCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acvitivty_viewfillipper);
        viewFlipper = findViewById(R.id.viewflipper);
        btn = findViewById(R.id.id_btn);
        linearLayout = findViewById(R.id.id_indicator);
        childCount = viewFlipper.getChildCount();
        initIndicator();

        gestureDetector = new GestureDetector(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewFillipperActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    /**
     * 初始化指示器
     */
    private void initIndicator() {

        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, width);
        for (int i = 0; i < childCount; i++) {
            View view = new View(this);
            view.setBackgroundResource(i == 0 ? R.drawable.dot_select : R.drawable.dot_normal);
            lp.rightMargin = 2 * width;
            view.setId(i);
            view.setLayoutParams(lp);
            linearLayout.addView(view);
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() > e2.getX()) {
            if (index >= childCount - 1) {
                return false;
            }
            viewFlipper.showNext();
            index = index + 1;
        } else if (e1.getX() < e2.getX()) {
            if (index <= 0) {
                return false;
            }
            viewFlipper.showPrevious();
            index = index - 1;
        } else {
            return false;
        }
        changeInicator();
        return false;
    }

    private void changeInicator() {
        for (int i = 0; i < childCount; i++) {
            View childAt = linearLayout.getChildAt(i);
            childAt.setBackgroundResource(i == index ? R.drawable.dot_select : R.drawable.dot_normal);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
