package com.waterfairy.gamecenter.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
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

import java.util.List;

public class HotSearchActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText title;
    private ImageView back,search;
    private PullableListView listView;
    private  String string="三国";
    int page=1;
    private List<ClassicBean.MsgEntity> list;
    HttpUtils httpUtils;
    String url;
    RankBaseAdapter rankBaseAdapter;
    PullToRefreshLayout pullToRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_search);
        init();
        httpUtils = new HttpUtils();
        send();
        listView.setOnItemClickListener(this);
        pullToRefreshLayout= (PullToRefreshLayout) findViewById(R.id.hot_search_pull);
        pullToRefreshLayout.setOnRefreshListener(new MyListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                super.onRefresh(pullToRefreshLayout);
                page=1;
                send();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                super.onLoadMore(pullToRefreshLayout);
                load();
            }
        });
    }
    public void load(){
        url = NetUtils.HOTSEARCH+"&page_index="+page+"&search="+string;
        HttpHandler<String> send = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                ClassicBean classicBean = new Gson().fromJson(result, ClassicBean.class);
                if ( classicBean.getMsg()!=null){
                    list.addAll(classicBean.getMsg());
                    rankBaseAdapter.notifyDataSetChanged();
                }
                page++;
            }

            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
            }
        });
    }
    public void send(){
        url = NetUtils.HOTSEARCH+"&page_index="+page+"&search="+string;
        HttpHandler<String> send = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                ClassicBean classicBean = new Gson().fromJson(result, ClassicBean.class);
                if (classicBean.getMsg()!=null){
                    list = classicBean.getMsg();
                    rankBaseAdapter = new RankBaseAdapter(list, HotSearchActivity.this, true);
                    listView.setAdapter(rankBaseAdapter);
                    page++;
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
            }
        });
    }
    public  void init(){
        title= (EditText) findViewById(R.id.hot_search_title);
        back= (ImageView) findViewById(R.id.hot_search_back);
        search= (ImageView) findViewById(R.id.hot_search_image);
        listView= (PullableListView) findViewById(R.id.hot_search_lv);
        Intent intent = getIntent();
        if (intent!=null){
            string = intent.getStringExtra("title");
        }
        title.setText(string);
        back.setOnClickListener(this);
        search.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hot_search_back:
                finish();
                break;
            case R.id.hot_search_image:
                if (!TextUtils.isEmpty(title.getText().toString())){
                    string=title.getText().toString();
                    send();
                }
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int num = list.get(position).getId();
        Intent intent=new Intent(this,Detail_RunkActivity.class);
        intent.putExtra("id",num);
        startActivity(intent);
    }
}
