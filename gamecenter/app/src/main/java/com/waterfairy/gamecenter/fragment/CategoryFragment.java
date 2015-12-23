package com.waterfairy.gamecenter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.waterfairy.gamecenter.BaseGame.GridBean;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.adapter.CateGridAdapter;
import com.waterfairy.gamecenter.adapter.GridAdapter;
import com.waterfairy.gamecenter.category.CateItemActivity;
import com.waterfairy.gamecenter.category.CateTopicActivity;
import com.waterfairy.gamecenter.utils.NetUtils;
import com.waterfairy.gamecenter.view.MyGridview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shui on 2015/10/16.
 */
public class CategoryFragment extends Fragment implements AdapterView.OnItemClickListener {
    private MyGridview gridView,gridView2;
    private List<GridBean.MsgEntity> list;
    private List<GridBean.SpecialEntity> list2;
   private int  pid;
    private GridBean gridBean;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.fragment_category, container, false);
        gridView= (MyGridview) view.findViewById(R.id.cate_grid);
        gridView2= (MyGridview) view.findViewById(R.id.cate_grid1);
        init();
        gridView.setOnItemClickListener(this);
        gridView2.setOnItemClickListener(this);

        return view;
    }

    public void init(){
        list=new ArrayList<>();
        list2=new ArrayList<>();

        HttpUtils http = new HttpUtils();
      http.send(HttpRequest.HttpMethod.GET, NetUtils.HTTPCATEGORIES, new RequestCallBack<String>() {
          @Override
          public void onSuccess(ResponseInfo<String> responseInfo) {
              String result = responseInfo.result;
              gridBean = new Gson().fromJson(result, GridBean.class);
              list=gridBean.getMsg();
              gridView.setAdapter(new CateGridAdapter(list,getActivity()));
              list2=gridBean.getSpecial();
              gridView2.setAdapter(new GridAdapter(list2,getActivity()));
          }

          @Override
          public void onFailure(HttpException e, String s) {
                e.printStackTrace();
          }
      });
    }

    String name;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GridView parent1 = (GridView) view.getParent();
        switch (parent1.getId()){
            case R.id.cate_grid1:
//                Toast.makeText(getActivity(), "grid" + position, Toast.LENGTH_LONG).show();
                Intent intent1=new Intent(getActivity(), CateTopicActivity.class);
                pid= gridBean.getSpecial().get(position).getReferId();
                intent1.putExtra("pid",pid);
                startActivity(intent1);
                break;
            case R.id.cate_grid:
//                Toast.makeText(getActivity(),"grid2-----"+position,Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getActivity(), CateItemActivity.class);
                pid= gridBean.getMsg().get(position).getId();
                name=gridBean.getMsg().get(position).getName();
                intent.putExtra("id",pid);
                intent.putExtra("title",name);
                startActivity(intent);
                break;
        }

    }
}
