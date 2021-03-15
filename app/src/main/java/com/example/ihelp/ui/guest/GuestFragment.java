package com.example.ihelp.ui.guest;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ihelp.R;
import com.example.ihelp.ui.login.LoginActivity;

public class GuestFragment extends Fragment implements View.OnClickListener {
    //fragment view
    private View mView;
    //ui components
    private Button mBtnLogin;

    public GuestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_guest, container, false);
        initView();
        initOnClickLisnter();
        return mView;
    }

    private void initView(){
        mBtnLogin = mView.findViewById(R.id.fragment_guest_btn_login);
    }

    private void initOnClickLisnter(){
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_guest_btn_login:
                Intent intent = new Intent(mView.getContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}