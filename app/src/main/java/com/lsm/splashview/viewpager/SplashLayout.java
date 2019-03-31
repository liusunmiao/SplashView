package com.lsm.splashview.viewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.lsm.splashview.R;

/**
 * 引导页效果
 */
public class SplashLayout extends FrameLayout {
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private int viewSize = 0;
    //指示器距离底部的间距
    private int pointBottomMargin;
    //指示器大小
    private int pointSize;
    //指示器右边间距
    private int pointRightMargin;
    //是否显示底部指示器
    private boolean showIndicator;

    public SplashLayout(Context context) {
        this(context, null);
    }

    public SplashLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SplashLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initView();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SplashLayout);
        pointBottomMargin = (int) array.getDimension(R.styleable.SplashLayout_bottomPointBottomMargin, dip2px(100));
        pointSize = (int) array.getDimension(R.styleable.SplashLayout_pointSize, dip2px(10));
        pointRightMargin = (int) array.getDimension(R.styleable.SplashLayout_pointRightMargin, dip2px(20));
        showIndicator = array.getBoolean(R.styleable.SplashLayout_showIndicator, true);
        array.recycle();
    }

    /**
     * 初始化viewpager 和底部指示器
     */
    @SuppressLint("ResourceType")
    private void initView() {
        //实例化viewpager
        viewPager = new ViewPager(getContext());
        //这里需要设置viewpager的id 不设置会报错
        viewPager.setId(1);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        viewPager.setLayoutParams(lp);
        //实例化底部指示器容器
        linearLayout = new LinearLayout(getContext());
        FrameLayout.LayoutParams indoctorLp = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        indoctorLp.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        indoctorLp.bottomMargin = pointBottomMargin;

        linearLayout.setLayoutParams(indoctorLp);

        addView(viewPager);
        addView(linearLayout);
        initEvent();
    }

    private void initEvent() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {
                if (viewSize == 0) {
                    return;
                }
                for (int i = 0; i < viewSize; i++) {
                    View childAt = linearLayout.getChildAt(i);
                    if(childAt==null){
                        continue;
                    }
                    childAt.setBackgroundResource(i == position ? R.drawable.dot_select : R.drawable.dot_normal);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * @param
     */
    public <T extends FragmentPagerAdapter> void showSplash(T adapter, int fragmentSize) {
        this.viewSize = fragmentSize;
        //给viewpager添加adapter
        viewPager.setAdapter(adapter);
        if (showIndicator) {
            initIndicator();
        }
    }

    /**
     * 初始化指示器
     */
    private void initIndicator() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(pointSize, pointSize);
        for (int i = 0; i < viewSize; i++) {
            View view = new View(getContext());
            view.setBackgroundResource(i == 0 ? R.drawable.dot_select : R.drawable.dot_normal);
            lp.rightMargin = pointRightMargin;
            view.setId(i);
            view.setLayoutParams(lp);
            linearLayout.addView(view);
        }
    }

    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }
}
