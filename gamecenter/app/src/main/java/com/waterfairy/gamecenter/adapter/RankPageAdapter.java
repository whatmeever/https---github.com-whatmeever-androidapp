package com.waterfairy.gamecenter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 *
 * @author james
 */
public class RankPageAdapter extends FragmentPagerAdapter {
    List<Fragment>list;
    public RankPageAdapter(FragmentManager fm,List<Fragment>list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
