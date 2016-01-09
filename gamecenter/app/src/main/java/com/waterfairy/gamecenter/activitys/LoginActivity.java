package com.waterfairy.gamecenter.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.utils.RegexUtils;

public class LoginActivity extends AppCompatActivity {
    private EditText name,pwd;
    private Button login,regist,find;
    private ImageView back;
    private RegexUtils regexUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    public void init(){
        name= (EditText) findViewById(R.id.login_et1);
        pwd= (EditText) findViewById(R.id.login_et2);
        login= (Button) findViewById(R.id.login);
        regist= (Button) findViewById(R.id.login_regist);
        find= (Button) findViewById(R.id.login_find);
        back= (ImageView) findViewById(R.id.login_back);
        regexUtils=new RegexUtils();

    }

    public void  onclick(View view){
        switch (view.getId()){
            case R.id.login_back:
                finish();
                break;
            case R.id.login:
                String sname = name.getText().toString()+"";
                String spwd = pwd.getText().toString()+"";
                if (TextUtils.isEmpty(sname)||TextUtils.isEmpty(spwd)){
                    Toast.makeText(this,"请将信息填写完整之后才能登录",Toast.LENGTH_LONG).show();
                }else if (!regexUtils.isEmail(sname)){
                    Toast.makeText(this,"请填写正确的邮箱",Toast.LENGTH_LONG).show();
                }else if (isuser(sname,spwd)){
                    Toast.makeText(this,"登陆完成",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.putExtra("name",sname);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(this,"用户名不存在,请先注册",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.login_regist:
                startActivity(new Intent(this,RegistActivity.class));
                break;
            case R.id.login_find:
                Toast.makeText(this,"密码已恢复到您的邮箱,请注意查收",Toast.LENGTH_LONG).show();
                break;
        }
    }
    //判断是否存在用户信息
    public boolean isuser(String name,String pwd){
        SharedPreferences sharedPreferences=getSharedPreferences("userinfo",MODE_PRIVATE);
        String name1 = sharedPreferences.getString("name", "");
        String pwd1 = sharedPreferences.getString("pwd", "");
        if (name1.equals(name)&&pwd1.equals(pwd)){
            return true;
        }
        return false;
    }


}
