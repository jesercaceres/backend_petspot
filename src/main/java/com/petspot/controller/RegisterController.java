package com.petspot.controller;

import com.petspot.dto.login.EmailDTO;
import com.petspot.dto.register.RegisterDTO;
import com.petspot.exceptions.*;
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
@RequestMapping("/petspot/register")
public class RegisterController {
    @Autowired
    private LoginRepository loginRepository;

    @PostMapping
    public ResponseEntity<EmailDTO> register(@RequestBody @Validated RegisterDTO registerDTO,
            UriComponentsBuilder uriBuilder) throws PasswordsNotMatchingException, DuplicateEmailException, PasswordSizeException {

        // Verifica se o e-mail já está cadastrado
        boolean emailFounded = loginRepository.existsByEmail(registerDTO.email());
        if (emailFounded) {
            throw new DuplicateEmailException("E-mail já cadastrado");
        }

        // Verifica se as senhas coincidem
        if (!registerDTO.senha().equals(registerDTO.repetirSenha())) {
            throw new PasswordsNotMatchingException("As senhas não coincidem.");
        }

        if (registerDTO.senha().length() < 8 || registerDTO.repetirSenha().length() < 8) {
            throw new PasswordSizeException("A senha deve conter no mínimo 8 caracteres");
        }


        // Cria um novo objeto PetOwner a partir do DTO
        PetOwner petOwner = new PetOwner(registerDTO);

        // Cria um novo objeto Login a partir do DTO e associa o PetOwner
        Login login = new Login(registerDTO);
        login.setPetOwner(petOwner);

        // Salva o login no repositório
        loginRepository.save(login);

        // Cria a URI para o novo recurso criado
        var uri = uriBuilder.path("/profile/{id}").buildAndExpand(login.getId()).toUri();


        // Retorna a resposta com a URI do novo recurso e o e-mail do usuário
        return ResponseEntity.created(uri).body(new EmailDTO(login.getEmail()));
    }
}