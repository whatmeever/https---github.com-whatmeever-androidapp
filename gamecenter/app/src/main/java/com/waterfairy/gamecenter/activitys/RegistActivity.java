package com.waterfairy.gamecenter.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.waterfairy.gamecenter.AgreementActivity;
import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.utils.RegexUtils;

/**
 *
 * @author james
 */
public class RegistActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView back;
    private EditText name,pwd;
    private CheckBox check1,check2;
    private TextView textView;
    private Button regist;
    RegexUtils regexUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        init();
    }
    public  void init(){
        back= (ImageView) findViewById(R.id.regist_back);
        name= (EditText) findViewById(R.id.regist_et1);
        pwd= (EditText) findViewById(R.id.regist_et2);
        check1= (CheckBox) findViewById(R.id.regist_check1);
        check2 = (CheckBox) findViewById(R.id.regist_check2);
        textView= (TextView) findViewById(R.id.regist_webtext);
        regist= (Button) findViewById(R.id.regist);
        regexUtils=new RegexUtils();
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//为textview添加下划线
        textView.setTextColor(Color.BLUE);
        textView.setOnClickListener(this);
        back.setOnClickListener(this);
        regist.setOnClickListener(this);
        check2.setOnClickListener(this);
        check1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.regist_back:
                finish();
                break;
            case R.id.regist_webtext:
                Intent intent = new Intent(this, AgreementActivity.class);
                Bundle bundle=new Bundle();
                String str1 = "aaaaaa";
                bundle.putString("str", str1);
                intent.putExtras(bundle);
                startActivityForResult(intent,1000);
                break;
            case R.id.regist:
                String sname = name.getText().toString();
                String spwd = pwd.getText().toString();
                if (TextUtils.isEmpty(sname)||TextUtils.isEmpty(spwd)){
                    Toast.makeText(this, "请将信息填写完整之后才能登录", Toast.LENGTH_LONG).show();
                }else if (!regexUtils.isEmail(sname)){
                    Toast.makeText(this,"请填写正确的邮箱",Toast.LENGTH_LONG).show();
                }else if (!check2.isChecked()){
                    Toast.makeText(this,"请先阅读协议,并同意",Toast.LENGTH_LONG).show();
                }else {
                    SharedPreferences sharedPreferences=getSharedPreferences("userinfo",MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("name",sname);
                    edit.putString("pwd",spwd);
                    edit.commit();
                    Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.regist_check2:
                if (check2.isChecked()){
                    regist.setBackgroundColor(Color.GREEN);
                }else {
                    regist.setBackgroundColor(Color.parseColor("#f4f4f4"));
                }
                break;
            case R.id.regist_check1:
                if (check1.isChecked()){
//                    pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pwd.setInputType(0x90);
                }else {
//                    pwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pwd.setInputType(0x81);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK&&requestCode==1000){
            check2.setChecked(true);
            regist.setBackgroundColor(Color.GREEN);
        }
    }

}
