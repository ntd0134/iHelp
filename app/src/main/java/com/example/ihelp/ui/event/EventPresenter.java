package com.example.ihelp.ui.event;

import android.content.Context;

import com.example.ihelp.data.model.response_object.EventsFindAllResponse;
import com.example.ihelp.data.remote.CallBackData;
import com.example.ihelp.data.remote.event.EventRepository;
import com.example.ihelp.data.remote.event.EventRepositoryImpl;

public class EventPresenter {
    private Context mContext;
    private EventView mEventView;
    private EventRepository mEventRepository;

    public EventPresenter(Context mContext, EventView mEventView) {
        this.mContext = mContext;
        this.mEventView = mEventView;
        this.mEventRepository = new EventRepositoryImpl(mContext);
    }

    public void getEventList(){
        // TODO: set page non static
        mEventRepository.eventsFindAll(0, new CallBackData<EventsFindAllResponse>() {
            @Override
            public void onSuccess(EventsFindAllResponse responsesEventsFindAll) {
                mEventView.loadEventList(responsesEventsFindAll);
            }

            @Override
            public void onFail(String message) {
                mEventView.showError(message);
            }
        });
    }
}
