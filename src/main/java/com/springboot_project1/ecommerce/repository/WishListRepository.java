package com.springboot_project1.ecommerce.repository;

import com.springboot_project1.ecommerce.model.User;
import com.springboot_project1.ecommerce.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WishListRepository extends JpaRepository<WishList,Integer> {
    public List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}