
package com.waterfairy.gamecenter.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
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

import java.util.List;

/**
 *
 * @author james
 */
public class CateTopicActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView back,search,imageView;
    private TextView title,text;
    private PullableListView lv;
    private int  id;
    private int position;
    private String bar;
    private int page=1;
    private List<ClassicBean.MsgEntity> list;
    private TextView recomend_des;
    View view;
    RankBaseAdapter rankBaseAdapter;
    HttpUtils httpUtils;
    String url;
    private PullToRefreshLayout pullToRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate_topic);
        init();
        Intent intent = getIntent();
        id = intent.getIntExtra("pid", 0);
        send();
        pullToRefreshLayout = ((PullToRefreshLayout) findViewById(R.id.topic_pull));
        pullToRefreshLayout.setOnRefreshListener(new MyListener() {
            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                super.onLoadMore(pullToRefreshLayout);
                loadmore();
            }

            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                super.onRefresh(pullToRefreshLayout);
                page = 1;
                lv.removeHeaderView(view);
                send();
            }
        });

    }
    public void send(){
        url = NetUtils.CATETOPIC + "&id=" + id + "&page_index=" + page;
        httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                if (!TextUtils.isEmpty(result)){
                    ClassicBean baseGame = new Gson().fromJson(result, ClassicBean.class);
                    list = baseGame.getMsg();
                    if (baseGame.getMsg()!=null){
                        title.setText(baseGame.getInfo().getName() + "");
                        BitmapUtils bitmapUtils = new BitmapUtils(CateTopicActivity.this);
                        bitmapUtils.display(imageView, baseGame.getInfo().getIcon());
                        text.setText(baseGame.getInfo().getDesc() + "");
                        rankBaseAdapter = new RankBaseAdapter(list, CateTopicActivity.this, true);
                        lv.addHeaderView(view);
                        lv.setAdapter(rankBaseAdapter);
                    }
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
            }
        });
    }
    public void init() {
        back = (ImageView) findViewById(R.id.topic_back);
         search= (ImageView) findViewById(R.id.topic_search);
        view = LayoutInflater.from(this).inflate(R.layout.topic_header,null);
        imageView= (ImageView) view.findViewById(R.id.topichead_img);
        title= (TextView) findViewById(R.id.topic_title);
        text= (TextView)view.findViewById(R.id.topichead_text);
        lv= (PullableListView) findViewById(R.id.topic_lv);
        back.setOnClickListener(this);
        search.setOnClickListener(this);
        lv.setOnItemClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.topic_back:
                finish();
                break;
            case R.id.topic_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
    }

    public void loadmore() {
        page++;
        url = NetUtils.CATETOPIC + "&id=" + id + "&page_index=" + page;
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                    ClassicBean classicBean = new Gson().fromJson(result, ClassicBean.class);
                    try {
                        Thread.sleep(1000);
                        if (classicBean.getMsg()!=null){
                            list.addAll(classicBean.getMsg());
                            rankBaseAdapter.notifyDataSetChanged();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
            }
        });
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String string=((TextView) view.findViewById(R.id.tv_id)).getText().toString();
        int num = Integer.parseInt(string);
        Intent intent=new Intent(this,Detail_RunkActivity.class);
        intent.putExtra("id",num);
        startActivity(intent);
    }
}
