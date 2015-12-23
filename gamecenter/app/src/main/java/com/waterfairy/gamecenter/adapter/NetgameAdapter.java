package com.waterfairy.gamecenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.waterfairy.gamecenter.BaseGame.NetGameBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.activitys.NetGameMoreActivity;
import com.waterfairy.gamecenter.category.CateItemActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shui on 2015/10/26.
 */
public class NetgameAdapter extends BaseAdapter {
    private static Context context;
    private ViewHolder viewHolder;
    private static List<NetGameBean.RecommendEntity> list;
    private float fix;
    private BitmapUtils utils;
    private String tag;

    public NetgameAdapter(Context context, float fix, List<NetGameBean.RecommendEntity> list,String tag) {
        this.context = context;
        this.fix = fix;
        this.list = list;
        this.tag=tag;
        utils = new BitmapUtils(context);
    }

    @Override
    public int getCount() {
//        Log.i("test5", "getView retu"+ list.size());
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
        //
//        Log.i("test5", "getView "+position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_netgame, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        viewHolder = (ViewHolder) convertView.getTag();
        NetGameBean.RecommendEntity entity = list.get(position);
        //icon
        utils.display(viewHolder.iconRecommend, entity.getIcon());
        //title
        viewHolder.titleRecommend.setText(entity.getName());
        //小编语录
        viewHolder.sloganRecommend.setText(entity.getRecommend_desc());
        //分类
        viewHolder.categoryRecommendItem.setText(entity.getType());
        viewHolder.categoryRecommendItem.setHint(position + "");
        //下载
        viewHolder.downloadRecommend.setHint(position + "");
        //用户量
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
        viewHolder.apksizeRecommend.setText("/" + entity.getSize() / 1000 + "M");
        //星级
        int num = (int) entity.getComment();
        for (int i = 1; i <= 5; i++) {
            if (num >= i) {
                viewHolder.imgList.get(i - 1).setImageResource(R.mipmap.game_detail_comment_star_full);
            } else {
                viewHolder.imgList.get(i - 1).setImageResource(R.mipmap.game_detail_comment_star_empty);
            }
        }

        if (tag.equals("netgame")) {
            if (position >= 3) {
                viewHolder.iconXv.setVisibility(View.GONE);
                viewHolder.contentFirst.setPadding(0, 0, 0, 0);
                if (position > 3) {//position 3-...
                    viewHolder.contentThird.setVisibility(View.GONE);
                    viewHolder.contentSecond.setVisibility(View.GONE);

                } else {//position 3
                    viewHolder.contentFirst.setPadding(0, (int) (31 * fix), 0, 0);
                    viewHolder.contentThird.setVisibility(View.VISIBLE);
                    viewHolder.contentSecond.setVisibility(View.GONE);
                }
            } else {

                viewHolder.iconXv.setVisibility(View.VISIBLE);

                if (position > 0) {//position 1-2
                    viewHolder.contentFirst.setPadding(0, 0, 0, 0);
                    viewHolder.contentThird.setVisibility(View.GONE);
                    viewHolder.contentSecond.setVisibility(View.GONE);
                } else {//  position 0
                    viewHolder.more.setHint(position + "");
                    viewHolder.contentFirst.setPadding(0, (int) (31 * fix), 0, 0);
                    viewHolder.contentThird.setVisibility(View.GONE);
                    viewHolder.contentSecond.setVisibility(View.VISIBLE);
                }
            }
        }else {
            viewHolder.contentThird.setVisibility(View.GONE);
            viewHolder.contentSecond.setVisibility(View.GONE);
        }


        return convertView;
    }

    static class ViewHolder implements View.OnClickListener {
        private RelativeLayout contentFirst;
        private RelativeLayout top;
        private ImageView iconRecommend;
        private TextView titleRecommend;
        private LinearLayout startRecommendLin;
        private ImageView startRecommend1;
        private ImageView startRecommend2;
        private ImageView startRecommend3;
        private ImageView startRecommend4;
        private ImageView startRecommend5;
        private TextView userCount;
        private TextView apksizeRecommend;
        private TextView downloadRecommend;
        private TextView line;
        private ImageView iconXv;
        private TextView sloganRecommend;
        private TextView categoryRecommendItem;
        private RelativeLayout contentSecond;
        private ImageView img2ItemNetgame;
        private ImageView contentThird;
        private TextView more;
        private List<ImageView> imgList;

        public ViewHolder(View view) {
            contentFirst = (RelativeLayout) view.findViewById(R.id.content_first);
            contentSecond = (RelativeLayout) view.findViewById(R.id.content_second);
            contentThird = (ImageView) view.findViewById(R.id.content_third);
            top = (RelativeLayout) view.findViewById(R.id.top);
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
            userCount = (TextView) view.findViewById(R.id.user_count);
            apksizeRecommend = (TextView) view.findViewById(R.id.apksize_recommend);
            downloadRecommend = (TextView) view.findViewById(R.id.download_recommend);
            downloadRecommend.setOnClickListener(this);
            line = (TextView) view.findViewById(R.id.line);
            iconXv = (ImageView) view.findViewById(R.id.icon_xv);
            sloganRecommend = (TextView) view.findViewById(R.id.slogan_recommend);
            categoryRecommendItem = (TextView) view.findViewById(R.id.category_recommend_item);
            categoryRecommendItem.setOnClickListener(this);
            img2ItemNetgame = (ImageView) view.findViewById(R.id.img2_item_netgame);
            more = (TextView) view.findViewById(R.id.more_netgame);
            more.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            String string = (String) ((TextView) v).getHint();
            NetGameBean.RecommendEntity entity = list.get(Integer.parseInt(string));
            if (v.getId() == R.id.download_recommend) {

//                Log.i("test", "onClick--item  下载 " + entity.getApkurl());
                return;
            }
            if (v.getId() == R.id.category_recommend_item) {
                Intent intent = new Intent(context, CateItemActivity.class);
                intent.putExtra("title", entity.getType());
                intent.putExtra("id", entity.getCategoryId());
//                Log.i("test", "onClick " + Integer.parseInt(string));
                context.startActivity(intent);

                return;
            }
//            if ((((TextView) v).getText()).toString().substring(0, 4).equals("查看更多")) {
            if (v.getId()==R.id.more_netgame) {
                Intent intent = new Intent(context, NetGameMoreActivity.class);
                context.startActivity(intent);
                return;
            }

        }
    }
}
