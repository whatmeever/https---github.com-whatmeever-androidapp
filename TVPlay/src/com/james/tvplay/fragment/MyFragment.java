package com.james.tvplay.fragment;

import java.util.List;

import com.james.tvplay.R;
import com.james.tvplay.adapter.DataInfoAdatper;
import com.james.tvplay.bean.DataInfo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * @author james
 *
 */
public class MyFragment extends ListFragment {
	
//	private List<DataInfo> list = null;
	private DataInfoAdatper adapter = null;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	public MyFragment(List<DataInfo> list, DataInfoAdatper adapter) {
//		this.list = list;
		this.adapter = adapter;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_one_layout, container, false);
		setListAdapter(adapter);
		
//		for(int i=0;i<list.size();i++){
//			ImageView img = (ImageView) view.findViewById(R.id.iv_bg);
//			TextView txtName = (TextView) view.findViewById(R.id.tv_name);
//			TextView txtTitle = (TextView) view.findViewById(R.id.tv_title);
//			
//			DataInfo dataInfo = list.get(i);
//			
//			txtName.setText(dataInfo.getName());
//			txtTitle.setText(dataInfo.getDesc());
//		}
		
//		ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
//		setListAdapter(adapter);
//		
//		String url = NewsConstants.URL_NEWS;
//		
//		try {
//			String urlJsonStr = HttpUtil.loadJsonFromNet(url, "get");
//			
//			new JsonStringAsyncTask(getActivity()).execute(urlJsonStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return view;
	}
}
