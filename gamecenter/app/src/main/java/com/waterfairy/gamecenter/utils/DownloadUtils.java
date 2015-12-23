package com.waterfairy.gamecenter.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.waterfairy.gamecenter.BaseGame.DbdetailsBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.activitys.DownloadingActivity;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shui on 2015/10/28.
 */
public class DownloadUtils {
    static DbUtils db;
    static SharedPreferences share;
    static SharedPreferences shareState;
    static Context context;
    static Intent intent;
    static boolean downState1;
    static boolean downState2;
    static boolean downState3;
    static NotificationManager notificationManager;
    public static final int DOWNLOADING = 1; //下载中
    public static final int SUCCESS = 2; //已经下载了
    public static final int JOIN = 3; //加入下载队列
    public static final int INSTALL = 4; //加入下载队列
    static Message message;
    public static final int NOTDOWNLOAD = 0; //未下载
    static int state;
    static int count;

//    static final  int  DOWNLOADING=1;

    public static int downLoad(Context context1, String url, String pkg, String name, String imgUrl, Handler handler) {
        context = context1;
        db = DbUtils.create(context1,"down_details");
        shareState=context.getSharedPreferences("down_state", Context.MODE_PRIVATE);
        message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("pkg", pkg);
        message.setData(bundle);
        notificationManager = (NotificationManager) context.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
        share = context.getSharedPreferences("down", Context.MODE_PRIVATE);
        state = shareState.getInt(pkg, 0);
        downState1 = share.getBoolean("downState1", false);
        downState2 = share.getBoolean("downState2", false);
        downState3 = share.getBoolean("downState3", false);
        switch (state) {
            case 0:
                int use = 1;
                count = share.getInt("count", 0);
                if (count < 3) {
                    intent = new Intent(pkg);
                    if (!downState1) {
                        use = 1;
                    } else if (!downState2) {
                        use = 2;
                    } else if (!downState3) {
                        use = 3;
                    }
                    Log.i("down", "downLoad use:"+use+"---count"+count);
                    share.edit().
                            putBoolean("downState" + use, true).
                            putInt("count", count + 1).//下载数量加1
//                            putInt(pkg, DOWNLOADING).//pkg 状态 下载中
                            putString(use + "down", pkg).apply();//使用未使用的下载线程
                    return downing(url, pkg, name, imgUrl, use, handler, intent);
                } else {
                    Toast.makeText(context1, "加入队列中...", Toast.LENGTH_SHORT).show();
                    return JOIN;
                    // TODO: 2015/10/29  加入队列中
                }
            case 1:
                Toast.makeText(context1, "下载中...", Toast.LENGTH_SHORT).show();
                return DOWNLOADING;

            case 2:
                Toast.makeText(context1, "已经下载过啦", Toast.LENGTH_SHORT).show();
                return SUCCESS;
        }
        return -1;
    }

    static int downing(final String url, final String pkg, final String name, final String imgUrl, final int use, final Handler handler, final Intent intent) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, DownloadingActivity.class), 0);
        builder.setContentTitle(name);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.icon);
        Toast.makeText(context, "即将下载...", Toast.LENGTH_SHORT).show();
        String path = Environment.getExternalStorageDirectory() + "/GameCenter/" + pkg + ".apk";
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.download(url, path, new RequestCallBack<File>() {
            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                DbdetailsBean details=new DbdetailsBean();
                details.setImgUrl(imgUrl);
                details.setName(name);
                details.setPkg(pkg);
                details.setPath(url);
                details.setState(SUCCESS);
                details.setDonwnIngState(SUCCESS);
                try {
                    db.save(details);
                } catch (DbException e) {
                    Log.i("wrong", "onSuccess ");
                    e.printStackTrace();
                }finally {
//                    db.close();
                }
                shareState.edit().putInt(pkg,SUCCESS).apply();
                builder.setContentText("下载完成");
                notificationManager.notify(use, builder.build());
                Log.i("down", "success use:"+use+"---count"+(share.getInt("count",1)-1));
                share.edit().
                        putInt("count", share.getInt("count",1)-1).//下载数量减1
                        putString(use + "down", "").
//                        putInt(pkg, SUCCESS).
                        putBoolean("downState" + use, false).apply();//添加未使用的下载线程//pkg 状态 完成
                handler.sendMessageDelayed(message,10);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                builder.setProgress(100, (int) (current * 100 / total), false);
                notificationManager.notify(use, builder.build());
                intent.putExtra("name", name);
                intent.putExtra("imgUrl", imgUrl);
                intent.putExtra("total", total);
                intent.putExtra("current", current);
                intent.putExtra("isUploading", isUploading);
                Log.i("test11", "onLoading 发送广播" + pkg);
                context.sendBroadcast(intent);
            }
        });
        return DOWNLOADING;
    }
    public static Map<String, ?> getPkgs(Context context){
        shareState=context.getSharedPreferences("down_state",Context.MODE_PRIVATE);
        Map<String, ?> hashMap = share.getAll();
        return hashMap;
    }
}
