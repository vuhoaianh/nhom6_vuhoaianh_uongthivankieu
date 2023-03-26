package com.example.data_masking_project.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class UserResponce implements Serializable {
        @SerializedName("id")
         private String id;
        @SerializedName("username")
        private String username;

        @SerializedName("password")
        private String password;

        @SerializedName("fullname")
        private String fullName;

        @SerializedName("gender")
        private boolean gender;

        @SerializedName("address")
        private String address;

        @SerializedName("birthday")
        private Timestamp birthday;

        @SerializedName("phone")
        private String phone;

        @SerializedName("email")
        private String email;

        @SerializedName("idCardNum")
        private String idCardNum;

        @SerializedName("bankNum")
        private String bankNum;

        @SerializedName("key")
        private String key;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
