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
public class CateGridAdapter extends BaseAdapter {
    private List<GridBean.MsgEntity> list;
private boolean flag;
    private Context context;


    public CateGridAdapter(List<GridBean.MsgEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public CateGridAdapter(List<GridBean.MsgEntity> list, Context context ,boolean flag) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.grid_item,null);
            viewHolder=new ViewHolder();
            viewHolder.img= (ImageView) convertView.findViewById(R.id.grid_item_img);
            viewHolder.text1= (TextView) convertView.findViewById(R.id.grid_item_tv1);
            viewHolder.text2= (TextView) convertView.findViewById(R.id.grid_item_tv2);
            convertView.setTag(viewHolder);
        }else {
             viewHolder = (ViewHolder) convertView.getTag();
        }

        if (flag){
            viewHolder.text1.setText(list.get(position).getName());
            viewHolder.text1.setTextSize(12);
            viewHolder.text1.setSingleLine();
            viewHolder.text2.setText("下载");
            viewHolder.text2.setBackgroundColor(Color.parseColor("#ff8500"));
        }else {
            viewHolder.text1.setText(list.get(position).getName());

            viewHolder.text1.setTextColor(Color.parseColor(list.get(position).getColor()));
            viewHolder.text2.setText(list.get(position).getTypeListDesc());
            viewHolder.text2.setTextColor(Color.parseColor(list.get(position).getColor()));
        }
         BitmapUtils bitmap=new BitmapUtils(context);
        bitmap.display(viewHolder.img,list.get(position).getIcon());
        return convertView;
    }
    public class ViewHolder{
        ImageView img;
        TextView text1,text2;
    }

}
