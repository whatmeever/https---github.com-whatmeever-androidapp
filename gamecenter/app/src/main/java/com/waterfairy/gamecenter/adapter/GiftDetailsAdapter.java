package com.waterfairy.gamecenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.waterfairy.gamecenter.BaseGame.GiftBean;
import com.waterfairy.gamecenter.R;

import java.util.List;

/**
 * Created by zhaohang on 2015/10/26.
 */
public class GiftDetailsAdapter extends BaseAdapter {
    List<GiftBean.GiftsEntity> list;
    private Context context;
    private boolean flag;

    public GiftDetailsAdapter(List<GiftBean.GiftsEntity> list, Context context, boolean flag) {
        this.list = list;
        this.context = context;
        this.flag = flag;
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
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder  viewHolder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.gift_details,null);
            viewHolder=new ViewHolder();
            viewHolder.title= (TextView) convertView.findViewById(R.id.gift_details_title);
            viewHolder.days= (TextView) convertView.findViewById(R.id.gift_details_days);
            viewHolder.des= (TextView) convertView.findViewById(R.id.gift_details_des);
            viewHolder.see= (TextView) convertView.findViewById(R.id.gift_details_see);
            viewHolder.use= (TextView) convertView.findViewById(R.id.gift_details_use);
            viewHolder.img= (ImageView) convertView.findViewById(R.id.gift_details_img);
            convertView.setTag(viewHolder);
            viewHolder.img.setTag(position);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(list.get(position).getTitle()+"");
        viewHolder.des.setText(list.get(position).getSummary()+"");
        if (list.get(position).getAvailable_gift()>0){
            viewHolder.days.setText(list.get(position).getAvailable_gift()+"");
            viewHolder.img.setImageResource(R.mipmap.game_get_gift_icon_normal);
        }else {
            viewHolder.days.setText(0+"");

            viewHolder.img.setImageResource(R.mipmap.game_get_gift_icon_ended);
        }

        viewHolder.use.setText(list.get(position).getDesc()+"");
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag){
                    finalViewHolder.use.setVisibility(View.VISIBLE);
            flag=true;
         }else {
                    finalViewHolder.use.setVisibility(View.GONE);
            flag=false;
         }
            }
        });
        return convertView;
    }
    public static class ViewHolder{
        private TextView title,des,days,see,use;
        private ImageView img;
    }
}
