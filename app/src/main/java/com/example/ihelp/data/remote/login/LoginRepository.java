package com.example.ihelp.data.remote.login;

import com.example.ihelp.data.model.request_object.LoginRequest;
import com.example.ihelp.data.model.response_object.LoginResponse;
import com.example.ihelp.data.remote.CallBackData;

public interface LoginRepository {
    void login(LoginRequest requestObj, CallBackData<LoginResponse> callBackData);
}
