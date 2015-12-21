package com.james.tvplay.activity;

import java.util.ArrayList;
import java.util.List;

import com.james.tvplay.R;
import com.james.tvplay.fragment.MyFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 
 * @author james
 *
 */
public class WorkActivity extends FragmentActivity {

	private ViewPager mVp;
	private PagerTabStrip mStrip;
//	private List<View> mList;
//	private String[] titles;
	private List<Fragment> fragmentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_work);

		mVp = (ViewPager) findViewById(R.id.vp);
		mStrip = (PagerTabStrip) findViewById(R.id.strip);

		// 设置导航标签的文字颜色
		mStrip.setTextColor(Color.WHITE);
		// 设置导航标签的背景色
		mStrip.setBackgroundColor(Color.GRAY);
		// 是否设置导航标签下面的下划线
		mStrip.setDrawFullUnderline(true);
		// 设置导航标签指示器的颜色
		mStrip.setTabIndicatorColor(Color.GREEN);
		// 设置导航标签的背景图.
//		mStrip.setBackgroundResource(R.drawable.ic_launcher);

		//	初始化数据
		initData();

//		titles = new String[] { "新闻", "生活", "会员", "少儿", "音乐" };

//		MyAdapter adapter = new MyAdapter();
//		mVp.setAdapter(adapter);
	}

	private void initData() {
//		mList = new ArrayList<View>();
//		LayoutInflater inflater = getLayoutInflater();
//		View view1 = inflater.inflate(R.layout.fragment_one_layout, null);
//		View view2 = inflater.inflate(R.layout.fragment_two_layout, null);
//		View view3 = inflater.inflate(R.layout.fragment_three_layout, null);
//		View view4 = inflater.inflate(R.layout.fragment_four_layout, null);
//		View view5 = inflater.inflate(R.layout.fragment_five_layout, null);
//		mList.add(view1);
//		mList.add(view2);
//		mList.add(view3);
//		mList.add(view4);
//		mList.add(view5);
		
		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(new MyFragment());
		fragmentList.add(new MyFragment());
		fragmentList.add(new MyFragment());
		fragmentList.add(new MyFragment());
		fragmentList.add(new MyFragment());
		
		MyAdapter adapter=new MyAdapter(getSupportFragmentManager(), fragmentList);
		mVp.setAdapter(adapter);
	}

//	class MyAdapter extends PagerAdapter {
//
//		@Override
//		public int getCount() {
//			return mList.size();
//		}
//
//		@Override
//		public boolean isViewFromObject(View arg0, Object arg1) {
//
//			return arg0 == arg1;
//		}
//
//		@Override
//		public Object instantiateItem(ViewGroup container, int position) {
////			if (mList.get(position) != null)
////				container.removeView(mList.get(position));
//			container.addView(mList.get(position));
//
//			return mList.get(position);
//		}
//
//		@Override
//		public void destroyItem(ViewGroup container, int position, Object object) {
//
//			container.removeView(mList.get(position));
//		}
//
//		// 设置ViewPager的标题
//		@Override
//		public CharSequence getPageTitle(int position) {
//
//			return titles[position];
//		}
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.work, menu);
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
}
