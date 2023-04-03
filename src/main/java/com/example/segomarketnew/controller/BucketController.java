package com.example.segomarketnew.controller;


import com.example.segomarketnew.domain.model.Order;
import com.example.segomarketnew.domain.response.Response;
import com.example.segomarketnew.dto.BucketDto;
import com.example.segomarketnew.service.BucketService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/bucket")
@RequiredArgsConstructor
@Slf4j
public class BucketController {

    private final BucketService bucketService;
    @GetMapping
    ResponseEntity<BucketDto> infoBucket( @NotNull Principal principal) {
        log.info("START endpoint infoBucket, request:{} ", principal.getName());
        if (principal == null){
            log.error("User:{} ", principal.getName(),"not authenticated");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BucketDto bucketDto = bucketService.getBucketByUser(principal.getName());
        log.info("END endpoint infoBucket , response: {}", bucketDto);
        return new ResponseEntity<>(bucketDto,HttpStatus.OK);
    }

    @DeleteMapping("/all")
    ResponseEntity<Response> deleteAllFromBucket(Principal principal){
        log.info("START endpoint deleteAllFromBucket, request:{} ", principal.getName());
        bucketService.deleteAllProductFromBucket(principal.getName());
        return new ResponseEntity<>(Response.builder()
                .massage("Delete all product from bucket " + principal.getName())
                .build()
                ,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Response> deleteProductFromBucket(@PathVariable Long id, Principal principal){
        log.info("START endpoint deleteAllFromBucket by id:{}, request:{} ",id, principal.getName());
        bucketService.deleteProductByIdFromBucket(id, principal.getName());
        return new ResponseEntity<>(Response.builder()
                .massage("Delete product from bucket " + principal.getName())
                .build()
                ,HttpStatus.OK);
    }
    @PostMapping()

    ResponseEntity<Response> commitBucket(@RequestBody String address,Principal principal){
        log.info("START endpoint commitBucket, address:{}, request:{} ",address, principal.getName());
        if (principal != null){
            bucketService.commitBucketToOrder(principal.getName(), address);
        }
        return new ResponseEntity<>(Response.builder()
                .massage("Your order has been accepted, please wait for a call from the manager")
                .build(),HttpStatus.ACCEPTED);
    }
}
