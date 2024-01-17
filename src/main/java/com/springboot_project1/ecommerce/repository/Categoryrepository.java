package com.springboot_project1.ecommerce.repository;

import com.springboot_project1.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Categoryrepository extends JpaRepository<Category, Integer> {

        Category findByCategoryName(String categoryName);


}