package com.personal.housework.DTO;

import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HouseWork {

    @SerializedName("house_id") private Integer house_id;
    @SerializedName("house_name") private String house_name;
    @SerializedName("house_desc") private String house_desc;
    @SerializedName("cate_id") private Integer cate_id;

    public HouseWork(Integer house_id, String house_name, String house_desc, Integer cate_id) {
        this.house_id = house_id;
        this.house_name = house_name;
        this.house_desc = house_desc;
        this.cate_id = cate_id;
    }

    public Integer getHouse_id() {
        return house_id;
    }

    public void setHouse_id(Integer house_id) {
        this.house_id = house_id;
    }

    public String getHouse_name() {
        return house_name;
    }

    public void setHouse_name(String house_name) {
        this.house_name = house_name;
    }

    public String getHouse_desc() {
        return house_desc;
    }

    public void setHouse_desc(String house_desc) {
        this.house_desc = house_desc;
    }

    public Integer getCate_id() {
        return cate_id;
    }

    public void setCate_id(Integer cate_id) {
        this.cate_id = cate_id;
    }
}
