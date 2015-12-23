package com.waterfairy.gamecenter.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.waterfairy.gamecenter.BaseGame.DbdetailsBean;
import com.waterfairy.gamecenter.BaseGame.RecommendBean;
import com.waterfairy.gamecenter.R;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shui on 2015/10/31.
 */
public class DownViewUtils {
    public static void setButton(Context context, List<String> listPkgs, HashMap<String, Integer> hashMap, TextView downloadRecommend, Button button, String pkg, long size) {
        int state;
        if (downloadRecommend == null) {
            if (listPkgs.contains(pkg)) {
                button.setText("打开");
                button.setBackgroundColor(context.getResources().getColor(R.color.open));
            } else {
                if (hashMap.containsKey(pkg)) {
                    Log.i("test12", "有"+hashMap.size());
                    state = hashMap.get(pkg);
                    Log.i("test12", "setButton --"+state+"--"+pkg);
                    if (state == DownloadUtils.SUCCESS) {
                        button.setText("安装");
                        button.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    } else if (state == DownloadUtils.DOWNLOADING) {
                        File file = new File(Environment.getExternalStorageDirectory() + "/GameCenter/" + pkg + ".apk");
                        if (file != null && file.length() == size) {

                        }
                        button.setBackgroundColor(context.getResources().getColor(R.color.green));
                        button.setText("下载中");
                    } else if (state == DownloadUtils.INSTALL) {
                        //此状态 取消了安装,或者w安装失败
                        button.setText("安装");
                        button.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    } else if (state == DownloadUtils.JOIN) {
                        button.setText("队列中");
                        button.setBackgroundColor(context.getResources().getColor(R.color.join));
                    }
                } else {
                    Log.i("test12", "空"+hashMap.size());
                    button.setBackgroundColor(context.getResources().getColor(R.color.down));
                    button.setText("下载");
                }
            }
        } else {
            if (listPkgs.contains(pkg)) {
                downloadRecommend.setText("打开");
                downloadRecommend.setBackgroundColor(context.getResources().getColor(R.color.open));
            } else {
                if (hashMap.containsKey(pkg)) {
                    Log.i("test12", "有"+hashMap.size());
                    state = hashMap.get(pkg);
                    Log.i("test12", "setButton --"+state+"--"+pkg);
                    if (state == DownloadUtils.SUCCESS) {
                        downloadRecommend.setText("安装");
                        downloadRecommend.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    } else if (state == DownloadUtils.DOWNLOADING) {
                        File file = new File(Environment.getExternalStorageDirectory() + "/GameCenter/" + pkg + ".apk");
                        if (file != null && file.length() == size) {

                        }
                        downloadRecommend.setBackgroundColor(context.getResources().getColor(R.color.green));
                        downloadRecommend.setText("下载中");
                    } else if (state == DownloadUtils.INSTALL) {
                        //此状态 取消了安装,或者w安装失败
                        downloadRecommend.setText("安装");
                        downloadRecommend.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    } else if (state == DownloadUtils.JOIN) {
                        downloadRecommend.setText("队列中");
                        downloadRecommend.setBackgroundColor(context.getResources().getColor(R.color.join));
                    }
                } else {
                    Log.i("test12", "空"+hashMap.size());
                    downloadRecommend.setBackgroundColor(context.getResources().getColor(R.color.down));
                    downloadRecommend.setText("下载");
                }
            }
        }

    }

    static Context context;
    static TextView down;

    public static void handleClick(Context context1, String content, SharedPreferences shareState, TextView v, Button button, DbdetailsBean entity,Handler handlerGet) {
        handlerGet1=handlerGet;
        context = context1;
        down = (TextView) v;
        //下载按钮
        switch (content) {
            case "打开":

                break;
            case "安装":
                shareState.edit().putInt(entity.getPkg(), DownloadUtils.INSTALL).apply();
                File file = new File(Environment.getExternalStorageDirectory() + "/GameCenter/" + entity.getName() + ".apk");
                if (file==null){
                    Toast.makeText(context1,"安装包丢失啦,哈哈",Toast.LENGTH_SHORT).show();
                    down.setText("下载");
                    down.setBackgroundColor(context.getResources().getColor(R.color.down));
                    shareState.edit().putInt(entity.getPkg(), DownloadUtils.NOTDOWNLOAD).apply();
                }else {
                    AutoInstall.setUrl(file.getAbsolutePath());
                    AutoInstall.install(context);
                }

                break;
            case "下载":
                int state = DownloadUtils.downLoad(context, entity.getPath(), entity.getPkg(), entity.getName(), entity.getImgUrl(), handler);
                if (state == DownloadUtils.JOIN) {

                    down.setText("队列中");
                    down.setBackgroundColor(context.getResources().getColor(R.color.join));
                    shareState.edit().putInt(entity.getPkg(), DownloadUtils.JOIN).apply();
                } else if (state == DownloadUtils.DOWNLOADING) {
                    down.setBackgroundColor(context.getResources().getColor(R.color.green));
                    down.setText("下载中");
                    shareState.edit().putInt(entity.getPkg(), DownloadUtils.DOWNLOADING).apply();
                }
                break;
            case "下载中":
                // TODO: 2015/10/30 暂停

                Toast.makeText(context, "暂停功能还没有实现呢/哭泣", Toast.LENGTH_SHORT).show();
                break;
            case "队列中":
                down.setText("下载");
                down.setBackgroundColor(context.getResources().getColor(R.color.down));
                shareState.edit().putInt(entity.getPkg(), DownloadUtils.NOTDOWNLOAD).apply();
                Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show();

                break;

        }
    }

    static Handler handlerGet1;
    static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            down.setBackgroundColor(context.getResources().getColor(R.color.gray));
            down.setText("安装");
            handlerGet1.sendEmptyMessageDelayed(0, 30);
        }
    };
}
