package com.example.ihelp.data.remote.signup;

import com.example.ihelp.data.model.request_object.SignUpRequest;
import com.example.ihelp.data.remote.CallBackData;

public interface SignUpRepository {
    void register(SignUpRequest requestObj, CallBackData<String> callBackData);
}
