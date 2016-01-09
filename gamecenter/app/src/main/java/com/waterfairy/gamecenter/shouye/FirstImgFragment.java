package com.waterfairy.gamecenter.shouye;

import android.widget.ImageView;

import com.waterfairy.gamecenter.R;

/**
 * Created by zhaohang on 2015/10/30.
 */
public class FirstImgFragment extends ShouYeBaseFragment {
    @Override
    public void init() {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.mipmap.page1);
    }
}
