package com.example.UserApi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.text.ParseException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSQLException(SQLException e)
    {
        return ResponseEntity.badRequest().body("Username is existed!.");
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<String> handleParseException(ParseException e)
    {
        return ResponseEntity.badRequest().body("Wrong birthday format!.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e)
    {
        return ResponseEntity.badRequest().body("Username is existed!.");
    }

}
