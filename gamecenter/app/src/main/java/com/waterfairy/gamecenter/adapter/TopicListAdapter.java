package com.waterfairy.gamecenter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.waterfairy.gamecenter.BaseGame.TopicListBean;
import com.waterfairy.gamecenter.R;

import java.util.List;

/**
 * Created by shui on 2015/10/20.
 */
public class TopicListAdapter extends BaseAdapter {
    private Context context;
    private List<TopicListBean.MsgEntity> list;
    private ViewHolder viewHolder;
    private BitmapUtils utils;
    private TopicListBean.MsgEntity entity;

    public TopicListAdapter(Context context, List<TopicListBean.MsgEntity> list) {
        this.list = list;
        this.context = context;
        utils=new BitmapUtils(context);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_base_topiclist, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }


        viewHolder= (ViewHolder) convertView.getTag();
        entity=list.get(position);
        if (TextUtils.isEmpty(entity.getName())){
            viewHolder.titleTopiclist.setText(entity.getTitle());
            viewHolder.contentTopiclist.setText(entity.getSummary());
            viewHolder.date.setText(entity.getStartDate()+"-"+entity.getEndDate());
            if (entity.getStatus().equals("0")){
                viewHolder.join.setText("马上参加");
                viewHolder.join.setBackgroundColor(context.getResources().getColor(R.color.status0_huodong));
            }
            else if(entity.getStatus().equals("2")){
                viewHolder.join.setText("即将开始");
                viewHolder.join.setBackgroundColor(context.getResources().getColor(R.color.status2_huodong));
            }
            else {
                viewHolder.join.setText("已结束");
                viewHolder.join.setBackgroundColor(context.getResources().getColor(R.color.status1_huodong));
            }
            utils.display(viewHolder.imageTopiclist,entity.getIcon_url());
        }else {
            viewHolder.titleTopiclist.setText(entity.getName());
            viewHolder.contentTopiclist.setText(entity.getDesc());
            utils.display(viewHolder.imageTopiclist,entity.getIcon());

        }



        return convertView;
    }

    static class ViewHolder {
        private ImageView imageTopiclist;
        private TextView titleTopiclist;
        private TextView contentTopiclist;
        private TextView date;
        private TextView join;

        private ViewHolder(View view) {
            imageTopiclist = (ImageView) view.findViewById(R.id.image_topiclist);
            titleTopiclist = (TextView) view.findViewById(R.id.title_topiclist);
            contentTopiclist = (TextView) view.findViewById(R.id.content_topiclist);
            date = (TextView) view.findViewById(R.id.date_activity);
            join = (TextView) view.findViewById(R.id.join_activity);

        }


    }
}
