package com.springboot_project1.ecommerce.repository;

import com.springboot_project1.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
     User findByEmail(String email);

}
