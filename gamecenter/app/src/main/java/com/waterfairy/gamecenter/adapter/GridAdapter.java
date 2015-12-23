package com.waterfairy.gamecenter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.waterfairy.gamecenter.BaseGame.GridBean;
import com.waterfairy.gamecenter.R;

import java.util.List;

/**
 * Created by Administrator on 2015/10/17.
 */
public class GridAdapter extends BaseAdapter {
    private List<GridBean.SpecialEntity> list;
    private Context context;

    public GridAdapter(List<GridBean.SpecialEntity> list, Context context) {
        this.list = list;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.grid_item2,null);
            viewHolder=new ViewHolder();
            viewHolder.img= (ImageView) convertView.findViewById(R.id.grid_item2_img);
            viewHolder.text= (TextView) convertView.findViewById(R.id.grid_item2_tv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.text.setText(list.get(position).getName());
        viewHolder.text.setTextColor(Color.parseColor(list.get(position).getColor()));

        BitmapUtils bitmap=new BitmapUtils(context);
        bitmap.display(viewHolder.img, list.get(position).getIcon());
        return convertView;
    }

    public class ViewHolder{
        ImageView img;
        TextView text;
    }
}
