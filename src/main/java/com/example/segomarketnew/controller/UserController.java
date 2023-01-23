package com.example.segomarketnew.controller;

import com.example.segomarketnew.dto.UserDto;
import com.example.segomarketnew.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new UserDto());
        return "user";
    }

    @PostMapping("/new")
    public String newUser(UserDto userDto, Model model){
        if (userService.save(userDto))return "redirect:/";
        else model.addAttribute("user", userDto);
        return "user";
    }
}
