package com.liangfeizc.slidepager;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.liangfeizc.slidepageindicator.PageIndicator;

import java.util.Arrays;

public class SlidePagerActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "slidepageractivity.extra.title";
    public static final String EXTRA_PICTURES = "slidepageractivity.extra.pictures";
    public static final String EXTRA_INDICATOR_TYPE = "slidepageractivity.extra.indicator.type";

    private PageIndicator mPageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fresco.initialize(this);

        setContentView(R.layout.activity_slide_pager);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        SlidePagerAdapter pagerAdapter =
                new SlidePagerAdapter(getSupportFragmentManager());

        if (getIntent() == null) return;

        // set title
        ActionBar ab = getActionBar();
        if (ab != null) {
            String title = getIntent().getStringExtra(EXTRA_TITLE);
            if (!TextUtils.isEmpty(title)) {
                ab.setTitle(title);
            }
        }

        // set pictures
        String[] pics = getIntent().getStringArrayExtra(EXTRA_PICTURES);
        pagerAdapter.addAll(Arrays.asList(pics));

        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        mPageIndicator = (PageIndicator) findViewById(R.id.indicator);
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
        int id = item.getItemId();
        if (id == R.id.action_indicator_circle) {
            mPageIndicator.setIndicatorType(PageIndicator.IndicatorType.CIRCLE);
        } else if (id == R.id.action_indicator_fraction) {
            mPageIndicator.setIndicatorType(PageIndicator.IndicatorType.FRACTION);
        }
        return super.onOptionsItemSelected(item);
    }
}
