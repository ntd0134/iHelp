package com.example.ihelp.ui.personal_event;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ihelp.R;
import com.example.ihelp.ui.event_create.CreateEventActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PersonalEventFragment extends Fragment implements View.OnClickListener {
    //fragment view
    private View mView;
    //ui components
    FloatingActionButton btnCreateEvent;

    public PersonalEventFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_personal_event, container, false);
        initView();
        initOnClickEvent();
        return mView;
    }

    private void initView(){
        btnCreateEvent = mView.findViewById(R.id.frgmt_personal_event_btn_create_event);
    }

    private void initOnClickEvent(){
        btnCreateEvent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.frgmt_personal_event_btn_create_event:
                Intent it = new Intent(getActivity(), CreateEventActivity.class);
                startActivity(it);
                break;
        }
    }
}