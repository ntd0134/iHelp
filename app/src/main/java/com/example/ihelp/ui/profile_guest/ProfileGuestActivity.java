package com.example.ihelp.ui.profile_guest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ihelp.R;
import com.example.ihelp.data.local.SharedPrefs;
import com.example.ihelp.ui.login.LoginActivity;
import com.example.ihelp.ui.profile.ProfileActivity;

public class ProfileGuestActivity extends AppCompatActivity implements View.OnClickListener {
    //ui components
    private Button mBtnLogin;
    private ImageView mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_guest);
        initView();
        initOnClickListener();
    }

    private void initView(){
        mBtnLogin = findViewById(R.id.activity_profile_guest_btn_login);
        mBtnBack = findViewById(R.id.activity_profile_guest_btn_back);
    }

    private void initOnClickListener(){
        mBtnLogin.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_profile_guest_btn_back:
                onBackPressed();
                break;
            case R.id.activity_profile_guest_btn_login:
                Intent it = new Intent(ProfileGuestActivity.this, LoginActivity.class);
                startActivity(it);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //redirect to Profile Activity if user has logged in
        if(!SharedPrefs.getAccessToken(this).isEmpty()){
            Intent intent = new Intent(ProfileGuestActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        }
    }
}