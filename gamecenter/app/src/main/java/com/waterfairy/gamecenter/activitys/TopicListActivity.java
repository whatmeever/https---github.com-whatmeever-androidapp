package com.waterfairy.gamecenter.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.waterfairy.gamecenter.BaseGame.TopicListBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.adapter.TopicListAdapter;
import com.waterfairy.gamecenter.category.SearchActivity;
import com.waterfairy.gamecenter.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author james
 */
public class TopicListActivity extends AppCompatActivity implements View.OnClickListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {
    private static final String TAG = "test";
    private ImageView backBase1;
    private TextView titleBase1;
    private ImageView searchBase1;
    private ListView listView;
    private BitmapUtils bitmapUtils;
    private HttpUtils utils;
    private Gson gson;
    private TopicListBean topicListBean;
    private List<TopicListBean.MsgEntity> list;
    private TopicListAdapter adapter;
    private String path;
    private int page = 1;
    private String name;
    private View listviewFoot;
    private TextView tog;
    private ImageView img_tog;

    private void assignViews() {
        DisplayMetrics display=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        backBase1 = (ImageView) findViewById(R.id.back_base1);
        titleBase1 = (TextView) findViewById(R.id.title_base1);
        searchBase1 = (ImageView) findViewById(R.id.search_base1);
        backBase1.setOnClickListener(this);
        searchBase1.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.list_base);
        listView.addFooterView(new View(this));
        listView.setDivider(null);
        listView.setDividerHeight((int) (display.density*6));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_game);
        listviewFoot = LayoutInflater.from(this).inflate(R.layout.listview_foot, null);
        tog = (TextView) listviewFoot.findViewById(R.id.tog);
        img_tog = (ImageView) listviewFoot.findViewById(R.id.img_tog);

        assignViews();
        list = new ArrayList<>();
        adapter = new TopicListAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
        utils = new HttpUtils();
        gson = new Gson();
        name = getIntent().getStringExtra("name");
        if (name != null) {
            if (name.equals("huodong")) {
                path = NetUtils.ACTIVITYLIST + "type=0&";
                titleBase1.setText("活动");
            } else if (name.equals("zhuanti")) {
                path = NetUtils.TOPICLIST;
                titleBase1.setText("游戏专题");
            }
        }

        getJson(path, page, null);


    }

    public void getJson(String path, final int page, String parameters) {
        utils = new HttpUtils();
        gson = new Gson();
        String parmeter = path + "elapsedtime=" + NetUtils.getCurrentTime() + "&appVersion=28";
        if (page != 0) {
            parmeter += "&page_index=" + page;
        }
        if (parameters != null) {
            parmeter += parameters;
        }
        utils.send(HttpRequest.HttpMethod.GET, parmeter, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                topicListBean = gson.fromJson(responseInfo.result, TopicListBean.class);
                list.addAll(topicListBean.getMsg());

                if (page != 1) {
                    img_tog.setImageBitmap(null);
                    img_tog.clearAnimation();
                    listView.removeFooterView(listviewFoot);
                    isReady = false;
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(HttpException e, String s) {
//                Log.i("test", "utils getJson faile" + s);
            }
        });

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_base1) {
            finish();
        }
        if (v.getId() == R.id.search_base1) {
            startActivity(new Intent(this, SearchActivity.class));
        }
    }

    private boolean canFreash;
    private boolean isReady;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (canFreash) {
            View tempView = view.getChildAt(view.getChildCount() - 1);
            if (tempView.getBottom() == view.getHeight() && scrollState == SCROLL_STATE_IDLE) {
                //加载数据,,
                listView.setSelection(adapter.getCount() - 1);
                if (topicListBean.isHasNext()) {
                    getJson(path, ++page, null);
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

    private View lastView;

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (topicListBean != null) {
            if (firstVisibleItem + visibleItemCount == totalItemCount) {
                lastView = view.getChildAt(visibleItemCount - 1);
                //1.确保不在加载状态,
                if (!isReady && ((lastView != null && lastView.getBottom() <= view.getHeight()))) {
                    //添加footer
                    tog.setText("继续上拉加载");
                    listView.addFooterView(listviewFoot);
                    isReady = true;
                    canFreash = true;
                }
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TopicListBean.MsgEntity msgEntity = list.get(position);
        Intent intent = null;
        if (msgEntity.getEndDate() != null) {
            intent = new Intent(this, ActivityDetail.class);
        } else {
            intent = new Intent(this, TopicGameActivity.class);
        }

        intent.putExtra("id", msgEntity.getId());
        startActivity(intent);

    }
}
