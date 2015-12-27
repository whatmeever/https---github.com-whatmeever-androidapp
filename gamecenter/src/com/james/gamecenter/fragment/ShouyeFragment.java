package com.james.gamecenter.fragment;

import com.james.gamecenter.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public abstract class ShouyeFragment extends Fragment {
	
	public ImageView img;
	public ImageButton btn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_shouye, container, false);
		
		img = (ImageView) view.findViewById(R.id.iv);
		btn = (ImageButton) view.findViewById(R.id.ibt);
		
		init();
		
		return view;
	}

	public abstract void init();
}
