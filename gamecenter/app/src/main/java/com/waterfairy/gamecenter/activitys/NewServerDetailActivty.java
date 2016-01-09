package com.waterfairy.gamecenter.activitys;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.waterfairy.gamecenter.BaseGame.NewServerDetailBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.category.SearchActivity;
import com.waterfairy.gamecenter.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

public class NewServerDetailActivty extends AppCompatActivity implements View.OnClickListener {

    private ImageView backNewserverdetail;
    private TextView title;
    private ImageView searchNewserverdetail;
    private LinearLayout line;
    private TextView titleNewserverDetail;
    private TextView descNewserverDetail;
    private RelativeLayout topTopic;
    private ImageView iconTopic;
    private TextView titleTopic;
    private LinearLayout startTopicLin;
    private ImageView startTopic1;
    private ImageView startTopic2;
    private ImageView startTopic3;
    private ImageView startTopic4;
    private ImageView startTopic5;
    private TextView userCountTopic;
    private TextView apksizeTopic;
    private TextView downloadTopic;
    private Gson gson;
    private HttpUtils httpUtils;
    private BitmapUtils bitmapUtils;
    private String path;
    private List<ImageView> list;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_server_detail_activty);
        assignViews();
        gson = new Gson();
        httpUtils = new HttpUtils();
        bitmapUtils = new BitmapUtils(this);
        int id = getIntent().getIntExtra("id", 0);
        path = NetUtils.ACTIVITYDETAIL + "elapsedtime=" + NetUtils.getCurrentTime() + "&type=2&appVersion=28&id=" + id;
        getJson(path);

    }

    private void getJson(String path) {
        httpUtils.send(HttpRequest.HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                NewServerDetailBean newServerDetailBean = gson.fromJson(responseInfo.result, NewServerDetailBean.class);
                setView(newServerDetailBean);


            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    private void setView(NewServerDetailBean newServerDetailBean) {
        NewServerDetailBean.MsgEntity msg = newServerDetailBean.getMsg();
        NewServerDetailBean.MsgEntity.GameInfoEntity gameInfo = msg.getGameInfo();
        String titleStr=msg.getTitle();
        title.setText(titleStr);
        titleNewserverDetail.setText(titleStr);
        titleTopic.setText(titleStr);
        bitmapUtils.display(iconTopic,gameInfo.getIcon());
        descNewserverDetail.setText("开服时间:"+msg.getStartDate());
        webView.loadData(msg.getDesc(), "text/html;charset=UTF-8", null);
        downloadTopic.setHint(gameInfo.getApkurl());
        String countStr;
        int count = gameInfo.getDownload();
        if (count < 10000) {
            countStr = count + "人在玩";
        } else if (count < 100000000) {
            countStr = count / 10000 + "万人在玩";
        } else if (count < 1000000000) {
            countStr = count / 10000000 + "千万人在玩";
        } else {
            countStr = count / 100000000 + "亿人在玩";
        }
       userCountTopic.setText(countStr);
        apksizeTopic.setText("/" + gameInfo.getSize() / 1000 + "M");
        int num = (int)gameInfo.getComment();
        for (int i = 1; i <= 5; i++) {
            if (num >= i) {
                list.get(i - 1).setImageResource(R.mipmap.game_detail_comment_star_full);
            } else {
                list.get(i - 1).setImageResource(R.mipmap.game_detail_comment_star_empty);
            }
        }

    }


    private void assignViews() {
        webView= (WebView) findViewById(R.id.webview_newserver_detail);
        backNewserverdetail = (ImageView) findViewById(R.id.back_newserverdetail);
        title = (TextView) findViewById(R.id.title_newserverdetail);
        searchNewserverdetail = (ImageView) findViewById(R.id.search_newserverdetail);
        line = (LinearLayout) findViewById(R.id.line);
        titleNewserverDetail = (TextView) findViewById(R.id.title_newserver_detail);
        descNewserverDetail = (TextView) findViewById(R.id.desc_newserver_detail);
        topTopic = (RelativeLayout) findViewById(R.id.top_topic);
        iconTopic = (ImageView) findViewById(R.id.icon_topic);
        titleTopic = (TextView) findViewById(R.id.title_topic);
        startTopicLin = (LinearLayout) findViewById(R.id.start_topic_lin);
        startTopic1 = (ImageView) findViewById(R.id.start_topic_1);
        startTopic2 = (ImageView) findViewById(R.id.start_topic_2);
        startTopic3 = (ImageView) findViewById(R.id.start_topic_3);
        startTopic4 = (ImageView) findViewById(R.id.start_topic_4);
        startTopic5 = (ImageView) findViewById(R.id.start_topic_5);
        list=new ArrayList<>();
        list.add(startTopic1);
        list.add(startTopic2);
        list.add(startTopic3);
        list.add(startTopic4);
        list.add(startTopic5);
        userCountTopic = (TextView) findViewById(R.id.user_count_topic);
        apksizeTopic = (TextView) findViewById(R.id.apksize_topic);
        downloadTopic = (TextView) findViewById(R.id.download_topic);
        downloadTopic.setOnClickListener(this);
        backNewserverdetail.setOnClickListener(this);
        searchNewserverdetail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_newserverdetail) {
            finish();
        }
        if (v.getId() == R.id.search_newserverdetail) {
            startActivity(new Intent(this, SearchActivity.class));
        }
        if (v.getId()==R.id.download_topic){

//            Log.i("test", "onClick " + ((TextView) v).getHint());
        }
    }


}
