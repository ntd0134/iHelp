package com.example.ihelp.ui.event;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ihelp.R;
import com.example.ihelp.data.model.response_object.EventsFindAllResponse;

import java.util.ArrayList;
import java.util.List;

public class EventFragment extends Fragment implements EventView{
    //fragment view
    private View mView;
    //ui components
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //MVP fields
    private EventPresenter mEventPresenter;

    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_event, container, false);
        initView();
        initVar();
        initData();
        return mView;
    }

    private void initView(){
        mRecyclerView = mView.findViewById(R.id.recycler_view);
    }

    private void initVar(){
        mEventPresenter = new EventPresenter(mView.getContext(), this);
    }

    private void initData(){
        mEventPresenter.getEventList();
    }


    @Override
    public void loadEventList(EventsFindAllResponse response) {
        //get require fields from response obj
        List eventItems = new ArrayList();
        for(int i=0; i<response.events.size();i++){
            //require fields
            String title;
            EventType type;
            Long startDate;
            int spot;
            //get fields
            title = response.events.get(i).title;
            if(response.events.get(i).onsite){
                type = EventType.ONSITE;
            }else{
                type = EventType.ONLINE;
            }
            startDate = response.events.get(i).startDate;
            spot = response.events.get(i).spot;
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
    public void showError(String msg) {
        Toast.makeText(mView.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}