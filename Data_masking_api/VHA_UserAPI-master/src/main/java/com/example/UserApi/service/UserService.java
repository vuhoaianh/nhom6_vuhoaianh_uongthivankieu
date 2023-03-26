package com.example.UserApi.service;

import com.example.UserApi.dto.UserDto;
import com.example.UserApi.entity.User;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    List<User> getAllUserInformation();
    UserDto addUser(UserDto userDto) throws Exception;
    User getUserDetailByUsername(String username);
}
