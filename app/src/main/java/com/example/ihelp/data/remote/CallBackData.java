package com.example.ihelp.data.remote;

public interface CallBackData<T> {
    void onSuccess(T t);
    void onFail(String message);
}
