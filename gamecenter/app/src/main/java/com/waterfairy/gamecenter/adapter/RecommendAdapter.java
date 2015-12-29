package com.waterfairy.gamecenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.tech.IsoDep;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.waterfairy.gamecenter.BaseGame.DbdetailsBean;
import com.waterfairy.gamecenter.BaseGame.RecommendBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.activitys.Detail_RunkActivity;
import com.waterfairy.gamecenter.activitys.TopicGameActivity;
import com.waterfairy.gamecenter.category.CateItemActivity;
import com.waterfairy.gamecenter.utils.AutoInstall;
import com.waterfairy.gamecenter.utils.DownViewUtils;
import com.waterfairy.gamecenter.utils.DownloadUtils;
import com.waterfairy.gamecenter.utils.NoSystemApk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author james
 */
public class RecommendAdapter extends BaseAdapter implements View.OnClickListener {
    private static List<String> listCon;
    private static Context context;
    private static List<RecommendBean.RecommendEntity> list;
    private RecommendBean.RecommendEntity entity;
    private BitmapUtils utils;
    private ViewHolder viewHolder;
    private static float fix;
    private static HashMap<String, Integer> hashMap;
    private static HashMap<String, Integer> hashMapDowning;
    private int state;
    private static List<String> listPkgs;
    private static SharedPreferences shareState;


    public void notification() {
        update();
        notifyDataSetChanged();
    }

    public RecommendAdapter(Context context, List<RecommendBean.RecommendEntity> list, float fix) {
        this.context = context;
        this.list = list;
        this.fix = fix;
        utils = new BitmapUtils(context);
        listCon = new ArrayList<>();
        update();

    }

    public static void update() {
        shareState = context.getSharedPreferences("down_state", Context.MODE_PRIVATE);
        hashMap = (HashMap<String, Integer>) shareState.getAll();
        listPkgs = NoSystemApk.getPkgs(context);
    }

    @Override
    public int getCount() {
        if (list != null) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        entity = list.get(position);
        viewHolder = (ViewHolder) convertView.getTag();

        if (entity.getRelative() == null) {
            viewHolder.contentItemRecommend.setVisibility(View.VISIBLE);
            viewHolder.imageRecommend.setVisibility(View.GONE);
            viewHolder.lin_recommend.setVisibility(View.GONE);
            utils.display(viewHolder.iconRecommend, entity.getIcon());
            viewHolder.titleRecommend.setText(entity.getName());
            int num = (int) entity.getComment();
            for (int i = 1; i <= 5; i++) {
                if (num >= i) {
                    viewHolder.imgList.get(i - 1).setImageResource(R.mipmap.game_detail_comment_star_full);
                } else {
                    viewHolder.imgList.get(i - 1).setImageResource(R.mipmap.game_detail_comment_star_empty);
                }
            }
            String countStr;
            int count = entity.getDownload();
            if (count < 10000) {
                countStr = count + "人在玩";
            } else if (count < 100000000) {
                countStr = count / 10000 + "万人在玩";
            } else if (count < 1000000000) {
                countStr = count / 10000000 + "千万人在玩";
            } else {
                countStr = count / 100000000 + "亿人在玩";
            }
            viewHolder.userCount.setText(countStr);
            viewHolder.size.setText("/" + entity.getSize() / 1000 + "M");
            viewHolder.sloganRecommend.setText(entity.getRecommend_desc());
            viewHolder.categoryRecommendItem.setText(entity.getType());
            viewHolder.categoryRecommendItem.setHint(position + "");
            viewHolder.downloadRecommend.setHint(position + "");
            String pkg = entity.getPkgName();
            DownViewUtils.setButton(context, listPkgs, hashMap, viewHolder.downloadRecommend, null, pkg, entity.getSize());
            if (position == 0) {
                viewHolder.iconXv.setVisibility(View.VISIBLE);
                viewHolder.categoryRecommendItem.setText("更多推荐");
            } else {
                viewHolder.iconXv.setVisibility(View.GONE);
            }
        } else {
            viewHolder.contentItemRecommend.setVisibility(View.GONE);
            viewHolder.imageRecommend.setVisibility(View.VISIBLE);
            utils.display(viewHolder.imageRecommend, entity.getPicUrl());
            if ("2".equals(entity.getRelativeType())) {
                viewHolder.lin_recommend.setVisibility(View.VISIBLE);
//                Log.i("test2", "getView " + entity.getRelativeType() + "---" + entity.getMsg().size());
                List<RecommendBean.RecommendEntity.MsgEntity> msg = entity.getMsg();
                if (viewHolder.lin_recommend.getChildCount() == 0) {
                    for (int i = 0; i < msg.size(); i++) {
                        ImageView imageView = new ImageView(context);
                        imageView.setOnClickListener(this);
                        imageView.setTag(msg.get(i).getId());
                        utils.display(imageView, msg.get(i).getIcon());
//                        Log.i("test2", "getView " + msg.get(i).getIcon());
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, (int) (55 * fix));
                        layoutParams.weight = 1;
                        imageView.setLayoutParams(layoutParams);
                        viewHolder.lin_recommend.addView(imageView);
                    }
                }
            }
        }

        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, Detail_RunkActivity.class);
        intent.putExtra("id", (int) (v.getTag()));
        context.startActivity(intent);
    }

    static class ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private RelativeLayout contentItemRecommend;
        private ImageView iconRecommend;
        private TextView titleRecommend;
        private LinearLayout startRecommendLin;
        private ImageView startRecommend1;
        private ImageView startRecommend2;
        private ImageView startRecommend3;
        private ImageView startRecommend4;
        private ImageView startRecommend5;
        private TextView userCount;
        private TextView downloadRecommend;
        private ImageView iconXv;
        private TextView sloganRecommend;
        private TextView categoryRecommendItem;
        private ImageView imageRecommend;
        private TextView size;
        private LinearLayout lin_recommend;//五个小模块

        private List<ImageView> imgList;

        public ViewHolder(View view) {
            contentItemRecommend = (RelativeLayout) view.findViewById(R.id.content__item_recommend);
            iconRecommend = (ImageView) view.findViewById(R.id.icon_recommend);
            titleRecommend = (TextView) view.findViewById(R.id.title_recommend);
            startRecommendLin = (LinearLayout) view.findViewById(R.id.start_recommend_lin);
            startRecommend1 = (ImageView) view.findViewById(R.id.start_recommend_1);
            startRecommend2 = (ImageView) view.findViewById(R.id.start_recommend_2);
            startRecommend3 = (ImageView) view.findViewById(R.id.start_recommend_3);
            startRecommend4 = (ImageView) view.findViewById(R.id.start_recommend_4);
            startRecommend5 = (ImageView) view.findViewById(R.id.start_recommend_5);
            imgList = new ArrayList<>();
            imgList.add(startRecommend1);
            imgList.add(startRecommend2);
            imgList.add(startRecommend3);
            imgList.add(startRecommend4);
            imgList.add(startRecommend5);
            size = (TextView) view.findViewById(R.id.apksize_recommend);
            userCount = (TextView) view.findViewById(R.id.user_count);
            downloadRecommend = (TextView) view.findViewById(R.id.download_recommend);
            downloadRecommend.setOnClickListener(this);
            downloadRecommend.setOnLongClickListener(this);
            iconXv = (ImageView) view.findViewById(R.id.icon_xv);
            sloganRecommend = (TextView) view.findViewById(R.id.slogan_recommend);
            categoryRecommendItem = (TextView) view.findViewById(R.id.category_recommend_item);
            categoryRecommendItem.setOnClickListener(this);
            imageRecommend = (ImageView) view.findViewById(R.id.image_recommend);
            lin_recommend = (LinearLayout) view.findViewById(R.id.lin_recommend);
        }

        TextView down;

        @Override
        public void onClick(final View v) {
            String content = (((TextView) v).getText()).toString();

            if (content.equals("更多推荐")) {
                Intent intent = new Intent(context, TopicGameActivity.class);
                intent.putExtra("title", "每日一荐");
                context.startActivity(intent);
                return;
            }
            String string = (String) ((TextView) v).getHint();
            final RecommendBean.RecommendEntity entity = RecommendAdapter.list.get(Integer.parseInt(string));
            if (v.getId() == R.id.download_recommend) {
                DbdetailsBean dbdetailsBean = new DbdetailsBean();
                dbdetailsBean.setPath(entity.getApkurl());
                dbdetailsBean.setPkg(entity.getPkgName());
                dbdetailsBean.setName(entity.getName());
                dbdetailsBean.setImgUrl(entity.getIcon());
                DownViewUtils.handleClick(context, content, shareState, (TextView) v, null, dbdetailsBean,handler);
                update();
                return;
            }
            if (v.getId() == R.id.category_recommend_item) {
                Intent intent = new Intent(RecommendAdapter.context, CateItemActivity.class);
                intent.putExtra("title", entity.getType());
                intent.putExtra("id", entity.getCategoryId());
//                Log.i("test", "onClick " + Integer.parseInt(string));
                RecommendAdapter.context.startActivity(intent);
//                Log.i("test", "onClick 跟多推荐");
                return;
            }

        }
       static Handler handler =new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                update();
            }
        };

        @Override
        public boolean onLongClick(View v) {
            String string = (String) ((TextView) v).getHint();
            final RecommendBean.RecommendEntity entity = RecommendAdapter.list.get(Integer.parseInt(string));
            ((TextView) v).setText("下载");
            v.setBackgroundColor(context.getResources().getColor(R.color.down));
            shareState.edit().putInt(entity.getPkgName(), DownloadUtils.NOTDOWNLOAD).apply();
            return false;
        }
    }


}
