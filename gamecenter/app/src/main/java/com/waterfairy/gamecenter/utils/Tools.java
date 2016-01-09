package com.waterfairy.gamecenter.utils;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.adapter.RankBaseAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/20.
 */
public class Tools {
    public static void GameDate(double commend, ImageView iv1, ImageView iv2, ImageView iv3, ImageView iv4, ImageView iv5) {
        if (commend >= 4.5) {
            changeColor(iv1, R.mipmap.game_detail_comment_star_full);
            changeColor(iv2, R.mipmap.game_detail_comment_star_full);
            changeColor(iv3, R.mipmap.game_detail_comment_star_full);
            changeColor(iv4, R.mipmap.game_detail_comment_star_full);
            changeColor(iv5, R.mipmap.game_detail_comment_star_full);
        }
        if (commend >= 3.5 & commend < 4.5) {
            changeColor(iv5, R.mipmap.game_detail_comment_star_empty);
        } else if (commend >= 2.5 & commend < 3.5) {
            changeColor(iv4, R.mipmap.game_detail_comment_star_empty);
            changeColor(iv5, R.mipmap.game_detail_comment_star_empty);
        } else if (commend >= 1.5 & commend < 2.5) {
            changeColor(iv3, R.mipmap.game_detail_comment_star_empty);
            changeColor(iv4, R.mipmap.game_detail_comment_star_empty);
            changeColor(iv5, R.mipmap.game_detail_comment_star_empty);
        } else if (commend >= 0.5 & commend < 1.5) {
            changeColor(iv2, R.mipmap.game_detail_comment_star_empty);
            changeColor(iv3, R.mipmap.game_detail_comment_star_empty);
            changeColor(iv4, R.mipmap.game_detail_comment_star_empty);
            changeColor(iv5, R.mipmap.game_detail_comment_star_empty);
        } else if (commend < 0.5) {
            changeColor(iv1, R.mipmap.game_detail_comment_star_empty);
            changeColor(iv2, R.mipmap.game_detail_comment_star_empty);
            changeColor(iv3, R.mipmap.game_detail_comment_star_empty);
            changeColor(iv4, R.mipmap.game_detail_comment_star_empty);
            changeColor(iv5, R.mipmap.game_detail_comment_star_empty);
        }
    }
    public static void changeColor(ImageView iv, int id) {
        iv.setImageResource(id);
    }

}
