package com.waterfairy.gamecenter.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.waterfairy.gamecenter.BaseGame.NetGameBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.adapter.NetgameAdapter;
import com.waterfairy.gamecenter.category.SearchActivity;
import com.waterfairy.gamecenter.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author james
 */
public class NetGameMoreActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener, View.OnClickListener {
    private ListView listView;
    private View listviewFoot;
    private NetgameAdapter adapter;
    List<NetGameBean.RecommendEntity> list;
    private String path;
    private int page = 1;
    private HttpUtils utils;
    private JSONObject json;
    private TextView title;
    //上拉刷新
    private TextView tog;
    private ImageView img_tog;
    private  JSONObject jsonObject;
    //bar
    private ImageView back;
    private ImageView search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_game_more);
        back= (ImageView) findViewById(R.id.back_netmore);
        search= (ImageView) findViewById(R.id.search_netmore);
        back.setOnClickListener(this);
        search.setOnClickListener(this);
        title= (TextView) findViewById(R.id.title_netmore);
        title.setText("网游新品");
        DisplayMetrics display=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        listView = (ListView) findViewById(R.id.listvie_netgame_more);
        listView.setDivider(null);
        listView.setDividerHeight((int) (display.density * 6));
        list = new ArrayList<>();
        utils = new HttpUtils();
        adapter = new NetgameAdapter(this, 0, list, "netgamenew");
        listviewFoot = LayoutInflater.from(this).inflate(R.layout.listview_foot, null);
        //上拉加载
        tog = (TextView) listviewFoot.findViewById(R.id.tog);
        img_tog = (ImageView) listviewFoot.findViewById(R.id.img_tog);
        listView.addFooterView(listviewFoot);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);

        getJson(path, page);

    }



    private void getJson(String path, int page) {
        path = NetUtils.NEWNETGAMELIST + "elapsedtime=" + NetUtils.getCurrentTime() + "&appVersion=28&page_index=" + page;
        utils.send(HttpRequest.HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
//                Log.i("test", "onSuccess " + responseInfo.result);
                 jsonObject = JSON.parseObject(responseInfo.result);

                JSONArray msg = jsonObject.getJSONArray("msg");
//                Log.i("test6", "onSuccess " + msg.size());
                for (int i = 0; i < msg.size(); i++) {
                    list.add(msg.getObject(i, NetGameBean.RecommendEntity.class));
                }

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NetGameBean.RecommendEntity entity = list.get(position);
        Intent intent=new Intent(this, Detail_RunkActivity.class);
        intent.putExtra("id",entity.getId());
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

                if ((jsonObject.getBoolean("hasNext")) ){
                    getJson(path, ++page);
//                    Log.i("test", "onScrollStateChanged "+page);
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
        if (jsonObject!=null) {
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
        if (v.getId() == R.id.back_netmore) {
            finish();
        }
        if (v.getId() == R.id.search_netmore) {
            startActivity(new Intent(this, SearchActivity.class));
        }
    }
}
