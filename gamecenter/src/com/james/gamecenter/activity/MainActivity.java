package com.james.gamecenter.activity;

import java.util.ArrayList;
import java.util.List;

import com.james.gamecenter.R;
import com.james.gamecenter.adapter.RankFragmentAdapter;
import com.james.gamecenter.fragment.FirstFragment;
import com.james.gamecenter.fragment.SecondFragment;
import com.james.gamecenter.fragment.ThirdFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener{

	private ViewPager vpShow = null;
	private LinearLayout llBottom = null;
	private ImageView imgFirst = null;

	private List<Fragment> listFragment = null;

	private SharedPreferences sp = null;

	private boolean isFirst = true;
	private ImageView lastimg;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (isFirst) {
				imgFirst.setVisibility(View.GONE);
				init();
			} else {
				Intent intent = new Intent(MainActivity.this, FirstActivity.class);
				startActivity(intent);
				finish();
			}
		}
	};

	private void init() {

		for (int i = 0; i < 3; i++) {
			ImageView img = new ImageView(this);

			img.setBackgroundResource(R.drawable.bg_white);
			img.setLayoutParams(new LinearLayout.LayoutParams(30, 15));

			if (i == 0) {
				img.setBackgroundResource(R.drawable.bg_red);
				lastimg = img;
			}

			llBottom.addView(img);
		}

		listFragment = new ArrayList<Fragment>();

		listFragment.add(new FirstFragment());
		listFragment.add(new SecondFragment());
		listFragment.add(new ThirdFragment());

		vpShow.setAdapter(new RankFragmentAdapter(getSupportFragmentManager(), listFragment));
		
//		vpShow.addOnPageChangeListener(this);
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

		sp = getSharedPreferences("db", MODE_PRIVATE);

		isFirst = sp.getBoolean("isFirst", true);

		handler.sendEmptyMessageDelayed(1, 3000);
	}

	private void initView() {
		vpShow = (ViewPager) findViewById(R.id.vpShow);
		llBottom = (LinearLayout) findViewById(R.id.ll);
		imgFirst = (ImageView) findViewById(R.id.img_first);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}
}