package com.example.segomarketnew.controller;


import com.example.segomarketnew.domain.response.Response;
import com.example.segomarketnew.dto.BucketDto;
import com.example.segomarketnew.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.security.Principal;

@RestController
@RequestMapping("/bucket")
@RequiredArgsConstructor
public class BucketController {

    private final BucketService bucketService;
    @GetMapping
    ResponseEntity<BucketDto> infoBucket( @NotNull Principal principal) {

        if (principal == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BucketDto bucketDto = bucketService.getBucketByUser(principal.getName());

        return new ResponseEntity<>(bucketDto,HttpStatus.OK);
    }

    @DeleteMapping("/all")
    ResponseEntity<Response> deleteAllFromBucket(Principal principal){
        bucketService.deleteAllProductFromBucket(principal.getName());
        return new ResponseEntity<>(Response.builder()
                .massage("Delete all product from bucket " + principal.getName())
                .build()
                ,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Response> deleteProductFromBucket(@PathVariable Long id, Principal principal){
        bucketService.deleteProductByIdFromBucket(id, principal.getName());
        return new ResponseEntity<>(Response.builder()
                .massage("Delete " + id + " product from bucket " + principal.getName())
                .build()
                ,HttpStatus.OK);
    }
}
