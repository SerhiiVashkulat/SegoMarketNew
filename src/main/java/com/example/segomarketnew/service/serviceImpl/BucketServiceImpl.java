package com.example.segomarketnew.service.serviceImpl;

import com.example.segomarketnew.domain.model.Bucket;
import com.example.segomarketnew.domain.model.Product;
import com.example.segomarketnew.domain.model.User;
import com.example.segomarketnew.dto.BucketDetailsDto;
import com.example.segomarketnew.dto.BucketDto;
import com.example.segomarketnew.repository.BucketRepository;
import com.example.segomarketnew.repository.ProductRepository;
import com.example.segomarketnew.repository.UserRepository;
import com.example.segomarketnew.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {

    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    @Override
    public void deleteAllProductFromBucket(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("User not found") );
        Bucket bucket = user.getBucket();
        bucket.removeAll();
        bucketRepository.save(bucket);
    }

    @Override
    public void deleteProductByIdFromBucket(Long productId, String name){
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("User not found") );
        Bucket bucket = user.getBucket();
        bucket.removeProductById(productId);
        bucketRepository.save(bucket);
    }

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
    public BucketDto getBucketByUser(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("User not found") );

        BucketDto bucketDto = new BucketDto();
        Map<Long, BucketDetailsDto> mapProductId = new HashMap<>();
        List<Product> productList = user.getBucket().getProducts();
        for (Product product: productList) {
            BucketDetailsDto detailsDto = mapProductId.get(product.getId());
            if (detailsDto == null){
                mapProductId.put(product.getId(), new BucketDetailsDto(product));
            }else {
                detailsDto.setAmount(detailsDto.getAmount() + 1);
                detailsDto.setSum(detailsDto.getSum() + product.getPrice());
            }
        }
        bucketDto.setBucketDetails(new ArrayList<>(mapProductId.values()));
        bucketDto.infoBucket();
        return bucketDto;
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
                .map(productRepository::getReferenceById)//берем ссылку на продукт
                .collect(Collectors.toList());
    }
}
