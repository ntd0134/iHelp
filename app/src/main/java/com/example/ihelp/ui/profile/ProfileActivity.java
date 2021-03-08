package com.example.ihelp.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ihelp.R;
import com.example.ihelp.data.local.SharedPrefs;
import com.example.ihelp.ui.login.LoginActivity;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    //ui components
    private Button mBtnLogout;
    private ImageView mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
        initOnClickListener();
    }

    private void initView(){
        mBtnLogout = findViewById(R.id.activity_profile_btn_logout);
        mBtnBack = findViewById(R.id.activity_profile_btn_back);
    }

    private void initOnClickListener() {
        mBtnLogout.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_profile_btn_logout:
                //clear user info
                SharedPrefs.removeRemoveUserInfo(this.getApplicationContext());
                //self-destroy
                finish();
                break;
            case R.id.activity_profile_btn_back:
                super.onBackPressed();
                break;
        }
    }
}