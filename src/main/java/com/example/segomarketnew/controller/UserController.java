package com.example.segomarketnew.controller;


import com.example.segomarketnew.domain.response.Response;
import com.example.segomarketnew.dto.UserDto;
import com.example.segomarketnew.mapper.UserMapper;
import com.example.segomarketnew.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @PostMapping("/manager")//add manager can only user with role admin
    private ResponseEntity<Response> addManager(@RequestBody UserDto userDto,Principal principal){

        userService.addUserManager(userMapper.toUser(userDto));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
