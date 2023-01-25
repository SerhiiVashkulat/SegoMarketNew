package com.example.segomarketnew.service.serviceImpl;

import com.example.segomarketnew.dto.ProductDto;
import com.example.segomarketnew.mapper.ProductMapper;
import com.example.segomarketnew.repository.ProductRepository;
import com.example.segomarketnew.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ProductServiceImpl implements ProductService {

   private final ProductMapper mapper = ProductMapper.mapper;

    private final ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAll() {
        return mapper.toListProductsDto(productRepository.findAll());
    }
}
