package com.waterfairy.gamecenter.fragment;

import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.waterfairy.gamecenter.BaseGame.ClassicBean;
import com.waterfairy.gamecenter.BaseGame.DetaliBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.adapter.RankDetailAdapter;
import com.waterfairy.gamecenter.utils.NetUtils;
import com.waterfairy.gamecenter.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author james
 */
public class Rank_Detail_SecondFragment extends Fragment implements AbsListView.OnScrollListener {
    private ListView listView;
    private int page_index = 1;
    private int id;
    private TextView tv_size, tv_comment;
    private DetaliBean.Comment_SC comment_sc;
    private int number;
    private ImageView iv1, iv2, iv3, iv4, iv5;
    private TextView tv_score1, tv_score2, tv_score3, tv_score4, tv_score5;
    private float density;
    private RankDetailAdapter rankDetailAdapter;
    private boolean isFreash;
    private boolean isLoading;
    private String Url;
    private HttpUtils httpUtils;
    List<DetaliBean.CommentEntity> listEntities = new ArrayList<DetaliBean.CommentEntity>();
    ;
    private Boolean hasNext;
    private View view1;
    private ImageView footImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_rank_detail_second, container, false);
        comment_sc = Rank_Base_DetailFragment.comment_sc;
        number = Rank_Base_DetailFragment.number;
        iv1 = (ImageView) view.findViewById(R.id.iv1);
        iv2 = (ImageView) view.findViewById(R.id.iv2);
        iv3 = (ImageView) view.findViewById(R.id.iv3);
        iv4 = (ImageView) view.findViewById(R.id.iv4);
        iv5 = (ImageView) view.findViewById(R.id.iv5);
        listView = (ListView) view.findViewById(R.id.lv);
        tv_score1 = (TextView) view.findViewById(R.id.tv_score1);
        tv_score2 = (TextView) view.findViewById(R.id.tv_score2);
        tv_score3 = (TextView) view.findViewById(R.id.tv_score3);
        tv_score4 = (TextView) view.findViewById(R.id.tv_score4);
        tv_score5 = (TextView) view.findViewById(R.id.tv_score5);
        view1 = LinearLayout.inflate(getActivity(), R.layout.rank_animation, null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        density = displayMetrics.density;
        //设置进度条的长度；
        getProcess(tv_score1, comment_sc.getScore_1() / (double) number);
        getProcess(tv_score2, comment_sc.getScore_2() / (double) number);
        getProcess(tv_score3, comment_sc.getScore_3() / (double) number);
        getProcess(tv_score4, comment_sc.getScore_4() / (double) number);
        getProcess(tv_score5, comment_sc.getScore_5() / (double) number);
        tv_comment = (TextView) view.findViewById(R.id.tv_comment);
        tv_size = (TextView) view.findViewById(R.id.tv_size);
        tv_size.setText(number + "份");
        //总的分数
        double comment = (comment_sc.getScore_1() + comment_sc.getScore_2() * 2 + comment_sc.getScore_3() * 3 + comment_sc.getScore_4() * 4 + comment_sc.getScore_5() * 5) / (double) number;
        String format = String.format("%.2f", comment);
        Tools.GameDate(comment, iv1, iv2, iv3, iv4, iv5);
        tv_comment.setText(format + "");
        listView.setOnScrollListener(this);
        footImage = (ImageView) view1.findViewById(R.id.iv);
        footImage.setImageResource(R.mipmap.game_progressbar_indeterminate_circle);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotating);
        footImage.setAnimation(animation);
        listView.addFooterView(view1);
        httpUtils = new HttpUtils();
        id = Rank_Base_DetailFragment.id;
        Url = NetUtils.HTTPCOMMENT + "id=" + id + "&page_index=" + page_index;
        httpUtils.send(HttpRequest.HttpMethod.GET, Url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                JSONObject jsonObject = JSON.parseObject(responseInfo.result);
                JSONArray relate = jsonObject.getJSONArray("list");
                for (int i = 0; i < relate.size(); i++) {
                    listEntities.add(relate.getObject(i, DetaliBean.CommentEntity.class));
                }
                rankDetailAdapter = new RankDetailAdapter(listEntities, getActivity());
                hasNext = jsonObject.getBoolean("hasNext");
                listView.setAdapter(rankDetailAdapter);
            }
            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
            }
        });

        return view;
    }
    //设置进度条的进度长度;
    public void getProcess(TextView tv, double precent) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (precent * 150 * density), (int) (8 * density));
        if (tv.equals(tv_score5)) {
            layoutParams.topMargin = (int) (9 * density);
        } else {
            layoutParams.topMargin = (int) (10 * density);
        }
        tv.setLayoutParams(layoutParams);

    }

    @Override
    public void onScrollStateChanged(final AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE && isFreash) {
            isFreash = false;
            isLoading = true;
            if (hasNext) {
                footImage = (ImageView) view1.findViewById(R.id.iv);
                footImage.setImageResource(R.mipmap.game_progressbar_indeterminate_circle);
                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotating);
                footImage.setAnimation(animation);
                listView.addFooterView(view1);
                page_index++;
                Url = NetUtils.HTTPCOMMENT + "id=" + id + "&page_index=" + page_index;
                httpUtils = new HttpUtils();
                httpUtils.send(HttpRequest.HttpMethod.GET, Url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        JSONObject jsonObject = JSON.parseObject(responseInfo.result);
                        JSONArray relate = jsonObject.getJSONArray("list");
                        for (int i = 0; i < relate.size(); i++) {
                            listEntities.add(relate.getObject(i, DetaliBean.CommentEntity.class));
                        }
                        hasNext = jsonObject.getBoolean("hasNext");
                        rankDetailAdapter.notifyDataSetChanged();//提示内容信息更新；
                        isLoading = false;
                        listView.removeFooterView(view1);//变量为final；当该变量需要一直变时，用一个final的长量来接收-->final对象不能new但可以赋值：
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        //打印错误信息；
                        e.printStackTrace();
                    }
                });
            } else if (isFreash) {
                isFreash = false;
                isLoading = true;
                footImage.setVisibility(View.GONE);
                TextView textView = (TextView) view1.findViewById(R.id.tv);
                textView.setText("已加载完...");
                listView.addFooterView(view1);
            }
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
}
