package com.example.ihelp.ui.event;

import com.example.ihelp.data.model.response_object.EventsFindAllResponse;

public interface EventView {
    void loadEventList(EventsFindAllResponse eventList);
    void showError(String msg);
}
