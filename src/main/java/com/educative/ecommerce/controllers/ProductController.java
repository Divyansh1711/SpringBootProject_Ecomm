package com.educative.ecommerce.controllers;

import com.educative.ecommerce.config.ApiResponse;
import com.educative.ecommerce.dto.ProductDto;
import com.educative.ecommerce.model.Category;

import com.educative.ecommerce.model.Product;
import com.educative.ecommerce.service.CategoryService;
import com.educative.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        List<ProductDto> productList= productService.listProduct();
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }
    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody @Valid ProductDto productDto){
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if(optionalCategory.isEmpty()){
            return new ResponseEntity<>(new ApiResponse(false,"Product Not Found"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();

        productService.updateProduct(productId, productDto, category);
        return new ResponseEntity<>(new ApiResponse(true,"Product updated Succesfully"), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto){
        Optional<Category> category =categoryService.readCategory(productDto.getCategoryId());
        if(category.isEmpty()){
            return new ResponseEntity<>(new ApiResponse(false,"Category Not exist"), HttpStatus.CONFLICT);
        }
        Category category1 = category.get();
        productService.addProduct(productDto,category1);
        return new ResponseEntity<>(new ApiResponse(true,"product added"), HttpStatus.OK);
    }
}
