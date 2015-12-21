package com.james.tvplay.async;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.james.tvplay.R;
import com.james.tvplay.bean.DataInfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class JsonStringAsyncTask extends AsyncTask<String, Void, List<DataInfo>> {
	
	private Context context = null;
	private ProgressDialog mpdDialog = null;

	public JsonStringAsyncTask(Context context) {
		this.context = context;
	}
	
	@Override
	protected void onPreExecute() {
		mpdDialog = new ProgressDialog(context);
		
		mpdDialog.setTitle("请稍候");
		mpdDialog.setIcon(R.drawable.ic_launcher);
		mpdDialog.setMessage("玩命加载中……");
		
		mpdDialog.show();
		
		super.onPreExecute();
	}

	@Override
	protected List<DataInfo> doInBackground(String... params) {
		List<DataInfo> list = new ArrayList<DataInfo>();
		
		JSONObject obj;
		try {
			obj = new JSONObject(params[0]);
			
			JSONArray array = obj.getJSONArray("templateData");
			
			DataInfo dataInfo = null;
			for(int i = 0; i < array.length(); i++){
				
				JSONObject dataInfoObj = (JSONObject) array.get(i);
				
				dataInfo = new DataInfo();
				
				dataInfo.setName(dataInfoObj.optString("name"));
				dataInfo.setJumpType(dataInfoObj.optString("jumpType"));
				dataInfo.setSubjectId(dataInfoObj.optString("subjectId"));
				dataInfo.setPicUrl(dataInfoObj.optString("picUrl"));
				dataInfo.setPlayUrl(dataInfoObj.optString("playUrl"));
				dataInfo.setIcon(dataInfoObj.optString("icon"));
				dataInfo.setTag(dataInfoObj.optString("tag"));
				dataInfo.setDesc(dataInfoObj.optString("desc"));
				dataInfo.setVideoId(dataInfoObj.optString("videoId"));
				dataInfo.setHotDegree(dataInfoObj.optString("hotDegree"));
				dataInfo.setHotType(dataInfoObj.optString("hotType"));
				dataInfo.setPlayTimeIconUrl(dataInfoObj.optString("playTimeIconUrl"));
				dataInfo.setWebUrl(dataInfoObj.optString("webUrl"));
				dataInfo.setSplitItem(dataInfoObj.optString("splitItem"));
				dataInfo.setExt(dataInfoObj.optString("ext"));
				dataInfo.setLibId(dataInfoObj.optString("libId"));
				dataInfo.setChannelId(dataInfoObj.optString("channelId"));
				dataInfo.setJumpChannel(dataInfoObj.optString("jumpChannel"));
				
				list.add(dataInfo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	protected void onPostExecute(List<DataInfo> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}
}
