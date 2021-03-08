package com.example.ihelp.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferencesAccessMethods {
    private static final String TAG = SharedPreferencesAccessMethods.class.getName();

    public static void saveStringSharedPreference(Context context, String key, String value) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(
                    context.getPackageName(), context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(key, value);
            editor.commit();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public static String getStringSharedPreference(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        String defaultValue = "";
        return prefs.getString(key, defaultValue);
    }

    public static void removeStringSharedPreference(Context context, String key) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(
                    context.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(key);
            editor.commit();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public static void saveIntSharedPreference(Context context, String key, int value) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(
                    context.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(key, value);
            editor.commit();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public static int getIntSharedPreference(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        int defaultValue = 0;
        return prefs.getInt(key, defaultValue);
    }

    public static void removeIntSharedPreference(Context context, String key) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(
                    context.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(key);
            editor.commit();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public static void saveBooleanSharedPreference(Context context, String key, boolean value) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(
                    context.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(key, value);
            editor.commit();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public static boolean getBooleanSharedPreference(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        boolean defaultValue = false;
        return prefs.getBoolean(key, defaultValue);
    }

    public static void removeBooleanSharedPreference(Context context, String key) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(
                    context.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(key);
            editor.commit();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }


}
