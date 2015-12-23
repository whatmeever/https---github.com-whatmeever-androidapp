package com.waterfairy.gamecenter.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.waterfairy.gamecenter.R;

public class LuntanActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backNewserver;
    private TextView titleNewserver;
    private ImageView searchNewserver;
    private WebView webviewLuntan;
    private String curPath;
    private String lastPath;
    private LinearLayout progress;
    private int width;

    private void assignViews() {
        progress = (LinearLayout) findViewById(R.id.progress_web);
        backNewserver = (ImageView) findViewById(R.id.back_newserver);
        titleNewserver = (TextView) findViewById(R.id.title_newserver);
        searchNewserver = (ImageView) findViewById(R.id.search_newserver);
        backNewserver.setOnClickListener(this);
        searchNewserver.setOnClickListener(this);
        webviewLuntan = (WebView) findViewById(R.id.webview_luntan);
        webviewLuntan.setNetworkAvailable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luntan);
        assignViews();
        width = getWindowManager().getDefaultDisplay().getWidth();
        progress.scrollTo(-width, 0);
        webviewLuntan.getSettings().setJavaScriptEnabled(true);
        webviewLuntan.setWebViewClient(new HelloWebViewClient());
        webviewLuntan.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progress.scrollTo((int) (width * (1 - newProgress / 100f)), 0);

            }
        });
        webviewLuntan.loadUrl("http://mbbs.g.vivo.com.cn/mvc/home?&taskKey=t5rlf74m3zbxn4m&appVersionName=1.5.1&model=HUAWEI+C8816&pixel=1.5&appVersion=28&elapsedtime=147849355&cs=0&imei=A00000499D5275&origin=341");
    }

    //Web视图
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            isBack = false;
            lastPath = curPath;
            curPath = url;
            view.loadUrl(url);
            return true;
        }
    }

    boolean isBack;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webviewLuntan.canGoBack()) {
            webviewLuntan.goBack(); //goBack()表示返回WebView的上一页面
            isBack = true;
            return true;
        } else if ((keyCode == KeyEvent.KEYCODE_BACK) && !webviewLuntan.canGoBack()) {
            finish();
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_newserver) {
            finish();
        }
        if (v.getId() == R.id.search_newserver) {
            if (isBack) {
                curPath = lastPath;
            }
            webviewLuntan.loadUrl(curPath);
        }
    }
}
