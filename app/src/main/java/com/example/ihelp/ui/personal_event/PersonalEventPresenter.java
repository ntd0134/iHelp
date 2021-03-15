package com.example.ihelp.ui.personal_event;

import android.content.Context;

import com.example.ihelp.data.model.response_object.EventListResponse;
import com.example.ihelp.data.remote.CallBackData;
import com.example.ihelp.data.remote.event.EventRepository;
import com.example.ihelp.data.remote.event.EventRepositoryImpl;

public class PersonalEventPresenter {
    private Context mContext;
    private PersonalEventView mPersonalEventView;
    private EventRepository mEventRepository;

    public PersonalEventPresenter(Context context, PersonalEventView personalEventView) {
        this.mContext = context;
        this.mPersonalEventView = personalEventView;
        mEventRepository = new EventRepositoryImpl(context);
    }

    public void getEventList(String email){
        mEventRepository.eventFindByAuthorEmail(email, 0, new CallBackData<EventListResponse>() {
            @Override
            public void onSuccess(EventListResponse eventListResponse) {
                mPersonalEventView.onLoadEventListSuccess(eventListResponse);
            }

            @Override
            public void onFail(String message) {
                mPersonalEventView.onLoadEventListFail(message);
            }
        });
    }
}
