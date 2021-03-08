package com.example.ihelp.data.remote.event;

import com.example.ihelp.data.model.response_object.EventsFindAllResponse;
import com.example.ihelp.data.remote.ConfigApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventService {
    @GET(ConfigApi.EVENTS_FIND_ALL)
    Call<EventsFindAllResponse> eventsFindAll(@Query("page") int page);
}
