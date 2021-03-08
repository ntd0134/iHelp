package com.example.ihelp.ui.register;

import com.example.ihelp.data.model.request_object.LoginRequest;
import com.example.ihelp.data.model.response_object.LoginResponse;

public interface RegisterView {
    void onRegisterSuccess(String msg);
    void onRegisterFail(String msg);

    void onLoginSuccess(LoginResponse responseObj);
    void onLoginFail(String msg);
}
