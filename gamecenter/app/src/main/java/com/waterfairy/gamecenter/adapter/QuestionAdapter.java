package com.waterfairy.gamecenter.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.waterfairy.gamecenter.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2015/10/23.
 */
public class QuestionAdapter extends BaseAdapter {
    String[] strings;
    Context context;
    HashMap<Integer,Boolean>hashMap=new HashMap<>();
    EditText editText;
    Handler handler;
    public QuestionAdapter(String[] strings, Context context,EditText editText,Handler handler) {
        this.strings = strings;
        this.context = context;
        this.editText=editText;
        this.handler=handler;
    }

    public HashMap<Integer, Boolean> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<Integer, Boolean> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.rank_question, parent, false);
        TextView tv = (TextView) convertView.findViewById(R.id.tv);
        tv.setText(strings[position]);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
        checkBox.setFocusable(false);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hashMap.put(position, isChecked);//hashMap可以-->List.add(location,value);添加不会覆盖；
                if (position == 5) {
                    if (isChecked) {
                        editText.setVisibility(View.VISIBLE);
                    }else {
                        editText.setVisibility(View.GONE);
                    }
                }
                handler.sendEmptyMessage(1);
            }
        });
        checkBox.setTag(position);
        return convertView;
    }
}
    //buttonView --->即对应的CheckBox的点击;


