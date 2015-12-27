package com.james.gamecenter.fragment;

import com.james.gamecenter.R;
import com.james.gamecenter.activity.FirstActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ThirdFragment extends ShouyeFragment {

	@Override
	public void init() {
		img.setScaleType(ImageView.ScaleType.FIT_XY);
		img.setImageResource(R.mipmap.page3);
		
		btn.setVisibility(View.VISIBLE);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SharedPreferences sp = getActivity().getSharedPreferences("db", getActivity().MODE_PRIVATE);
				sp.edit().putBoolean("isFirst", false).apply();
				Intent intent = new Intent(getActivity(), FirstActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		});
	}

}
