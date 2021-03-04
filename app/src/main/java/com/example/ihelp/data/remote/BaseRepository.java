package com.example.ihelp.data.remote;

import android.content.Context;

import com.example.ihelp.data.remote.event.EventService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseRepository {

    protected final String BASE_URL = "http://45.76.161.254:8082";

    protected Context mContext;
    protected Retrofit mRetrofit;

    protected void initRetrofit(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
