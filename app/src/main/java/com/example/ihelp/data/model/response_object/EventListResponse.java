package com.example.ihelp.data.model.response_object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventListResponse {
    @SerializedName("totalItems")
    public Integer totalItems;
    @SerializedName("totalPages")
    public Integer totalPages;
    @SerializedName("currentPage")
    public Integer currentPage;
    @SerializedName("events")
    public List<Events> events;

    public class Events{
        @SerializedName("id")
        public String id;
        @SerializedName("title")
        public String title;
        @SerializedName("spot")
        public Integer spot;
        @SerializedName("startDate")
        public Long startDate;
        @SerializedName("endDate")
        public Long endDate;
        @SerializedName("authorAccount")
        public AuthorAccount authorAccount;
        @SerializedName("status")
        public Status status;
        @SerializedName("category")
        public Category category;
        @SerializedName("images")
        public List<Images> images;
        @SerializedName("onsite")
        public Boolean onsite;

        public class AuthorAccount{
            @SerializedName("email")
            public String email;
            @SerializedName("fullname")
            public String fullname;
            @SerializedName("phone")
            public String phone;
            @SerializedName("dateOfBirth")
            public Long dateOfBirth;
            @SerializedName("gender")
            public Boolean gender;
            @SerializedName("balancePoint")
            public Integer balancePoint;
            @SerializedName("cumulativePoint")
            public Integer cumulativePoint;
            @SerializedName("createdDate")
            public Long createdDate;
        }

        public class Status{
            @SerializedName("id")
            public Integer id;
            @SerializedName("name")
            public String name;
        }

        public class Category{
            @SerializedName("id")
            public Integer id;
            @SerializedName("name")
            public String name;
        }

        public class Images{
            @SerializedName("imageId")
            public String imageId;
            @SerializedName("url")
            public String url;
            @SerializedName("type")
            public String type;
            @SerializedName("authorEmail")
            public String authorEmail;
        }
    }
}
