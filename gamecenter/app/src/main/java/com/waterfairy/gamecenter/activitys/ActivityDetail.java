package com.waterfairy.gamecenter.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
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
import com.waterfairy.gamecenter.BaseGame.ActivityDetailBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.category.SearchActivity;
import com.waterfairy.gamecenter.utils.NetUtils;

import java.util.List;

import static com.waterfairy.gamecenter.R.id.webview_activity_detail;

public class ActivityDetail extends AppCompatActivity implements View.OnClickListener {
    private HttpUtils httpUtils;
    private BitmapUtils bitmapUtils;
    private Gson gson;
    private ActivityDetailBean.MsgEntity msg;
    private List<ActivityDetailBean.MsgEntity.GameInfoEntities> gameInfos;
    private WebView webView;
    private ImageView imageView;
    private TextView title;
    private ImageView back;
    private ImageView search;
    private String path;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_detail);
        initView();
        httpUtils=new HttpUtils();
        bitmapUtils=new BitmapUtils(this);
        gson=new Gson();
        Intent intent = getIntent();
        type="1";
        getJson(NetUtils.ACTIVITYDETAIL,intent.getIntExtra("id",0),type);

    }

    private void initView() {
        title= (TextView) findViewById(R.id.title_activity_detail);
        back= (ImageView) findViewById(R.id.back_activity_detail);
        search= (ImageView) findViewById(R.id.search_activity_detail);
        back.setOnClickListener(this);
        search.setOnClickListener(this);
        imageView= (ImageView)findViewById(R.id.image_activity_detail);
        webView= (WebView) findViewById(R.id.webview_activity_detail);

    }

    private void getJson(String path, int id,String type) {
        path = path + "elapsedtime=" + NetUtils.getCurrentTime() + "&appVersion=28&id=" + id+"&type="+type;
        httpUtils.send(HttpRequest.HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //5988
//                String json=responseInfo.result;
//                Log.i("test3", "onSuccess "+json.substring(5900,6150));
                ActivityDetailBean activityDetailBean = gson.fromJson(responseInfo.result, ActivityDetailBean.class);
                msg = activityDetailBean.getMsg();
                bitmapUtils.display(imageView, msg.getIcon_url());
                title.setText(msg.getTitle());
                webView.loadData(msg.getDesc(), "text/html;charset=UTF-8", null);
//                gameInfos = msg.getGameInfo();
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_activity_detail) {
            finish();
        }
        if (v.getId() == R.id.search_activity_detail) {
            startActivity(new Intent(this, SearchActivity.class));
        }
    }
}
