package com.waterfairy.gamecenter.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.adapter.RankPageAdapter;
import com.waterfairy.gamecenter.category.SearchActivity;
import com.waterfairy.gamecenter.fragment.GiftFragment;
import com.waterfairy.gamecenter.fragment.MyGiftFragment;

import java.util.ArrayList;
import java.util.List;

public class ActivityListActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private  static ViewPager viewPager;
    private ImageView back,search;
    private TextView tv1,tv2;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_list);
        init();
    }
    public void init(){
        viewPager= (ViewPager) findViewById(R.id.main_gift_vp);
        back= (ImageView) findViewById(R.id.main_gift_back);
        search= (ImageView) findViewById(R.id.main_gift_search);
        tv1= (TextView) findViewById(R.id.main_gift_tv1);
        tv2= (TextView) findViewById(R.id.main_gift_tv2);

        list=new ArrayList<>();
        list.add(new GiftFragment());
        list.add(new MyGiftFragment());

        RankPageAdapter rankPageAdapter=new RankPageAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(rankPageAdapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(this);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        back.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_gift_back:
                finish();
                break;
            case R.id.main_gift_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;

            case R.id.main_gift_tv1:
                //F48D35选中是的颜色
                if (viewPager.getCurrentItem()==1){
                    viewPager.setCurrentItem(0);
                    tv1.setBackgroundColor(Color.parseColor("#F48D35"));
                    tv2.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                break;
            case R.id.main_gift_tv2:
                if (viewPager.getCurrentItem()==0){
                    viewPager.setCurrentItem(1);
                    tv2.setBackgroundColor(Color.parseColor("#F48D35"));
                    tv1.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                tv1.setBackgroundColor(Color.parseColor("#F48D35"));
                tv2.setBackgroundColor(Color.parseColor("#ffffff"));

                break;
            case 1:
                tv2.setBackgroundColor(Color.parseColor("#F48D35"));
                tv1.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
