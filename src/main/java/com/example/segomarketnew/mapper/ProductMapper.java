package com.example.segomarketnew.mapper;

import com.example.segomarketnew.dto.ProductDto;
import com.example.segomarketnew.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDto productDto);
    ProductDto toDto(Product product);
    List<Product> toListProducts(List<ProductDto> listProductsDto);
    List<ProductDto> toListProductsDto(List<Product> listProducts);

}
