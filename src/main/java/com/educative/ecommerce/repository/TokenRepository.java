package com.educative.ecommerce.repository;

import com.educative.ecommerce.model.AuthenticationToken;
import com.educative.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Integer> {
    public AuthenticationToken findAuthenticationTokenByUser(User user);
    public AuthenticationToken findAuthenticationTokenByToken(String token);


}
