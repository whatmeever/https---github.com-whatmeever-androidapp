package com.waterfairy.gamecenter.adapter;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.waterfairy.gamecenter.BaseGame.ClassicBean;
import com.waterfairy.gamecenter.MainActivity;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.utils.AutoInstall;
import com.waterfairy.gamecenter.utils.DownloadUtils;
import com.waterfairy.gamecenter.utils.Tools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2015/10/16.
 * //记录是否点击下载-->点击下载后设置为true-->不管是等待还是下载;
 //判断如果下载量小于3那么直接下载-->加isClick判断true下载/点击false表示暂停
 //如果下载大于3那么直接等待-->当有下载完成后，直接执行下一个等待的下载;
 */
public class RankBaseAdapter extends BaseAdapter {
    private List<ClassicBean.MsgEntity> list;
    private Context context;
    private boolean flag;
    HashMap<Integer,Boolean>hashMap;
    private int download;
    private SharedPreferences sharedPreferences;
    private String anInt,imageUri;
    private boolean isClick,isWait;
    private List<Integer>waitName=new ArrayList<>();
    public RankBaseAdapter(List<ClassicBean.MsgEntity> list, Context context) {
        this.list = list;
        this.context = context;
        init();
    }
    public RankBaseAdapter(List<ClassicBean.MsgEntity> list, Context context,boolean flag) {
        this.list = list;
        this.context = context;
        this.flag=flag;
        init();
    }

    @Override
    public int getCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=LinearLayout.inflate(context, R.layout.item_rank,null);
            viewHolder.iv1= (ImageView) convertView.findViewById(R.id.iv1);
            viewHolder.iv2= (ImageView) convertView.findViewById(R.id.iv2);
            viewHolder.iv3= (ImageView) convertView.findViewById(R.id.iv3);
            viewHolder.iv4= (ImageView) convertView.findViewById(R.id.iv4);
            viewHolder.iv5= (ImageView) convertView.findViewById(R.id.iv5);
            viewHolder.iv_guan= (ImageView) convertView.findViewById(R.id.iv_guan);
            viewHolder.iv_icon= (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_download= (TextView) convertView.findViewById(R.id.tv_download);
            viewHolder.tv_size= (TextView) convertView.findViewById(R.id.tv_size);
            viewHolder.bt_download= (Button) convertView.findViewById(R.id.bt_download);
            viewHolder.tv_number= (TextView) convertView.findViewById(R.id.tv_number);
            viewHolder.tv_id= (TextView) convertView.findViewById(R.id.tv_id);
            viewHolder.text_topic= (TextView) convertView.findViewById(R.id.rank_text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        final String gamename=list.get(position).getName();
        final String name=list.get(position).getPkgName();
        imageUri=list.get(position).getIcon();
        viewHolder.bt_download.setTag(name);//设置标记;
        viewHolder.bt_download.setFocusable(false);//设置button不抢焦点;
        //下载...
        final ViewHolder finalViewHolder = viewHolder;//finalViewHolder-->常量来接收变量;
        final String apkUrl=list.get(position).getApkurl();
        final File file=new File("/sdcard/GameCenter/");
        if(!file.exists()){
            file.mkdirs();//创建文件夹;
        }
        anInt = sharedPreferences.getString(name +"完成", "");//判断是否已经下载;
        isClick=sharedPreferences.getBoolean(name + "zt", false);//判断是否是已点击下载;
        isWait=sharedPreferences.getBoolean(name+"wait",false);
        //是否是下载完成
        if(name.equals(anInt)){
            viewHolder.bt_download.setBackgroundColor(0xFFFCD103);
            viewHolder.bt_download.setText("打开");
        }else {
            //表示正在下载；
            if (sharedPreferences.getBoolean(name+"xz",false)) {
                //表示是否是暂停状态；
                if(isClick) {
                    viewHolder.bt_download.setBackgroundColor(0xFFF56B25);
                    viewHolder.bt_download.setText("下载中");
                }
            } else {
                viewHolder.bt_download.setText("下载");
                viewHolder.bt_download.setBackgroundColor(0xFFFCD103);
            }if(isWait){
                viewHolder.bt_download.setText("等待中");
                viewHolder.bt_download.setBackgroundColor(0xFFF56B25);
            }
        }
        //下载完成后执行;
        final Handler handler=new Handler(){
            @Override
                public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                String tagName=data.getString("pkg");
                sharedPreferences.edit().putString(tagName + "完成", tagName).apply();
                if(sharedPreferences.getBoolean(tagName+"xz",false)){
                    finalViewHolder.bt_download.setText("打开");
                    finalViewHolder.bt_download.setBackgroundColor(0xFFFCD103);
                    download--;
                    //实现等待中的下载任务；
                    if(waitName.size()>0){
                    download++;
                    String apkUrl=list.get(waitName.get(0)).getApkurl();
                    String name=list.get(waitName.get(0)).getPkgName();
                    DownloadUtils.downLoad(context,apkUrl,name,list.get(position).getName(),list.get(position).getIcon(),this);
                    sharedPreferences.edit().putBoolean(name+"xz",true).apply();
                    isClick=true;
                    waitName.remove(0);
                    if(sharedPreferences.getBoolean(name+"xz",false)) {
                        finalViewHolder.bt_download.setClickable(true);
                        finalViewHolder.bt_download.setText("下载中");
                    }
                }
                    //下载完成后-->在重新存储下载个数;
                    sharedPreferences.edit().putInt("download",download).apply();
                }
            }
        };
        //点击事件中用到的参数必须在其内部写，如果写在外部不换页面的话不会去更新;
        viewHolder.bt_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick=sharedPreferences.getBoolean(name + "zt", false);
                download= sharedPreferences.getInt("download", 0);//设置下载量的个数;
                anInt = sharedPreferences.getString(name + "完成", "");//判断是否已经下载;
                //防止当前不在那个下载页面;
                String name= (String) v.getTag();
                //当已经下载后...
                if (name.equals(anInt)) {
                    AutoInstall.setUrl(file.getAbsolutePath()+"/"+name+".apk");
                    AutoInstall.install(context);//安装界面;
                } else {
                    //如果点击过后-->download只加一次;
                    if(!sharedPreferences.getBoolean(name+"first",false)){
                        download++;
                        sharedPreferences.edit().putInt("download", download).apply();
                    }
                    sharedPreferences.edit().putBoolean(name+"first",true).apply();//判断是否是第一次点击;
                    sharedPreferences.edit().putBoolean(name+ "xz", true).apply();//设置下载事件的存储;
                    if (download <= 3 && !isClick) {
                        finalViewHolder.bt_download.setText("下载中");
                        finalViewHolder.bt_download.setBackgroundColor(0xFFF56B25);
                        DownloadUtils.downLoad(context, apkUrl, name,gamename,imageUri,handler);
                        isClick = true;
                    } else if(download>3){
                        waitName.add(position);//添加等待的数量;
                        finalViewHolder.bt_download.setText("等待中");
                        finalViewHolder.bt_download.setBackgroundColor(0xFFF56B25);
                        sharedPreferences.edit().putBoolean(name+"wait",true).apply();
                        finalViewHolder.bt_download.setClickable(false);
                    }
                    sharedPreferences.edit().putBoolean(name + "zt", isClick).apply();//判断下载状态;
                }
            }
        });
        if(position==0){
            viewHolder.iv_guan.setImageResource(R.mipmap.game_top_no1);
        }else if(position==1){
            viewHolder.iv_guan.setImageResource(R.mipmap.game_top_no2);
        }else if(position==2){
            viewHolder.iv_guan.setImageResource(R.mipmap.game_top_no3);
        }else {
            viewHolder.iv_guan.setImageResource(R.mipmap.game_top_no4);
        }
        String imagUrl=list.get(position).getIcon();
        int download=list.get(position).getDownload();
        int size=list.get(position).getSize();
        double commend=list.get(position).getComment();
        //5颗星;
        Tools.GameDate(commend,viewHolder.iv1,viewHolder.iv2,viewHolder.iv3,viewHolder.iv4,viewHolder.iv5);
        int mb=size/1024;
        viewHolder.tv_name.setText(list.get(position).getName());
        viewHolder.tv_size.setText("/"+mb+" MB");
        viewHolder.tv_number.setText(position+1+"");
        viewHolder.tv_id.setText(list.get(position).getId()+"");
        if(download/10000>0){
            viewHolder.tv_download.setText(download/1000+"万人在玩");
        }else {
            viewHolder.tv_download.setText(download+"人在玩");
        }
        //加载图片;
        BitmapUtils bitmapUtils=new BitmapUtils(context);
        bitmapUtils.display(viewHolder.iv_icon,imagUrl);
            if (flag){
                viewHolder.text_topic.setVisibility(View.VISIBLE);
                viewHolder.text_topic.setText(list.get(position).getRecommend_desc() + "");
            }
        convertView.setTag(R.id.runk_id,list.get(position).getId());//id:报错；
        //下载
        return convertView;
    }
  public static class ViewHolder{
       ImageView iv_icon,iv1,iv2,iv3,iv4,iv5,iv_guan;
       TextView tv_number,tv_download,tv_name,tv_size,tv_id;
       Button bt_download;
       TextView text_topic;
   }
    public void init(){
        sharedPreferences=context.getSharedPreferences("game",Context.MODE_PRIVATE);
    }
}
