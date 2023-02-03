package com.example.segomarketnew.service;

import com.example.segomarketnew.domain.model.User;
import com.example.segomarketnew.dto.UserDto;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    List<User> getAll();
    void updateUserEmailOrPassword(User user);
}
