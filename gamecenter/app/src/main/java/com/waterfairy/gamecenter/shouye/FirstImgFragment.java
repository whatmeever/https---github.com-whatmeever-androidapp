package com.waterfairy.gamecenter.shouye;

import android.widget.ImageView;

import com.waterfairy.gamecenter.R;

/**
 *
 * @author james
 */
public class FirstImgFragment extends ShouYeBaseFragment {
    @Override
    public void init() {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.mipmap.page1);
    }
}
