package com.example.ihelp.data.remote.category;

import com.example.ihelp.data.model.dtos.EventCategory;
import com.example.ihelp.data.model.response_object.EventCategoryFindAllResponse;
import com.example.ihelp.data.remote.ConfigApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EventCategoryService {
    @GET(ConfigApi.EVENT_CATEGORY_FIND_ALL)
    Call<List<EventCategory>> eventCategoryFindAll();
}
