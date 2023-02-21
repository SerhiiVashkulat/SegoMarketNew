package com.example.segomarketnew.service.serviceImpl;


import com.example.segomarketnew.domain.model.Bucket;
import com.example.segomarketnew.domain.model.Product;
import com.example.segomarketnew.domain.model.User;
import com.example.segomarketnew.repository.ProductRepository;
import com.example.segomarketnew.repository.UserRepository;
import com.example.segomarketnew.service.BucketService;
import com.example.segomarketnew.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final BucketService bucketService;


    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public void addProductToShop(Product product) {
        if (productRepository.existsByTitle(product.getTitle())){
            throw new RuntimeException("Product is already adding");
        }
        productRepository.save(product);
    }

    @Override
    public void addProductToUserBucket(Long productId, String name)  {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("User not found" + name));
        Bucket bucket = user.getBucket();
        if (productId > productRepository.findAll().size() + 1){
            throw new RuntimeException("Unable to find product");
        }
        if (bucket == null){
            Bucket bucketNew = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(bucketNew);
            userRepository.save(user);
        }else {
            bucketService.addProduct(bucket,Collections.singletonList(productId));
        }

    }


}
