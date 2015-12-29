package com.waterfairy.gamecenter.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.waterfairy.gamecenter.BaseGame.TopicGameBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.adapter.TopicAdapter;
import com.waterfairy.gamecenter.category.SearchActivity;
import com.waterfairy.gamecenter.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author james
 */
public class TopicGameActivity extends AppCompatActivity implements View.OnClickListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener  {
    private View headView;
    private ImageView back;
    private ImageView search;
    private TextView titleBase1;
    private ListView listView;
    private HttpUtils utils;
    private TopicGameBean topicGame;
    private Gson gson;
    private List<TopicGameBean.MsgEntity> list;
    private TopicGameBean.InfoEntity infoEntity;
    private TopicAdapter adapter;
    private ImageView imageBase;
    private TextView contentBase;
    private BitmapUtils bitmapUtils;
    private int id;//输送过来的数据,用于请求
    private String title;//判断页面源
    private String path;
    private int page = 1;
    private View listviewFoot;
    private TextView tog;
    private ImageView img_tog;
    private boolean isHasHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_game);

        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
        assignViews();
        initData();
        getJson(path, page, id, null);

    }

    private void initData() {
        bitmapUtils = new BitmapUtils(this);
    }

    private void initHeadView() {
        imageBase = (ImageView) headView.findViewById(R.id.image_base);
        contentBase = (TextView) headView.findViewById(R.id.content_base);
    }

    private void assignViews() {
        DisplayMetrics display=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        headView = LayoutInflater.from(this).inflate(R.layout.lin_base, null);
        listviewFoot = LayoutInflater.from(this).inflate(R.layout.listview_foot, null);
        tog = (TextView) listviewFoot.findViewById(R.id.tog);
        img_tog = (ImageView) listviewFoot.findViewById(R.id.img_tog);
        titleBase1 = (TextView) findViewById(R.id.title_base1);
        titleBase1.setText(title);
        listView = (ListView) findViewById(R.id.list_base);
        listView.setOnScrollListener(this);
        listView.setDivider(null);
        listView.setDividerHeight((int) (display.density*6));
        //返回 搜索
        back = (ImageView) findViewById(R.id.back_base1);
        search = (ImageView) findViewById(R.id.search_base1);
        back.setOnClickListener(this);
        back.setFocusable(false);
        search.setOnClickListener(this);
        search.setFocusable(false);
        listView.setOnItemClickListener(this);

        if ("新品速递".equals(title)) {
            isHasHeader=false;
            path = NetUtils.NEWGAMELIST;
        } else if ("每日一荐".equals(title)) {
            path = NetUtils.RECOMMENDHISTORY;
        } else {
            isHasHeader=true;
            path = NetUtils.CATETOPIC;
            initHeadView();
            listView.addHeaderView(headView, null, false);
        }

        listView.addFooterView(new View(this), null, false);
        list = new ArrayList<>();
        adapter = new TopicAdapter(this, list);
        listView.setAdapter(adapter);
    }

    public void getJson(String path, int index, int id, String parameters) {
        utils = new HttpUtils();
        gson = new Gson();
        String parmeter = path + "elapsedtime=" + NetUtils.getCurrentTime() + "&appVersion=28";
        if (id != 0) {
            parmeter += "&id=" + id;
        }
        if (index != 0) {
            parmeter += "&page_index=" + index;
        }
        if (parameters != null) {
            parmeter += parameters;
        }
        utils.send(HttpRequest.HttpMethod.GET, parmeter, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                topicGame = gson.fromJson(responseInfo.result, TopicGameBean.class);
                infoEntity = topicGame.getInfo();
                if (infoEntity != null) {
                    titleBase1.setText(infoEntity.getName());
                    bitmapUtils.display(imageBase, infoEntity.getIcon());
                    contentBase.setText(infoEntity.getDesc());
                    contentBase.setBackgroundResource(R.drawable.bg_content_base);
                }
                list.addAll(topicGame.getMsg());
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
//        Log.i("test", "onClick ");
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
                if (topicGame.isHasNext()) {
                    getJson(path, ++page, id, null);
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
        if (topicGame != null) {
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
        if (isHasHeader){
            position=position-1;
        }
        TopicGameBean.MsgEntity entity = list.get(position);
        Intent intent1 = new Intent(this, Detail_RunkActivity.class);
        intent1.putExtra("id", entity.getId());
        startActivity(intent1);

    }


}
