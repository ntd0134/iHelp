package com.example.ihelp.ui.event_create;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ihelp.R;
import com.example.ihelp.data.local.SharedPrefs;
import com.example.ihelp.data.model.dtos.EventCategory;
import com.example.ihelp.data.model.dtos.Image;
import com.example.ihelp.data.model.enums.EventType;
import com.example.ihelp.data.model.request_object.AddEventRequest;
import com.example.ihelp.ui.main.MainActivity;
import com.example.ihelp.util.DateConverter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CreateEventActivity extends AppCompatActivity implements CreateEventView, View.OnClickListener, AdapterView.OnItemSelectedListener, MaterialPickerOnPositiveButtonClickListener {
    //ui components
    private ChipGroup mChipGroup;
    private TextInputEditText mEdtTitle, mEdtDescription, mEdtQuota, mEdtPoint, mEdtLocation;
    private Spinner mSpnType;
    private TextView mTxtDate;
    private ImageView mBtnBack;
    private MaterialButton mBtnCreate;
    private FrameLayout mBtnAddCover;
    private ImageView mImgCover;
    private TextView mTxtCategory;
    private TextView mTxtCoverPhoto;
    private LinearLayout mLoutLocation;
    //global variables
    private EventType mEventType;
    private Long mStartDate;
    private Long mEndDate;
    private List<EventCategory> mCategories;
    private Uri mImageUri;
    //MVP
    private CreateEventPresenter mCreateEventPresenter;
    //Constants
    private final String DATE_PICKER = "DatePicker";
    private final Integer PICK_IMAGE_REQUEST = 1;
    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        initVar();
        initView();
        initOnClickListener();
    }

    private void initView() {
        mChipGroup = findViewById(R.id.activity_create_event_chip_group);
        mEdtTitle = findViewById(R.id.activity_create_event_edt_title);
        mEdtDescription = findViewById(R.id.activity_create_event_edt_description);
        mEdtQuota = findViewById(R.id.activity_create_event_edt_quota);
        mEdtPoint = findViewById(R.id.activity_create_event_edt_point);
        mEdtLocation = findViewById(R.id.activity_create_event_edt_location);
        mSpnType = findViewById(R.id.activity_create_event_spn_type);
        mTxtDate = findViewById(R.id.activity_create_event_txt_date);
        mBtnBack = findViewById(R.id.activity_create_event_btn_back);
        mBtnCreate = findViewById(R.id.activity_create_event_btn_create);
        mBtnAddCover = findViewById(R.id.activity_create_event_btn_add_cover);
        mImgCover = findViewById(R.id.activity_create_event_img_cover);
        mTxtCategory = findViewById(R.id.activity_create_event_txt_category);
        mTxtCoverPhoto = findViewById(R.id.activity_create_event_txt_cover_photo);
        mLoutLocation = findViewById(R.id.activity_create_event_lout_location);
        //init spinner
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spn_event_type_data, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnType.setAdapter(spinnerAdapter);
        //retrieve categories
        mCreateEventPresenter.getEventCategories();
    }

    private void initVar(){
        mCreateEventPresenter = new CreateEventPresenter(this.getApplicationContext(), this);
        mStartDate = 0L;
        mEndDate = 0L;
    }

    private void initOnClickListener() {
        mSpnType.setOnItemSelectedListener(this);
        mTxtDate.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);
        mBtnCreate.setOnClickListener(this);
        mBtnAddCover.setOnClickListener(this);
    }

    //create category chips on ui
    private void setupCategoryChip(List<EventCategory> categories) {
        for (EventCategory category : categories) {
            //a chip is a layout contain category name
            //create new chip for every category
            Chip mChip = (Chip) this.getLayoutInflater().inflate(R.layout.item_category_chip, null, false);
            //set category name
            mChip.setText(category.getName());
            //some ui properties
            int paddingDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 10,
                    getResources().getDisplayMetrics()
            );
            mChip.setPadding(paddingDp, 0, paddingDp, 0);
            //add chip to chip group
            mChipGroup.addView(mChip);
        }
    }

    //init date picker
    private MaterialDatePicker initDatePicker() {
        //init builder
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        //set title
        builder.setTitleText("Date of birth");
        //init datepicker
        MaterialDatePicker materialDatePicker = builder.build();
        //onClick when click OK
        materialDatePicker.addOnPositiveButtonClickListener(this);
        //return back datepicker
        return materialDatePicker;
    }

    //function return back id of selected category
    private int getSelectedCategory() {
        int selectedId = -1;
        //get checked chip id
        int checkedChipId = mChipGroup.getCheckedChipId();
        //check if any chip is selected
        if (checkedChipId != -1) {
            //get selected chip
            Chip checkedChip = mChipGroup.findViewById(checkedChipId);
            //get category name
            String categoryName = checkedChip.getText().toString();
            //search through fetched category list
            for (EventCategory category : mCategories) {
                if (categoryName.equals(category.getName())) {
                    //set selected category id
                    selectedId = category.getId();
                }
            }
        }
        return selectedId;
    }

    //open image picker
    private void openFileChoose(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    //get Image extension
    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    //create new event
    private void addNewEvent() {
        String authorEmail, description, location, title;
        Integer categoryId, point, quota, statusId;
        Long endDate, startDate;
        Boolean onsite = true;
        List<Image> images = new ArrayList<>();
        //get values
        authorEmail = SharedPrefs.getEmail(getApplicationContext());
        title = mEdtTitle.getText().toString().trim();
        description = mEdtDescription.getText().toString().trim();
        location = mEdtLocation.getText().toString().trim();
        categoryId = getSelectedCategory();
        try{
            point = Integer.parseInt(mEdtPoint.getText().toString().trim());
        }catch(NumberFormatException e){
            //-1 for no number was inputted
            point = -1;
        }
        try{
            quota = Integer.parseInt(mEdtQuota.getText().toString().trim());
        }catch(NumberFormatException e){
            //-1 for no number was inputted
            quota = -1;
        }
        //default status is 2 (2 is pending)
        statusId = 2;
        endDate = mEndDate;
        startDate = mStartDate;
        switch (mEventType){
            case ONSITE:
                onsite = true;
                break;
            case ONLINE:
                onsite = false;
                break;
        }
        //get image extension
        String imageExtension;
        try{
            imageExtension = getFileExtension(mImageUri);
        }catch (NullPointerException e){
            imageExtension = "";
        }
        //init new request obj
        AddEventRequest requestObj = new AddEventRequest(authorEmail, categoryId, title, description,
                images, startDate, endDate, location, onsite, point, quota, statusId);
        //call create event function
        mCreateEventPresenter.addNewEvent(requestObj, mImageUri, imageExtension);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_create_event_txt_date:
                //show calendar
                MaterialDatePicker materialDatePicker = initDatePicker();
                materialDatePicker.show(getSupportFragmentManager(), DATE_PICKER);
                break;
            case R.id.activity_create_event_btn_back:
                super.onBackPressed();
                break;
            case R.id.activity_create_event_btn_create:
                addNewEvent();
                break;
            case R.id.activity_create_event_btn_add_cover:
                openFileChoose();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //check if this was result from pick image request and check if the result is success
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            //get image uri
            mImageUri = data.getData();
            //load selected image on ui
            Picasso.get().load(mImageUri).into(mImgCover);
        }
    }

    @Override
    //function trigger when an item in Spinner is selected
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        switch (text) {
            case "Onsite":
                mEventType = EventType.ONSITE;
                mLoutLocation.setVisibility(View.VISIBLE);
                break;
            case "Online":
                mEventType = EventType.ONLINE;
                mLoutLocation.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    //function trigger when click "OK" button in calendar
    public void onPositiveButtonClick(Object selection) {
        Pair<Long, Long> datePair = (Pair) selection;
        mStartDate = datePair.first;
        mEndDate = datePair.second;
        String date = DateConverter.convertToString(mStartDate) + " - " + DateConverter.convertToString(mEndDate);
        mTxtDate.setText(date);
    }

    @Override
    //required function for implementing Spinner onItemSelect
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing
    }

    @Override
    public void onEventCategoriesLoadSuccess(List<EventCategory> eventCategories) {
        mCategories = eventCategories;
        setupCategoryChip(mCategories);
    }

    @Override
    public void onEventCategoriesLoadFail(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddEventSuccess(String successMsg) {
        Toast.makeText(this, successMsg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CreateEventActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAddEventFail(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEdtTitleError(String errorMsg) {
        mEdtTitle.setError(errorMsg);
        mEdtTitle.requestFocus();
    }

    @Override
    public void onEdtDescriptionError(String errorMsg) {
        mEdtDescription.setError(errorMsg);
        mEdtDescription.requestFocus();
    }

    @Override
    public void onParticipantLimitError(String errorMsg) {
        mEdtQuota.setError(errorMsg);
        mEdtQuota.requestFocus();
    }

    @Override
    public void onPointError(String errorMsg) {
        mEdtPoint.setError(errorMsg);
        mEdtPoint.requestFocus();
    }

    @Override
    public void onDateError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationError(String errorMsg) {
        mEdtLocation.setError(errorMsg);
        mEdtLocation.requestFocus();
    }

    @Override
    public void onCategoryError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCoverPhotoError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }
}