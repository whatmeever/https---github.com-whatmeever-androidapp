package com.waterfairy.gamecenter.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.waterfairy.gamecenter.BaseGame.GiftBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.adapter.GiftDetailsAdapter;
import com.waterfairy.gamecenter.category.SearchActivity;
import com.waterfairy.gamecenter.utils.AutoInstall;
import com.waterfairy.gamecenter.utils.NetUtils;
import com.waterfairy.gamecenter.utils.Tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GiftDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView back,search;
    private ListView lv;
    String url;
    List<GiftBean.GiftsEntity> list;
    private Button bt;
    GiftBean.GameEntity game;
    private ImageView iv_icon;
    private TextView tv_guan;
    private TextView tv_download,tv_type,tv_size;
    private ImageView iv1,iv2,iv3,iv4,iv5;
    HttpUtils httpUtils;
    HttpHandler download;
    String apkurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_details);
        httpUtils=new HttpUtils();
        init();
    }
    public void init(){
        back= (ImageView) findViewById(R.id.gift_main_back);
        search= (ImageView) findViewById(R.id.gift_main_search);
        lv= (ListView) findViewById(R.id.gift_main_lv);
        bt= (Button) findViewById(R.id.gift_main_ok);
        bt.setOnClickListener(this);
        back.setOnClickListener(this);
        search.setOnClickListener(this);
        HttpUtils httpUtils=new HttpUtils();
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 1);
        //给listview添加head
        final View view = LayoutInflater.from(this).inflate(R.layout.details_head, null);
        iv1= (ImageView) view. findViewById(R.id.details_iv1);
        iv2= (ImageView)  view.findViewById(R.id.details_iv2);
        iv3= (ImageView) view. findViewById(R.id.details_iv3);
        iv4= (ImageView) view.findViewById(R.id.details_iv4);
        iv5= (ImageView) view.findViewById(R.id.details_iv5);
        iv_icon= (ImageView) view.findViewById(R.id.details_iv_icon);
        tv_guan= (TextView) view.findViewById(R.id.details_iv_guan);
        tv_download= (TextView) view.findViewById(R.id.details_tv_download);
        tv_type= (TextView) view.findViewById(R.id.details_tv_type);
        tv_size= (TextView) view.findViewById(R.id.details_tv_size);
        //http://info.gamecenter.vivo.com.cn/clientRequest/gameDetail?pkgName=com.platform2y9y.youai.common.xianjianhj.vivo
        // &t/sdcard/GameCenter/ype=local&patch_sup=1&cs=0&content=gift&sign=1%7C2305044360&id=28226&appVersion=28&elapsedtime=77868881
        url= NetUtils.giftdetails+"content=gift&id="+id;
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //fastjson
                list = new ArrayList<GiftBean.GiftsEntity>();
                JSONObject jsonObject = JSON.parseObject(responseInfo.result);
                game = jsonObject.getObject("game",  GiftBean.GameEntity.class);
                if (game!=null){

                    apkurl = "/sdcard/GameCenter/" + game.getName()+".apk";
                    File file = new File(apkurl);
                    if (file.exists()){
                        bt.setText("打开");
                    }else {
                        bt.setText("下载");
                    }
                    //设置数据;
                    tv_guan.setText(game.getName());
                    int download = game.getDownload();
                    if (download >= 10000) {
                        tv_download.setText(download / 10000 + "万人在玩/");
                    }
                    tv_type.setText(game.getType() + "/");
                    tv_size.setText(game.getSize() / 1024 + "MB");
                    double comment1 = game.getComment();
                    Tools.GameDate(comment1, iv1, iv2, iv3, iv4, iv5);
                    String imgUrl = game.getIcon();
                    //设置图片;-->Application;
                    BitmapUtils bitmaputils = new BitmapUtils(GiftDetailsActivity.this);
                    bitmaputils.display(iv_icon, imgUrl);
                }
                JSONArray jsonArray = jsonObject.getJSONArray("gifts");
                if (jsonArray!=null){
                    for (int i = 0; i < jsonArray.size(); i++) {
                        list.add(jsonArray.getObject(i,GiftBean.GiftsEntity.class));
                    }
                }
                GiftDetailsAdapter giftDetailsAdapter=new GiftDetailsAdapter(list,GiftDetailsActivity.this,false);
                lv.addHeaderView(view);
                lv.setAdapter(giftDetailsAdapter);
            }
            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
            }
        });
    }
    int tag=0;//初始状态可以下载
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gift_main_back:
                finish();
                break;
            case R.id.gift_main_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.gift_main_ok:

                String string = bt.getText().toString();
//                " + game.getName() + "
                if(string.equals("下载")||string.equals("暂停")){
                    downLoad();
                }else if (string.equals("打开")){
                    // 在外部Activity调用者看来，不需要在意太多内部实现，只需要传入一个url跟一个context即可
                    AutoInstall.setUrl(apkurl);
                    AutoInstall.install(this);
                }else {
                    download.cancel();
                    bt.setText("下载");
                }
                break;
        }
    }

    public void downLoad(){

        download = httpUtils.download(game.getApkurl(), apkurl, new RequestCallBack<File>() {
            @Override
            public void onLoading(final long total, final long current, final boolean isUploading) {
                bt.setText((long) ((double) current / total * 100) + "%");
            }
            @Override
            public void onSuccess(ResponseInfo responseInfo) {
                bt.setText("打开");
            }
            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
                bt.setText("暂停");
            }
        });
    }
}
