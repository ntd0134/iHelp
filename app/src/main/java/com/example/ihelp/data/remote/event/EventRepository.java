package com.example.ihelp.data.remote.event;

import com.example.ihelp.data.model.response_object.EventsFindAllResponse;
import com.example.ihelp.data.remote.CallBackData;

import java.util.List;

public interface EventRepository {
    void eventsFindAll(int page, CallBackData<EventsFindAllResponse> callBackData);
}
