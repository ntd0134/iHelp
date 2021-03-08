package com.example.ihelp.ui.login;

import com.example.ihelp.data.model.response_object.LoginResponse;

public interface LoginView {
    void loginSuccess(LoginResponse responseObj);
    void loginFail(String errorMsg);
}
