package com.james.tvplay.fragment;

import java.util.ArrayList;
import java.util.List;

import com.james.tvplay.R;
import com.james.tvplay.async.JsonStringAsyncTask;
import com.james.tvplay.bean.DataInfo;
import com.james.tvplay.interf.OnGetData;
import com.james.tvplay.utils.HttpUtil;
import com.james.tvplay.utils.NewsConstants;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 * 
 * @author james
 *
 */
public class MyFragment extends ListFragment implements OnGetData{
	
	private List<DataInfo> list = null;

	public MyFragment(List<DataInfo> list) {
		this.list = list;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_one_layout, container, false);
		
		Toast.makeText(getActivity(), list.size() + "", Toast.LENGTH_LONG).show();
		
		List<String> list=new ArrayList<String>();
		
		for(int i=0;i<30;i++){
			list.add("我的工资是1000" + i);
		}
		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
		setListAdapter(adapter);
		
		String url = NewsConstants.URL_NEWS;
		
		try {
			String urlJsonStr = HttpUtil.loadJsonFromNet(url, "get");
			
			new JsonStringAsyncTask(getActivity()).execute(urlJsonStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return view;
	}

	@Override
	public void getData(List<DataInfo> list) {
		Toast.makeText(getActivity(), "" + list.size(), 1).show();
	}
}
