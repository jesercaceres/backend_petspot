package com.petspot.dto.pet;

import jakarta.validation.constraints.NotBlank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record RegisterPetDTO(
        @NotBlank String nome,
        String especie,
        Integer genero,
        String raca,
        String peso,
        String dataDeNascimento) {
    public Date getDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return format.parse(dataDeNascimento);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
