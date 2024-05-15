package com.petspot.dto.pet;

import com.petspot.model.Pet;

import java.util.Date;

public record SavedDatasPetDTO(
        String id,
        String nome,
        int genero,
        Date idade) {
    public SavedDatasPetDTO(Pet pet) {
        this(pet.getId(), pet.getPetName(), pet.getGender(), pet.getPetBirthday());
    }
}
