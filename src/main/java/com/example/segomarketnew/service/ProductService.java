package com.example.segomarketnew.service;

import com.example.segomarketnew.domain.model.Product;


import java.util.List;

public interface ProductService {
    List<Product> getAll();
    void addProductToUserBucket(Long productId, String name);

}
