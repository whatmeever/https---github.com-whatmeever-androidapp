package com.waterfairy.gamecenter.activitys;

import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.adapter.InstallAdapter;
import com.waterfairy.gamecenter.utils.NoSystemApk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NativeActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lv;
    private List<Map<String, Object>> data;
    public InstallAdapter installAdapter;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        lv= (ListView) findViewById(R.id.native_lv);
        back= (ImageView) findViewById(R.id.app_back);
        back.setOnClickListener(this);
        initnative();
    }
    private void initnative()  {
        data = new ArrayList<Map<String, Object>>();
        installAdapter = new InstallAdapter(this, data, R.layout.install,
                new String[] {}, new int[] {});
        //获得非系统的apk列表
        List<PackageInfo> list = NoSystemApk
                .getPackageInfo(this);
        for (PackageInfo pack : list) {
            //在集合中添加 应用名字 应用图标 应用包名等信xi
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("appname",
                    pack.applicationInfo.loadLabel(getPackageManager())
                            .toString());
            map.put("appico", (Drawable) pack.applicationInfo
                    .loadIcon(getPackageManager()));
            map.put("apppackage", pack.applicationInfo.packageName);
            data.add(map);
        }
        lv.setAdapter(installAdapter);
    }


    @Override
    public void onClick(View v) {
        finish();
    }
}
