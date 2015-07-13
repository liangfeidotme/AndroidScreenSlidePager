package com.liangfeizc.slidepageindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by liangfeizc on 3/26/15.
 */
public class CirclePageIndicator extends LinearLayout implements ViewPager.OnPageChangeListener {
    public static final int INDICATOR_TYPE_CIRCLE = 0;
    public static final int INDICATOR_TYPE_FRACTION = 1;

    public enum IndicatorType {
        CIRCLE(INDICATOR_TYPE_CIRCLE),
        FRACTION(INDICATOR_TYPE_FRACTION),
        UNKNOWN(-1);

        private int type;
        IndicatorType(int type) {
            this.type = type;
        }

        public static IndicatorType of(int value) {
            switch (value) {
                case INDICATOR_TYPE_CIRCLE:
                    return CIRCLE;
                case INDICATOR_TYPE_FRACTION:
                    return FRACTION;
                default:
                    return UNKNOWN;
            }
        }
    }

    public static final int DEFAULT_INDICATOR_SPACING = 5;

    private int mActivePosition = -1;
    private int mIndicatorSpacing;
    private boolean mIndicatorTypeChanged = false;

    private IndicatorType mIndicatorType = IndicatorType.of(INDICATOR_TYPE_CIRCLE);
    private ViewPager mViewPager;

    private ViewPager.OnPageChangeListener mUserDefinedPageChangeListener;

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
            mIndicatorSpacing = a.getDimensionPixelSize(
                    R.styleable.CirclePageIndicator_indicator_spacing,
                    DEFAULT_INDICATOR_SPACING);
            int indicatorTypeValue = a.getInt(
                    R.styleable.CirclePageIndicator_indicator_type,
                    mIndicatorType.type);
            mIndicatorType = IndicatorType.of(indicatorTypeValue);
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
        mViewPager = pager;
        mUserDefinedPageChangeListener = getOnPageChangeListener(pager);
        pager.setOnPageChangeListener(this);
        setIndicatorType(mIndicatorType);
    }

    public void setIndicatorType(IndicatorType indicatorType) {
        mIndicatorType = indicatorType;
        mIndicatorTypeChanged = true;
        if (mViewPager != null) {
            addIndicator(mViewPager.getAdapter().getCount());
        }
    }

    private void removeIndicator() {
        removeAllViews();
    }

    private void addIndicator(int count) {
        removeIndicator();
        if (count <= 0) return;
        if (mIndicatorType == IndicatorType.CIRCLE) {
            for (int i = 0; i < count; i++) {
                ImageView img = new ImageView(getContext());
                LayoutParams params = new LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.leftMargin = mIndicatorSpacing;
                params.rightMargin = mIndicatorSpacing;
                img.setImageResource(R.drawable.circle_indicator_stroke);
                addView(img, params);
            }
        } else if (mIndicatorType == IndicatorType.FRACTION) {
            TextView textView = new TextView(getContext());
            textView.setTag(count);
            LayoutParams params = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            addView(textView, params);
        }
        updateIndicator(mViewPager.getCurrentItem());
    }

    private void updateIndicator(int position) {
        if (mIndicatorTypeChanged || mActivePosition != position) {
            mIndicatorTypeChanged = false;
            if (mIndicatorType == IndicatorType.CIRCLE) {
                ((ImageView) getChildAt(mActivePosition))
                        .setImageResource(R.drawable.circle_indicator_stroke);
                ((ImageView) getChildAt(position))
                        .setImageResource(R.drawable.circle_indicator_solid);
            } else if (mIndicatorType == IndicatorType.FRACTION) {
                TextView textView = (TextView) getChildAt(0);
                //noinspection RedundantCast
                textView.setText(String.format("%d/%d", position + 1, (int) textView.getTag()));
            }
            mActivePosition = position;
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mUserDefinedPageChangeListener != null) {
            mUserDefinedPageChangeListener.onPageScrolled(position, positionOffset,
                    positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        updateIndicator(position);
        if (mUserDefinedPageChangeListener != null) {
            mUserDefinedPageChangeListener.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mUserDefinedPageChangeListener != null) {
            mUserDefinedPageChangeListener.onPageScrollStateChanged(state);
        }
    }
}

