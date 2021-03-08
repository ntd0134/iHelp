package com.example.ihelp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ihelp.R;
import com.example.ihelp.data.local.SharedPrefs;
import com.example.ihelp.ui.community.CommunityFragment;
import com.example.ihelp.ui.dashboard.DashboardFragment;
import com.example.ihelp.ui.personal_service.PersonalServiceFragment;
import com.example.ihelp.ui.personal_event.PersonalEventFragment;
import com.example.ihelp.ui.profile.ProfileActivity;
import com.example.ihelp.ui.profile_guest.ProfileGuestActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //ui components
    private BottomNavigationView mBotNav;
    private ImageView mBtnProfile;

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
        mBotNav = findViewById(R.id.activity_main_bottom_navigation);
        mBtnProfile = findViewById(R.id.activity_main_btn_profile);
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
            case R.id.activity_main_btn_profile:
                Intent intent;
                //check if user has logged in (has accesstoken)
                //user is not logged in
                if(SharedPrefs.getAccessToken(this).isEmpty()){
                    intent = new Intent(MainActivity.this, ProfileGuestActivity.class);
                }
                //user logged in
                else{
                    intent = new Intent(MainActivity.this, ProfileActivity.class);
                }
                //start activity
                startActivity(intent);

                break;
        }
    }
}