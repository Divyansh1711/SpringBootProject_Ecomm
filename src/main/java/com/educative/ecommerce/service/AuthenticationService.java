package com.educative.ecommerce.service;


import com.educative.ecommerce.config.MessageString;
import com.educative.ecommerce.exceptions.AuthenticationFailException;
import com.educative.ecommerce.model.AuthenticationToken;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {

       @Autowired
       private TokenRepository tokenRepository;

       // save the confiramtion token
       public void saveConfirmationToken(AuthenticationToken authenticationToken){
              tokenRepository.save(authenticationToken);
       }
       // get the Authentication token by user
       public AuthenticationToken getAuthenticationToken(User user){
          return tokenRepository.findAuthenticationTokenByUser(user);
       }
       //get user by token
       public User getUser(String token){
          AuthenticationToken authenticationToken= tokenRepository.findAuthenticationTokenByToken(token);
          if(Objects.nonNull(authenticationToken)){
                 if(Objects.nonNull(authenticationToken.getUser())){
                        return authenticationToken.getUser();
                 }
          }
          return null;
       }
       // check if token is valid
       public void authenticate(String token) throws AuthenticationFailException{
             if(!Objects.nonNull(token)){
                    throw new AuthenticationFailException(
                            MessageString.AUTH_TOEKN_NOT_PRESENT
                    );
             }
             if(!Objects.nonNull(getUser(token))){
                    throw new AuthenticationFailException(MessageString.AUTH_TOEKN_NOT_VALID);
             }
       }

}
