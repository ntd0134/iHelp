package com.example.ihelp.ui.event_create;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.ihelp.data.model.dtos.EventCategory;
import com.example.ihelp.data.model.dtos.Image;
import com.example.ihelp.data.model.request_object.AddEventRequest;
import com.example.ihelp.data.remote.CallBackData;
import com.example.ihelp.data.remote.category.EventCategoryRepository;
import com.example.ihelp.data.remote.category.EventCategoryRepositoryImpl;
import com.example.ihelp.data.remote.event.EventRepository;
import com.example.ihelp.data.remote.event.EventRepositoryImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CreateEventPresenter {
    private CreateEventView mCreateEventView;
    private EventCategoryRepository mEventCategoryRepository;
    private EventRepository mEventRepository;
    //Global variables
    private Uri mDownloadUrl;
    private boolean mErrorFound;
    private AddEventRequest mRequestObj;
    //Constants
    private final String TAG = this.getClass().getName();
    //firebase ref
    private StorageReference mStorageRef;

    public CreateEventPresenter(Context context, CreateEventView createEventView) {
        mCreateEventView = createEventView;
        mEventCategoryRepository = new EventCategoryRepositoryImpl(context);
        mEventRepository = new EventRepositoryImpl(context);
        mStorageRef = FirebaseStorage.getInstance().getReference("images");
    }

    public void getEventCategories(){
        mEventCategoryRepository.eventCategoryFindAll(new CallBackData<List<EventCategory>>() {
            @Override
            public void onSuccess(List<EventCategory> eventCategories) {
                mCreateEventView.onEventCategoriesLoadSuccess(eventCategories);
            }

            @Override
            public void onFail(String message) {
                mCreateEventView.onEventCategoriesLoadFail(message);
            }
        });
    }

    public void addNewEvent(AddEventRequest requestObj, Uri imageUri, String imageExtension){
        //assign requestObj
        mRequestObj = requestObj;
        //check inputs
        checkInputValues(imageUri);
        //if error found stop
        if(mErrorFound){
            return;
        }
        //continue if no error found
        //upload image
        uploadImage(imageUri, imageExtension);
    }

    private void createEvent(){
        //init new Image after image is uploaded to firebase Storage
        Image coverPhoto = new Image("cover", mDownloadUrl.toString());
        List<Image> images = new ArrayList<>();
        images.add(coverPhoto);
        mRequestObj.setImages(images);
        //call add event api
        mEventRepository.eventAddEvent(mRequestObj, new CallBackData<String>() {
            @Override
            public void onSuccess(String s) {
                mCreateEventView.onAddEventSuccess(s);
            }

            @Override
            public void onFail(String message) {
                mCreateEventView.onAddEventFail(message);
            }
        });
    }

    private void checkInputValues(Uri imageUri){
        mErrorFound = false;
        checkImage(imageUri);
        checkCategory(mRequestObj.getCategoryId());
        checkLocation(mRequestObj.getLocation());
        checkDate(mRequestObj.getStartDate(), mRequestObj.getEndDate());
        checkPoint(mRequestObj.getPoint());
        checkQuota(mRequestObj.getQuota());
        checkDescription(mRequestObj.getDescription());
        checkTitle(mRequestObj.getTitle());
    }

    private void checkTitle(String title){
        if(title.isEmpty()){
            mCreateEventView.onEdtTitleError("Please fill in event title!");
            mErrorFound = true;
        }
    }

    private void checkDescription(String description){
        if(description.isEmpty()){
            mCreateEventView.onEdtDescriptionError("Please fill in event description!");
            mErrorFound = true;
        }
    }

    private void checkQuota(int quota){
        if(quota == 0 || quota == -1){
            mCreateEventView.onParticipantLimitError("Please choose a participant limit!");
            mErrorFound = true;
        }else if(quota < 0){
            mCreateEventView.onParticipantLimitError("Invalid participant limit!");
            mErrorFound = true;
        }
    }

    private void checkPoint(int point){
        if(point == 0 || point == -1){
            mCreateEventView.onPointError("Please select given point!");
            mErrorFound = true;
        }else if(point < 0){
            mCreateEventView.onPointError("Invalid point!");
            mErrorFound = true;
        }
    }

    private void checkDate(Long startDate, Long endDate){
        if(startDate == 0L || endDate == 0L){
            mCreateEventView.onDateError("Please select date range");
            mErrorFound = true;
        }
    }

    private void checkLocation(String location){
        if(location.isEmpty()){
            mCreateEventView.onLocationError("Please fill in event location");
            mErrorFound = true;
        }
    }

    private void checkCategory(int categoryId){
        if(categoryId == -1){
            mCreateEventView.onCategoryError("Please select an event category!");
            mErrorFound = true;
        }
    }

    private void checkImage(Uri imageUri){
        if(Uri.EMPTY.equals(imageUri)){
            mCreateEventView.onCoverPhotoError("Please select a cover photo!");
            mErrorFound = true;
        }
    }



    //upload image to firebase
    private void uploadImage(Uri imageUri, String imageExtension){
        //check if an image is selected
        if(imageUri != null){
            //set upload image name
            String uploadFileName = System.currentTimeMillis() + "." + imageExtension;
            //append upload image name to store ref
            StorageReference fileRef = mStorageRef.child(uploadFileName);
            //upload image to firebase storage
            fileRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    //get image download url
                    mStorageRef.child(uploadFileName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //assign image's download url
                            mDownloadUrl = uri;
                            //call create event api
                            createEvent();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG, e.getMessage());
                        }
                    });
                }
            });
        }
    }


}
