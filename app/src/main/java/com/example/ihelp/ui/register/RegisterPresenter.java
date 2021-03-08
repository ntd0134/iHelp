package com.example.ihelp.ui.register;

import android.content.Context;

import com.example.ihelp.data.model.request_object.LoginRequest;
import com.example.ihelp.data.model.request_object.SignUpRequest;
import com.example.ihelp.data.model.response_object.LoginResponse;
import com.example.ihelp.data.remote.CallBackData;
import com.example.ihelp.data.remote.login.LoginRepository;
import com.example.ihelp.data.remote.login.LoginRepositoryImpl;
import com.example.ihelp.data.remote.signup.SignUpRepository;
import com.example.ihelp.data.remote.signup.SignUpRepositoryImpl;

public class RegisterPresenter {
    private RegisterView mRegisterView;
    private SignUpRepository mSignUpRepository;
    private LoginRepository mLoginRepository;

    public RegisterPresenter(Context mContext, RegisterView mView) {
        this.mRegisterView = mView;
        this.mSignUpRepository = new SignUpRepositoryImpl(mContext);
        this.mLoginRepository = new LoginRepositoryImpl(mContext);
    }

    public void registerNewAccount(SignUpRequest signUpObj){
        //check if email is null
        if(signUpObj.getEmail().isEmpty()){
            mRegisterView.onRegisterFail("Please fill in your email ");
            return;
        }
        //check if password is null
        if(signUpObj.getPassword().isEmpty()){
            mRegisterView.onRegisterFail("Please fill in your password !");
            return;
        }
        //check if fullname is null
        if(signUpObj.getFullname().isEmpty()){
            mRegisterView.onRegisterFail("Please fill in your full name !");
            return;
        }
        //check if phone number is null
        if(signUpObj.getPhone().isEmpty()){
            mRegisterView.onRegisterFail("Please fill in your phone number !");
            return;
        }
        //check if birth date is null
        if(signUpObj.getDateOfBirth() == 0L){
            mRegisterView.onRegisterFail("Please select your birth date !");
            return;
        }

        //register new account
        mSignUpRepository.register(signUpObj, new CallBackData<String>() {
            @Override
            public void onSuccess(String s) {
                mRegisterView.onRegisterSuccess(s);
            }

            @Override
            public void onFail(String message) {
                mRegisterView.onRegisterFail(message);
            }
        });
    }

    public void login(LoginRequest loginObj){
        mLoginRepository.login(loginObj, new CallBackData<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse loginResponse) {
                mRegisterView.onLoginSuccess(loginResponse);
            }

            @Override
            public void onFail(String message) {
                mRegisterView.onLoginFail(message);
            }
        });
    }
}
