package com.waterfairy.gamecenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.waterfairy.gamecenter.BaseGame.GridBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.activitys.GiftDetailsActivity;

import java.util.List;

/**
 * Created by zhaohang on 2015/10/24.
 */
public class GiftGridAdapter extends BaseAdapter {
    private List<GridBean.MsgEntity> list;
    private Context context;
    private boolean flag;

    public GiftGridAdapter(List<GridBean.MsgEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public GiftGridAdapter(List<GridBean.MsgEntity> list, boolean flag, Context context) {
        this.list = list;
        this.flag = flag;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (list!=null){
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.gift_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.icon= (ImageView) convertView.findViewById(R.id.gift_item_img);
            viewHolder.text= (TextView) convertView.findViewById(R.id.gift_item_num);
            viewHolder.title= (TextView) convertView.findViewById(R.id.gift_item_title);
            viewHolder.bt= (Button) convertView.findViewById(R.id.gift_item_bt);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(list.get(position).getTitle());
        viewHolder.text.setText(list.get(position).getGift_size() + "");
        BitmapUtils bitmap=new BitmapUtils(context);
        bitmap.display(viewHolder.icon, list.get(position).getIcon());
        if (flag){
            viewHolder.bt.setVisibility(View.VISIBLE);

        }
        viewHolder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, GiftDetailsActivity.class);
                int id = list.get(position).getId();
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    private class ViewHolder{
        private ImageView icon;
        private TextView title,text;
        private Button bt;
    }
}
