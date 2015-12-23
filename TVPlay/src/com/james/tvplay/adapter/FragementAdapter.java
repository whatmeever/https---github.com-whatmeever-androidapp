package com.james.tvplay.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 
 * @author james
 *
 */
public class FragementAdapter extends FragmentPagerAdapter {

	private List<Fragment> mList;
	private String[] titles = new String[] { "新闻", "生活", "会员", "少儿", "音乐" };
	
	public FragementAdapter(FragmentManager fm,List<Fragment> list) {
		super(fm);
		this.mList=list;
	}

	@Override
	public Fragment getItem(int position) {
		
		return mList.get(position);
	}

	@Override
	public int getCount() {
		
		return mList.size();
	}
	
	// 设置ViewPager的标题
	@Override
	public CharSequence getPageTitle(int position) {

		return titles[position];
	}
}