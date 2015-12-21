package com.james.tvplay.async;

import com.james.tvplay.interf.OnGetURLString;
import com.james.tvplay.utils.HttpUtil;

import android.content.Context;
import android.os.AsyncTask;

public class URL2StringAsyncTask extends AsyncTask<String, Void, String> {
	
	private Context context = null;
	OnGetURLString listener = null;
	
	public URL2StringAsyncTask(Context context) {
		this.context = context;
		listener = (OnGetURLString) context;
	}

	@Override
	protected String doInBackground(String... params) {
		String str = null;
		
		try {
			str = HttpUtil.loadJsonFromNet(params[0], "get");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return str;
	}

	@Override
	protected void onPostExecute(String result) {
		listener.getUrlString(result);
		super.onPostExecute(result);
	}
}
