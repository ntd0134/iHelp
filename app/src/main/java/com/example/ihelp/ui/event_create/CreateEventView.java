package com.example.ihelp.ui.event_create;

import android.net.Uri;

import com.example.ihelp.data.model.dtos.EventCategory;

import java.util.List;

import okhttp3.ResponseBody;

public interface CreateEventView {
    void onEventCategoriesLoadSuccess(List<EventCategory> eventCategories);
    void onEventCategoriesLoadFail(String errorMsg);

    void onAddEventSuccess(String successMsg);
    void onAddEventFail(String errorMsg);

    void onEdtTitleError(String errorMsg);
    void onEdtDescriptionError(String errorMsg);
    void onParticipantLimitError(String errorMsg);
    void onPointError(String errorMsg);
    void onDateError(String errorMsg);
    void onLocationError(String errorMsg);
    void onCategoryError(String errorMsg);
    void onCoverPhotoError(String errorMsg);
}
