package com.waterfairy.gamecenter.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhaohang on 2015/10/23.
 */
public class MyOpenHelper extends SQLiteOpenHelper {
    private static String DATABASENAME="download.db";
    private static int version=1;

    public MyOpenHelper(Context context) {
        super(context, DATABASENAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建下载信息表; id自增,子线程数,开始位置,结束位置,以及文件大小,地址;
        db.execSQL("create table download_info(_id integer PRIMARY KEY AUTOINCREMENT, thread_id integer, "
                + "start_pos integer, end_pos integer, compelete_size integer,url char)");
        //创建用户注册信息表;
        db.execSQL("create table userinfo(_id text,pwd text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
