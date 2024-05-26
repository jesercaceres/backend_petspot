package com.petspot.repository;

import com.petspot.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;



public interface LoginRepository extends JpaRepository<Login, String> {
    Login findByEmailAndPasswordLogin(String email, String senha);

     boolean existsByEmail(String email);
    
}
