package com.example.ihelp.ui.event;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihelp.R;
import com.example.ihelp.data.model.response_object.EventsFindAllResponse;
import com.example.ihelp.ui.event_detail.EventDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context mContext;
    private List<EventItem> mEventList;

    private final String COLOR_AVAILABLE = "#39ba4c";
    private final String COLOR_NOT_AVAILABLE = "#ff495f";

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView mTxtType, mTxtTitle, mTxtTime, mTxtAvailableSpot;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtType = itemView.findViewById(R.id.txt_type);
            mTxtTitle = itemView.findViewById(R.id.txt_title);
            mTxtTime = itemView.findViewById(R.id.txt_time);
            mTxtAvailableSpot = itemView.findViewById(R.id.txt_available_spot);
        }
    }

    public EventAdapter(Context mContext, List<EventItem> mEventList) {
        this.mContext = mContext;
        this.mEventList = mEventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        EventViewHolder evh = new EventViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventItem currentItem = mEventList.get(position);
        holder.mTxtType.setText((currentItem.getType()+"").toLowerCase());
        holder.mTxtTitle.setText(currentItem.getTitle());
        holder.mTxtTime.setText(currentItem.getStartDate()+"");
        int availableSpot = currentItem.getAvailableSpot();
        holder.mTxtAvailableSpot.setText(availableSpot+" spots left");

        if(availableSpot != 0){
            holder.mTxtAvailableSpot.setBackgroundColor(Color.parseColor(COLOR_AVAILABLE));
        }else{
            holder.mTxtAvailableSpot.setBackgroundColor(Color.parseColor(COLOR_NOT_AVAILABLE));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //need update
                Intent intent = new Intent(mContext, EventDetailActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }
}
