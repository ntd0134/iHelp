package com.example.ihelp.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ihelp.R;
import com.example.ihelp.ui.login.LoginActivity;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtnLogout, mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
        initOnClickListener();
    }

    private void initView(){
        mBtnLogin = findViewById(R.id.activity_profile_btn_login);
        mBtnLogout = findViewById(R.id.activity_profile_btn_logout);
    }

    private void initOnClickListener() {
        mBtnLogin.setOnClickListener(this);
        mBtnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_profile_btn_login:
                Intent it = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(it);
                break;
            case R.id.activity_profile_btn_logout:
                break;
        }
    }
}