package com.waterfairy.gamecenter.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.category.SearchActivity;

import java.io.File;
import java.lang.reflect.Field;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt;
    private ImageView back, search;
    private TextView fankui, guanyu, clear, cacheSize;
    private ToggleButton push, update;
    private SharedPreferences shareSet;
    private  long size;
    private File[] files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        shareSet = getSharedPreferences("setting", MODE_PRIVATE);
        init();
        initData();
    }

    private void initData() {
        File file = new File(getExternalCacheDir() + "/xBitmapCache");
        files = file.listFiles();
        for (File filetemp:files){
            size+=filetemp.length();
        }
        if (size>1024*1024){
            cacheSize.setText(size/(1024*1024)+" M");
        }else {
            cacheSize.setText(size/1024+" K");
        }



    }

    public void init() {
        push = (ToggleButton) findViewById(R.id.push);
        update = (ToggleButton) findViewById(R.id.update);
        push.setOnClickListener(this);
        update.setOnClickListener(this);
        push.setChecked(true);
        update.setChecked(true);

        bt = (Button) findViewById(R.id.set_bt);
        back = (ImageView) findViewById(R.id.setting_back);
        search = (ImageView) findViewById(R.id.setting_search);
        fankui = (TextView) findViewById(R.id.setting_fankui);
        guanyu = (TextView) findViewById(R.id.setting_about);
        cacheSize = (TextView) findViewById(R.id.cache_size);
        clear = (TextView) findViewById(R.id.clear);
        clear.setOnClickListener(this);
        back.setOnClickListener(this);
        search.setOnClickListener(this);
        fankui.setOnClickListener(this);
        guanyu.setOnClickListener(this);
        btremove();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.setting_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.setting_fankui:
                startActivity(new Intent(this, FanKuiActivity.class));
                break;
            case R.id.setting_about:

                break;
            case R.id.update:
                if (!update.isChecked()) {
                    shareSet.edit().putBoolean("update", false).apply();
                    Toast.makeText(this, "关闭更新", Toast.LENGTH_SHORT).show();
                } else {
                    shareSet.edit().putBoolean("update", true).apply();
                    Toast.makeText(this, "打开更新", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.push:
                if (!push.isChecked()) {
                    shareSet.edit().putBoolean("push", false).apply();
                    Toast.makeText(this, "关闭推送", Toast.LENGTH_SHORT).show();
                } else {
                    shareSet.edit().putBoolean("push", true).apply();
                    Toast.makeText(this, "打开推送", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.clear:
                for (File file:files) {
                    file.delete();
                }
                Toast.makeText(this,"清除成功",Toast.LENGTH_SHORT).show();
                cacheSize.setText("0 K");


                break;

        }
    }

    //按钮移动;

    public void btremove() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        final int screenWidth = dm.widthPixels;
        final int screenHeight = dm.heightPixels - 50;
        bt.setOnTouchListener(new View.OnTouchListener() {
            int lastX, lastY; //记录移动的最后的位置

            public boolean onTouch(View v, MotionEvent event) {
                //获取Action
                int ea = event.getAction();
                switch (ea) {
                    case MotionEvent.ACTION_DOWN:   //按下
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        break;
                    /**
                     * layout(l,t,r,b)
                     * l  Left position, relative to parent
                     t  Top position, relative to parent
                     r  Right position, relative to parent
                     b  Bottom position, relative to parent
                     * */
                    case MotionEvent.ACTION_MOVE:  //移动
                        //移动中动态设置位置
                        int dx = (int) event.getRawX() - lastX;
                        int dy = (int) event.getRawY() - lastY;
                        int left = v.getLeft() + dx;
                        int top = v.getTop() + dy;
                        int right = v.getRight() + dx;
                        int bottom = v.getBottom() + dy;
                        if (left < 0) {
                            left = 0;
                            right = left + v.getWidth();
                        }
                        if (right > screenWidth) {
                            right = screenWidth;
                            left = right - v.getWidth();
                        }
                        if (top < 0) {
                            top = 0;
                            bottom = top + v.getHeight();
                        }
                        if (bottom > screenHeight) {
                            bottom = screenHeight;
                            top = bottom - v.getHeight();
                        }
                        v.layout(left, top, right, bottom);
                        Log.i("", "position：" + left + ", " + top + ", " + right + ", " + bottom);
                        //将当前的位置再次设置
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:   //脱离
                        break;
                }
                return false;
            }
        });
    }
}
