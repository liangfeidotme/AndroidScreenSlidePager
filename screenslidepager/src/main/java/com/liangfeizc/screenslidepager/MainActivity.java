package com.liangfeizc.screenslidepager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.liangfeizc.slidepageindicator.CirclePageIndicator;

import java.util.Arrays;


public class MainActivity extends ActionBarActivity {

    private static final String[] IMAGES = new String[] {
            "http://images.qianlong.com/mmsource/imghylanda/201108/25/21/7131919880274907937.jpg",
            "http://i48.tinypic.com/ipqddt.jpg",
            "http://photocdn.sohu.com/20110223/Img279487938.jpg",
            "http://photocdn.sohu.com/20100609/Img272666987.jpg",
            "http://pic11.nipic.com/20101205/5311590_013818784193_2.jpg",
            "http://www.people.com.cn/mediafile/pic/20150122/18/16165324206901040562.jpg"
    };


    private ViewPager mPager;
    private CirclePageIndicator mPageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fresco.initialize(this);

        setContentView(R.layout.activity_main);
        mPager = (ViewPager) findViewById(R.id.pager);

        ScreenSlidePagerAdapter pagerAdapter =
                new ScreenSlidePagerAdapter(getSupportFragmentManager());

        pagerAdapter.addAll(Arrays.asList(IMAGES));

        mPager.setAdapter(pagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MainActivity.this, position + "Page in activity",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mPageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mPageIndicator.setViewPager(mPager);
    }


    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
