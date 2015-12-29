package com.waterfairy.gamecenter.fragment;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.waterfairy.gamecenter.BaseGame.DetaliBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.adapter.RankPageAdapter;
import com.waterfairy.gamecenter.utils.NetUtils;
import com.waterfairy.gamecenter.utils.Tools;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author james
 */
public class Rank_Base_DetailFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager viewPager;
    private Rank_Detail_FirstFragment firstFragment;
    private Rank_Detail_SecondFragment secondFragment;
    private List<Fragment> list;
    private ImageView iv_icon;
    private TextView tv_guan;
    private TextView tv_download,tv_type,tv_size,tv_number;
    private TextView tv1,tv2,tv;
    private ImageView iv1,iv2,iv3,iv4,iv5;
    public static String date;
    public static String versonName;
    public static String desc;
    public static List<DetaliBean.CommentEntity>CommentList;
    public static String screenshot;
    public static List<DetaliBean.RelatedEmtity>RelateList;
    public static int id,number;
    public static DetaliBean.Comment_SC comment_sc;
    private RelativeLayout relativeLayout,relativeLayout2;
    private ImageView footImage;
    private View view;
    private AnimationDrawable animationDrawable;
    public static RelativeLayout Relative;
    public static ViewPager maxViewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rank_base_detail, container, false);
        viewPager= (ViewPager) view.findViewById(R.id.viewpager);
        iv1= (ImageView) view.findViewById(R.id.iv1);
        iv2= (ImageView) view.findViewById(R.id.iv2);
        iv3= (ImageView) view.findViewById(R.id.iv3);
        iv4= (ImageView) view.findViewById(R.id.iv4);
        iv5= (ImageView) view.findViewById(R.id.iv5);
        tv1= (TextView) view.findViewById(R.id.tv1);
        tv2= (TextView) view.findViewById(R.id.tv2);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv1.setClickable(true);
        tv2.setClickable(true);
        tv= (TextView) view.findViewById(R.id.tv);
        Relative= (RelativeLayout) view.findViewById(R.id.Relative);
        relativeLayout= (RelativeLayout) view.findViewById(R.id.rl_comment);
        relativeLayout2= (RelativeLayout) view.findViewById(R.id.rll);
        maxViewPager= (ViewPager) view.findViewById(R.id.maxView);
        relativeLayout2.setVisibility(View.GONE);
        Bundle arguments = getArguments();
        viewPager.addOnPageChangeListener(this);
        footImage = (ImageView) view.findViewById(R.id.iv_anim);
        footImage.setImageResource(R.drawable.anim_loading);
        animationDrawable= (AnimationDrawable) footImage.getDrawable();
        animationDrawable.start();
        id= arguments.getInt("id");
        iv_icon= (ImageView) view.findViewById(R.id.iv_icon);
        tv_guan= (TextView) view.findViewById(R.id.iv_guan);
        tv_download= (TextView) view.findViewById(R.id.tv_download);
        tv_type= (TextView) view.findViewById(R.id.tv_type);
        tv_size= (TextView) view.findViewById(R.id.tv_size);
        tv_number= (TextView) view.findViewById(R.id.tv_number);
        HttpUtils httpUtils=new HttpUtils();
        String Url= NetUtils.HTTPDETAIL+"id="+id;
        httpUtils.send(HttpRequest.HttpMethod.GET, Url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                animationDrawable.stop();
                footImage.setVisibility(View.GONE);
                tv.setVisibility(View.GONE
                );
                relativeLayout2.setVisibility(View.VISIBLE);
                //fastJson解析；
                CommentList = new ArrayList<DetaliBean.CommentEntity>();
                RelateList = new ArrayList<DetaliBean.RelatedEmtity>();
                JSONObject jsonObject = JSON.parseObject(responseInfo.result);
                comment_sc = jsonObject.getObject("comment_sc", DetaliBean.Comment_SC.class);
                DetaliBean.Game game = jsonObject.getObject("game", DetaliBean.Game.class);
                JSONArray comment = jsonObject.getJSONArray("comment");
                JSONArray related = jsonObject.getJSONArray("related");
                if (comment != null || related != null || game != null) {
                    //便利集合;
                    for (int i = 0; i < comment.size(); i++) {
                        CommentList.add(comment.getObject(i, DetaliBean.CommentEntity.class));
                    }
                    for (int j = 0; j < related.size(); j++) {
                        RelateList.add(related.getObject(j, DetaliBean.RelatedEmtity.class));
                    }
                    //设置数据;
                    tv_guan.setText(game.getName());
                    int download = game.getDownload();
                    if (download >= 10000) {
                        tv_download.setText(download / 10000 + "万人在玩/");
                    }
                    tv_type.setText(game.getType() + "/");
                    tv_size.setText(game.getSize() / 1024 + "MB");
                    double comment1 = game.getComment();
                    Tools.GameDate(comment1, iv1, iv2, iv3, iv4, iv5);
                    String imgUrl = game.getIcon();
                    //设置图片;-->Application;
                    BitmapUtils bitmaputils = new BitmapUtils(getActivity());
                    bitmaputils.display(iv_icon, imgUrl);
                    versonName = game.getVersonName();
                    screenshot = game.getScreenshot();
                    date = game.getDate();
                    desc = game.getDesc();
                    //都要设置在Fragment->创建之前;
                    number = comment_sc.getScore_1() + comment_sc.getScore_2() + comment_sc.getScore_3() + comment_sc.getScore_4() + comment_sc.getScore_5();
                    firstFragment = new Rank_Detail_FirstFragment();
                    secondFragment = new Rank_Detail_SecondFragment();
                    list = new ArrayList<>();
                    list.add(firstFragment);
                    list.add(secondFragment);
                    RankPageAdapter rankPageAdapter = new RankPageAdapter(getChildFragmentManager(), list);
                    viewPager.setAdapter(rankPageAdapter);
                    number = number + 3;
                    tv_number.setText("(" + number + ")");
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
            }

        });
        return view;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                relativeLayout.setBackgroundColor(Color.WHITE);
                tv1.setBackgroundColor(0xFFE6DE69);
                break;
            case 1:
                tv1.setBackgroundColor(Color.WHITE);
                relativeLayout.setBackgroundColor(0xFFE6DE69);
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
        }
    }
}
