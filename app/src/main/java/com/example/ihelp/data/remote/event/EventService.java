package com.example.ihelp.data.remote.event;

import com.example.ihelp.data.model.request_object.AddEventRequest;
import com.example.ihelp.data.model.response_object.EventListResponse;
import com.example.ihelp.data.remote.ConfigApi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EventService {
    @GET(ConfigApi.EVENTS_FIND_ALL)
    Call<EventListResponse> eventFindAll(@Query("page") int page);

    @POST(ConfigApi.EVENT_ADD_EVENT)
    Call<Void> eventAddEvent(@Header("Authorization") String authToken, @Body AddEventRequest requestObj);

    @GET(ConfigApi.EVENT_FIND_BY_AUTHOR_EMAIL)
    Call<EventListResponse> eventFindByAuthorEmail(@Path("email") String email, @Query("page") int page);
}
