package com.james.gamecenter.fragment;

import com.james.gamecenter.R;

import android.widget.ImageView;

public class FirstFragment extends ShouyeFragment {

	@Override
	public void init() {
		img.setScaleType(ImageView.ScaleType.FIT_XY);
		img.setImageResource(R.mipmap.page1);
	}

}