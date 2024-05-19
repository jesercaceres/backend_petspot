package com.petspot.dto.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record RegisterPetDTO(
        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotBlank(message = "Espécie é obrigatória") String especie,
        @NotNull(message = "Gênero é obrigatório") Integer genero,
        @NotBlank(message = "Raça é obrigatória") String raca,
        @NotBlank(message = "Peso é obrigatório") String peso,
        @NotBlank(message = "Data de nascimento é obrigatória")
        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Data deve estar no formato dd/MM/yyyy") String dataDeNascimento) {
    
    public Date getDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return format.parse(dataDeNascimento);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}