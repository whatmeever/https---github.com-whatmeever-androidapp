package com.james.tvplay.async;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.james.tvplay.R;
import com.james.tvplay.bean.DataInfo;
import com.james.tvplay.interf.OnGetData;
import com.james.tvplay.utils.HttpUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class JsonStringAsyncTask extends AsyncTask<String, Void, List<DataInfo>> {
	
	private Context context = null;
	private ProgressDialog mpdDialog = null;
	private OnGetData listener = null;

	public JsonStringAsyncTask(Context context) {
		this.context = context;
		listener = (OnGetData) context;
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
		
		String str = null;
		
		try {
			str = HttpUtil.loadJsonFromNet(params[0], "get");
			obj = new JSONObject(str);
			
			JSONArray array = obj.getJSONArray("data");
			
			for(int i = 0; i < array.length(); i++){
				
				JSONObject dataInfoObj = (JSONObject) array.get(i);
				
				JSONArray arrayData = dataInfoObj.getJSONArray("templateData");
				
				DataInfo dataInfo = null;
				for(int j = 0; j < arrayData.length(); j++){
					
					JSONObject dataInfoObjInner = (JSONObject) arrayData.get(j);
					
					dataInfo = new DataInfo();
					
					dataInfo.setName(dataInfoObjInner.optString("name"));
					dataInfo.setJumpType(dataInfoObjInner.optString("jumpType"));
					dataInfo.setSubjectId(dataInfoObjInner.optString("subjectId"));
					dataInfo.setPicUrl(dataInfoObjInner.optString("picUrl"));
					dataInfo.setPlayUrl(dataInfoObjInner.optString("playUrl"));
					dataInfo.setIcon(dataInfoObjInner.optString("icon"));
					dataInfo.setTag(dataInfoObjInner.optString("tag"));
					dataInfo.setDesc(dataInfoObjInner.optString("desc"));
					dataInfo.setVideoId(dataInfoObjInner.optString("videoId"));
					dataInfo.setHotDegree(dataInfoObjInner.optString("hotDegree"));
					dataInfo.setHotType(dataInfoObjInner.optString("hotType"));
					dataInfo.setPlayTimeIconUrl(dataInfoObjInner.optString("playTimeIconUrl"));
					dataInfo.setWebUrl(dataInfoObjInner.optString("webUrl"));
					dataInfo.setSplitItem(dataInfoObjInner.optString("splitItem"));
					dataInfo.setExt(dataInfoObjInner.optString("ext"));
					dataInfo.setLibId(dataInfoObjInner.optString("libId"));
					dataInfo.setChannelId(dataInfoObjInner.optString("channelId"));
					dataInfo.setJumpChannel(dataInfoObjInner.optString("jumpChannel"));
					
					list.add(dataInfo);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	protected void onPostExecute(List<DataInfo> result) {
		listener.getData(result);
		mpdDialog.dismiss();
		super.onPostExecute(result);
	}
}
