package com.springboot_project1.ecommerce.exceptions;

public class AuthenticationFailException extends Exception{
     public AuthenticationFailException(String msg){
         super(msg);
     }
}
