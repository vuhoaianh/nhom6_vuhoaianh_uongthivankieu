package com.example.data_masking_project.model;

public class DataSave {
    private String inputKey;
    private String maskPhone;
    private String maskBankNum;
    private String maskCardID;

    public DataSave(String inputKey, String maskPhone, String maskBankNum, String maskCardID) {
        this.inputKey = inputKey;
        this.maskPhone = maskPhone;
        this.maskBankNum = maskBankNum;
        this.maskCardID = maskCardID;
    }

    public String getInputKey() {
        return inputKey;
    }

    public void setInputKey(String inputKey) {
        this.inputKey = inputKey;
    }

    public String getMaskPhone() {
        return maskPhone;
    }

    public void setMaskPhone(String maskPhone) {
        this.maskPhone = maskPhone;
    }

    public String getMaskBankNum() {
        return maskBankNum;
    }

    public void setMaskBankNum(String maskBankNum) {
        this.maskBankNum = maskBankNum;
    }

    public String getMaskCardID() {
        return maskCardID;
    }

    public void setMaskCardID(String maskCardID) {
        this.maskCardID = maskCardID;
    }
}
