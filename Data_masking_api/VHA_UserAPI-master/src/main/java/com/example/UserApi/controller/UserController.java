package com.example.UserApi.controller;

import com.example.UserApi.dto.LoginDto;
import com.example.UserApi.dto.ResponseData;
import com.example.UserApi.dto.UserDto;
import com.example.UserApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping({"/user/detail", "/user/detail/{username}"})
    public ResponseEntity getUserInformation(@PathVariable(value = "username", required = false) String username) {
        if (username == null)
            return ResponseEntity.ok(userService.getAllUserInformation());
        else
            return ResponseEntity.ok(userService.getUserDetailByUsername(username));
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData> authenticateUser(@RequestBody LoginDto loginDto) {
        try
        {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(new ResponseData(true,
                    userService.getUserDetailByUsername(loginDto.getUsername()).getKey(),
                    userService.getAllUserInformation()));
        }
        catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().body(new ResponseData(false, null, null));
        }
    }
}
