package com.example.data_masking_project.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {
    @SerializedName("isValid")
    boolean isvalid;

    @SerializedName("key")
    String key;

    @SerializedName("userList")
    List<UserResponce> userList;

    public LoginResponse(boolean isvalid, String key, List<UserResponce> userList) {
        this.isvalid = isvalid;
        this.key = key;
        this.userList = userList;
    }

    public boolean isIsvalid() {
        return isvalid;
    }

    public void setIsvalid(boolean isvalid) {
        this.isvalid = isvalid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<UserResponce> getUserList() {
        return userList;
    }

    public void setUserList(List<UserResponce> userList) {
        this.userList = userList;
    }
}
