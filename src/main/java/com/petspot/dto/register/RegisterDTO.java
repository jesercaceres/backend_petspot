package com.petspot.dto.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record RegisterDTO(
        @Email String email,
        @NotBlank @Size(min = 8) String senha,
        @NotBlank @Size(min = 8) String repetirSenha,
        @NotBlank String usuario,
        @NotBlank String nome,
        @NotBlank String sobrenome,
        @NotBlank String dataDeNascimento,
        @NotBlank String pais,
        @NotBlank String telefone,
        boolean newsletterCheck) {  

    public Date getDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return format.parse(dataDeNascimento);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String senha() {
        return senha;
    }

    public String repetirSenha() {
        return repetirSenha;
    }
}
