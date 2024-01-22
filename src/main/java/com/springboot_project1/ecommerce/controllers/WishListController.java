package com.springboot_project1.ecommerce.controllers;


import com.springboot_project1.ecommerce.config.ApiResponse;
import com.springboot_project1.ecommerce.dto.ProductDto;
import com.springboot_project1.ecommerce.exceptions.AuthenticationFailException;
import com.springboot_project1.ecommerce.model.Product;
import com.springboot_project1.ecommerce.model.User;
import com.springboot_project1.ecommerce.model.WishList;
import com.springboot_project1.ecommerce.repository.ProductRepository;
import com.springboot_project1.ecommerce.repository.WishListRepository;
import com.springboot_project1.ecommerce.service.AuthenticationService;
import com.springboot_project1.ecommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @Autowired
   private AuthenticationService authenticationService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addWishList(@RequestBody ProductDto productDto, @RequestParam String token) throws AuthenticationFailException{
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        Product product= productRepository.getById(productDto.getId());
        WishList wishList= new WishList(user,product);
        wishListService.createWishlist(wishList);
        return  new ResponseEntity<>(new ApiResponse(true,"added to wishlist"), HttpStatus.CREATED);
    }

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@RequestParam String token) throws AuthenticationFailException{
        authenticationService.authenticate(token);
        User user= authenticationService.getUser(token);
        List<WishList> wishLists= wishListService.readWishList(user);
        List<ProductDto> products= new ArrayList<>();

        for(WishList wishList: wishLists){
            products.add(new ProductDto(wishList.getProduct()));
        }
         return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
