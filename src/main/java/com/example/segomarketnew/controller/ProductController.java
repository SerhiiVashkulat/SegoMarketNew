package com.example.segomarketnew.controller;

import com.example.segomarketnew.domain.response.Response;
import com.example.segomarketnew.dto.ProductDto;
import com.example.segomarketnew.mapper.ProductMapper;
import com.example.segomarketnew.service.BucketService;
import com.example.segomarketnew.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final BucketService bucketService;
    private final ProductMapper mapper = ProductMapper.mapper;



    @GetMapping
    public ResponseEntity<List<ProductDto>> allProducts(){
        log.info("START endpoint  allProducts");
        return new ResponseEntity<>(mapper.toListProductsDto(productService.getAll()),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<Response> addProductToBucket(@PathVariable Long id, Principal principal){
        log.info("START endpoint  addProductToBucket, id:{}, user:{}",id,principal.getName());
        if (principal == null){
            log.error("Trow exception user:{} not authenticated",principal.getName());
            throw new RuntimeException("User not authenticated");
        }
            productService.addProductToUserBucket(id,principal.getName());
            return new ResponseEntity<>( Response.builder()
                    .massage("Add product to bucket " + principal.getName())
                    .build(), HttpStatus.OK);
        }
        @PreAuthorize("hasAuthority('MANAGER')")
        @PostMapping
    ResponseEntity<Response> addProductsToShop(@RequestBody ProductDto productDto){
            log.info("START endpoint  addProductsToShop, product:{}",productDto);
        productService.addProductToShop(mapper.toProduct(productDto));
        return new ResponseEntity<>(Response.builder()
                .massage("Adding product " + productDto.getTitle() + " to shop.")
                .build(), HttpStatus.OK);
        }
}
