package com.example.helloworld;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText username ;
    private EditText password;
    private Button login;
    private Button signup;
    private static final int REQUEST_CODE_REGISTER = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取VIEW控件
//        icon = (ImageView) findViewById(R.id.icon);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        //设置监听器
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //创建Snackbar对象
                Snackbar bar = Snackbar.make(v, "你点我干啥？", Snackbar.LENGTH_LONG);
                //显示Snackbar
                bar.show();
            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, TestFragmentActivity.class);
                startActivityForResult(intent, REQUEST_CODE_REGISTER);
            }
        });
        ActionBar actionBar = MainActivity.this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CODE_REGISTER){//说明是注册页面返回
            if(resultCode == RESULT_OK){//说明时成功返回
                String username = data.getStringExtra("username");
                String password = data.getStringExtra("password");
                Log.i("testLogin", "username = " + username + ", password = " + password);
                MainActivity.this.username.setText(username);
                MainActivity.this.password.setText(password);
            }
        }
        //调用一下父类的实现。
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            Snackbar snackbar = Snackbar.make(username, "你再点我，就真要退出了", Snackbar.LENGTH_LONG);
            snackbar.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}