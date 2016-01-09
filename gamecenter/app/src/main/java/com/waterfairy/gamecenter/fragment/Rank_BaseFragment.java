package com.waterfairy.gamecenter.fragment;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
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
import com.waterfairy.gamecenter.BaseGame.ClassicBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.activitys.Detail_RunkActivity;
import com.waterfairy.gamecenter.adapter.RankBaseAdapter;
import com.waterfairy.gamecenter.utils.NetUtils;

import java.util.List;
import java.util.logging.Handler;

/**
 * Created by Administrator on 2015/10/16.
 */
public abstract class Rank_BaseFragment extends Fragment implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private List<ClassicBean.MsgEntity> list;
    protected String Url;
    public String type;
    int page = 1;
    private boolean isFreash;
    private boolean isLoading;
    HttpUtils httpUtils;
    ClassicBean classicBean;
    RankBaseAdapter rankBaseAdapter;
    private ImageView footImage;
    private Animation animation;
    private View view1;
    private TextView tv1, tv2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rank_base, container, false);
        listView = (ListView) view.findViewById(R.id.lv);
        httpUtils = new HttpUtils();
        Url = getUrl() + "type=" + type + "&page_index=" + page;
        listView.setOnScrollListener(this);
        listView.setOnItemClickListener(this);
        //写在这。不然会卡屏;没次刷新页面都new()会卡屏;
        view1 = LinearLayout.inflate(getActivity(), R.layout.rank_animation, null);

        listView.addFooterView(view1);
        httpUtils.send(HttpRequest.HttpMethod.GET, Url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                classicBean = new Gson().fromJson(result, ClassicBean.class);
                list = classicBean.getMsg();
                rankBaseAdapter = new RankBaseAdapter(list, getActivity());
                listView.setAdapter(rankBaseAdapter);
                listView.removeFooterView(view1);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
            }
        });
        return view;
    }

    public abstract String getUrl();

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (classicBean.isHasNext() && isFreash) {
            //加载动画效果
            footImage = (ImageView) view1.findViewById(R.id.iv);
            footImage.setImageResource(R.mipmap.game_progressbar_indeterminate_circle);
            animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotating);
            footImage.setAnimation(animation);
            listView.addFooterView(view1);
            httpUtils = new HttpUtils();
            if (scrollState == SCROLL_STATE_IDLE && isFreash) {
                isFreash = false;
                isLoading = true;
                page++;
                Url = getUrl() + "type=" + type + "&page_index=" + page;
                httpUtils.send(HttpRequest.HttpMethod.GET, Url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result;
                        classicBean = new Gson().fromJson(result, ClassicBean.class);
                        list.addAll(classicBean.getMsg());
                        rankBaseAdapter.notifyDataSetChanged();//提示信息更新；
                        isLoading = false;
                        listView.removeFooterView(view1);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        //打印错误信息；
                        e.printStackTrace();
                    }

                });
            }
        } else if (isFreash) {
            isFreash = false;
            isLoading = true;
            footImage.setVisibility(View.GONE);
            TextView textView = (TextView) view1.findViewById(R.id.tv);
            textView.setText("已加载完...");
            listView.addFooterView(view1);
            rankBaseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            int height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
            View childAt = view.getChildAt(visibleItemCount - 1);
            if (childAt != null) {
                if (childAt.getHeight() <= height & !isLoading) {
                    isFreash = true;
                }
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String string = ((TextView) view.findViewById(R.id.tv_id)).getText().toString();
        int num = Integer.parseInt(string);
        Intent intent = new Intent(getActivity(), Detail_RunkActivity.class);
        intent.putExtra("id", num);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listView.removeFooterView(view1);
    }
}
