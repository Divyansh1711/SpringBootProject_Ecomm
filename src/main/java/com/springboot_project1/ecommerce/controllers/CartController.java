package com.springboot_project1.ecommerce.controllers;


import com.springboot_project1.ecommerce.config.ApiResponse;
import com.springboot_project1.ecommerce.dto.AddToCartDto;
import com.springboot_project1.ecommerce.exceptions.AuthenticationFailException;
import com.springboot_project1.ecommerce.exceptions.ProductNotExistException;
import com.springboot_project1.ecommerce.model.Product;
import com.springboot_project1.ecommerce.model.User;
import com.springboot_project1.ecommerce.service.AuthenticationService;
import com.springboot_project1.ecommerce.service.CartService;
import com.springboot_project1.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam String token) throws AuthenticationFailException, ProductNotExistException {
          authenticationService.authenticate(token);
          User user= authenticationService.getUser(token);

        Product product= productService.getProductById(addToCartDto.getProductId());
        cartService.addToCart(addToCartDto,product,user);

        return new ResponseEntity<>(new ApiResponse(true,"Added to cart"), HttpStatus.CREATED);
    }
}
