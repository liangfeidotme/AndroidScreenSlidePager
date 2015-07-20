package com.liangfeizc.screenslidepager.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.liangfeizc.slidepager.SlidePagerActivity;


public class MainActivity extends ActionBarActivity {

    private static final String[] IMAGES = new String[] {
            "http://img1.cache.netease.com/catchpic/B/B2/B2F274C1CCD5A89133261E6252A0C8E9.jpg",
            "http://img5.duitang.com/uploads/item/201408/09/20140809204759_CwtQN.jpeg",
            "http://img5q.duitang.com/uploads/item/201204/06/20120406151343_HefME.jpeg",
            "http://images.qianlong.com/mmsource/imghylanda/201108/25/21/7131919880274907937.jpg",
            "http://imgsrc.baidu.com/forum/pic/item/78370e46f91582056a63e576.jpg"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://www.liangfeizc.com/about"));
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void viewPictures(View view) {
        Intent intent = new Intent(this, SlidePagerActivity.class);
        intent.putExtra(SlidePagerActivity.EXTRA_PICTURES, IMAGES);

        startActivity(intent);
    }
}
