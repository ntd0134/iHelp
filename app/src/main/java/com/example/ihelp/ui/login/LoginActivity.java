package com.example.ihelp.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ihelp.R;
import com.example.ihelp.ui.main.MainActivity;
import com.example.ihelp.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button mBtnLogin, mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initOnClickListener();
    }

    private void initView(){
        mBtnLogin = findViewById(R.id.activity_login_btn_login);
        mBtnRegister = findViewById(R.id.activity_login_btn_register);
    }

    private void initOnClickListener(){
        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_login_btn_login:
                break;
            case R.id.activity_login_btn_register:
                Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(it);
                break;
        }
    }
}