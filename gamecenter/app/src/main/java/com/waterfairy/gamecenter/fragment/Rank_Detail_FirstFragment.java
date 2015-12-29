package com.waterfairy.gamecenter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.waterfairy.gamecenter.BaseGame.DetaliBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.activitys.Detail_RunkActivity;
import com.waterfairy.gamecenter.activitys.QuestionActivity;
import com.waterfairy.gamecenter.adapter.Rank_MaxAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author james
 */
public class Rank_Detail_FirstFragment extends Fragment implements View.OnClickListener {
    private ImageView iv_down;
    private TextView tv_desc,tv_versonName,tv_date;
    private boolean isDown=true;
    private int height;
    private WebView webView;
    private LinearLayout linearLayout,ll_scrollview;
    private float density;
    private HorizontalScrollView scrollView;
    private List<DetaliBean.RelatedEmtity>Relatelist;
    private TextView tv1,tv2,tv3,tv4;
    private ImageView iv1,iv2,iv3,iv4;
    private BitmapUtils bitmap;
    private Intent intent;
    private TextView tv_question;
    private int i;
    private int heightPixels;
    private int widthPixels;
    private RelativeLayout relativeLayout;
    private ViewPager viewPager;
    private List<ImageView>list=new ArrayList<>();
    private int position;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_rank_detail_first, container, false);
        iv_down= (ImageView) view.findViewById(R.id.iv_down);
        tv_desc= (TextView) view.findViewById(R.id.tv_desc);
        tv_versonName= (TextView) view.findViewById(R.id.versonName);
        tv_date= (TextView) view.findViewById(R.id.date);
        webView= (WebView) view.findViewById(R.id.webview);
        tv_question= (TextView) view.findViewById(R.id.tv_question);
        viewPager=Rank_Base_DetailFragment.maxViewPager;
        tv_question.setOnClickListener(this);
        relativeLayout=Rank_Base_DetailFragment.Relative;
        ll_scrollview= (LinearLayout) view.findViewById(R.id.ll_scrollview);
        linearLayout= (LinearLayout) view.findViewById(R.id.ll_desc);
        tv_versonName.setText(Rank_Base_DetailFragment.versonName);
        scrollView= (HorizontalScrollView) view.findViewById(R.id.hor_scrollview);
        tv_date.setText(Rank_Base_DetailFragment.date);
        String desc = Rank_Base_DetailFragment.desc;
        scrollView.setHorizontalScrollBarEnabled(false);
        if(desc!=null) {
            i = desc.indexOf("<");
        }
        if(i>0) {
            tv_desc.setText(desc.substring(0, i));
            webView.loadData(desc.substring(i), "text/html;charset=utf-8", null);
        }else {
            tv_desc.setText(desc);
            webView.setVisibility(View.GONE);
        }
        linearLayout.setOnClickListener(this);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        heightPixels=getActivity().getWindowManager().getDefaultDisplay().getHeight();
        widthPixels=getActivity().getWindowManager().getDefaultDisplay().getWidth();

        density = displayMetrics.density;//得到该手机的像素密度；
        height = (int) (80*density);
        iv_down.setOnClickListener(this);
        String screenshot = Rank_Base_DetailFragment.screenshot;
        if(screenshot!=null) {
            String[] split = screenshot.split("###");
            for (int j = 0; j < split.length; j++) {
                final ImageView imag = new ImageView(getActivity());
                final ImageView imag1 = new ImageView(getActivity());
                imag.setLayoutParams(new LinearLayout.LayoutParams((int) (130 * density), (int) (200 * density)));
                imag1.setLayoutParams(new LinearLayout.LayoutParams(widthPixels, heightPixels));
                imag.setTag(j);//设置该view的Tag;
                bitmap = new BitmapUtils(getActivity());

                imag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setVisibility(View.VISIBLE);
                        relativeLayout.setVisibility(View.GONE);
                        position= (int) v.getTag();//得到该View的Tag();
                        viewPager.setCurrentItem(position);

                    }
                });
                imag1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        relativeLayout.setVisibility(View.VISIBLE);
                        viewPager.setVisibility(View.GONE);
                        viewPager.setCurrentItem(0);
                    }
                });
                bitmap.display(imag, split[j]);
                bitmap.display(imag1, split[j]);
                ll_scrollview.addView(imag);
                list.add(imag1);
            }
            Rank_MaxAdapter rank_maxAdapter=new Rank_MaxAdapter(list);
            viewPager.setAdapter(rank_maxAdapter);
        }
        Relatelist=Rank_Base_DetailFragment.RelateList;
        into(view);
        tv1.setText(Relatelist.get(0).getName());
        tv2.setText(Relatelist.get(1).getName());
        tv3.setText(Relatelist.get(2).getName());
        tv4.setText(Relatelist.get(3).getName());
        bitmap.display(iv1, Relatelist.get(0).getIcon());
        bitmap.display(iv2, Relatelist.get(1).getIcon());
        bitmap.display(iv3, Relatelist.get(2).getIcon());
        bitmap.display(iv4, Relatelist.get(3).getIcon());
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_desc:
            case R.id.iv_down:
            case R.id.webview:
                if(isDown) {
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    iv_down.setImageResource(R.mipmap.game_push_up_pressed);
                    isDown=false;
                }else {
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height));
                    iv_down.setImageResource(R.mipmap.game_point_arrow_press);
                    isDown=true;
                }
                break;
            case R.id.iv1:
                intent=new Intent(getActivity(),Detail_RunkActivity.class);
                intent.putExtra("id",Relatelist.get(0).getId());
                startActivity(intent);
                break;
            case R.id.iv2:
                intent=new Intent(getActivity(),Detail_RunkActivity.class);
                intent.putExtra("id",Relatelist.get(1).getId());
                startActivity(intent);
                break;
            case R.id.iv3:
                intent=new Intent(getActivity(),Detail_RunkActivity.class);
                intent.putExtra("id",Relatelist.get(2).getId());
                startActivity(intent);
                break;
            case R.id.iv4:
                intent=new Intent(getActivity(),Detail_RunkActivity.class);
                intent.putExtra("id",Relatelist.get(3).getId());
                startActivity(intent);
                break;
            case R.id.tv_question:
                //报告问题;
                startActivity(new Intent(getActivity(), QuestionActivity.class));
                break;
        }
    }
    public void into(View view){
        tv1= (TextView) view.findViewById(R.id.tv1);
        tv2= (TextView) view.findViewById(R.id.tv2);
        tv3= (TextView) view.findViewById(R.id.tv3);
        tv4= (TextView) view.findViewById(R.id.tv4);
        iv1= (ImageView) view.findViewById(R.id.iv1);
        iv1.setOnClickListener(this);
        iv2= (ImageView) view.findViewById(R.id.iv2);
        iv2.setOnClickListener(this);
        iv3= (ImageView) view.findViewById(R.id.iv3);
        iv3.setOnClickListener(this);
        iv4= (ImageView) view.findViewById(R.id.iv4);
        iv4.setOnClickListener(this);
    }
}
