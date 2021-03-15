package com.example.ihelp.data.remote;

public class ConfigApi {
    //account related
    public static final String LOGIN = "/ihelp/login";
    public static final String SIGNUP = "/ihelp/signup";
    public static final String REFRESH_TOKEN = "/ihelp/refreshtoken";
    //Event
    public static final String EVENT_CATEGORY_FIND_ALL = "/ihelp/api/event-category";
    public static final String EVENTS_FIND_ALL = "/ihelp/api/events";
    public static final String EVENT_ADD_EVENT = "/ihelp/api/events";
    public static final String EVENT_FIND_BY_AUTHOR_EMAIL = "/ihelp/api/events/account/{email}";

}
