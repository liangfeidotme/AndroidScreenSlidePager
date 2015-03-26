package com.liangfeizc.screenslidepager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

/**
 * Created by liangfei on 3/26/15.
 */
public class CirclePageIndicator extends LinearLayout implements ViewPager.OnPageChangeListener {

    public static final int DEFAULT_INDICATOR_SPACING = 5;

    private int activePosition;
    private int indicatorSpacing;

    private ViewPager.OnPageChangeListener userDefinedPageChangeListener;

    public CirclePageIndicator(Context context) {
        this(context, null);
    }

    public CirclePageIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirclePageIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.CirclePageIndicator, 0, 0);
        try {
            indicatorSpacing = a.getDimensionPixelSize(
                    R.styleable.CirclePageIndicator_indicator_spacing, DEFAULT_INDICATOR_SPACING);
        } finally {
            a.recycle();
        }

        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        if (!(getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.gravity = Gravity.BOTTOM | Gravity.START;
            setLayoutParams(params);
        }
    }

    public void setViewPager(ViewPager pager) {
        userDefinedPageChangeListener = getOnPageChangeListener(pager);
        pager.setOnPageChangeListener(this);
        addIndicator(pager.getAdapter().getCount());
    }

    private void addIndicator(int count) {
        for (int i = 0;  i < count; i++) {
            ImageView img = new ImageView(getContext());
            LayoutParams params = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = indicatorSpacing;
            params.rightMargin = indicatorSpacing;
            img.setImageResource(R.mipmap.circle_indicator_stroke);
            addView(img, params);
        }

        if (count > 0) {
            ((ImageView) getChildAt(0)).setImageResource(R.mipmap.home_circle_full);
        }
    }

    private void updateIndicator(int position) {
        if (activePosition != position) {
            ((ImageView) getChildAt(activePosition)).setImageResource(R.mipmap.circle_indicator_stroke);
            ((ImageView) getChildAt(position)).setImageResource(R.mipmap.home_circle_full);
            activePosition = position;
        }
    }

    private ViewPager.OnPageChangeListener getOnPageChangeListener(ViewPager pager) {
        try {
            Field f = pager.getClass().getDeclaredField("mOnPageChangeListener");
            f.setAccessible(true);
            return (ViewPager.OnPageChangeListener) f.get(pager);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int dp2px(float dpValue) {
        return (int) (dpValue * getContext().getResources().getDisplayMetrics().density);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (userDefinedPageChangeListener != null) {
            userDefinedPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        updateIndicator(position);
        if (userDefinedPageChangeListener != null) {
            userDefinedPageChangeListener.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (userDefinedPageChangeListener != null) {
            userDefinedPageChangeListener.onPageScrollStateChanged(state);
        }
    }
}

