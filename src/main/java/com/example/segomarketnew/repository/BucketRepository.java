package com.example.segomarketnew.repository;

import com.example.segomarketnew.domain.model.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface BucketRepository extends JpaRepository<Bucket,Long> {



}
