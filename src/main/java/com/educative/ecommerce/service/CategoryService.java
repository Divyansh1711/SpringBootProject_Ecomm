package com.educative.ecommerce.service;

import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category readCategory(String categoryName){
        return categoryRepository.findByCategoryName(categoryName);
    }
    public Optional<Category> readCategory(Integer categoryId){
        return categoryRepository.findById(categoryId);
    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public void updateCategory(Integer categoryId, Category newcategory){
       Category category= categoryRepository.getById(categoryId);
       category.setCategoryDescription(newcategory.getCategoryDescription());
       category.setCategoryName(newcategory.getCategoryName());
       category.setImgUrl(newcategory.getImgUrl());
       categoryRepository.save(category);
    }

    public void createCategory(Category category){
        categoryRepository.save(category);
    }
}
