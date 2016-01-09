package com.waterfairy.gamecenter.shouye;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;

import com.waterfairy.gamecenter.MainActivity;
import com.waterfairy.gamecenter.R;

/**
 * Created by zhaohang on 2015/10/30.
 */
public class ThirdImgFragment extends ShouYeBaseFragment {
    @Override
    public void init() {
        //给viewpager第三个页面设置图片

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.mipmap.page3);
        //将imagebutton设置为可见的
        ibt.setVisibility(View.VISIBLE);
        //设置监听,跳转界面
        ibt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getActivity().getSharedPreferences("db",getActivity().MODE_PRIVATE);
                sharedPreferences.edit().putBoolean("isfirst",false).apply();
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();//销毁页面;
            }
        });
    }
}
