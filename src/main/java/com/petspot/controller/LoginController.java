package com.petspot.controller;

import com.petspot.dto.login.LoginDTO;
import com.petspot.model.Login;
import com.petspot.repository.LoginRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginRepository repository;

    @PostMapping
    public ResponseEntity<String> signIn(@RequestBody @Valid LoginDTO loginDTO) {

        Login auth = repository.findByEmailAndPasswordLogin(loginDTO.email(), loginDTO.senha());

        if (auth != null) {
            return ResponseEntity.ok("Autenticação bem-sucedida.");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas. Autenticação falhou");
    }
}