package com.example.ihelp.ui.personal_event;

import com.example.ihelp.data.model.response_object.EventListResponse;

public interface PersonalEventView {
    void onLoadEventListSuccess(EventListResponse eventList);
    void onLoadEventListFail(String errorMsg);
}
