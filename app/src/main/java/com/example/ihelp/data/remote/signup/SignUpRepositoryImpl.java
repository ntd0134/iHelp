package com.example.ihelp.data.remote.signup;

import android.content.Context;

import com.example.ihelp.data.model.request_object.SignUpRequest;
import com.example.ihelp.data.remote.BaseRepository;
import com.example.ihelp.data.remote.CallBackData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpRepositoryImpl extends BaseRepository implements SignUpRepository{

    private SignUpService mSignUpService;

    public SignUpRepositoryImpl(Context Context) {
        this.mContext = Context;
        initRetrofit();
        mSignUpService = mRetrofit.create(SignUpService.class);
    }

    @Override
    public void register(SignUpRequest requestObj, CallBackData<String> callBackData) {
        Call<Void> call = mSignUpService.register(requestObj);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200){
                    callBackData.onSuccess("Your account has been created");
                }else{
                    callBackData.onFail("Signup Failed!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callBackData.onFail("Signup Failed!");
            }
        });
    }
}
