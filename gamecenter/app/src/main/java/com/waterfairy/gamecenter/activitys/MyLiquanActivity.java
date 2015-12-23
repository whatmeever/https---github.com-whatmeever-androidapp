package com.waterfairy.gamecenter.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.waterfairy.gamecenter.R;

public class MyLiquanActivity extends AppCompatActivity implements View.OnClickListener {
    public ImageView back,refresh;
    public WebView webView;
    public LinearLayout prgress;
    int width;
    String loadUrl="http://hf.gamecenter.vivo.com.cn/h5/gift-certificate.html?appVersionName=1.5.1&model=HYF9200&pixel=0.0&appVersion=28&elapsedtime=9005812&cs=0&imei=864970025057133&origin=343";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_liquan);
        init();
    }
    public void init(){
        back= (ImageView) findViewById(R.id.liquan_back);
        refresh= (ImageView) findViewById(R.id.liquan_refresh);
        webView= (WebView) findViewById(R.id.webview_liquan);
        prgress= (LinearLayout) findViewById(R.id.liquan_progress);

        width = getWindowManager().getDefaultDisplay().getWidth();
        prgress.scrollTo(-width,0);
        // JavaScript使能(如果要加载的页面中有JS代码，则必须使能JS)
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                prgress.scrollTo((int) (width * (1 - newProgress / 100f)), 0);

            }
        });
        webView.loadUrl(loadUrl);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.liquan_back:
                finish();
                break;
            case R.id.liquan_refresh:
                webView.loadUrl(loadUrl);
                break;
        }
    }
}


