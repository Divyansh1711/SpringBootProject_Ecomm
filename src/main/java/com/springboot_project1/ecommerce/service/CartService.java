package com.springboot_project1.ecommerce.service;

import com.springboot_project1.ecommerce.dto.AddToCartDto;
import com.springboot_project1.ecommerce.model.Cart;
import com.springboot_project1.ecommerce.model.Product;
import com.springboot_project1.ecommerce.model.User;
import com.springboot_project1.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;


    public void addToCart(AddToCartDto addToCartDto, Product product, User user){
        Cart cart= new Cart(product,user,addToCartDto.getQuantity());
        cartRepository.save(cart);
    }
}
