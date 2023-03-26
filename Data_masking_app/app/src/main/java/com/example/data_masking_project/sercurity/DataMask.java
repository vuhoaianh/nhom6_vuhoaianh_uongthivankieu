package com.example.data_masking_project.sercurity;

import com.example.data_masking_project.model.DataSave;

import java.util.List;

public class DataMask {
    public static String maskIdCard(String creditCard) {
        // Replace all characters in password except the first and last 2 characters with asterisks
        if (creditCard == null || creditCard.length() < 4) {
            // Password is too short to mask
            return creditCard;
        }
        String firstTwoChars = creditCard.substring(0, 2);
        String lastTwoChars = creditCard.substring(creditCard.length() - 2);
        String mask = new String(new char[creditCard.length() - 4]).replace('\0', '*');
        return firstTwoChars + mask + lastTwoChars;
    }

    public static String maskBankNum(String bankNum) {
        // Replace all characters in password except the first and last 2 characters with asterisks
        if (bankNum == null || bankNum.length() < 8) {
            // Password is too short to mask
            return bankNum;
        }
        String firstTwoChars = bankNum.substring(0, 4);
        String lastTwoChars = bankNum.substring(bankNum.length() - 4);
        String mask = "-XXXX-XXXX-";
        return firstTwoChars + mask + lastTwoChars;
    }



    public static String maskDob(String dob) {
        if (dob == null || dob.length() < 10) {
            // Password is too short to mask
            return dob;
        }
        String[] parts = dob.split("/"); // Tách ngày, tháng, năm bằng ký tự '/'
        return "**/**/" + parts[2];
    }

    public static String maskPhoneNumber(String phoneNumber)
    {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            // Password is too short to mask
            return phoneNumber;
        }
        String lastFourChars = phoneNumber.substring(phoneNumber.length() - 4);
        String mask = new String(new char[phoneNumber.length() - 4]).replace('\0', '*');
        return  mask + lastFourChars;
    }

    public static DataSave findByInputKey(String inputKey, List<DataSave> dataList) {
        for (DataSave data : dataList) {
            if (data.getInputKey().equals(inputKey)) {
                return data;
            }
        }
        return null;
    }
}
