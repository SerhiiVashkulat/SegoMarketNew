package com.example.segomarketnew.controller;

import com.example.segomarketnew.dto.UserDto;
import com.example.segomarketnew.mapper.UserMapper;
import com.example.segomarketnew.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper = UserMapper.mapper;
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUser(){

        return new ResponseEntity<>(userMapper.toListUserDto(userService.getAll()),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateProfile
            (@RequestBody UserDto userDto){

        userService.updateUserEmailOrPassword(userMapper.toUser(userDto));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
