package com.waterfairy.gamecenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

/**
 *
 * @author james
 */
public class AgreementActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    private WebView webView;
    private ImageView back,refresh;
    String loadUrl="http://download0.vivo.com.cn/pro/reg_pro_zh_CN.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        button= (Button) findViewById(R.id.ok);
        webView= (WebView) findViewById(R.id.agree_web);
        back= (ImageView) findViewById(R.id.xieyi_back);
        refresh= (ImageView) findViewById(R.id.xieyi_refresh);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        button.setOnClickListener(this);
        webView.loadUrl(loadUrl);
    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xieyi_back:
                finish();
                break;
            case R.id.xieyi_refresh:
                webView.loadUrl(loadUrl);
                break;
            case R.id.ok:
                setResult(RESULT_OK,new Intent());
                finish();
                break;
        }

    }
}
