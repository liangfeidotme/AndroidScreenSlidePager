package com.liangfeizc.screenslidepager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.liangfeizc.slidepageindicator.CirclePageIndicator;

import java.util.Arrays;

public class SlidePagerActivity extends ActionBarActivity {

    public static final String EXTRA_PICTURES = "slidepageractivity.extra.pictures";

    private CirclePageIndicator mPageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fresco.initialize(this);

        setContentView(R.layout.activity_slide_pager);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        SlidePagerAdapter pagerAdapter =
                new SlidePagerAdapter(getSupportFragmentManager());

        if (getIntent() == null) return;

        String[] pics = getIntent().getStringArrayExtra(EXTRA_PICTURES);
        pagerAdapter.addAll(Arrays.asList(pics));

        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mPageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mPageIndicator.setViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_slide_pager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_indicator_circle:
                mPageIndicator.setIndicatorType(CirclePageIndicator.IndicatorType.CIRCLE);
                break;
            case R.id.action_indicator_fraction:
                mPageIndicator.setIndicatorType(CirclePageIndicator.IndicatorType.FRACTION);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
