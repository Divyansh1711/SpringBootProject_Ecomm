package com.springboot_project1.ecommerce.service;

import com.springboot_project1.ecommerce.dto.ProductDto;
import com.springboot_project1.ecommerce.model.Category;
import com.springboot_project1.ecommerce.model.Product;
import com.springboot_project1.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Optional;

@Service
public class ProductService {
     @Autowired
     private ProductRepository productRepository;

     public void addProduct(ProductDto productDto, Category category){
          Product product= getProductFromDto(productDto,category);
          productRepository.save(product);
     }

     public List<Product> getAllProduct(){
          return productRepository.findAll();
     }

     public Optional<Product> readProduct(Integer productId){
          return productRepository.findById(productId);
     }

     public void updateProduct(Integer productId,ProductDto productDto,Category category){
          Product product = getProductFromDto(productDto,category);
          product.setId(productId);
          productRepository.save(product);
     }

     private Product getProductFromDto(ProductDto productDto, Category category){
          Product product = new Product();
          product.setCategory(category);
          product.setId(productDto.getId());
          product.setDescription(productDto.getDescription());
          product.setName(productDto.getName());
          product.setImageUrl(productDto.getImageURL());
          return product;
     }

     public List<ProductDto> listProduct(){
          List<Product> products= productRepository.findAll();
          List<ProductDto> productDtos = new ArrayList<>();

          for(Product product : products) {
               // for each product change it to DTO
               productDtos.add(new ProductDto(product));
          }
          return productDtos;
     }

}
