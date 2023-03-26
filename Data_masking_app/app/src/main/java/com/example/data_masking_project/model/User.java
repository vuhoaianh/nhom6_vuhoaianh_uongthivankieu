package com.example.data_masking_project.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
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

        @SerializedName("birthdayStr")
        private String birthday;

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

    public User(String id, String username, String password, String fullName, boolean gender, String address, String birthday, String phone, String email, String idCardNum, String bankNum, String key) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
        this.address = address;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.idCardNum = idCardNum;
        this.bankNum = bankNum;
        this.key = key;
    }

    public User(String username, String password, String fullName, boolean gender, String address, String birthday, String phone, String email, String idCardNum, String bankNum) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
        this.address = address;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.idCardNum = idCardNum;
        this.bankNum = bankNum;
    }



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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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
