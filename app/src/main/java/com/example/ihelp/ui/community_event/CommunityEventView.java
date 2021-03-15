package com.example.ihelp.ui.community_event;

import com.example.ihelp.data.model.response_object.EventListResponse;

public interface CommunityEventView {
    void loadEventList(EventListResponse eventList);
    void showError(String msg);
}
