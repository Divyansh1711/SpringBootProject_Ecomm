package com.springboot_project1.ecommerce.controllers;


import com.springboot_project1.ecommerce.dto.SignInDto;
import com.springboot_project1.ecommerce.dto.SignInResponseDto;
import com.springboot_project1.ecommerce.dto.SignUpResponseDto;
import com.springboot_project1.ecommerce.dto.SignupDto;
import com.springboot_project1.ecommerce.exceptions.AuthenticationFailException;
import com.springboot_project1.ecommerce.exceptions.CustomException;
import com.springboot_project1.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) throws CustomException, AuthenticationFailException {
        return userService.signIn(signInDto);
    }
    @PostMapping("/signup")
    public SignUpResponseDto Signup(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }
}
