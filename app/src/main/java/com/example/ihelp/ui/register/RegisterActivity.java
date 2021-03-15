package com.example.ihelp.ui.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ihelp.R;
import com.example.ihelp.data.local.SharedPrefs;
import com.example.ihelp.data.model.enums.Gender;
import com.example.ihelp.data.model.request_object.LoginRequest;
import com.example.ihelp.data.model.request_object.SignUpRequest;
import com.example.ihelp.data.model.response_object.LoginResponse;
import com.example.ihelp.ui.main.MainActivity;
import com.example.ihelp.util.DateConverter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity implements RegisterView, View.OnClickListener, AdapterView.OnItemSelectedListener {
    //ui components
    private TextInputEditText mEdtEmail, mEdtPwd, mEdtFullname, mEdtPhone;
    private Spinner mSpnGender;
    private TextView mTxtBirthDate;
    private MaterialButton mBtnRegister;
    private ImageView mBtnBack;
    //global variables
    private Gender mGender;
    private Long mBirthDate;
    private RegisterPresenter mRegisterPresenter;
    //Constants
    private final String DATE_PICKER = "DatePicker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initVar();
        initOnClickListener();
    }

    private void initView(){
        mEdtEmail = findViewById(R.id.activity_register_edt_email);
        mEdtPwd = findViewById(R.id.activity_register_edt_password);
        mEdtFullname = findViewById(R.id.activity_register_edt_fullname);
        mEdtPhone = findViewById(R.id.activity_register_edt_phone);
        mSpnGender = findViewById(R.id.activity_register_spn_gender);
        mTxtBirthDate = findViewById(R.id.activity_register_txt_birthdate);
        mBtnRegister = findViewById(R.id.activity_register_btn_register);
        mBtnBack = findViewById(R.id.activity_register_btn_back);
        //init spinner
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spn_gender_data, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnGender.setAdapter(spinnerAdapter);
    }

    private void initVar(){
        mRegisterPresenter = new RegisterPresenter(this.getApplicationContext(), this);
        mBirthDate = 0L;
    }

    private MaterialDatePicker initDatePicker(){
        //init builder
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        //set select to input
        builder.setInputMode(MaterialDatePicker.INPUT_MODE_TEXT);
        //set title
        builder.setTitleText("Date of birth");
        //init datepicker
        MaterialDatePicker materialDatePicker = builder.build();
        //onClick when click OK
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                mBirthDate = Long.parseLong(selection.toString());
                String birthDate = DateConverter.convertToString(mBirthDate);
                mTxtBirthDate.setText(birthDate);
            }
        });
        return materialDatePicker;
    }

    private void initOnClickListener(){
        mBtnRegister.setOnClickListener(this);
        mTxtBirthDate.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);
        mSpnGender.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_register_btn_register:
                //variables
                String email, pwd, fullName, phone;
                long birthDate;
                boolean gender = true;
                //get values
                email = mEdtEmail.getText().toString().trim();
                pwd = mEdtPwd.getText().toString().trim();
                fullName = mEdtFullname.getText().toString().trim();
                phone = mEdtPhone.getText().toString().trim();
                birthDate = mBirthDate;
                switch (mGender){
                    case FEMALE:
                        gender = false;
                        break;
                    case MALE:
                        gender = true;
                        break;
                }
                //init dto
                SignUpRequest requestObj = new SignUpRequest(email, pwd, fullName, phone, birthDate, gender);
                //register new account
                mRegisterPresenter.registerNewAccount(requestObj);
                break;
            case R.id.activity_register_txt_birthdate:
                MaterialDatePicker materialDatePicker = initDatePicker();
                materialDatePicker.show(getSupportFragmentManager(), DATE_PICKER);
                break;
            case R.id.activity_register_btn_back:
                super.onBackPressed();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //on spinner gender item selected
        String text = parent.getItemAtPosition(position).toString();
        switch (text){
            case "Male":
                mGender = Gender.MALE;
                break;
            case "Female":
                mGender = Gender.FEMALE;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing
    }

    @Override
    public void onRegisterSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        //login after account is created
        //get email, password
        String email = mEdtEmail.getText().toString().trim();
        String password = mEdtPwd.getText().toString().trim();
        //init login obj
        LoginRequest loginObj = new LoginRequest(email, password);
        //authenticate user
        mRegisterPresenter.login(loginObj);
    }

    @Override
    public void onRegisterFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginSuccess(LoginResponse responseObj) {
        //get save info
        String accessToken = responseObj.accessToken;
        String email = mEdtEmail.getText().toString().trim();
        //save info
        SharedPrefs.saveLoginInfo(this, accessToken, email);
        //redirect back to main activity and clear all previous activities
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onLoginFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}