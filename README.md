# Android-ScreenSlidePager
Full screen slide pager to display images fetched from Internet by Picasso

## demo

![](art/sample.gif)

---

### Indicator

The indicator at the bottom of ScreenSlidePager has been extracted as an aar. 
Your can add it to your dependecies to use it independently.

#### Gradle

```groovy
compile 'com.liangfeizc:SlidePageIndicator:1.1.0@aar'
```

#### Maven

```xml
<dependency>
    <groupId>com.liangfeizc</groupId>
    <artifactId>SlidePageIndicator</artifactId>
    <version>1.1.0</version>
    <type>aar</type>
</dependency>
```

#### Usage

`CirclePageIndicator` and `ViewPager` shoud be used as child views of a `Framelayout`. But here we used `merge` instead, because the root view in any activity is a `FrameLayout`.
You can use `android:gravity` to position the `CirclePageIndicator` and use `app:indicator_spacing` to adjust the spacing between two adjencent circle indicators.

```xml
<merge xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <android.support.v4.view.ViewPager
        android:id="@+id/pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
    <com.liangfeizc.slidepageindicator.CirclePageIndicator
        android:id="@+id/indicator"
        android:gravity="bottom|center_horizontal"
        app:indicator_spacing="5dp"
        android:layout_marginBottom="20dp"
        android:layout_marginStart="20dp"
        android:layout_marginLeft="20dp"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</merge>
```

### SlidePager

You can also use `SlidePager` by passing an array of picture urls to it.

*Define an array of picture urls*
```java
private static final String[] IMAGES = new String[] {
    "http://img1.cache.netease.com/catchpic/B/B2/B2F274C1CCD5A89133261E6252A0C8E9.jpg",
    "http://img5.duitang.com/uploads/item/201408/09/20140809204759_CwtQN.jpeg",
    "http://img5q.duitang.com/uploads/item/201204/06/20120406151343_HefME.jpeg",
    "http://images.qianlong.com/mmsource/imghylanda/201108/25/21/7131919880274907937.jpg",
    "http://imgsrc.baidu.com/forum/pic/item/78370e46f91582056a63e576.jpg"
```

*View pictures in a new activity*
```java
public void viewPictures(View view) {
    Intent intent = new Intent(this, SlidePagerActivity.class);
    intent.putExtra(SlidePagerActivity.EXTRA_TITLE, "堀北真希の写真");
    intent.putExtra(SlidePagerActivity.EXTRA_PICTURES, IMAGES);
    startActivity(intent);
}
```

## dependencies

* [Fresco](https://github.com/facebook/fresco)
