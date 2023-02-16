package com.example.segomarketnew.service;

import com.example.segomarketnew.domain.model.Bucket;
import com.example.segomarketnew.domain.model.Product;
import com.example.segomarketnew.domain.model.User;
import com.example.segomarketnew.dto.BucketDto;
import com.example.segomarketnew.repository.BucketRepository;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);
    void addProduct(Bucket bucket, List<Long>productIds);
    void deleteAllProductFromBucket(String name);
    void deleteProductByIdFromBucket(Long productId, String name);
    BucketDto getBucketByUser(String name);

}
