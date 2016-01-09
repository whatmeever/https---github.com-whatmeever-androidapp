package com.waterfairy.gamecenter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.waterfairy.gamecenter.BaseGame.GridBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.activitys.GiftDetailsActivity;
import com.waterfairy.gamecenter.adapter.GiftGridAdapter;
import com.waterfairy.gamecenter.refresh.MyListener;
import com.waterfairy.gamecenter.refresh.PullToRefreshLayout;
import com.waterfairy.gamecenter.refresh.PullableGridView;
import com.waterfairy.gamecenter.utils.NetUtils;

import java.util.List;

/**
 * Created by zhaohang on 2015/10/24.
 */
public class GiftFragment extends Fragment implements View.OnClickListener, TextWatcher, AdapterView.OnItemClickListener {
    private PullableGridView gridView;
    private EditText editText;
    private ImageView img_search;
    private ListView lv;
    private LinearLayout linearLayout;
    private TextView textoff;
    String url;
    int page=1;
    private List<GridBean.MsgEntity> list;
    private PullToRefreshLayout pullkv,pullgrid;
    HttpUtils httpUtils;
    GiftGridAdapter giftGridAdapter;
    private int lvpage=1;
    String string;//edittext的值
    GridBean gridBean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gift_fragment, null);
        gridView= (PullableGridView) view.findViewById(R.id.gift_grid);
        editText= (EditText) view.findViewById(R.id.gift_et);
        img_search= (ImageView) view.findViewById(R.id.gift_search);
        linearLayout= (LinearLayout) view.findViewById(R.id.gift_ll);
        textoff= (TextView) view.findViewById(R.id.gift_text_off);
        pullgrid= (PullToRefreshLayout) view.findViewById(R.id.gift_refresh);
        pullkv= (PullToRefreshLayout) view.findViewById(R.id.gift_lvrefresh);
        lv= (ListView) view.findViewById(R.id.gift_lv);
        init();
        refresh();
        return view;
    }
    public void init(){
        img_search.setOnClickListener(this);
        grid_send();
        editText.addTextChangedListener(this);
        lvload();
        gridView.setOnItemClickListener(this);
    }
    public void refresh(){
        pullgrid.setOnRefreshListener(new MyListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                super.onRefresh(pullToRefreshLayout);
                grid_send();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                super.onLoadMore(pullToRefreshLayout);
                grid_load();
            }
        });
        pullkv.setOnRefreshListener(new MyListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                super.onRefresh(pullToRefreshLayout);
                lv_send();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                super.onLoadMore(pullToRefreshLayout);
                lvload();
            }
        });
    }
    private void lvload() {
        lvpage++;
        url= NetUtils.ACTIVITYLIST + "page_index=" + lvpage + "&appVersion=28&search=" + string + "&type=3";
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                gridBean = new Gson().fromJson(result, GridBean.class);
                if (gridBean.getMsg() != null) {
                  list.addAll(gridBean.getMsg());
                    giftGridAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
            }
        });
    }

    public void grid_send(){
        page = 1;
        url = NetUtils.ACTIVITYLIST + "page_index=" + page + "&appVersion=28&type=3";
        httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                gridBean = new Gson().fromJson(result, GridBean.class);
                if (gridBean.getMsg() != null) {
                    list = gridBean.getMsg();
                    giftGridAdapter = new GiftGridAdapter(list, getActivity());
                    gridView.setAdapter(giftGridAdapter);
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
            }
        });
    }
    public  void grid_load(){
        page++;
        url= NetUtils.ACTIVITYLIST+"page_index="+page+"&appVersion=28&type=3";
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                gridBean = new Gson().fromJson(result, GridBean.class);
                if (gridBean.getMsg() != null) {
                    list.addAll(gridBean.getMsg());
                    giftGridAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
            }
        });
    }
    @Override
    public void onClick(View v) {

        string = editText.getText().toString();
        linearLayout.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(string)){
            lv_send();
        }
    }
    public void lv_send(){
        lvpage=1;
        url= NetUtils.ACTIVITYLIST + "page_index=" + lvpage + "&appVersion=28&search=" + string + "&type=3";
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                gridBean = new Gson().fromJson(result, GridBean.class);
                if (gridBean.getMsg() != null) {
                    list = gridBean.getMsg();
                   giftGridAdapter = new GiftGridAdapter(list, true, getActivity());
                    lv.setAdapter(giftGridAdapter);
                    pullkv.setVisibility(View.VISIBLE);
                } else {
                    textoff.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
            }
        });
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        textoff.setVisibility(View.GONE);
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (TextUtils.isEmpty(editText.getText().toString())){
            pullkv.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), GiftDetailsActivity.class);
                    int id1 = list.get(position).getId();
                    intent.putExtra("id",id1);
                    startActivity(intent);
    }
}
