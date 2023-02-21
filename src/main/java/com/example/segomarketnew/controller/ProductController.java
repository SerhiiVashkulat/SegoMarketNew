package com.example.segomarketnew.controller;

import com.example.segomarketnew.domain.response.Response;
import com.example.segomarketnew.dto.ProductDto;
import com.example.segomarketnew.mapper.ProductMapper;
import com.example.segomarketnew.service.BucketService;
import com.example.segomarketnew.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final BucketService bucketService;
    private final ProductMapper mapper = ProductMapper.mapper;


    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll(){
        return new ResponseEntity<>(mapper.toListProductsDto(productService.getAll()),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<Response> addProductToBucket(@PathVariable Long id, Principal principal){
        if (principal == null){
            throw new RuntimeException("User not authenticated");
        }
            productService.addProductToUserBucket(id,principal.getName());
            return new ResponseEntity<>( Response.builder()
                    .massage("Add product to bucket " + principal.getName())
                    .build(), HttpStatus.OK);
        }
        @PostMapping
    ResponseEntity<Response> addProductsToShop(@RequestBody ProductDto productDto){

        productService.addProductToShop(mapper.toProduct(productDto));
        return new ResponseEntity<>(Response.builder()
                .massage("Adding product " + productDto.getTitle() + " to shop.")
                .build(), HttpStatus.OK);
        }
}
