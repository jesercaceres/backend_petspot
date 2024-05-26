package com.petspot.dto.register;

import jakarta.validation.constraints.Email;
<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
=======
>>>>>>> 0861db8e17ca107accefe0fd0ce002247b49ad84

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record RegisterDTO(
        @Email String email,
<<<<<<< HEAD
        @NotBlank @Size(min = 8) String senha,
        @NotBlank @Size(min = 8) String repetirSenha,
        @NotBlank String usuario,
        @NotBlank String nome,
        @NotBlank String sobrenome,
        @NotBlank String dataDeNascimento,
        @NotBlank String pais,
        @NotBlank String telefone,
        boolean newsletterCheck) {  
=======
        String senha,
        String repetirSenha,
        String usuario,
        String nome,
        String sobrenome,
        String dataDeNascimento) {
>>>>>>> 0861db8e17ca107accefe0fd0ce002247b49ad84

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
<<<<<<< HEAD
}
=======

    
}
>>>>>>> 0861db8e17ca107accefe0fd0ce002247b49ad84
