package com.waterfairy.gamecenter.shouye;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.waterfairy.gamecenter.MainActivity;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.adapter.RankPageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author james
 */
public class FirstActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager vp;
    private ImageView img;
    private LinearLayout linearLayout;
    private List<Fragment>  list;
    private boolean isFirst;//判断是不是第一次进入游戏
    private ImageView lastimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        vp= (ViewPager) findViewById(R.id.vp);
        img= (ImageView) findViewById(R.id.first_img);
        linearLayout= (LinearLayout) findViewById(R.id.ll);
        //读取存储数据的文件
        SharedPreferences share=getSharedPreferences("db",MODE_PRIVATE);
        //获取首次存储是的参数,如果没有默认是false;
        isFirst = share.getBoolean("isfirst", true);

        //延迟三秒,由imageview改为显示viewpager;
        handler.sendEmptyMessageDelayed(1, 3000);
    }
    public void init(){
        for (int i=0;i<3;i++){
            ImageView imageView=new ImageView(this);
            imageView.setBackgroundResource(R.drawable.white_bg);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(30, 15));
            if (i==0){
                imageView.setBackgroundResource(R.drawable.red_bg);
                lastimg=imageView;
            }
            linearLayout.addView(imageView);
        }

        list=new ArrayList<>();
        list.add(new FirstImgFragment());
        list.add(new SecondImgFragment());
        list.add(new ThirdImgFragment());
        vp.setAdapter(new RankPageAdapter(getSupportFragmentManager(), list));
        vp.addOnPageChangeListener(this);

    }
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //根据标记得到是否是第一次加载,如果是第一次加载,就加载viewpager
            if (isFirst) {
                img.setVisibility(ImageView.GONE);
                init();
            }else {//如果不是第一次加载,就直接跳到主界面,显示信息
                Intent intent=new Intent(FirstActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //将上一个界面的图标设成红色;
                lastimg.setBackgroundResource(R.drawable.white_bg);
        //根据位置得到当前的view
                View childAt = linearLayout.getChildAt(position);
        childAt.setBackgroundResource(R.drawable.red_bg);
        //将当前图片设成lastImageview,以备下次使用;
                lastimg= (ImageView) childAt;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
