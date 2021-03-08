package com.example.ihelp.data.local;

import android.content.Context;

public class SharedPrefs {
    private static final String ACCESS_TOKEN = "accessToken";
    private static final String EMAIL = "email";

    public static void saveLoginInfo(Context context, String accessToken, String email){
        saveAccessToken(context, accessToken);
        saveEmail(context, email);
    }

    public static void removeRemoveUserInfo(Context context){
        removeAccessToken(context);
        removeEmail(context);
    }

    public static String getAccessToken(Context context){
        return SharedPreferencesAccessMethods.getStringSharedPreference(context, ACCESS_TOKEN);
    }

    public static void saveAccessToken(Context context, String value){
        SharedPreferencesAccessMethods.saveStringSharedPreference(context, ACCESS_TOKEN, value);
    }

    public static void removeAccessToken(Context context){
        SharedPreferencesAccessMethods.removeStringSharedPreference(context, ACCESS_TOKEN);
    }

    public static String getEmail(Context context){
        return SharedPreferencesAccessMethods.getStringSharedPreference(context, EMAIL);
    }

    public static void saveEmail(Context context, String value){
        SharedPreferencesAccessMethods.saveStringSharedPreference(context, EMAIL, value);
    }

    public static void removeEmail(Context context){
        SharedPreferencesAccessMethods.removeStringSharedPreference(context, EMAIL);
    }
}
