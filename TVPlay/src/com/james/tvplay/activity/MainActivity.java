package com.james.tvplay.activity;

import com.james.tvplay.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 
 * @author james
 *
 */
public class MainActivity extends Activity {
	
	//	是否是第一次运行App
	private boolean isFirst = true;
	
	//	获取本地first.xml文件的SharedPreferences对象
	SharedPreferences sp = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//	通过私有模式来查找first.xml文件构建一个SharedPreferences对象
		sp = getSharedPreferences("first", MODE_PRIVATE);
		
		//	获取SharedPreferences对象中的isFirst的值，如果未找到，则认为是true（第一次）
		isFirst = sp.getBoolean("isFirst", true);
		
		//	构建Intent对象，准备进行界面的跳转
		Intent intent = new Intent();
		if(isFirst){
			
			//	如果是第一次进入app，则跳转到WelcomeActivity界面
			intent.setClass(this, WelcomeActivity.class);
			
			//	给SharedPreferences对象中写入isFirst到值为false（不是第一次）
			sp.edit().putBoolean("isFirst", false).commit();
		}else{
			
			//	如果不是第一次，则无需进入WelcomeActivity界面，而直接进入WorkdActivity界面
			intent.setClass(this, WorkActivity.class);
		}
		
		//	设置isFirst为false
		isFirst = false;
		
		//	实现开启Activity界面到跳转
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}