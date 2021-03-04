package com.example.ihelp.ui.event;

import com.example.ihelp.data.model.response_object.EventsFindAllResponse;

import java.util.List;

public interface EventView {
    void loadEventList(EventsFindAllResponse eventList);
    void showError(String msg);
}
