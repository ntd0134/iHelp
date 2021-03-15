package com.example.ihelp.ui.community_event;

import android.content.Context;

import com.example.ihelp.data.model.response_object.EventListResponse;
import com.example.ihelp.data.remote.CallBackData;
import com.example.ihelp.data.remote.event.EventRepository;
import com.example.ihelp.data.remote.event.EventRepositoryImpl;

public class CommunityEventPresenter {
    private Context mContext;
    private CommunityEventView mCommunityEventView;
    private EventRepository mEventRepository;

    public CommunityEventPresenter(Context mContext, CommunityEventView mCommunityEventView) {
        this.mContext = mContext;
        this.mCommunityEventView = mCommunityEventView;
        this.mEventRepository = new EventRepositoryImpl(mContext);
    }

    public void getEventList(){
        // TODO: set page non static
        mEventRepository.eventFindAll(0, new CallBackData<EventListResponse>() {
            @Override
            public void onSuccess(EventListResponse responsesEventsFindAll) {
                mCommunityEventView.loadEventList(responsesEventsFindAll);
            }

            @Override
            public void onFail(String message) {
                mCommunityEventView.showError(message);
            }
        });
    }
}
