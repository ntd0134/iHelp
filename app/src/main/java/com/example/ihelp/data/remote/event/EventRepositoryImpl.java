package com.example.ihelp.data.remote.event;

import android.content.Context;

import com.example.ihelp.data.model.response_object.EventsFindAllResponse;
import com.example.ihelp.data.remote.BaseRepository;
import com.example.ihelp.data.remote.CallBackData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventRepositoryImpl extends BaseRepository implements EventRepository {

    private EventService mEventService;

    public EventRepositoryImpl(Context mContext) {
        this.mContext = mContext;
        initRetrofit();
        mEventService = mRetrofit.create(EventService.class);
    }

    @Override
    public void eventsFindAll(int page, CallBackData<EventsFindAllResponse> callBackData) {
        initRetrofit();
        Call<EventsFindAllResponse> call = mEventService.eventsFindAll(page);
        call.enqueue(new Callback<EventsFindAllResponse>() {
            @Override
            public void onResponse(Call<EventsFindAllResponse> call, Response<EventsFindAllResponse> response) {
                if (response.isSuccessful()) {
                    callBackData.onSuccess(response.body());
                } else {
                    callBackData.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<EventsFindAllResponse> call, Throwable t) {
                callBackData.onFail(t.getMessage());
            }
        });
    }
}
