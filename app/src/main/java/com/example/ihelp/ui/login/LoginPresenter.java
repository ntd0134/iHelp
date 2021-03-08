package com.example.ihelp.ui.login;

import android.content.Context;

import com.example.ihelp.data.model.request_object.LoginRequest;
import com.example.ihelp.data.model.response_object.LoginResponse;
import com.example.ihelp.data.remote.CallBackData;
import com.example.ihelp.data.remote.login.LoginRepository;
import com.example.ihelp.data.remote.login.LoginRepositoryImpl;

public class LoginPresenter {
    private LoginView mLoginView;
    private LoginRepository mLoginRepository;

    public LoginPresenter(Context mContext, LoginView mLoginView) {
        this.mLoginView = mLoginView;
        this.mLoginRepository = new LoginRepositoryImpl(mContext);
    }

    public void authenticateUser(LoginRequest requestObj){
        //check email null
        if(requestObj.getEmail().isEmpty()){
            mLoginView.loginFail("Email can't be empty!");
            return;
        }
        //check password null
        if(requestObj.getPassword().isEmpty()){
            mLoginView.loginFail("Password can't be empty!");
            return;
        }
        //authenticate user
        mLoginRepository.login(requestObj, new CallBackData<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse loginResponse) {
                mLoginView.loginSuccess(loginResponse);
            }

            @Override
            public void onFail(String message) {
                mLoginView.loginFail(message);
            }
        });
    }
}
