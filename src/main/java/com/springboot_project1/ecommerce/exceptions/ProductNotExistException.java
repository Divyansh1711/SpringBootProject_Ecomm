package com.springboot_project1.ecommerce.exceptions;

import com.springboot_project1.ecommerce.model.Product;

public class ProductNotExistException extends Exception{
    public ProductNotExistException(String msg){
        super(msg);
    }
}
