package com.example.ihelp.data.remote.login;

import android.content.Context;

import com.example.ihelp.data.model.request_object.LoginRequest;
import com.example.ihelp.data.model.response_object.LoginResponse;
import com.example.ihelp.data.remote.BaseRepository;
import com.example.ihelp.data.remote.CallBackData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepositoryImpl extends BaseRepository implements LoginRepository{

    private LoginService mLoginService;

    public LoginRepositoryImpl(Context Context) {
        this.mContext = Context;
        initRetrofit();
        mLoginService = mRetrofit.create(LoginService.class);
    }

    @Override
    public void login(LoginRequest requestObj, CallBackData<LoginResponse> callBackData) {
        Call<LoginResponse> call = mLoginService.login(requestObj);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.code() == 200){
                    callBackData.onSuccess(response.body());
                }else{
                    callBackData.onFail("Username/password is incorrect!");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callBackData.onFail("Login failed!");
            }
        });
    }
}
