package com.example.UserApi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "DBUser")
public class User {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Username", unique = true, nullable = false)
    private String username;
    @Column(name = "Password", nullable = false)
    private String password;
    @Column(name = "Fullname")
    private String fullname;
    @Column(name = "Gender", nullable = false)
    private Boolean gender;
    @Column(name = "Address")
    private String address;
    @Column(name = "Birthday")
    private Timestamp birthday;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Email")
    private String email;
    @Column(name = "IDCardNum")
    private String idCardNum;
    @Column(name = "bankNum")
    private String bankNum;
    @Column(name = "UserKey")
    private String key;
}
