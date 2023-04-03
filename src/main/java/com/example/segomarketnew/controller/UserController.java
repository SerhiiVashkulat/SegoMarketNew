package com.example.segomarketnew.controller;



import com.example.segomarketnew.domain.model.User;
import com.example.segomarketnew.domain.response.Response;
import com.example.segomarketnew.dto.UserDto;
import com.example.segomarketnew.mapper.UserMapper;
import com.example.segomarketnew.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper = UserMapper.mapper;


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> allUser(){
        log.info("START endpoint  allUsers");
        return new ResponseEntity<>(userMapper.toListUserDto(userService.getAll()),HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('CLIENT')")
    @PutMapping()
    public ResponseEntity<Response> updateProfile
            (@RequestBody @Valid UserDto userDto, Principal principal){
        log.info("START endpoint  updateProfile, request:{}", userDto);
            userDto.setName(principal.getName());
            userService.updateUserEmailOrPassword(userMapper.toUser(userDto));
        return new ResponseEntity<>(Response.builder()
                .massage("You have changed your profile")
                .build(),HttpStatus.OK);
    }
@PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping
    public ResponseEntity<Response> addManager(@RequestBody @Valid UserDto userDto){

        userService.addUserManager(userMapper.toUser(userDto));
        return new ResponseEntity<>(Response.builder()
                .massage("Add manager " + userDto.getName() + " to shop ")
                .build(), HttpStatus.CREATED);
  }
}
