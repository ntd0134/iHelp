package com.example.ihelp.data.model.request_object;

import com.example.ihelp.data.model.dtos.Image;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddEventRequest {
    @SerializedName("id")
    private String id;
    @SerializedName("authorEmail")
    private String authorEmail;
    @SerializedName("categoryId")
    private Integer categoryId;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("images")
    private List<Image> images;
    @SerializedName("startDate")
    private Long startDate;
    @SerializedName("endDate")
    private Long endDate;
    @SerializedName("location")
    private String location;
    @SerializedName("onsite")
    private Boolean onsite;
    @SerializedName("point")
    private Integer point;
    @SerializedName("quota")
    private Integer quota;
    @SerializedName("statusId")
    private Integer statusId;

    public AddEventRequest() {
    }

    public AddEventRequest(String authorEmail, Integer categoryId, String title, String description,
                           List<Image> images, Long startDate, Long endDate, String location,
                           Boolean onsite, Integer point, Integer quota, Integer statusId) {
        this.id = "";
        this.authorEmail = authorEmail;
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.images = images;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.onsite = onsite;
        this.point = point;
        this.quota = quota;
        this.statusId = statusId;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getOnsite() {
        return onsite;
    }

    public void setOnsite(Boolean onsite) {
        this.onsite = onsite;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
}
