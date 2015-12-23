package com.waterfairy.gamecenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;
import com.umeng.message.local.UmengLocalNotification;
import com.umeng.update.UmengUpdateAgent;
import com.waterfairy.gamecenter.activitys.ActivityListActivity;
import com.waterfairy.gamecenter.activitys.DownloadingActivity;
import com.waterfairy.gamecenter.activitys.LoginActivity;
import com.waterfairy.gamecenter.activitys.MyLiquanActivity;
import com.waterfairy.gamecenter.activitys.NativeActivity;
import com.waterfairy.gamecenter.activitys.SettingActivity;
import com.waterfairy.gamecenter.category.HotSearchActivity;
import com.waterfairy.gamecenter.category.SearchActivity;
import com.waterfairy.gamecenter.fragment.CategoryFragment;
import com.waterfairy.gamecenter.fragment.NetgameFragment;
import com.waterfairy.gamecenter.fragment.RankFragment;
import com.waterfairy.gamecenter.fragment.RecommendFragment;
import com.waterfairy.gamecenter.slidemenu.SlidingMenu;
import com.waterfairy.gamecenter.utils.DownloadUtils;
import com.waterfairy.gamecenter.utils.RegexUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {
    private SlidingMenu mMenu;
    private Fragment lastFragMennt;
    private RecommendFragment recommendFragment;
    private RankFragment rankFragment;
    private CategoryFragment categoryFragment;
    private NetgameFragment netgameFragment;

    private ImageView search;
    private EditText editText;
    private LinearLayout l1, l2, l3, l4;
    private ImageView img1, img2, img3, img4;
    private TextView tv1, tv2, tv3, tv4;
    private FragmentTransaction transaction;
    private RelativeLayout relativeLayout;
    private ImageView touxiang;
    private TextView email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences shareSet = getSharedPreferences("setting", MODE_PRIVATE);
        if (shareSet.getBoolean("update", true)) {
            //更新
            UmengUpdateAgent.setUpdateOnlyWifi(false);
            UmengUpdateAgent.update(this);
        }
        if (shareSet.getBoolean("push", true)) {
            //开启推送
            PushAgent mPushAgent = PushAgent.getInstance(this);
            PushAgent.getInstance(this).onAppStart();
            mPushAgent.enable();
        }
        SharedPreferences shareState=getSharedPreferences("down_state", MODE_PRIVATE);
        Map<String, Integer> map = (Map<String, Integer>) shareState.getAll();
        Set<String> set = map.keySet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            if (map.get(str)== DownloadUtils.DOWNLOADING){
                shareState.edit().putInt(str,DownloadUtils.NOTDOWNLOAD).apply();
                Log.i("down", "" + map.get(str));
            }
        }


        //初始化下载
        SharedPreferences sharedPreferences = getSharedPreferences("down", MODE_PRIVATE);
        sharedPreferences.edit().
                putBoolean("downState1", false).
                putBoolean("downState2", false).
                putBoolean("downState3",false).
                putInt("count", 0).apply();

//        Log.i("test10", "onCreate "+device_token);
        mMenu = (SlidingMenu) findViewById(R.id.id_menu);
        init();
        recommendFragment = new RecommendFragment();
        rankFragment = new RankFragment();
        categoryFragment = new CategoryFragment();
        netgameFragment = new NetgameFragment();
        lastFragMennt = recommendFragment;
        editText.addTextChangedListener(this);
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame, recommendFragment).
                add(R.id.frame, rankFragment).hide(rankFragment).
                add(R.id.frame, categoryFragment).hide(categoryFragment).
                add(R.id.frame, netgameFragment).hide(netgameFragment).
                commit();
    }

    public void init() {
        l1 = (LinearLayout) findViewById(R.id.bar_l1);
        l2 = (LinearLayout) findViewById(R.id.bar_l2);
        l3 = (LinearLayout) findViewById(R.id.bar_l3);
        l4 = (LinearLayout) findViewById(R.id.bar_l4);

        img1 = (ImageView) findViewById(R.id.bar_img1);
        img2 = (ImageView) findViewById(R.id.bar_img2);
        img3 = (ImageView) findViewById(R.id.bar_img3);
        img4 = (ImageView) findViewById(R.id.bar_img4);
        search = (ImageView) findViewById(R.id.main_search_img);
        //这是三个关于个人信息的东西
        relativeLayout = (RelativeLayout) findViewById(R.id.main_rl);
        touxiang = (ImageView) findViewById(R.id.touxiang);
        email = (TextView) findViewById(R.id.email);

        tv1 = (TextView) findViewById(R.id.bar_tv1);
        tv2 = (TextView) findViewById(R.id.bar_tv2);
        tv3 = (TextView) findViewById(R.id.bar_tv3);
        tv4 = (TextView) findViewById(R.id.bar_tv4);
        editText = (EditText) findViewById(R.id.search);
        search.setOnClickListener(this);

        //账户名
        SharedPreferences userinfo = getSharedPreferences("userinfo", MODE_PRIVATE);
        String name = userinfo.getString("name", "");
        if (!TextUtils.isEmpty(name)) {
            email.setText(name);
        } else {
            email.setText("请登录");
        }
    }

    public void toggleMenu(View view) {
        mMenu.toggle();
    }

    public void onclick(View view) {
        transaction = getSupportFragmentManager().beginTransaction();

        transaction.hide(recommendFragment);
        transaction.hide(rankFragment);
        transaction.hide(categoryFragment);
        transaction.hide(netgameFragment);

        switch (view.getId()) {
            case R.id.bar_l1:
                reset();
                img1.setBackgroundResource(R.mipmap.game_tab_recommended_pressed);
                tv1.setTextColor(Color.parseColor("#e6de69"));
                transaction.show(recommendFragment).commit();
                lastFragMennt = recommendFragment;
                break;
            case R.id.bar_l2:
                reset();
                img2.setBackgroundResource(R.mipmap.game_tab_top_pressed);
                tv2.setTextColor(Color.parseColor("#e6de69"));
                transaction.show(rankFragment).commit();
                lastFragMennt = rankFragment;
                break;
            case R.id.bar_l3:
                reset();
                img3.setBackgroundResource(R.mipmap.game_tab_category_pressed);
                tv3.setTextColor(Color.parseColor("#e6de69"));
                transaction.show(categoryFragment).commit();
                lastFragMennt = categoryFragment;
                break;
            case R.id.bar_l4:
                reset();
                img4.setBackgroundResource(R.mipmap.game_tab_search_pressed);
                tv4.setTextColor(Color.parseColor("#e6de69"));
                transaction.show(netgameFragment).commit();
                lastFragMennt = netgameFragment;
                break;
        }

    }

    //侧滑栏点击效果
    public void slide_click(View view) {
        switch (view.getId()) {
            case R.id.main_rl:
                startActivityForResult(new Intent(this, LoginActivity.class), 10001);
                break;
            case R.id.slide_r1:
                startActivity(new Intent(this, ActivityListActivity.class));
                break;
            case R.id.slide_r2:
                startActivity(new Intent(this, NativeActivity.class));
                break;
            case R.id.slide_r3:
                startActivity(new Intent(this, DownloadingActivity.class));
                break;
            case R.id.slide_r4:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.slide_r5:
                startActivity(new Intent(this, MyLiquanActivity.class));
                break;
            case R.id.slide_r6:
                finish();
                break;
        }
    }

    public void reset() {
        img1.setBackgroundResource(R.mipmap.game_tab_recommended_normal);
        img2.setBackgroundResource(R.mipmap.game_tab_top_normal);
        img3.setBackgroundResource(R.mipmap.game_tab_category_normal);
        img4.setBackgroundResource(R.mipmap.game_tab_search_normal);
        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onClick(View v) {
        String text = editText.getText().toString();
        if (!TextUtils.isEmpty(text)) {
            Intent intent = new Intent(this, HotSearchActivity.class);
            intent.putExtra("title", text);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 10001) {
            email.setText(data.getStringExtra("name"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = getSharedPreferences("down", MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("downing1", false).putBoolean("downing2", false).putBoolean("downing3", false).apply();
        Log.i("test13", "onDestroy ");
    }


    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();      //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}
