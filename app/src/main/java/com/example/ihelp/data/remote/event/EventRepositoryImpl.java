package com.example.ihelp.data.remote.event;

import android.content.Context;

import com.example.ihelp.data.local.SharedPrefs;
import com.example.ihelp.data.model.request_object.AddEventRequest;
import com.example.ihelp.data.model.response_object.EventListResponse;
import com.example.ihelp.data.remote.BaseRepository;
import com.example.ihelp.data.remote.CallBackData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventRepositoryImpl extends BaseRepository implements EventRepository {

    private EventService mEventService;

    public EventRepositoryImpl(Context context) {
        this.mContext = context;
        initRetrofit();
        mEventService = mRetrofit.create(EventService.class);
    }

    @Override
    public void eventFindAll(int page, CallBackData<EventListResponse> callBackData) {
        Call<EventListResponse> call = mEventService.eventFindAll(page);
        call.enqueue(new Callback<EventListResponse>() {
            @Override
            public void onResponse(Call<EventListResponse> call, Response<EventListResponse> response) {
                if (response.isSuccessful()) {
                    callBackData.onSuccess(response.body());
                } else {
                    callBackData.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<EventListResponse> call, Throwable t) {
                callBackData.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void eventFindByAuthorEmail(String email, int page, CallBackData<EventListResponse> callBackData) {
        Call<EventListResponse> call = mEventService.eventFindByAuthorEmail(email, page);
        call.enqueue(new Callback<EventListResponse>() {
            @Override
            public void onResponse(Call<EventListResponse> call, Response<EventListResponse> response) {
                if(response.isSuccessful()){
                    callBackData.onSuccess(response.body());
                }else{
                    callBackData.onFail("Fail getting event list");
                }
            }

            @Override
            public void onFailure(Call<EventListResponse> call, Throwable t) {
                callBackData.onFail("Error getting event list!");
            }
        });
    }

    @Override
    public void eventAddEvent(AddEventRequest requestObj, CallBackData<String> callBackData) {
        String authToken = "Bearer " + SharedPrefs.getAccessToken(mContext);
        Call<Void> call = mEventService.eventAddEvent(authToken, requestObj);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    callBackData.onSuccess("Event Added Successful!");
                }else{
                    callBackData.onFail("Fail Adding Event!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callBackData.onFail("Error Adding Event");
            }
        });
    }
}
