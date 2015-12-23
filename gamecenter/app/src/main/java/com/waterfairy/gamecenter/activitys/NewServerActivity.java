package com.waterfairy.gamecenter.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.waterfairy.gamecenter.BaseGame.NewServerBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.adapter.NewServerAdapter;
import com.waterfairy.gamecenter.category.SearchActivity;
import com.waterfairy.gamecenter.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

public class NewServerActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    private HttpUtils utils;
    private Gson gson;
    private String path;
    private int page = 1;
    private NewServerBean newServerBean;
    private List<NewServerBean.MsgEntity> list;
    private ListView listView;
    private ImageView backNewserver;
    private TextView titleNewserver;
    private ImageView searchNewserver;
    private View listviewFoot;
    private TextView tog;
    private ImageView img_tog;
    private NewServerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_server);
        assignViews();
        listviewFoot = LayoutInflater.from(this).inflate(R.layout.listview_foot, null);
        //上拉加载
        tog = (TextView) listviewFoot.findViewById(R.id.tog);
        img_tog = (ImageView) listviewFoot.findViewById(R.id.img_tog);
        listView.addFooterView(listviewFoot);
        utils = new HttpUtils();
        gson = new Gson();
        list = new ArrayList<>();
        adapter = new NewServerAdapter(list, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
        path = NetUtils.ACTIVITYLIST + "elapsedtime=" + NetUtils.getCurrentTime() + "&type=2&appVersion=28&page_index=";
        getJson(path, page);
    }

    private void getJson(String path, int page) {
        path += page;
//        Log.i("test8", "getJson "+path);
        utils.send(HttpRequest.HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
//                Log.i("test8", "onSuccess "+responseInfo.result);
                newServerBean = gson.fromJson(responseInfo.result, NewServerBean.class);
                list.addAll(newServerBean.getMsg());
                listView.removeFooterView(listviewFoot);
                adapter.notifyDataSetChanged();
                img_tog.setImageBitmap(null);
                img_tog.clearAnimation();
                isReady = false;
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    private void assignViews() {
        backNewserver = (ImageView) findViewById(R.id.back_newserver);
        titleNewserver = (TextView) findViewById(R.id.title_newserver);
        titleNewserver.setText("开服");
        searchNewserver = (ImageView) findViewById(R.id.search_newserver);
        listView = (ListView) findViewById(R.id.lv_newserver);
        backNewserver.setOnClickListener(this);
        searchNewserver.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewServerBean.MsgEntity entity = list.get(position);
        Intent intent = new Intent(this, NewServerDetailActivty.class);
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

                if (newServerBean.isHasNext()) {
                    getJson(path, ++page);
//                    Log.i("test", "onScrollStateChanged " + page);
                    tog.setText("加载中...");
                    img_tog.setImageResource(R.mipmap.loading);
                    Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotating);
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
        if (newServerBean != null) {
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
    public void onClick(View v) {
        if (v.getId() == R.id.back_newserver) {
            finish();
        }
        if (v.getId() == R.id.search_newserver) {
            startActivity(new Intent(this, SearchActivity.class));
        }
    }

}
