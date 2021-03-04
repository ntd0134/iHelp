package com.example.ihelp.ui.event;

public class EventItem {
    private String mTitle;
    private EventType mType;
    private Long mStartDate;
    private Integer mAvailableSpot;

    public EventItem(String mTitle, EventType mType, Long mStartDate, Integer mAvailableSpot) {
        this.mTitle = mTitle;
        this.mType = mType;
        this.mStartDate = mStartDate;
        this.mAvailableSpot = mAvailableSpot;
    }

    public EventType getType() {
        return mType;
    }

    public void setType(EventType mType) {
        this.mType = mType;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Long getStartDate() {
        return mStartDate;
    }

    public void setStartDate(Long mStartDate) {
        this.mStartDate = mStartDate;
    }

    public Integer getAvailableSpot() {
        return mAvailableSpot;
    }

    public void setAvailableSpot(Integer mAvailableSpot) {
        this.mAvailableSpot = mAvailableSpot;
    }
}
