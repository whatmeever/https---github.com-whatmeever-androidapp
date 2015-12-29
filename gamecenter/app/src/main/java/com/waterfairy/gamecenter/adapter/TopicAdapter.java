package com.waterfairy.gamecenter.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
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
import com.waterfairy.gamecenter.BaseGame.TopicGameBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.utils.DownloadUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author james
 */
public class TopicAdapter extends BaseAdapter {
    private static Context context;
    private static List<TopicGameBean.MsgEntity> list;
    private ViewHolder viewHolder;
    private BitmapUtils utils;

    public TopicAdapter(Context context, List<TopicGameBean.MsgEntity> list) {
        this.context = context;
        this.list = list;
        utils = new BitmapUtils(context);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_base, parent, false);
            convertView.setTag(new ViewHolder(convertView));

        }
        TopicGameBean.MsgEntity entity = list.get(position);

        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.titleTopic.setText(entity.getName());
        int num = (int) entity.getComment();
        for (int i = 1; i <= 5; i++) {
            if (num >= i) {
                viewHolder.imgList.get(i - 1).setImageResource(R.mipmap.game_detail_comment_star_full);
            } else {
                viewHolder.imgList.get(i - 1).setImageResource(R.mipmap.game_detail_comment_star_empty);
            }
        }

        viewHolder.downloadTopic.setHint(position+"");
        utils.display(viewHolder.iconTopic, entity.getIcon());
        String date = entity.getDate();
        if (TextUtils.isEmpty(date)) {
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
            viewHolder.apksizeTopic.setText("/" + entity.getSize() / 1000 + "M");
            viewHolder.userCountTopic.setText(countStr);
            String recommendDate = entity.getRecommendDate();
            if (!TextUtils.isEmpty(recommendDate)) {
                viewHolder.img_xb.setImageResource(R.mipmap.game_editor_vivo);
                viewHolder.sloganTopic1.setText("  推荐日期:" + recommendDate);
                viewHolder.sloganTopic2.setText("  " + entity.getRecommend_desc());
            } else {
                viewHolder.lin_xb.setVisibility(View.GONE);
                viewHolder.sloganTopic.setText(entity.getRecommend_desc());
            }


        } else {
            viewHolder.apksizeTopic.setText(entity.getSize() / 1000 + "M");
            viewHolder.sloganTopic.setText(date + "  首发");
            viewHolder.userCountNew.setText(entity.getDownload() + "");
            viewHolder.textView.setText("人已抢先体验");
            String[] urls = entity.getScreenshot().split("###");
            final ImageView[] li = new ImageView[urls.length];
            for (int i = 0; i < urls.length; i++) {
                ImageView imageView = new ImageView(context);
                imageView.setMaxHeight(150);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                utils.display(imageView, urls[i]);
                li[i] = imageView;
            }

        }


        return convertView;
    }

    static class ViewHolder implements View.OnClickListener {
        private RelativeLayout contentItemTopic;
        private RelativeLayout topTopic;
        private ImageView iconTopic;
        private TextView titleTopic;
        private LinearLayout startTopicLin;
        private ImageView startTopic1;
        private ImageView startTopic2;
        private ImageView startTopic3;
        private ImageView startTopic4;
        private ImageView startTopic5;
        private TextView userCountTopic;
        private TextView apksizeTopic;
        private TextView downloadTopic;
        private TextView lineTopic;
        private TextView sloganTopic;
        private TextView sloganTopic1;
        private TextView sloganTopic2;
        private RelativeLayout lin_xb;
        private List<ImageView> imgList;

        private LinearLayout scroll_lin;
        //        private ViewPager viewPager;
        private TextView userCountNew;
        private TextView textView;

        private ImageView img_xb;

        public ViewHolder(View view) {
            contentItemTopic = (RelativeLayout) view.findViewById(R.id.content__item_topic);
            topTopic = (RelativeLayout) view.findViewById(R.id.top_topic);
            iconTopic = (ImageView) view.findViewById(R.id.icon_topic);
            titleTopic = (TextView) view.findViewById(R.id.title_topic);
            startTopicLin = (LinearLayout) view.findViewById(R.id.start_topic_lin);
            startTopic1 = (ImageView) view.findViewById(R.id.start_topic_1);
            startTopic2 = (ImageView) view.findViewById(R.id.start_topic_2);
            startTopic3 = (ImageView) view.findViewById(R.id.start_topic_3);
            startTopic4 = (ImageView) view.findViewById(R.id.start_topic_4);
            startTopic5 = (ImageView) view.findViewById(R.id.start_topic_5);
            userCountTopic = (TextView) view.findViewById(R.id.user_count_topic);
            apksizeTopic = (TextView) view.findViewById(R.id.apksize_topic);
            downloadTopic = (TextView) view.findViewById(R.id.download_topic);
            downloadTopic.setOnClickListener(this);
            lineTopic = (TextView) view.findViewById(R.id.line_topic);
            sloganTopic = (TextView) view.findViewById(R.id.slogan_topic);
            sloganTopic1 = (TextView) view.findViewById(R.id.slogan_topic1);
            sloganTopic2 = (TextView) view.findViewById(R.id.slogan_topic2);
            lin_xb = (RelativeLayout) view.findViewById(R.id.lin_xb);

//            viewPager = (ViewPager) view.findViewById(R.id.vp_base);
            userCountNew = (TextView) view.findViewById(R.id.user_count_new);
            textView = (TextView) view.findViewById(R.id.text_view);

            img_xb = (ImageView) view.findViewById(R.id.img_xb);

            imgList = new ArrayList<>();
            imgList.add(startTopic1);
            imgList.add(startTopic2);
            imgList.add(startTopic3);
            imgList.add(startTopic4);
            imgList.add(startTopic5);
        }

        @Override
        public void onClick(final View v) {


            int position = Integer.parseInt((String) ((TextView) v).getHint());
            TopicGameBean.MsgEntity entity = list.get(position);
            int state = DownloadUtils.downLoad(context, entity.getApkurl(), entity.getPkgName(), entity.getName(), entity.getIcon(), new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    ((TextView) v).setText("安装");
                }
            });
            if (state == DownloadUtils.SUCCESS) {
                ((TextView) v).setText("安装");
            } else {
                ((TextView) v).setText("下载中");
            }


        }
    }
}







