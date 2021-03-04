package com.example.ihelp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ihelp.R;
import com.example.ihelp.ui.community.CommunityFragment;
import com.example.ihelp.ui.dashboard.DashboardFragment;
import com.example.ihelp.ui.personal_service.PersonalServiceFragment;
import com.example.ihelp.ui.personal_event.PersonalEventFragment;
import com.example.ihelp.ui.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BottomNavigationView mBotNav;
    ImageView mBtnProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initBotNavOnClick();
        initOnClickListener();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, new DashboardFragment()).commit();
    }

    private void initView(){
        mBotNav = findViewById(R.id.bottom_navigation);
        mBtnProfile = findViewById(R.id.btn_profile);
    }

    private void initOnClickListener(){
        mBtnProfile.setOnClickListener(this);
    }

    private void initBotNavOnClick(){
        mBotNav.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch(item.getItemId()){
                case R.id.page_1:
                    selectedFragment = new DashboardFragment();
                    break;
                case R.id.page_2:
                    selectedFragment = new CommunityFragment();
                    break;
                case R.id.page_3:
                    selectedFragment = new PersonalEventFragment();
                    break;
                case R.id.page_4:
                    selectedFragment = new PersonalServiceFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, selectedFragment).commit();
            return true;
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_profile:
                Intent it = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(it);
                break;
        }
    }
}