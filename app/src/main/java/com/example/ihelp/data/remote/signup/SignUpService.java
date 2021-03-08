package com.example.ihelp.data.remote.signup;

import com.example.ihelp.data.model.request_object.SignUpRequest;
import com.example.ihelp.data.remote.ConfigApi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpService {
    @POST(ConfigApi.SIGNUP)
    Call<Void> register(@Body SignUpRequest requestObj);
}
