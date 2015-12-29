package com.waterfairy.gamecenter.activitys;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.adapter.QuestionAdapter;

import java.util.HashMap;

/**
 *
 * @author james
 */
public class QuestionActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private ImageView iv_back;
    private TextView tv_send;
    private ListView listView;
    private ImageView iv;
    private LinearLayout ll_anim;
    private EditText editText;
    private QuestionAdapter adapter;
    private int totalHeight;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_send = (TextView) findViewById(R.id.tv_send);
        iv = (ImageView) findViewById(R.id.iv);
        ll_anim = (LinearLayout) findViewById(R.id.ll_anim);
        editText = (EditText) findViewById(R.id.et);
        scrollView = (ScrollView) findViewById(R.id.scroll);
        scrollView.setVerticalScrollBarEnabled(false);
        tv_send.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.lv);
        String[] strings = {"无法下载", "无法安装", "运行出错", "与描述不符", "包含不良内容", "其他问题"};
        adapter = new QuestionAdapter(strings, this, editText, handler);
        listView.setAdapter(adapter);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotating);
        iv.setImageResource(R.mipmap.game_progressbar_indeterminate_circle);
        iv.setAnimation(animation);
        //得到listview的一个单独的item;
        View itemView = adapter.getView(0, null, listView);
        //重新绘制-->得到高度;
        itemView.measure(0, 0);
        //得到ListView的总高度;
        totalHeight = itemView.getMeasuredHeight() * strings.length + (listView.getDividerHeight() * (strings.length - 1));
        //设置lsitView的总高度;--->RelativeLayout;
        listView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, totalHeight));
        editText.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_send:
                showAnim();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    //提交后的显示动画效果
    public void showAnim() {
        tv_send.setClickable(true);
        ll_anim.setVisibility(View.VISIBLE);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                ll_anim.clearAnimation();
                finish();
                Toast.makeText(QuestionActivity.this, "提交成功,谢谢您的参与", Toast.LENGTH_SHORT).show();
            }
        };
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            HashMap<Integer, Boolean> hashMap = adapter.getHashMap();
            switch (what) {
                case 1:
                    if (hashMap.containsKey(5)) {
                        hashMap.remove(5);
                    }
                    if (hashMap.containsValue(true)) {
                        tv_send.setTextColor(0xFF000000);
                        tv_send.setClickable(true);
                    } else {
                        tv_send.setTextColor(0xFF666666);
                        tv_send.setClickable(false);
                    }
                    break;
                case 2:
                    tv_send.setTextColor(0xFF000000);
                    tv_send.setClickable(true);
                    break;
            }
        }
    };

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        handler.sendEmptyMessage(2);
    }
}
