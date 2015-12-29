package com.waterfairy.gamecenter.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.waterfairy.gamecenter.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author james
 */
public abstract class stateChange {
    int download = 0;
    int tag;
    String anInt;
    boolean isClick,isWait;
    final List<Integer> waitList=new ArrayList<>();
    String nextName,nextApkUrl;
    public void stateDownload(final Context context, final String imagUri,final String name,final int position, final View view, final String pkgname, final String apkUrl,final SharedPreferences sharedPreferences) {
        view.setTag(pkgname);//设置标记;
        view.setFocusable(false);//设置button不抢焦点;
        //下载...
        final File file = new File("/sdcard/GameCenter/");
        if (!file.exists()) {
            file.mkdirs();//创建文件夹;
        }
        anInt = sharedPreferences.getString(pkgname + "完成", "");//判断是否已经下载;
        isClick = sharedPreferences.getBoolean(pkgname + "zt", false);//判断是否是已点击下载;
        isWait = sharedPreferences.getBoolean(pkgname + "wait", false);
        //是否是下载完成
        if (name.equals(anInt)) {
            view.setBackgroundColor(0xFFFCD103);
            if(view instanceof TextView){
                ((TextView) view).setText("打开");
            }if(view instanceof Button){
                ((Button) view).setText("打开");
            }
        } else {
            //表示正在下载；
            if (sharedPreferences.getBoolean(pkgname + "xz", false)) {
                //表示是否是暂停状态；
                if (isClick) {
                    view.setBackgroundColor(0xFFF56B25);
                    if(view instanceof TextView){
                        ((TextView) view).setText("下载中");
                    }if(view instanceof Button){
                        ((Button) view).setText("下载中");
                    }
                }
            } else {
                if(view instanceof TextView){
                    ((TextView) view).setText("下载");
                }if(view instanceof Button){
                    ((Button) view).setText("下载");
                }
                view.setBackgroundColor(0xFFFCD103);
            }
            if (isWait) {
                if(view instanceof TextView){
                    ((TextView) view).setText("等待中");
                }if(view instanceof Button){
                    ((Button) view).setText("等待中");
                }
                view.setBackgroundColor(0xFFF56B25);
            }
        }
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                String tagname = data.getString("pkg");
                sharedPreferences.edit().putString(tagname + "完成", tagname).apply();
                if (sharedPreferences.getBoolean(tagname + "xz", false)) {
                    if(view instanceof TextView){
                        ((TextView) view).setText("打开");
                    }if(view instanceof Button){
                        ((Button) view).setText("打开");
                    }
                    view.setBackgroundColor(0xFFFCD103);
                    download--;
                    if (waitList.size() > 0) {
                        download++;
//                        String apkUrl =findList().get(waitList.get(0)).getApkurl();
//                        String name = findList().get(waitList.get(0)).getPkgName();
//                        DownloadUtils.downLoad(context, apkUrl, name, this, waitList.get(0));
                        sharedPreferences.edit().putBoolean(waitList.get(0) + "xz", true).apply();
                        isClick = true;
//                        waitList.remove(0);
//                        if (sharedPreferences.getBoolean(waitList.get(0) + "xz", false)) {
//                            view.setClickable(true);
//                            if(view instanceof TextView){
//                                ((TextView) view).setText("下载中");
//                            }if(view instanceof Button){
//                                ((Button) view).setText("下载中");
//                            }
//                        }
                    }
                    //下载完成后-->在重新存储下载个数;
                    sharedPreferences.edit().putInt("download", download).apply();
                }
            }
        };
        //点击事件中用到的参数必须在其内部写，如果写在外部不换页面的话不会去更新;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick = sharedPreferences.getBoolean(position + "zt", false);
                download = sharedPreferences.getInt("download", 0);//设置下载量的个数;
                anInt = sharedPreferences.getString(pkgname + "完成", "");//判断是否已经下载;
                //防止当前不在那个下载页面;
                tag = (int) v.getTag();
                //当已经下载后...
                if (pkgname.equals(anInt)) {
                    AutoInstall.setUrl(file.getAbsolutePath() + "/" + pkgname + ".apk");
                    AutoInstall.install(context);//安装界面;FFFCD103);
                } else {
                    //如果点击过后-->download只加一次;
                    if (!sharedPreferences.getBoolean(position + "first", false)) {
                        download++;
                        sharedPreferences.edit().putInt("download", download).apply();
                    }
                    sharedPreferences.edit().putBoolean(position + "first", true).apply();//判断是否是第一次点击;
                    sharedPreferences.edit().putBoolean(position + "xz", true).apply();//设置下载事件的存储;
                    if (download <= 3 && !isClick) {
                        if (view instanceof TextView) {
                            ((TextView) view).setText("下载中");
                        }
                        if (view instanceof Button) {
                            ((Button) view).setText("下载中");
                        }
                        view.setBackgroundColor(0xFFF56B25);
                        DownloadUtils.downLoad(context, apkUrl, name, pkgname, imagUri, handler);
                        isClick = true;
                    } else {
                        waitList.add(position);
                        if (download <= 3 && !isClick) {
                            if (view instanceof TextView) {
                                ((TextView) view).setText("等待中");
                            }
                            if (view instanceof Button) {
                                ((Button) view).setText("等待中");
                            }
                            view.setBackgroundColor(0xFFF56B25);
                            sharedPreferences.edit().putBoolean(pkgname + "wait", true).apply();
                            view.setClickable(false);
                        }
                        sharedPreferences.edit().putBoolean(pkgname + "zt", isClick).apply();//判断下载状态;
                    }
                }
            }
        });
    }
}
