package com.springboot_project1.ecommerce.service;


import com.springboot_project1.ecommerce.model.User;
import com.springboot_project1.ecommerce.model.WishList;
import com.springboot_project1.ecommerce.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;


    public void createWishlist(WishList wishList){
        wishListRepository.save(wishList);
    }

    public List<WishList> readWishList(User user){
        return wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
    }
}
