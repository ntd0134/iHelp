package com.example.ihelp.data.remote.access_token;

import androidx.annotation.Nullable;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class TokenAuthenticator implements Authenticator {
    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, Response response) throws IOException {
        //TODO: implement refresh
        //do nothing yet, only return null
        return null;

//        // Refresh your access_token using a synchronous api request
//        newAccessToken = service.refreshToken();
//
//        // Add new header to rejected request and retry it
//        return response.request().newBuilder()
//                .header(AUTHORIZATION, newAccessToken)
//                .build();
    }


}
