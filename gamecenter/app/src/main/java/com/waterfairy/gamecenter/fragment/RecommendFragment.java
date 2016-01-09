package com.waterfairy.gamecenter.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.waterfairy.gamecenter.BaseGame.RecommendBean;
import com.waterfairy.gamecenter.BaseGame.TopicGameBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.activitys.ActivityDetail;
import com.waterfairy.gamecenter.activitys.ActivityListActivity;
import com.waterfairy.gamecenter.activitys.Detail_RunkActivity;
import com.waterfairy.gamecenter.activitys.TopicGameActivity;
import com.waterfairy.gamecenter.activitys.TopicListActivity;
import com.waterfairy.gamecenter.adapter.RecommendAdapter;
import com.waterfairy.gamecenter.utils.DownloadUtils;
import com.waterfairy.gamecenter.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shui on 2015/10/16.
 */
public class RecommendFragment extends Fragment implements ViewPager.OnPageChangeListener, AbsListView.OnScrollListener, View.OnTouchListener, AdapterView.OnItemClickListener, View.OnClickListener {
    private static final String TAG = "test";
    private View listviewHeader;
    private View listviewFoot;
    private View viewMain;
    private int page = 1;
    private static int curPos = 0;
    private float fix;
    private int lastPage;
    private Gson gson;
    private HttpUtils utils;
    private BitmapUtils bitmapUtils;
    private ListView listView;
    private ViewPager viewPager;
    private ImageView img_cate1;
    private ImageView img_cate2;
    private ImageView img_cate3;
    private ImageView img_cate4;
    private TextView more;
    private LinearLayout lin_new;
    private List<View> viewList;
    private RecommendAdapter adapter;
    private RecommendBean recommend;
    private static List<RecommendBean.AdinfoEntity> adinfoEntities;
    private List<RecommendBean.ChartEntity> chartEntities;
    private List<RecommendBean.NewgameEntity> newgameEntities;
    private List<RecommendBean.RecommendEntity> recommendEntities;
    private List<RecommendBean.RecommendEntity> list;
    private Context context;
    private TextView cycle1, cycle2, cycle3, cycle4, lastCycle;
    private static FragmentActivity activity;

    //上拉刷新
    private TextView tog;
    private ImageView img_tog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = getActivity();
        listviewHeader = LayoutInflater.from(activity).inflate(R.layout.fragment_recommend, null);
        listviewFoot = LayoutInflater.from(activity).inflate(R.layout.listview_foot, null);
        viewMain = inflater.inflate(R.layout.fragment_list, container, false);
        listviewHeader.setVisibility(View.INVISIBLE);
        DisplayMetrics display = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(display);
        fix = display.density;
        initView();
        gson = new Gson();
        utils = new HttpUtils();
        bitmapUtils = new BitmapUtils(activity);
        getJson(NetUtils.HTTPRECOMMEND, 1, null);
        return viewMain;
    }

    private void initView() {
        listView = (ListView) viewMain.findViewById(R.id.listview_recommend);
        listView.setDivider(null);
        listView.setDividerHeight((int) (6 * fix));
        listView.addHeaderView(listviewHeader);
        listView.setOnScrollListener(this);
        listView.setOnTouchListener(this);
        listView.setOnItemClickListener(this);
        list = new ArrayList<>();
        adapter = new RecommendAdapter(activity, list, fix);
        listView.setAdapter(adapter);
        viewPager = (ViewPager) listviewHeader.findViewById(R.id.vp_recommend);
        viewPager.setOnPageChangeListener(this);
        img_cate1 = (ImageView) listviewHeader.findViewById(R.id.img_cate1);
        img_cate2 = (ImageView) listviewHeader.findViewById(R.id.img_cate2);
        img_cate3 = (ImageView) listviewHeader.findViewById(R.id.img_cate3);
        img_cate4 = (ImageView) listviewHeader.findViewById(R.id.img_cate4);
        img_cate1.setOnClickListener(this);
        img_cate2.setOnClickListener(this);
        img_cate3.setOnClickListener(this);
        img_cate4.setOnClickListener(this);
        more = (TextView) listviewHeader.findViewById(R.id.more_recommend);
        more.setOnClickListener(this);
        lin_new = (LinearLayout) listviewHeader.findViewById(R.id.lin_new);
        //上拉加载
        tog = (TextView) listviewFoot.findViewById(R.id.tog);
        img_tog = (ImageView) listviewFoot.findViewById(R.id.img_tog);
        cycle1 = (TextView) viewMain.findViewById(R.id.cycle1_move);
        cycle2 = (TextView) viewMain.findViewById(R.id.cycle2_move);
        cycle3 = (TextView) viewMain.findViewById(R.id.cycle3_move);
        cycle4 = (TextView) viewMain.findViewById(R.id.cycle4_move);
        lastCycle = cycle1;
    }

    /**
     * @param path       请求地址
     * @param index      请求页码  没有该项输入 0
     * @param parameters 其他请求参数 格式如:"&name=jom&age=12"
     * @return
     */
    public void getJson(String path, final int index, String parameters) {
        utils = new HttpUtils();
        String parmeter = path + "elapsedtime=" + NetUtils.getCurrentTime() + "&appVersion=28";
        if (index != 0) {
            parmeter += "&page_index=" + index;
        }
        if (parameters != null) {
            parmeter += parameters;
        }
//        Log.i(TAG, "onSuccess " + parmeter);
        utils.send(HttpRequest.HttpMethod.GET, parmeter, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                recommend = gson.fromJson(responseInfo.result, RecommendBean.class);
                if (index == 1) {
                    handler.sendEmptyMessage(0);
                } else {
                    handler.sendEmptyMessage(2);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
//                Log.i("test", "utils getJson faile" + s);
            }
        });

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (recommend != null) {
//                        Log.i(TAG, "handleMessage 数据请求成功");
                        adinfoEntities = recommend.getAdinfo();
                        chartEntities = recommend.getChart();
                        newgameEntities = recommend.getNewgame();
                        recommendEntities = recommend.getRecommend();
                        setMoveViews();
                        listviewHeader.setVisibility(View.VISIBLE);
                        setChartViews();
                        setNewGameViews();
                        list.addAll(recommendEntities);
                        adapter.notifyDataSetChanged();
//                        setRecommendViews();
                        handler.sendEmptyMessageDelayed(1, 5000);
                    }
                    break;
                case 1:
                    viewPager.setCurrentItem(++curPos);
                    handler.sendEmptyMessageDelayed(1, 5000);
                    break;
                case 3:
                    listView.removeFooterView(listviewFoot);
                    break;
                case 2://上拉加载
                    list.addAll(recommend.getRecommend());
                    listView.removeFooterView(listviewFoot);
                    adapter.notifyDataSetChanged();
                    img_tog.setImageBitmap(null);
                    img_tog.clearAnimation();
                    isReady = false;
                    break;
            }


        }
    };

    private void setNewGameViews() {
        for (RecommendBean.NewgameEntity entity : newgameEntities) {
            //lin布局  height 10
            LinearLayout linItem = null;
            if (activity != null) {
                linItem = new LinearLayout(activity);
                //设置点击事件  跳转详情
                linItem.setOnClickListener(this);
                linItem.setTag(entity.getId());
                if (linItem != null) {
                    linItem.setRight((int) (20 * fix));
                    linItem.setLeft((int) (20 * fix));

                    linItem.setPadding(5, 5, 5, 5);
                    linItem.setOrientation(LinearLayout.VERTICAL);
                    //图片  height  55
                    ImageView imageItem = new ImageView(activity);
                    if (imageItem != null) {
                        imageItem.setScaleType(ImageView.ScaleType.FIT_XY);
                        imageItem.setLayoutParams(new ViewGroup.LayoutParams((int) (55 * fix), (int) (55 * fix)));
                        bitmapUtils.display(imageItem, entity.getIcon());
                    }
                    //文本
                    TextView textItem = new TextView(activity);
                    if (textItem != null) {
                        textItem.setEllipsize(TextUtils.TruncateAt.END);
                        textItem.setMaxWidth((int) (50 * fix));
                        textItem.setPadding(0, 3, 0, 0);
                        textItem.setSingleLine(true);
//                        textItem.setTextSize(7 * fix);
                        textItem.setText(entity.getName());
                    }

                    linItem.addView(imageItem);
                    linItem.addView(textItem);
                }
                lin_new.addView(linItem);
            } else {
                return;
            }


        }

    }

    private void setChartViews() {
        bitmapUtils.display(img_cate1, chartEntities.get(0).getPicUrl());
        bitmapUtils.display(img_cate2, chartEntities.get(1).getPicUrl());
        bitmapUtils.display(img_cate3, chartEntities.get(2).getPicUrl());
        bitmapUtils.display(img_cate4, chartEntities.get(3).getPicUrl());
    }

    private void setMoveViews() {
        viewList = getMoveViews();
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                int pos = position % viewList.size();
                View view = viewList.get(pos);
                if (adinfoEntities != null) {
                    RecommendBean.AdinfoEntity entity = adinfoEntities.get(pos);
                    ViewHolder viewHolder = new ViewHolder(view);
                    BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
                    bitmapUtils.display(viewHolder.imageMove, entity.getPicUrl());
                    bitmapUtils.display(viewHolder.iconMove, entity.getIcon());
                    viewHolder.downloadMove.setHint(pos+"");
                    if (entity.getRelativeType().equals("1")) {
                        viewHolder.titleMove.setText(entity.getTitle());
                        viewHolder.titleCenter.setVisibility(View.GONE);
                        viewHolder.lin.setVisibility(View.VISIBLE);
                        int num = (int) entity.getRelative().getComment();//3
                        for (int i = 1; i <= 5; i++) {
                            if (num >= i) {
                                viewHolder.imgList.get(i - 1).setImageResource(R.mipmap.game_detail_comment_star_full);
                            } else {
                                viewHolder.imgList.get(i - 1).setImageResource(R.mipmap.game_detail_comment_star_empty);
                            }
                        }
                        viewHolder.imgStart1.setImageResource(R.mipmap.game_detail_comment_star_full);
                        viewHolder.startMove.setText("/" + entity.getRelative().getSize() / 1000 + "M");
                        viewHolder.downloadMove.setText("下载");
                    } else {
                        viewHolder.titleCenter.setVisibility(View.VISIBLE);
                        viewHolder.titleCenter.setText(entity.getTitle());
                        viewHolder.lin.setVisibility(View.GONE);
                        viewHolder.downloadMove.setText("查看");
                    }
                }
                ViewGroup viewGrouptemp;
                if ((viewGrouptemp = (ViewGroup) view.getParent()) != null) {
                    viewGrouptemp.removeView(view);
                }
                container.addView(view);
                return view;
            }
        });
    }


    private List<View> getMoveViews() {
        List<View> viewList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            try {
                View view = View.inflate(getActivity(), R.layout.item_move, null);
                viewList.add(view);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return viewList;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        curPos = position;
        int posTemp = position % 4;

       switch (posTemp){
           case  0:
               lastCycle.setBackgroundResource(R.drawable.bg_cycle_little);
               cycle1.setBackgroundResource(R.drawable.bg_cycle_big);
               lastCycle=cycle1;
               break;
           case  1:
               lastCycle.setBackgroundResource(R.drawable.bg_cycle_little);
               cycle2.setBackgroundResource(R.drawable.bg_cycle_big);
               lastCycle=cycle2;
               break;
           case  2:
               lastCycle.setBackgroundResource(R.drawable.bg_cycle_little);
               cycle3.setBackgroundResource(R.drawable.bg_cycle_big);
               lastCycle=cycle3;
               break;
           case  3:
               lastCycle.setBackgroundResource(R.drawable.bg_cycle_little);
               cycle4.setBackgroundResource(R.drawable.bg_cycle_big);
               lastCycle=cycle4;
               break;

       }

    }

    /**
     * listview
     */
    private View lastView;
    boolean canFreash;
    private boolean isReady;
    //listview
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if (canFreash) {
            View tempView = view.getChildAt(view.getChildCount() - 1);
            if (tempView.getBottom() == view.getHeight() && scrollState == SCROLL_STATE_IDLE) {
                //加载数据,,
                listView.setSelection(adapter.getCount() - 1);

                if (recommend.isHasNext()) {
                    getJson(NetUtils.HTTPRECOMMEND, ++page, null);
                    tog.setText("加载中...");
                    img_tog.setImageResource(R.mipmap.loading);
                    Animation rotateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotating);
                    img_tog.setAnimation(rotateAnimation);
                } else {
                    tog.setText("已经到最后啦...");
                }
                canFreash = false;

            }
            if (tempView.getBottom() > view.getHeight() && scrollState == SCROLL_STATE_IDLE) {
                listView.removeFooterView(listviewFoot);
                canFreash = false;
                isReady = false;
            }
        }
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        Log.i(TAG, "onScroll " + totalItemCount);
        //说明: addHeader   addFooter   会增加listview的数量
        if (recommend != null) {
            if (firstVisibleItem + visibleItemCount == totalItemCount) {
                lastView = view.getChildAt(visibleItemCount - 1);
                //1.确保不在加载状态,
                if (!isReady && ((lastView != null && lastView.getBottom() <= view.getHeight()))) {
                    //添加footer
                    listView.addFooterView(listviewFoot);
                    isReady = true;
//                    isHasFoot = true;
                    //继续上拉刷新
                    tog.setText("继续上拉刷新");
                    canFreash = true;
                }
            }

        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RecommendBean.RecommendEntity entity = list.get(position - 1);
        String type = entity.getRelativeType();
//        Log.i(TAG, "onItemClick  type--- " + type);
        if (TextUtils.isEmpty(type)) {//一般 界面 跳详情
            Intent intent = new Intent(getActivity(), Detail_RunkActivity.class);
            intent.putExtra("id", entity.getId());
            startActivity(intent);
        } else if ("1".equals(type)) {
            Intent intent = new Intent(getActivity(), Detail_RunkActivity.class);
            intent.putExtra("id", entity.getRelative().getId());
            startActivity(intent);
        } else if ("2".equals(type)) {
            Intent intent2 = new Intent(getActivity(), TopicGameActivity.class);
            intent2.putExtra("id", entity.getRelative().getId());
            intent2.putExtra("title", entity.getTitle());
            startActivity(intent2);
        } else if ("3".equals(type)) {//官方活动  跳活动
            Intent intent2 = new Intent(getActivity(), TopicListActivity.class);
            intent2.putExtra("name", "huodong");
            startActivity(intent2);
        }


    }

    static class ViewHolder implements View.OnClickListener {
        private ImageView imageMove;
        private TextView titleMove;
        private TextView startMove;
        private TextView titleCenter;
        private ImageView iconMove;
        private TextView downloadMove;
        private LinearLayout lin;
        private ImageView imgStart1;
        private ImageView imgStart2;
        private ImageView imgStart3;
        private ImageView imgStart4;
        private ImageView imgStart5;
        private List<ImageView> imgList;

        public ViewHolder(View view) {
            imageMove = (ImageView) view.findViewById(R.id.image_move);
            imageMove.setOnClickListener(this);
            titleMove = (TextView) view.findViewById(R.id.title_move);
            titleCenter = (TextView) view.findViewById(R.id.title_move_center);
            downloadMove = (TextView) view.findViewById(R.id.download_move);
            downloadMove.setOnClickListener(this);
            startMove = (TextView) view.findViewById(R.id.start_move);
            iconMove = (ImageView) view.findViewById(R.id.icon_move);
            lin = (LinearLayout) view.findViewById(R.id.lin_start);
            imgStart1 = (ImageView) view.findViewById(R.id.start_move_1);
            imgStart2 = (ImageView) view.findViewById(R.id.start_move_2);
            imgStart3 = (ImageView) view.findViewById(R.id.start_move_3);
            imgStart4 = (ImageView) view.findViewById(R.id.start_move_4);
            imgStart5 = (ImageView) view.findViewById(R.id.start_move_5);
            imgList = new ArrayList<>();
            imgList.add(imgStart1);
            imgList.add(imgStart2);
            imgList.add(imgStart3);
            imgList.add(imgStart4);
            imgList.add(imgStart5);

        }

        @Override
        public void onClick(final View v) {
            RecommendBean.AdinfoEntity entity = RecommendFragment.adinfoEntities.get(RecommendFragment.curPos % 4);
            if (v.getId() == R.id.download_move) {
                if (((TextView) v).getText().equals("查看")) {
                    if (entity.getRelativeType().equals("3")) {
                        Intent intent=new Intent(RecommendFragment.activity,ActivityDetail.class);
                        intent.putExtra("id",entity.getRelative().getId());
                        RecommendFragment.activity.startActivity(intent);
                    } else {
                        Intent intent2 = new Intent(RecommendFragment.activity, TopicGameActivity.class);
                        int id2 = entity.getRelative().getId();
                        intent2.putExtra("id", id2);
                        String title = entity.getTitle();
                        intent2.putExtra("title", title);
                        RecommendFragment.activity.startActivity(intent2);
                    }

                } else {

                    int position = Integer.parseInt((String) ((TextView) v).getHint());
                    RecommendBean.AdinfoEntity.RelativeEntity relative = adinfoEntities.get(position).getRelative();
                    int state = DownloadUtils.downLoad(RecommendFragment.activity, relative.getPkgName(), relative.getPkgName(), relative.getName(), relative.getIcon(), new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            ((TextView) v).setText("安装");
                        }
                    });
                    if (state == DownloadUtils.SUCCESS) {
                        ((TextView) v).setText("安装");
                    } else {
                        ((TextView) v).setText("下载中");
                    }
                    Log.i(TAG, "onClick " + entity.getRelative().getApkurl());
                }
            }
            //move 下载
            if (v.getId() == R.id.image_move) {//image->详情

                switch (entity.getRelativeType()) {
                    case "1"://1.下载
                        int id = entity.getRelative().getId();
                        Intent intent1 = new Intent(RecommendFragment.activity, Detail_RunkActivity.class);
                        intent1.putExtra("id", id);
                        Log.i(TAG, "onClick " + id);
                        RecommendFragment.activity.startActivity(intent1);
                        break;
                    case "2": // 2.主题?
                        Intent intent2 = new Intent(RecommendFragment.activity, TopicGameActivity.class);
                        int id2 = entity.getRelative().getId();
                        intent2.putExtra("id", id2);
                        String title = entity.getTitle();
                        intent2.putExtra("title", title);
                        RecommendFragment.activity.startActivity(intent2);
                        break;

                    case "3": // 3.活动详情
                        Intent intent3 = new Intent(RecommendFragment.activity, ActivityDetail.class);
                        int id3 = entity.getRelative().getId();
                        intent3.putExtra("id", id3);
                        RecommendFragment.activity.startActivity(intent3);
                        // TODO: 2015/10/21
                        break;
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notification();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.more_recommend:
                Intent intent = new Intent(getActivity(), TopicGameActivity.class);
                intent.putExtra("title", "新品速递");
                startActivity(intent);
                break;
            case R.id.img_cate1://首发
                Intent intent1 = new Intent(getActivity(), TopicGameActivity.class);
                intent1.putExtra("id", chartEntities.get(0).getRelative().getId());
                intent1.putExtra("title", "每日首发");
                startActivity(intent1);
                break;
            case R.id.img_cate2://专题
                Intent intent2 = new Intent(getActivity(), TopicListActivity.class);
                intent2.putExtra("name", "zhuanti");
                startActivity(intent2);
                break;
            case R.id.img_cate3://礼包
                Intent intent3 = new Intent(getActivity(), ActivityListActivity.class);
                intent3.putExtra("id", chartEntities.get(2).getRelative().getId());
                startActivity(intent3);
                break;
            case R.id.img_cate4://免费
                Intent intent4 = new Intent(getActivity(), TopicGameActivity.class);
                intent4.putExtra("id", chartEntities.get(3).getRelative().getId());
                intent4.putExtra("title", "免费精选推荐");
                startActivity(intent4);
                break;
            default://新品速递
                Intent intentNew = new Intent(getActivity(), Detail_RunkActivity.class);
                intentNew.putExtra("id", (int) (view.getTag()));
                startActivity(intentNew);
        }
    }

}
