package com.waterfairy.gamecenter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.waterfairy.gamecenter.BaseGame.DbdetailsBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.utils.AutoInstall;
import com.waterfairy.gamecenter.utils.NoSystemApk;

import java.util.List;

/**
 * Created by shui on 2015/10/30.
 */
public class DownAdapter extends BaseAdapter {
    private static Context context;
    private List<DbdetailsBean> list;
    private ViewHolder viewHolder;
    private BitmapUtils utils;
    private List<String> listPkg;

    public DownAdapter(Context context, List<DbdetailsBean> list) {
        this.list = list;
        this.context = context;
        utils = new BitmapUtils(context);
        listPkg = NoSystemApk.getPkgs(context);
    }

    public void update() {
        listPkg = NoSystemApk.getPkgs(context);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list != null) {
            Log.i("test1", "getCount " + list.size());
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_down, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        viewHolder = (ViewHolder) convertView.getTag();
        DbdetailsBean bean = list.get(position);
        viewHolder.nameDown.setText(bean.getName());
        viewHolder.buttonDown.setText("安装");
        viewHolder.buttonDown.setHint(bean.getPkg());
        viewHolder.stateInstall.setText("等待安装");

        viewHolder.buttonDown.setBackgroundColor(context.getResources().getColor(R.color.gray));
        for (String pkg : listPkg) {
            if (pkg.equals(bean.getPkg())) {
                viewHolder.buttonDown.setText("打开");
                viewHolder.buttonDown.setBackgroundColor(context.getResources().getColor(R.color.open));
                viewHolder.stateInstall.setText("已经安装");
                break;
            }
        }

        utils.display(viewHolder.imgDown, bean.getImgUrl());
        return convertView;
    }

    static class ViewHolder implements View.OnClickListener {
        private ImageView imgDown;
        private TextView nameDown;
        private TextView stateInstall;
        private TextView buttonDown;

        private ViewHolder(View view) {
            imgDown = (ImageView) view.findViewById(R.id.img_down);
            nameDown = (TextView) view.findViewById(R.id.name_down);
            stateInstall = (TextView) view.findViewById(R.id.state_install);
            buttonDown = (TextView) view.findViewById(R.id.button_down);
            buttonDown.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String path = Environment.getExternalStorageDirectory() + "/GameCenter/" + ((TextView) v).getHint() + ".apk";
            AutoInstall.setUrl(path);
            AutoInstall.install(context);

        }
    }
}
