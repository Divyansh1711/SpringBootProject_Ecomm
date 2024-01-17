package com.educative.ecommerce.service;

import antlr.Token;
import com.educative.ecommerce.config.MessageString;
import com.educative.ecommerce.dto.SignInDto;
import com.educative.ecommerce.dto.SignInResponseDto;
import com.educative.ecommerce.dto.SignUpResponseDto;
import com.educative.ecommerce.dto.SignupDto;
import com.educative.ecommerce.exceptions.AuthenticationFailException;
import com.educative.ecommerce.exceptions.CustomException;
import com.educative.ecommerce.model.AuthenticationToken;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;


@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    public SignUpResponseDto signUp(SignupDto signupDto) throws CustomException {
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            throw new CustomException("User Already Exists");
        }

        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }

        User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(), encryptedPassword);
        try {
            userRepository.save(user);
         final AuthenticationToken authenticationToken= new AuthenticationToken(user);

         authenticationService.saveConfirmationToken(authenticationToken);

            return new SignUpResponseDto("Sucess", "User Succesfully created");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
    // sign in
    public SignInResponseDto signIn(SignInDto signInDto) throws AuthenticationFailException,CustomException {
        User user= userRepository.findByEmail(signInDto.getEmail());
        if(!Objects.nonNull(user)){
            throw new AuthenticationFailException("User not Present");
        }
        try {
            //check if password is right
            if(!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                  throw new AuthenticationFailException(MessageString.WRONG_PASSWORD);
            }
        }catch (NoSuchAlgorithmException e){
           e.printStackTrace();
           logger.error("Hashing Password fail "+e.getMessage());
           throw new CustomException(e.getMessage());
        }
        AuthenticationToken authenticationToken= authenticationService.getAuthenticationToken(user);
        if(!Objects.nonNull(authenticationToken)){
            throw new CustomException(MessageString.AUTH_TOEKN_NOT_PRESENT);
        }
        return new SignInResponseDto("success",authenticationToken.getToken());
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        return myHash;
    }
}
