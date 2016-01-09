package com.waterfairy.gamecenter.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.category.SearchActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//友盟key------>562a1f6567e58e0e83000dbc
public class FanKuiActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et1,et2;
    private Button bt;
    private ImageView back,search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_kui);
        init();
    }
    public void init(){
        et1= (EditText) findViewById(R.id.fankui_et);
        et2= (EditText) findViewById(R.id.fankui_email);
        bt= (Button) findViewById(R.id.fankui_ok);
        back= (ImageView) findViewById(R.id.fankui_back);
        search= (ImageView) findViewById(R.id.fankui_search);
        bt.setOnClickListener(this);
        back.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fankui_back:
                finish();
                break;
            case R.id.fankui_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.fankui_ok:
                if (TextUtils.isEmpty(et1.getText().toString())){
                    Toast.makeText(this,"您给个建议呗",Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(et2.getText().toString())){
                    Toast.makeText(this,"加上联系方式吧,@_@",Toast.LENGTH_LONG).show();
                }else  if (isNumeric(et2.getText().toString())){
                   if (isMobileNO(et2.getText().toString())){
                       Toast.makeText(this,"谢谢的建议,我们会马上改善",Toast.LENGTH_LONG).show();
                   }else {
                       Toast.makeText(this,"请输入正确的手机号码",Toast.LENGTH_LONG).show();
                   }
                }else {
                    if (isEmail(et2.getText().toString())){
                        Toast.makeText(this,"谢谢的建议,我们会马上改善",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(this,"请输入正确的邮箱",Toast.LENGTH_LONG).show();
                    }
                }

                break;
        }
    }


    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }
    //判断email格式是否正确
    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    //判断是否全是数字
    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

}
