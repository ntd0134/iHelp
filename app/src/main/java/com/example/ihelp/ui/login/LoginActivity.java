package com.example.ihelp.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ihelp.R;
import com.example.ihelp.data.local.SharedPrefs;
import com.example.ihelp.data.model.request_object.LoginRequest;
import com.example.ihelp.data.model.response_object.LoginResponse;
import com.example.ihelp.ui.register.RegisterActivity;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {
    //ui components
    private Button mBtnLogin, mBtnRegister;
    private TextInputEditText mEdtEmail, mEdtPwd;
    private ImageView mBtnBack;
    //MVP fields
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initVar();
        initOnClickListener();
    }

    private void initView(){
        mBtnLogin = findViewById(R.id.activity_login_btn_login);
        mBtnRegister = findViewById(R.id.activity_login_btn_register);
        mEdtEmail = findViewById(R.id.activity_login_edt_email);
        mEdtPwd = findViewById(R.id.activity_login_edt_pwd);
        mBtnBack = findViewById(R.id.activity_login_btn_back);
    }

    private void initVar(){
        mLoginPresenter = new LoginPresenter(this.getApplicationContext(), this);
    }

    private void initOnClickListener(){
        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_login_btn_login:
                //get user info
                String email = mEdtEmail.getText().toString().trim();
                String pwd = mEdtPwd.getText().toString().trim();
                //init request obj
                LoginRequest requestObj = new LoginRequest(email, pwd);
                //authenticate method
                mLoginPresenter.authenticateUser(requestObj);
                break;
            case R.id.activity_login_btn_register:
                Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(it);
                break;
            case R.id.activity_login_btn_back:
                super.onBackPressed();
                break;
        }
    }

    @Override
    public void loginSuccess(LoginResponse responseObj) {
        //get save info
        String accessToken = responseObj.accessToken;
        String email = mEdtEmail.getText().toString().trim();
        //save info
        SharedPrefs.saveLoginInfo(this, accessToken, email);
        //redirect back to previous activity and self-destroy
        finish();
    }

    @Override
    public void loginFail(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }
}