package com.example.ihelp.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.ihelp.R;
import com.example.ihelp.ui.login.LoginActivity;
import com.example.ihelp.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new MyAsyncTask().execute();
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(SPLASH_DISPLAY_LENGTH);
            } catch (InterruptedException e) {
                Log.d(TAG, e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent;
            intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            super.onPostExecute(aVoid);
        }
    }
}