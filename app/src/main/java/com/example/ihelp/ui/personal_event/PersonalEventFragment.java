package com.example.ihelp.ui.personal_event;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ihelp.R;
import com.example.ihelp.data.local.SharedPrefs;
import com.example.ihelp.data.model.dtos.EventItem;
import com.example.ihelp.data.model.enums.EventType;
import com.example.ihelp.data.model.response_object.EventListResponse;
import com.example.ihelp.ui.community_event.EventAdapter;
import com.example.ihelp.ui.event_create.CreateEventActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PersonalEventFragment extends Fragment implements View.OnClickListener, PersonalEventView {
    //fragment view
    private View mView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //ui components
    private FloatingActionButton btnCreateEvent;
    //MVP presenter
    private PersonalEventPresenter mPersonalEventPresenter;

    public PersonalEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_personal_event, container, false);
        initView();
        initVar();
        initData();
        initOnClickEvent();
        return mView;
    }

    private void initView() {
        btnCreateEvent = mView.findViewById(R.id.frgmt_personal_event_btn_create_event);
        mRecyclerView = mView.findViewById(R.id.fragment_personal_event_recycler_view);
    }

    private void initVar() {
        mPersonalEventPresenter = new PersonalEventPresenter(getContext(), this);
    }

    private void initData() {
        String email = SharedPrefs.getEmail(mView.getContext());
        mPersonalEventPresenter.getEventList(email);
    }

    private void initOnClickEvent() {
        btnCreateEvent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frgmt_personal_event_btn_create_event:
                Intent it = new Intent(getActivity(), CreateEventActivity.class);
                startActivity(it);
                break;
        }
    }

    @Override
    public void onLoadEventListSuccess(EventListResponse eventList) {
        //get require fields from response obj
        List eventItems = new ArrayList();
        for (int i = 0; i < eventList.events.size(); i++) {
            //require fields
            String title;
            EventType type;
            Long startDate;
            int spot;
            //get fields
            title = eventList.events.get(i).title;
            if (eventList.events.get(i).onsite) {
                type = EventType.ONSITE;
            } else {
                type = EventType.ONLINE;
            }
            startDate = eventList.events.get(i).startDate;
            spot = eventList.events.get(i).spot;
            //init new obj
            EventItem eventItem = new EventItem(title, type, startDate, spot);
            //add to list
            eventItems.add(eventItem);
        }

        //init recyclerview
        mLayoutManager = new LinearLayoutManager(mView.getContext());
        mAdapter = new EventAdapter(this.getContext(), eventItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoadEventListFail(String errorMsg) {
        Toast.makeText(mView.getContext(), errorMsg, Toast.LENGTH_SHORT).show();
    }
}