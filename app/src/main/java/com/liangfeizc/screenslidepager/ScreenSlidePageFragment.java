package com.liangfeizc.screenslidepager;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by liangfeizc on 3/26/15.
 */
public class ScreenSlidePageFragment extends Fragment {
    private static final String PIC_URL = "screenslidepagefragment.picurl";

    public static ScreenSlidePageFragment newInstance(String picUrl) {
        Bundle arguments = new Bundle();
        arguments.putString(PIC_URL, picUrl);

        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);

        SimpleDraweeView view = (SimpleDraweeView) rootView.findViewById(R.id.pic);

        Bundle arguments = getArguments();
        if (arguments != null) {
            String url = arguments.getString(PIC_URL);
            view.setImageURI(Uri.parse(url));
        }
        return rootView;
    }
}
