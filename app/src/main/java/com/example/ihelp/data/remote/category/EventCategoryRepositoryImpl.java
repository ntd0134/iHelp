package com.example.ihelp.data.remote.category;

import android.content.Context;

import com.example.ihelp.data.model.dtos.EventCategory;
import com.example.ihelp.data.model.response_object.EventCategoryFindAllResponse;
import com.example.ihelp.data.remote.BaseRepository;
import com.example.ihelp.data.remote.CallBackData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventCategoryRepositoryImpl extends BaseRepository implements EventCategoryRepository {

    private EventCategoryService mEventCategoryService;

    public EventCategoryRepositoryImpl(Context context) {
        this.mContext = context;
        initRetrofit();
        this.mEventCategoryService = this.mRetrofit.create(EventCategoryService.class);
    }

    @Override
    public void eventCategoryFindAll(CallBackData<List<EventCategory>> callBackData) {
        Call<List<EventCategory>> call = mEventCategoryService.eventCategoryFindAll();
        call.enqueue(new Callback<List<EventCategory>>() {
            @Override
            public void onResponse(Call<List<EventCategory>> call, Response<List<EventCategory>> response) {
                if (response.isSuccessful()) {
                    callBackData.onSuccess(response.body());
                } else {
                    callBackData.onFail("Fail to retrieve event categories!");
                }
            }

            @Override
            public void onFailure(Call<List<EventCategory>> call, Throwable t) {
                callBackData.onFail("Fail to retrieve event categories!");
            }
        });
    }
}
