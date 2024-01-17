package com.educative.ecommerce.controllers;


import com.educative.ecommerce.dto.SignInDto;
import com.educative.ecommerce.dto.SignInResponseDto;
import com.educative.ecommerce.dto.SignUpResponseDto;
import com.educative.ecommerce.dto.SignupDto;
import com.educative.ecommerce.exceptions.AuthenticationFailException;
import com.educative.ecommerce.exceptions.CustomException;
import com.educative.ecommerce.service.UserService;
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
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) throws CustomException,AuthenticationFailException{
        return userService.signIn(signInDto);
    }
    @PostMapping("/signup")
    public SignUpResponseDto Signup(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }
}
