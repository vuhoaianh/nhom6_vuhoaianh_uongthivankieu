package com.example.UserApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("birthday")
public class UserDto {
    private String username;
    private String password;
    private String fullname;
    private Boolean gender;
    private String address;
    private String birthdayStr;
    private String phone;
    private String email;
    private String idCardNum;
    private String bankNum;
    private Timestamp birthday;
}
