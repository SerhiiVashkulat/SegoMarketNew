package com.example.segomarketnew.controller;


import com.example.segomarketnew.dto.BucketDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bucket")
public class BucketController {


    @GetMapping
    ResponseEntity<BucketDto> infoBucket() {
        return null;
    }

}
