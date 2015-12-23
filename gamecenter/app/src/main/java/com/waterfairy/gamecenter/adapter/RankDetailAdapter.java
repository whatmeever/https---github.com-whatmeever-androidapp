package com.waterfairy.gamecenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.waterfairy.gamecenter.BaseGame.DetaliBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.utils.Tools;

import java.util.List;

/**
 * Created by Administrator on 2015/10/21.
 */
public class RankDetailAdapter extends BaseAdapter {
    private List<DetaliBean.CommentEntity> list;
    private Context context;

    public RankDetailAdapter(List<DetaliBean.CommentEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
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
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_rank_detail_second_list,null);
            viewHolder.tv_version= (TextView) convertView.findViewById(R.id.tv_version);
            viewHolder.tv_user= (TextView) convertView.findViewById(R.id.tv_user);
            viewHolder.tv_date= (TextView) convertView.findViewById(R.id.tv_date);
            viewHolder.tv_model= (TextView) convertView.findViewById(R.id.tv_model);
            viewHolder.tv_comment= (TextView) convertView.findViewById(R.id.tv_comment);
            viewHolder.iv1= (ImageView) convertView.findViewById(R.id.iv1);
            viewHolder.iv2= (ImageView) convertView.findViewById(R.id.iv2);
            viewHolder.iv3= (ImageView) convertView.findViewById(R.id.iv3);
            viewHolder.iv4= (ImageView) convertView.findViewById(R.id.iv4);
            viewHolder.iv5= (ImageView) convertView.findViewById(R.id.iv5);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_version.setText(list.get(position).getVersion());
        viewHolder.tv_date.setText(list.get(position).getDate());
        viewHolder.tv_comment.setText(list.get(position).getComment());
        viewHolder.tv_model.setText(list.get(position).getModel());
        viewHolder.tv_user.setText(list.get(position).getUser());
        double score = list.get(position).getScore();
        Tools.GameDate(score,viewHolder.iv1,viewHolder.iv2,viewHolder.iv3,viewHolder.iv4,viewHolder.iv5);
        return convertView;
    }
  static class ViewHolder{
      private ImageView iv1,iv2,iv3,iv4,iv5;
      private TextView tv_version,tv_user,tv_date,tv_model,tv_comment;

  }
}
