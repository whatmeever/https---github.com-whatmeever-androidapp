package com.waterfairy.gamecenter.activitys;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.waterfairy.gamecenter.BaseGame.DbdetailsBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.adapter.DownAdapter;
import com.waterfairy.gamecenter.category.SearchActivity;
import com.waterfairy.gamecenter.utils.AutoInstall;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DownloadingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    SharedPreferences share;
    String action1;
    String action2;
    String action3;
    MyBroadcast myBroadcast;
    IntentFilter intentFilter;
    private BitmapUtils utils;

    private ImageView topicBack;
    private TextView topicTitle;
    private ImageView topicSearch;
    private RelativeLayout re1;
    private ImageView img1;
    private TextView title1;
    private LinearLayout progress1;
    private TextView down1;
    private RelativeLayout re2;
    private ImageView img2;
    private TextView title2;
    private LinearLayout progress2;
    private TextView down2;
    private RelativeLayout re3;
    private ImageView img3;
    private TextView title3;
    private LinearLayout progress3;
    private TextView down3;
    private View headerView;

    private ListView listView;

    private int width;
    private DbUtils db;
    List<DbdetailsBean> list;
    private DownAdapter adapter;
    File apkPath ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloading);
        apkPath = new File(Environment.getExternalStorageDirectory() + "/GameCenter/");
        headerView = LayoutInflater.from(this).inflate(R.layout.header_down, null);
        listView = (ListView) findViewById(R.id.listview_down);
        listView.addHeaderView(headerView, null, false);
        width = getWindowManager().getDefaultDisplay().getWidth();
        assignViews();
        initData();
    }

    private void initData() {
        utils = new BitmapUtils(this);
        share = getSharedPreferences("down", Context.MODE_PRIVATE);
        action1 = share.getString("1down", "");
        action2 = share.getString("2down", "");
        action3 = share.getString("3down", "");
        myBroadcast = new MyBroadcast();
        intentFilter = new IntentFilter();
        intentFilter.addAction(action1);
        intentFilter.addAction(action2);
        intentFilter.addAction(action3);
        registerReceiver(myBroadcast, intentFilter);
        list = new ArrayList<>();
        setList();

    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setList();
        }
    };

    private void setList() {

        db = DbUtils.create(this, "down_details");
        try {
            list = db.findAll(DbdetailsBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
//            db.close();
        }
        adapter = new DownAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private List<String> getLocalPkg() {
        List<String> list = new ArrayList<>();

        String path;
        if (apkPath.exists()) {
            File[] files = apkPath.listFiles();
            for (File filetemp : files) {
                path=filetemp.getAbsolutePath();
                if (path.substring(path.length()-3, path.length()).equals("apk")){
                    list.add(path);
                }
//                list.add();
            }
        } else {
            apkPath.mkdir();
        }
        return list;
    }


    private void assignViews() {
        topicBack = (ImageView) findViewById(R.id.topic_back);
        topicTitle = (TextView) findViewById(R.id.topic_title);
        topicSearch = (ImageView) findViewById(R.id.topic_search);
        topicBack.setOnClickListener(this);
        topicSearch.setOnClickListener(this);
        re1 = (RelativeLayout) headerView.findViewById(R.id.re1);
        img1 = (ImageView) headerView.findViewById(R.id.img1);
        title1 = (TextView) headerView.findViewById(R.id.title1);
        progress1 = (LinearLayout) headerView.findViewById(R.id.progress1);
        down1 = (TextView) headerView.findViewById(R.id.down1);
        re2 = (RelativeLayout) headerView.findViewById(R.id.re2);
        img2 = (ImageView) headerView.findViewById(R.id.img2);
        title2 = (TextView) headerView.findViewById(R.id.title2);
        progress2 = (LinearLayout) headerView.findViewById(R.id.progress2);
        down2 = (TextView) headerView.findViewById(R.id.down2);
        re3 = (RelativeLayout) headerView.findViewById(R.id.re3);
        img3 = (ImageView) headerView.findViewById(R.id.img3);
        title3 = (TextView) headerView.findViewById(R.id.title3);
        progress3 = (LinearLayout) headerView.findViewById(R.id.progress3);
        down3 = (TextView) headerView.findViewById(R.id.down3);
        progress1.scrollTo(0, -progress1.getWidth());
        progress2.scrollTo(0, -progress2.getWidth());
        progress3.scrollTo(0, -progress3.getWidth());
        re1.setVisibility(View.GONE);
        re2.setVisibility(View.GONE);
        re3.setVisibility(View.GONE);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }


        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.topic_back) {
                finish();
            }
            if (v.getId() == R.id.topic_search) {
                startActivity(new Intent(this, SearchActivity.class));
            }
        }


    class MyBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            long total = intent.getLongExtra("total", -1);
            long current = intent.getLongExtra("current", 0);
            boolean isUploading = intent.getBooleanExtra("isUploading", false);
            String name = intent.getStringExtra("name");
            String imgUrl = intent.getStringExtra("imgUrl");
            if (action1.equals(intent.getAction())) {
                re1.setVisibility(View.VISIBLE);
                Log.i("test12", "onReceive " + current + "---" + toString());
                progress1.scrollTo((int) ((1 - (current * 100 / total) * 0.01) * (progress1.getWidth())), 0);
                title1.setText(name);
                utils.display(img1, imgUrl);
                down1.setText(current * 100 / total + "%");
                if (current * 100 / total == 100) {
                    re1.setVisibility(View.GONE);
                    handler.sendEmptyMessageDelayed(0, 500);
                }
                return;
            }
            if (action2.equals(intent.getAction())) {
                re2.setVisibility(View.VISIBLE);
                utils.display(img2, imgUrl);
                progress2.scrollTo((int) ((1 - (current * 100 / total) * 0.01) * (progress2.getWidth())), 0);
                title2.setText(name);
                down2.setText(current * 100 / total + "%");
                if (current * 100 / total == 100) {
                    re2.setVisibility(View.GONE);
                    handler.sendEmptyMessageDelayed(0, 500);
                }
                return;
            }
            if (action3.equals(intent.getAction())) {
                re3.setVisibility(View.VISIBLE);
                utils.display(img3, imgUrl);
                progress3.scrollTo((int) ((1 - (current * 100 / total) * 0.01) * (progress3.getWidth())), 0);
                title3.setText(name);
                down3.setText(current * 100 / total + "%");
                if (current * 100 / total == 100) {
                    re3.setVisibility(View.GONE);
                    handler.sendEmptyMessageDelayed(0, 500);
                }
                return;
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcast);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.update();
    }
}
