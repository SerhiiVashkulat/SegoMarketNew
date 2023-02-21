package com.example.segomarketnew.service;

import com.example.segomarketnew.domain.model.User;


import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
    void addUserManager(User user);
    List<User> getAll();
    void updateUserEmailOrPassword(User user);
}
