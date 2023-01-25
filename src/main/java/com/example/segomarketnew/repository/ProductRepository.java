package com.example.segomarketnew.repository;

import com.example.segomarketnew.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
