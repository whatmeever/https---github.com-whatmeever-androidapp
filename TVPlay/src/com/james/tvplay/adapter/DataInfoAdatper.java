package com.james.tvplay.adapter;

import java.util.List;

import com.james.tvplay.R;
import com.james.tvplay.bean.DataInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DataInfoAdatper extends BaseAdapter {

	private List<DataInfo> list = null;
	private Context context = null;

	public DataInfoAdatper(Context context, List<DataInfo> list) {
		this.context = context;
		this.list = list;
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
		
		View view = null;
		ViewHolder viewHolder;
		
		if(convertView == null){
			view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
			
			viewHolder = new ViewHolder();
			
			viewHolder.img = (ImageView) view.findViewById(R.id.iv_bg);
			viewHolder.txtName = (TextView) view.findViewById(R.id.tv_name);
			viewHolder.txtDesc = (TextView) view.findViewById(R.id.tv_title);
			
			view.setTag(viewHolder);
		}else{
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		
		DataInfo dataInfo = list.get(position);
		
		viewHolder.txtName.setText(dataInfo.getName());
		viewHolder.txtDesc.setText(dataInfo.getDesc());
		
		return view;
	}

	class ViewHolder{
		ImageView img;
		TextView txtName, txtDesc;
	}
}
