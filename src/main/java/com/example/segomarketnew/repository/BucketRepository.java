package com.example.segomarketnew.repository;

import com.example.segomarketnew.domain.model.Bucket;
import com.example.segomarketnew.domain.model.Product;
import com.example.segomarketnew.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface BucketRepository extends JpaRepository<Bucket,Long> {

}
