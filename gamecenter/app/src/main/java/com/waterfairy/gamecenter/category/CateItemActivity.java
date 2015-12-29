package com.waterfairy.gamecenter.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.waterfairy.gamecenter.BaseGame.ClassicBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.activitys.Detail_RunkActivity;
import com.waterfairy.gamecenter.adapter.RankBaseAdapter;
import com.waterfairy.gamecenter.refresh.MyListener;
import com.waterfairy.gamecenter.refresh.PullToRefreshLayout;
import com.waterfairy.gamecenter.refresh.PullableListView;
import com.waterfairy.gamecenter.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author james
 */
public class CateItemActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public ImageView back, search;
    public TextView title;
    public PullableListView lv;
    protected String url;
    private String tagurl;
    private int page = 1;
    private List<ClassicBean.MsgEntity> list;
    int id;
    String bar;
    private PullToRefreshLayout pullToRefreshLayout;
    HttpUtils httpUtils;
    RankBaseAdapter rankBaseAdapter;
    private List<ClassicBean.TagEntity> taglist;
    List<Button> buttons;//taglist,存放按钮的数组;
    private Button lastButton;
    private boolean isfirst = true;
    RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate_item);
        pullToRefreshLayout = ((PullToRefreshLayout) findViewById(R.id.refresh_view));
        pullToRefreshLayout.setOnRefreshListener(new MyListener(){
            //加载更多
            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                super.onLoadMore(pullToRefreshLayout);
                page++;
                url = NetUtils.CATEITEM + "&id=" + id + "&page_index=" + page;
                httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result;
                        if (!TextUtils.isEmpty(result)) {
                            ClassicBean classicBean = new Gson().fromJson(result, ClassicBean.class);
                            try {
                                Thread.sleep(2000);
                                if (classicBean.getMsg() != null) {
                                    list.addAll(classicBean.getMsg());
                                    rankBaseAdapter.notifyDataSetChanged();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        e.printStackTrace();
                    }
                });

            }
            //刷新界面.
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                super.onRefresh(pullToRefreshLayout);
                page=1;
                lv.removeHeaderView(layout);
                send();
            }
        });

        //得到传过来了的id,以及title;
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        bar = intent.getStringExtra("title");
        init();
        //请求数据,
        httpUtils = new HttpUtils();
        send();
        lv.setOnItemClickListener(this);
    }
    public void bt_clcik() {
        //按钮的点击事件
        for (int k = 0; k < buttons.size(); k++) {
            buttons.get(k).setTag(k + 1);
            buttons.get(k).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (Integer) v.getTag();
                    if (isfirst) {
                        v.setBackgroundResource(R.drawable.bt_bg2);
                        lastButton = (Button) v;
                        isfirst = false;
                    } else {
                        lastButton.setBackgroundResource(R.drawable.bt_backgroud);
                        v.setBackgroundResource(R.drawable.bt_bg2);
                        lastButton = (Button) v;
                    }
                    //标签按钮的请求;
                    tagurl = url + "&subId=" + (tag);
                    Log.e("xxxxx", "" + tagurl);
                    httpUtils.send(HttpRequest.HttpMethod.GET, tagurl, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            String result = responseInfo.result;
                            ClassicBean baseGame = new Gson().fromJson(result, ClassicBean.class);
                            list = baseGame.getMsg();
                            rankBaseAdapter = new RankBaseAdapter(list, CateItemActivity.this);
                            lv.setAdapter(rankBaseAdapter);
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            e.printStackTrace();
                        }
                    });
                }
            });
        }
    }
    public void send(){
        url = NetUtils.CATEITEM + "&id=" + id + "&page_index=" + page;
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                    ClassicBean baseGame = new Gson().fromJson(result, ClassicBean.class);
                    if (baseGame.getTag()!=null){
                        taglist = baseGame.getTag();
                    }
                    if (baseGame.getMsg()!=null){
                        list = baseGame.getMsg();
                        setTag();
                        rankBaseAdapter = new RankBaseAdapter(list, CateItemActivity.this,false);
                        lv.addHeaderView(layout);
                        lv.setAdapter(rankBaseAdapter);
                    }
                    bt_clcik();//标签按钮的点击请求方法,更换数据源,
            }
            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
            }
        });
    }

    public  void setTag(){
        //获取屏幕大小，以合理设定 按钮 大小及位置
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        //自定义layout组件
        layout = new RelativeLayout(CateItemActivity.this);
        int j = -1;
        buttons = new ArrayList<Button>();
        for (int i = 0; i < taglist.size(); i++) {
            buttons.add(new Button(CateItemActivity.this));
            buttons.get(i).setText(taglist.get(i).getName());
            buttons.get(i).setTextSize(10);
            buttons.get(i).setBackgroundResource(R.drawable.bt_backgroud);
            RelativeLayout.LayoutParams btParams = new RelativeLayout.LayoutParams((width - 25) / 4, 50);
            //设置按钮的宽度和高度
            if (i % 4 == 0) {
                j++;
            }
            btParams.leftMargin = 10 + ((width - 25) / 4 + 10) * (i % 4); //横坐标定位
            btParams.topMargin = 10 + 55 * j; //纵坐标定位
            layout.addView(buttons.get(i), btParams); //将按钮放入layout组件
        }
    }

    public void init() {
        back = (ImageView) findViewById(R.id.classic_back);
        search = (ImageView) findViewById(R.id.classic_search);
        title = (TextView) findViewById(R.id.classic_title);
        lv = (PullableListView) findViewById(R.id.classic_lv);
        title.setText(bar);
        back.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.classic_back:
                finish();
                break;
            case R.id.classic_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String string = ((TextView) view.findViewById(R.id.tv_id)).getText().toString();
        int num = Integer.parseInt(string);
        Intent intent = new Intent(this, Detail_RunkActivity.class);
        intent.putExtra("id", num);
        startActivity(intent);
    }

}
