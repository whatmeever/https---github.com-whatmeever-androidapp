package com.waterfairy.gamecenter.category;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.waterfairy.gamecenter.BaseGame.GridBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.activitys.Detail_RunkActivity;
import com.waterfairy.gamecenter.adapter.CateGridAdapter;
import com.waterfairy.gamecenter.utils.NetUtils;
import com.waterfairy.gamecenter.view.MyGridview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author james
 */
public class SearchActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView back,search;
    private MyGridview gridView1;
    private MyGridview gridView2;

    private ImageView anim;
    private LinearLayout linearLayout;
    private EditText et;
    private Button bt;
    private List<String> list;
    GridBean searchBean;
    int page=1;
    HttpUtils httpUtils;
    private List<GridBean.MsgEntity> listEntityList;
    String s = NetUtils.HTTPSEARCH ;
    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
        httpUtils = new HttpUtils();
        send();


    }
    public  void send(){
        httpUtils.send(HttpRequest.HttpMethod.GET, s, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    String result = responseInfo.result;
                    searchBean = new Gson().fromJson(result, GridBean.class);
                    listEntityList = new ArrayList<GridBean.MsgEntity>();
                    if (searchBean.getList() != null) {
                        Random random = new Random();
                        boolean[] boo = new boolean[searchBean.getList().size()];
                        int ran = 0;
                        for (int j = 0; j < 8; j++) {
                            do {
                                ran = random.nextInt(searchBean.getList().size());
                            } while (boo[ran]);
                            boo[ran] = true;
                            listEntityList.add(searchBean.getList().get(ran));
                        }
                        gridView1.setAdapter(new CateGridAdapter(listEntityList, SearchActivity.this, true));
                        String hotWord_array = searchBean.getHotWord_array();
                        String[] split = hotWord_array.split(";");
                        int splitindex = 0;
                        list = new ArrayList<String>();
                        Log.e("xxxxx", "hotWord_array--------->" + hotWord_array);

                        for (int j = 0; j < 9; j++) {
                            int i = random.nextInt(split.length);
                            list.add(split[i]);
                        }
                        ArrayAdapter arrayAdapter = new ArrayAdapter(SearchActivity.this, R.layout.hotword_item, list);
                        gridView2.setAdapter(arrayAdapter);
                        Thread.sleep(1000);
                        animationDrawable.stop();
                        anim.setVisibility(View.GONE);
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {
            }
        });
        gridView1.setOnItemClickListener(this);
        gridView2.setOnItemClickListener(this);
    }

    public void init(){
        back= (ImageView) findViewById(R.id.mysearch_back);
        search= (ImageView) findViewById(R.id.mysearch_image);
        et= (EditText) findViewById(R.id.mysearch);
        gridView1= (MyGridview) findViewById(R.id.mysearch_grid1);
        gridView2= (MyGridview) findViewById(R.id.mysearch_grid2);
        bt= (Button) findViewById(R.id.mysearch_huan);
        anim= (ImageView) findViewById(R.id.mysearch_anim);
        linearLayout= (LinearLayout) findViewById(R.id.mysearch_ll);
        linearLayout.setVisibility(View.GONE);
        //动画效果
        anim.setImageResource(R.drawable.animation_list);
        animationDrawable = (AnimationDrawable) anim.getDrawable();
        animationDrawable.start();

        bt.setOnClickListener(this);
        back.setOnClickListener(this);
        search.setOnClickListener(this);
        btremove();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mysearch_back:
                finish();
                break;
            case R.id.mysearch_huan:
                send();
                break;
            case R.id.mysearch_image:
                Intent intent=new Intent(this,HotSearchActivity.class);
                String text = et.getText().toString();
                if (!TextUtils.isEmpty(text)){
                    intent.putExtra("title",text);
                }else {
                    intent.putExtra("title","三国");
                }
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MyGridview parent1 = (MyGridview) view.getParent();
        switch (parent1.getId()){
            case R.id.mysearch_grid1:
                int num= listEntityList.get(position).getId();
                Intent intent=new Intent(this,Detail_RunkActivity.class);
                intent.putExtra("id",num);
                startActivity(intent);
                break;
            case R.id.mysearch_grid2:
                String s1=((TextView) view).getText()+"";
                Intent intent1=new Intent(this,HotSearchActivity.class);
                intent1.putExtra("title",s1);
                startActivity(intent1);
                break;
        }
    }

    //按钮移动;

    public void btremove(){
        DisplayMetrics dm = getResources().getDisplayMetrics();
        final int screenWidth = dm.widthPixels;
        final int screenHeight = dm.heightPixels - 50;
        bt.setOnTouchListener(new View.OnTouchListener(){
            int lastX, lastY; //记录移动的最后的位置
            public boolean onTouch(View v, MotionEvent event) {
                //获取Action
                int ea=event.getAction();
                switch(ea){
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
                        int dx =(int)event.getRawX() - lastX;
                        int dy =(int)event.getRawY() - lastY;
                        int left = v.getLeft() + dx;
                        int top = v.getTop() + dy;
                        int right = v.getRight() + dx;
                        int bottom = v.getBottom() + dy;
                        if(left < 0){
                            left = 0;
                            right = left + v.getWidth();
                        }
                        if(right > screenWidth){
                            right = screenWidth;
                            left = right - v.getWidth();
                        }
                        if(top < 0){
                            top = 0;
                            bottom = top + v.getHeight();
                        }
                        if(bottom > screenHeight){
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
            }});
    }
}
