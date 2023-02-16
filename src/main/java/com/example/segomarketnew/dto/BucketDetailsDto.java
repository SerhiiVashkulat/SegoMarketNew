package com.example.segomarketnew.dto;

import com.example.segomarketnew.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BucketDetailsDto {
    private String title;
    private Long productId;
    private double price;
    private double amount;
    private Double sum;

    public BucketDetailsDto(Product product) {
        this.title = product.getTitle();
        this.productId = product.getId();
        this.price = product.getPrice();
        this.amount = 1.0;
        this.sum =product.getPrice();
    }
}
