package com.example.segomarketnew.dto;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private Double price;
}
