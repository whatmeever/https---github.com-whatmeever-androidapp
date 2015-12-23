package com.waterfairy.gamecenter.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.adapter.RankPageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/16.
 */
public class RankFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
   private ViewPager viewPager;
    private Rank_FirstFragment rank_firstFragment;
    private Rank_SecondFragment rank_secondFragment;
    private Rank_ThridFragment rank_thridFragment;
    private List<Fragment>list=new ArrayList<>();
    private TextView tv1,tv2,tv3,lastText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        viewPager= (ViewPager) view.findViewById(R.id.viewpager);
        tv1= (TextView) view.findViewById(R.id.tv1);
        tv2= (TextView) view.findViewById(R.id.tv2);
        tv3= (TextView) view.findViewById(R.id.tv3);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv1.setClickable(true);
        tv2.setClickable(true);
        tv3.setClickable(true);
        tv1.setBackgroundColor(0xFFE6DE69);
        lastText=tv1;
        rank_firstFragment=new Rank_FirstFragment();
        rank_secondFragment=new Rank_SecondFragment();
        rank_thridFragment=new Rank_ThridFragment();
        list.add(rank_firstFragment);
        list.add(rank_secondFragment);
        list.add(rank_thridFragment);
        RankPageAdapter rankPageAdapter=new RankPageAdapter(getChildFragmentManager(),list);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(rankPageAdapter);
        viewPager.addOnPageChangeListener(this);
        return view;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                lastText.setBackgroundColor(Color.WHITE);
                tv1.setBackgroundColor(0xFFE6DE69);
                lastText=tv1;
                break;
            case 1:
                lastText.setBackgroundColor(Color.WHITE);
                tv2.setBackgroundColor(0xFFE6DE69);
                lastText=tv2;
                break;
            case 2:
                lastText.setBackgroundColor(Color.WHITE);
                tv3.setBackgroundColor(0xFFE6DE69);
                lastText=tv3;
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv3:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
