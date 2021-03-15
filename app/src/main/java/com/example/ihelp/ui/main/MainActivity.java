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
import com.example.ihelp.ui.guest.GuestFragment;
import com.example.ihelp.ui.personal_service.PersonalServiceFragment;
import com.example.ihelp.ui.personal_event.PersonalEventFragment;
import com.example.ihelp.ui.profile.ProfileActivity;
import com.example.ihelp.ui.profile_guest.ProfileGuestActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //ui components
    private BottomNavigationView mBotNav;
    private ImageView mBtnProfile;
    //global variables
    private String mEmail;
    private String mToken;
    private Fragment mSelectedFragment;
    private int mCurrentSelectedPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getUserInfo();
        initBotNavOnClick();
        initOnClickListener();
        //default page 2 (community fragment) is selected
        mBotNav.setSelectedItemId(R.id.page_2);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, new CommunityFragment()).commit();
    }

    private void initView() {
        mBotNav = findViewById(R.id.activity_main_bottom_navigation);
        mBtnProfile = findViewById(R.id.activity_main_btn_profile);
    }

    private void getUserInfo() {
        mEmail = SharedPrefs.getEmail(getApplicationContext());
        mToken = SharedPrefs.getAccessToken(getApplicationContext());
    }

    private void initOnClickListener() {
        mBtnProfile.setOnClickListener(this);
    }

    private void initBotNavOnClick() {
        mBotNav.setOnNavigationItemSelectedListener(item -> {
            mSelectedFragment = null;
            switch (item.getItemId()) {
                case R.id.page_1:
                    checkPage1();
                    mCurrentSelectedPage = 1;
                    break;
                case R.id.page_2:
                    mSelectedFragment = new CommunityFragment();
                    mCurrentSelectedPage = 2;
                    break;
                case R.id.page_3:
                    checkPage3();
                    mCurrentSelectedPage = 3;
                    break;
                case R.id.page_4:
                    checkPage4();
                    mCurrentSelectedPage = 4;
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, mSelectedFragment).commit();
            return true;
        });
    }

    private void checkPage1() {
        if (!mEmail.isEmpty() && !mToken.isEmpty()) {
            mSelectedFragment = new DashboardFragment();
            return;
        }
        mSelectedFragment = new GuestFragment();
    }

    private void checkPage3() {
        if (!mEmail.isEmpty() && !mToken.isEmpty()) {
            mSelectedFragment = new PersonalEventFragment();
            return;
        }
        mSelectedFragment = new GuestFragment();
    }

    private void checkPage4() {
        if (!mEmail.isEmpty() && !mToken.isEmpty()) {
            mSelectedFragment = new PersonalServiceFragment();
            return;
        }
        mSelectedFragment = new GuestFragment();
    }

    private void checkActivePage() {
        switch (mCurrentSelectedPage) {
            case 1:
                checkPage1();
                break;
            case 2:
                //do nothing
                break;
            case 3:
                checkPage3();
                break;
            case 4:
                checkPage4();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_btn_profile:
                Intent intent;
                //check if user has logged in (has accesstoken)
                //user is not logged in
                if (SharedPrefs.getAccessToken(this).isEmpty()) {
                    intent = new Intent(MainActivity.this, ProfileGuestActivity.class);
                }
                //user logged in
                else {
                    intent = new Intent(MainActivity.this, ProfileActivity.class);
                }
                //start activity
                startActivity(intent);

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserInfo();
        checkActivePage();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, mSelectedFragment).commit();
    }
}