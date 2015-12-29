package com.waterfairy.gamecenter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.waterfairy.gamecenter.BaseGame.NetGameBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.activitys.ActivityListActivity;
import com.waterfairy.gamecenter.activitys.Detail_RunkActivity;
import com.waterfairy.gamecenter.activitys.LuntanActivity;
import com.waterfairy.gamecenter.activitys.NewServerActivity;
import com.waterfairy.gamecenter.activitys.TopicListActivity;
import com.waterfairy.gamecenter.adapter.NetgameAdapter;
import com.waterfairy.gamecenter.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author james
 */
public class NetgameFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    //view
    private static int curPos;
    private View view;
    private ListView listView;
    private TextView cycle1, cycle2, cycle3, lastCycle;
    //headview
    private View headerView;
    private ViewPager vpNetgame;
    private LinearLayout lin1ItemNetgame;
    private ImageView img1ItemLin1Game;
    private TextView text1ItemLin1Game;
    private LinearLayout lin2ItemNetgame;
    private ImageView img2ItemLin2Game;
    private TextView text2ItemLin2Game;
    private LinearLayout lin3ItemNetgame;
    private ImageView img3ItemLin3Game;
    private TextView text3ItemLin3Game;
    private LinearLayout lin4ItemNetgame;
    private ImageView img4ItemLin4Game;
    private TextView text4ItemLin4Game;
    private TextView cycle1Move;
    private TextView cycle2Move;
    private TextView cycle3Move;
    //footview
    private View listviewFoot;
    //adapter
    private NetgameAdapter adapter;
    List<NetGameBean.RecommendEntity> list;
    //data
    private float fix;
    //utils
    private HttpUtils httpUtils;
    private BitmapUtils bitmapUtils;
    private Gson gson;
    //path
    private String path;
    //getactivity
    public static FragmentActivity activity;
    //上拉刷新
    private int page = 1;
    private TextView tog;
    private ImageView img_tog;
    private NetGameBean netGameBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = getActivity();
        view = inflater.inflate(R.layout.fragment_netgame, container, false);
        headerView = LayoutInflater.from(activity).inflate(R.layout.header_netgame, null);
        //初始化view
        initView();
        //初始化数据
        initData();
        //数据请求
        getJson(path, 1);
        return view;
    }

    private void getJson(String path, final int page) {
        path += page;
        httpUtils.send(HttpRequest.HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                netGameBean = gson.fromJson(responseInfo.result, NetGameBean.class);
//                Log.i("test", "onSuccess " + responseInfo.result);
                if (page == 1) {
                    setHeaderView(netGameBean.getAdinfo(), netGameBean.getChart());
                }
                list.addAll(netGameBean.getRecommend());
                listView.removeFooterView(listviewFoot);
                adapter.notifyDataSetChanged();
                img_tog.setImageBitmap(null);
                img_tog.clearAnimation();
                isReady = false;
//                Log.i("test5", "onSuccess " + adapter.getCount());
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    private void setHeaderView(final List<NetGameBean.AdinfoEntity> adinfo, final List<NetGameBean.ChartEntity> chart) {
        //滚动
        final List<ImageView> imgList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(activity);
            bitmapUtils.display(imageView, adinfo.get(i).getPicUrl());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(this);
            imgList.add(imageView);
        }
//        Log.i("test4", "setHeaderView--size " + imgList.size());
        vpNetgame.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = imgList.get(position % 3);
                ViewGroup viewGroup;
                if ((viewGroup = (ViewGroup) view.getParent()) != null) {
                    viewGroup.removeView(view);
                }
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
            }
        });
        handler.sendEmptyMessageDelayed(0, 5000);
        //chart
        bitmapUtils.display(img1ItemLin1Game, chart.get(0).getPicUrl());
        text1ItemLin1Game.setText(chart.get(0).getTitle());
        bitmapUtils.display(img2ItemLin2Game, chart.get(1).getPicUrl());
        text2ItemLin2Game.setText(chart.get(1).getTitle());
        bitmapUtils.display(img3ItemLin3Game, chart.get(2).getPicUrl());
        text3ItemLin3Game.setText(chart.get(2).getTitle());
        bitmapUtils.display(img4ItemLin4Game, chart.get(3).getPicUrl());
        text4ItemLin4Game.setText(chart.get(3).getTitle());

    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
//                Log.i("test1", "handleMessage " + curPos);
                vpNetgame.setCurrentItem(++curPos);
                handler.sendEmptyMessageDelayed(0, 5000);

            }

        }
    };

    private void initData() {

        //display
        DisplayMetrics display = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(display);
        fix = display.density;

        //adapter
        list = new ArrayList<>();
        adapter = new NetgameAdapter(activity, fix, list, "netgame");
        //listview
        listView.setDivider(null);
        listView.setDividerHeight((int) (6 * fix));
        //添加headerview
        listView.addHeaderView(headerView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
        //utils
        httpUtils = new HttpUtils();
        bitmapUtils = new BitmapUtils(activity);
        gson = new Gson();
        //path
        path = NetUtils.HTTPNETGAMES + "elapsedtime=" + NetUtils.getCurrentTime() + "&appVersion=28&page_index=";
    }

    private void initView() {
        //view
        listView = (ListView) view.findViewById(R.id.listvie_netgame);
//footview
        listviewFoot = LayoutInflater.from(activity).inflate(R.layout.listview_foot, null);
        //上拉加载
        tog = (TextView) listviewFoot.findViewById(R.id.tog);
        img_tog = (ImageView) listviewFoot.findViewById(R.id.img_tog);
        //headerView
        vpNetgame = (ViewPager) headerView.findViewById(R.id.vp_netgame);
        vpNetgame.setOnPageChangeListener(this);
        lin1ItemNetgame = (LinearLayout) headerView.findViewById(R.id.lin1_item_netgame);
        lin1ItemNetgame.setOnClickListener(this);
        img1ItemLin1Game = (ImageView) headerView.findViewById(R.id.img1_item_lin1_game);
        text1ItemLin1Game = (TextView) headerView.findViewById(R.id.text1_item_lin1_game);
        lin2ItemNetgame = (LinearLayout) headerView.findViewById(R.id.lin2_item_netgame);
        lin2ItemNetgame.setOnClickListener(this);
        img2ItemLin2Game = (ImageView) headerView.findViewById(R.id.img2_item_lin2_game);
        text2ItemLin2Game = (TextView) headerView.findViewById(R.id.text2_item_lin2_game);
        lin3ItemNetgame = (LinearLayout) headerView.findViewById(R.id.lin3_item_netgame);
        lin3ItemNetgame.setOnClickListener(this);
        img3ItemLin3Game = (ImageView) headerView.findViewById(R.id.img3_item_lin3_game);
        text3ItemLin3Game = (TextView) headerView.findViewById(R.id.text3_item_lin3_game);
        lin4ItemNetgame = (LinearLayout) headerView.findViewById(R.id.lin4_item_netgame);
        lin4ItemNetgame.setOnClickListener(this);
        img4ItemLin4Game = (ImageView) headerView.findViewById(R.id.img4_item_lin4_game);
        text4ItemLin4Game = (TextView) headerView.findViewById(R.id.text4_item_lin4_game);
        cycle1 = (TextView) headerView.findViewById(R.id.cycle1_netgame);
        cycle2 = (TextView) headerView.findViewById(R.id.cycle2_netgame);
        cycle3 = (TextView) headerView.findViewById(R.id.cycle3_netgame);
        lastCycle = cycle1;
        //
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin1_item_netgame:
                Intent intent3 = new Intent(getActivity(), ActivityListActivity.class);
//                intent3.putExtra("id", chartEntities.get(2).getRelative().getId());
                startActivity(intent3);
                break;
            case R.id.lin2_item_netgame:
                Intent intent2 = new Intent(getActivity(), TopicListActivity.class);
                intent2.putExtra("name", "huodong");
                startActivity(intent2);
                break;
            case R.id.lin3_item_netgame:
                Intent intent = new Intent(getActivity(), NewServerActivity.class);
                startActivity(intent);
                break;
            case R.id.lin4_item_netgame:
                startActivity(new Intent(getActivity(), LuntanActivity.class));
                break;


        }

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.i("test", "onPageSelected --"+position);
        curPos = position;
        int posTemp = position % 3;
        switch (posTemp) {
            case 0:
                lastCycle.setBackgroundResource(R.drawable.bg_cycle_little);
                cycle1.setBackgroundResource(R.drawable.bg_cycle_big);
                lastCycle = cycle1;
                break;
            case 1:
                lastCycle.setBackgroundResource(R.drawable.bg_cycle_little);
                cycle2.setBackgroundResource(R.drawable.bg_cycle_big);
                lastCycle = cycle2;
                break;
            case 2:
                lastCycle.setBackgroundResource(R.drawable.bg_cycle_little);
                cycle3.setBackgroundResource(R.drawable.bg_cycle_big);
                lastCycle = cycle3;
                break;

        }

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NetGameBean.RecommendEntity entity = list.get(position - 1);
        Intent intent = new Intent(activity, Detail_RunkActivity.class);
        intent.putExtra("id", entity.getId());
        startActivity(intent);

    }

    private View lastView;
    boolean canFreash;
    private boolean isReady;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (canFreash) {
            View tempView = view.getChildAt(view.getChildCount() - 1);
            if (tempView.getBottom() == view.getHeight() && scrollState == SCROLL_STATE_IDLE) {
                //加载数据,,
                listView.setSelection(adapter.getCount() - 1);

                if (netGameBean.isHasNext()) {
                    getJson(path, ++page);
//                    Log.i("test7", "onScrollStateChanged " + page);
                    tog.setText("加载中...");
                    img_tog.setImageResource(R.mipmap.loading);
                    Animation rotateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotating);
                    img_tog.setAnimation(rotateAnimation);
                } else {
                    tog.setText("已经到最后啦...");
                }
                canFreash = false;

            }
            if (tempView.getBottom() > view.getHeight() && scrollState == SCROLL_STATE_IDLE) {
                listView.removeFooterView(listviewFoot);
                canFreash = false;
                isReady = false;
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //说明: addHeader   addFooter   会增加listview的数量
        if (netGameBean != null) {
            if (firstVisibleItem + visibleItemCount == totalItemCount) {
                lastView = view.getChildAt(visibleItemCount - 1);
                //1.确保不在加载状态,
                if (!isReady && ((lastView != null && lastView.getBottom() <= view.getHeight()))) {
                    //添加footer
                    listView.addFooterView(listviewFoot);
                    isReady = true;
//                    isHasFoot = true;
                    //继续上拉刷新
                    tog.setText("继续上拉刷新");
                    canFreash = true;
                }
            }

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeMessages(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
    }
}
