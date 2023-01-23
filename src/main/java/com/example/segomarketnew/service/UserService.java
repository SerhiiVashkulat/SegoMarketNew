package com.example.segomarketnew.service;

import com.example.segomarketnew.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean save(UserDto userDto);
}
