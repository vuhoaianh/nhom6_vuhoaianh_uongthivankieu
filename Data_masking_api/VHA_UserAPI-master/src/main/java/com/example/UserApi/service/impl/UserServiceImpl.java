package com.example.UserApi.service.impl;

import com.example.UserApi.config.security.UserDetailsServiceImpl;
import com.example.UserApi.dto.UserDto;
import com.example.UserApi.entity.User;
import com.example.UserApi.repository.UserRepository;
import com.example.UserApi.service.UserService;
import com.example.UserApi.util.AESUtil;
import com.example.UserApi.util.GenerateKey;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public List<User> getAllUserInformation() {
        return userRepo.findAll().stream().map(user -> {
                    if (!user.getUsername().equals(userDetailsService.getCurrentUsername()))
                        return user;
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto addUser(UserDto userDto) throws Exception {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = mapper.map(userDto, User.class);
        if (userDto.getBirthdayStr() != null)
            user.setBirthday(toTimestamp(userDto.getBirthdayStr()));
        user.setKey(GenerateKey.generateRandomKey());
        if (user.getPhone() != null)
            user.setPhone(AESUtil.encrypt(user.getPhone(), user.getKey()));
        if (user.getBankNum() != null)
            user.setBankNum(AESUtil.encrypt(user.getBankNum(), user.getKey()));
        if (user.getIdCardNum() != null)
            user.setIdCardNum(AESUtil.encrypt(user.getIdCardNum(), user.getKey()));
        userRepo.save(user);
        return userDto;
    }

    @Override
    public User getUserDetailByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    private Timestamp toTimestamp(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date parsedDate = dateFormat.parse(dateStr);
        return new Timestamp(parsedDate.getTime());
    }

//    private String getRandomKey() {
//        int length = 16;
//        boolean useLetters = true;
//        boolean useNumbers = true;
//        return RandomStringUtils.random(length, useLetters, useNumbers);
//    }
}
