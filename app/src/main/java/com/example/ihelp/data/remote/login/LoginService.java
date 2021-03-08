package com.example.ihelp.data.remote.login;

import com.example.ihelp.data.model.request_object.LoginRequest;
import com.example.ihelp.data.model.response_object.LoginResponse;
import com.example.ihelp.data.remote.ConfigApi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST(ConfigApi.LOGIN)
    Call<LoginResponse> login(@Body LoginRequest requestObj);
}
