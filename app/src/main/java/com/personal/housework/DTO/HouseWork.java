package com.personal.housework.DTO;

import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HouseWork {

    @SerializedName("cloth_id") private Integer cloth_id;
    @SerializedName("cloth_name") private String cloth_name;
    @SerializedName("cloth_photo_path") private String cloth_photo_path;
    @SerializedName("cloth_desc") private String cloth_desc;
    @SerializedName("user_id") private Integer user_id;
    @SerializedName("category_id") private Integer category_id;

    @Expose
    @SerializedName("success")
    private Boolean success;
    @Expose
    @SerializedName("message")
    private String message;

    public HouseWork(Integer cloth_id, String cloth_name, String cloth_photo_path, String cloth_desc, Integer user_id, Integer category_id) {
        this.cloth_id = cloth_id;
        this.cloth_name = cloth_name;
        this.cloth_photo_path = cloth_photo_path;
        this.cloth_desc = cloth_desc;
        this.user_id = user_id;
        this.category_id = category_id;
    }

    public Integer getCloth_id() {
        return cloth_id;
    }

    public void setCloth_id(Integer cloth_id) {
        this.cloth_id = cloth_id;
    }

    public String getCloth_name() {
        return cloth_name;
    }

    public void setCloth_name(String cloth_name) {
        this.cloth_name = cloth_name;
    }

    public String getCloth_photo_path() {
        return cloth_photo_path;
    }

    public void setCloth_photo_path(String cloth_photo_path) {
        this.cloth_photo_path = cloth_photo_path;
    }

    public String getCloth_desc() {
        return cloth_desc;
    }

    public void setCloth_desc(String cloth_desc) {
        this.cloth_desc = cloth_desc;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
