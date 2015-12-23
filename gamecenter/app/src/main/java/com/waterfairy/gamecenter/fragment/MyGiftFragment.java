package com.waterfairy.gamecenter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.waterfairy.gamecenter.R;

/**
 * Created by zhaohang on 2015/10/24.
 */
public class MyGiftFragment extends Fragment  {
    private RelativeLayout relativeLayout;
    private ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mygift_fragment, null);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.mygift_ll);
        imageView= (ImageView) view.findViewById(R.id.mygift_img);
        return view;
    }

}
