package com.example.ihelp.ui.event_detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ihelp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EventDetailActivity extends AppCompatActivity implements View.OnClickListener {
    //ui components
    private ImageView mBtnBack;
    private TextView mTxtTitle, mTxtParticipant, mTxtSpot, mTxtPoint, mTxtDescription,
            mTxtCategory, mTxtStartDate, mTxtEndDate, mTxtLocation;
    private RecyclerView mRclrParticipant;
    private FloatingActionButton mBtnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        initView();
        initOnClickListener();
    }

    private void initView(){
        mTxtTitle = findViewById(R.id.activity_event_detail_txt_title);
        mTxtParticipant = findViewById(R.id.activity_event_detail_txt_participant);
        mTxtSpot = findViewById(R.id.activity_event_detail_txt_spot);
        mTxtPoint = findViewById(R.id.activity_event_detail_txt_point);
        mTxtDescription = findViewById(R.id.activity_event_detail_txt_description);
        mTxtCategory = findViewById(R.id.activity_event_detail_txt_category);
        mTxtStartDate = findViewById(R.id.activity_event_detail_txt_start_date);
        mTxtEndDate = findViewById(R.id.activity_event_detail_txt_end_date);
        mTxtLocation = findViewById(R.id.activity_event_detail_txt_location);
        mRclrParticipant = findViewById(R.id.activity_event_detail_rclr_participants);
        mBtnMenu = findViewById(R.id.activity_event_detail_btn_menu);
        mBtnBack = findViewById(R.id.activity_event_detail_btn_back);
    }

    private void initOnClickListener(){
        mBtnMenu.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_event_detail_btn_menu:
                break;
            case R.id.activity_event_detail_btn_back:
                super.onBackPressed();
                break;
        }
    }
}