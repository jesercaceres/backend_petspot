package com.petspot.controller;

import com.petspot.dto.login.EmailDTO;
import com.petspot.dto.register.RegisterDTO;
import com.petspot.model.Login;
import com.petspot.model.PetOwner;
import com.petspot.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("register")
public class RegisterController {
    @Autowired
    private LoginRepository loginRepository;

    @PostMapping
    private ResponseEntity register(@RequestBody @Validated RegisterDTO registerDTO, UriComponentsBuilder uriBuilder) {
        Login login = new Login(registerDTO);
        PetOwner petOwner = new PetOwner(registerDTO);

        login.setPetOwner(petOwner);

        loginRepository.save(login);

        var uri = uriBuilder.path("/profile/{id}").buildAndExpand(login.getId()).toUri();

        return ResponseEntity.created(uri).body(new EmailDTO(login.getEmail()));
    }

}
