package com.example.segomarketnew.service.serviceImpl;

import com.example.segomarketnew.domain.model.Bucket;
import com.example.segomarketnew.domain.model.Product;
import com.example.segomarketnew.domain.model.User;
import com.example.segomarketnew.repository.BucketRepository;
import com.example.segomarketnew.repository.ProductRepository;
import com.example.segomarketnew.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {

    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;


    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Product> productList = getListProductsByIds(productIds);//список продуктов к данному юзеру
        bucket.setProducts(productList);//корзина с продуктами данного юзера
        return bucketRepository.save(bucket);
    }

    @Override
    public Bucket getBucketByUser(String name) {
        return null;
    }

    @Override
    public void addProduct(Bucket bucket, List<Long> productIds) {
        List<Product> products = bucket.getProducts();
        List<Product> productList = products == null ?new ArrayList<>() : new ArrayList<>(products);
        productList.addAll(getListProductsByIds(productIds));
        bucket.setProducts(productList);
        bucketRepository.save(bucket);
    }

    private List<Product>getListProductsByIds(List<Long> productIds){
        return productIds.stream()
                .map(productRepository::getOne)//берем ссылку на продукт
                .collect(Collectors.toList());
    }
}
