package com.example.ihelp.data.remote.event;

import com.example.ihelp.data.model.request_object.AddEventRequest;
import com.example.ihelp.data.model.response_object.EventListResponse;
import com.example.ihelp.data.remote.CallBackData;

public interface EventRepository {
    void eventFindAll(int page, CallBackData<EventListResponse> callBackData);
    void eventFindByAuthorEmail(String email, int page, CallBackData<EventListResponse> callBackData);
    void eventAddEvent(AddEventRequest requestObj, CallBackData<String> callBackData);
}
