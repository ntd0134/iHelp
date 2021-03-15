package com.example.ihelp.data.remote.category;

import com.example.ihelp.data.model.dtos.EventCategory;
import com.example.ihelp.data.model.response_object.EventCategoryFindAllResponse;
import com.example.ihelp.data.remote.CallBackData;

import java.util.List;

public interface EventCategoryRepository {
    void eventCategoryFindAll(CallBackData<List<EventCategory>> callBackData);
}
