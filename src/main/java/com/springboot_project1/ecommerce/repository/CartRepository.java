package com.springboot_project1.ecommerce.repository;

import com.springboot_project1.ecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {

}
