package com.waterfairy.gamecenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.waterfairy.gamecenter.BaseGame.NewServerBean;
import com.waterfairy.gamecenter.R;

import java.util.List;

/**
 *
 * @author james
 */
public class NewServerAdapter extends BaseAdapter {
    private Context context;
    private List<NewServerBean.MsgEntity> list;
    private ViewHolder viewHolder;
    private BitmapUtils utils;

    public NewServerAdapter(List<NewServerBean.MsgEntity> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_newserver, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        viewHolder= (ViewHolder) convertView.getTag();
        NewServerBean.MsgEntity entity=list.get(position);
        viewHolder.titleNewserver.setText(entity.getTitle());
        viewHolder.dataNewserver.setText("开服时间:"+entity.getTitle());
        viewHolder.descNewserver.setText(entity.getSummary());
        utils.display(viewHolder.imgNewserver,entity.getIcon());
        return convertView;
    }

    static class ViewHolder {

        public ViewHolder(View view) {
            imgNewserver = (ImageView) view.findViewById(R.id.img_newserver);
            titleNewserver = (TextView) view.findViewById(R.id.title_newserver);
            descNewserver = (TextView) view.findViewById(R.id.desc_newserver);
            dataNewserver = (TextView) view.findViewById(R.id.data_newserver);
        }

        private ImageView imgNewserver;
        private TextView titleNewserver;
        private TextView descNewserver;
        private TextView dataNewserver;


    }
}
