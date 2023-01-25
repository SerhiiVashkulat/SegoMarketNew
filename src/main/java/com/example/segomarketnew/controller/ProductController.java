package com.example.segomarketnew.controller;

import com.example.segomarketnew.dto.ProductDto;
import com.example.segomarketnew.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    private String getAllProducts(Model model){
    List<ProductDto> list = productService.getAll();
    model.addAttribute("products", list);
    return "products";
    }
}
