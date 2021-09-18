package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AndroidException;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //工具栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //返回功能
        Button cancel = (Button) findViewById(R.id.signup_cancel);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //关闭当前Activity。
                RegisterActivity.this.finish();
            }
        });
        //确认功能
        Button ok = (Button) findViewById(R.id.signup_ok);
        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //获取控件
                EditText editTextUsername = (EditText) findViewById(R.id.signup_usernname);
                EditText editTextPassword = (EditText) findViewById(R.id.signup_password);
                EditText editTextPasswordAgain = (EditText) findViewById(R.id.signup_password_agin);
                EditText editTextEmail = (EditText) findViewById(R.id.signup_email);
                EditText editTextPhone = (EditText) findViewById(R.id.signup_phone);
                EditText editTextAddress = (EditText) findViewById(R.id.signup_address);
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.signup_radio_group);
                //获取数据
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                String password2= editTextPasswordAgain.getText().toString();
                String email    = editTextEmail.getText().toString();
                String phone    = editTextPhone.getText().toString();
                String address  = editTextAddress.getText().toString();
                boolean isMan   = radioGroup.getCheckedRadioButtonId() == R.id.signup_radio_man;
                //注册
                //TODO: 做好后台后实现此代码。
                //设置Intent
                Intent intent = new Intent();
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                //设置状态码与结构
                RegisterActivity.this.setResult(RESULT_OK, intent);
                //关闭Activity
                RegisterActivity.this.finish();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}