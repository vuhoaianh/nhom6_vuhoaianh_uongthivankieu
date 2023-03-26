package com.example.UserApi.dto;

import com.example.UserApi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {
    private Boolean isValid;
    private String key;
    private List<User> userList;
}
