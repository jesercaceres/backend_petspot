package com.petspot.dto.pet;

import com.petspot.model.Pet;
import java.util.Date;

public record SavedDatasPetDTO(
        String id,
        String nome,
        String peso,
        String especie,
        Integer genero,
        String raca,
        Boolean castrado,
        String comportamento,
        String porte,
        Boolean vacinado,
        Date idade) {
    
    public SavedDatasPetDTO(Pet pet) {
        this(pet.getId(), pet.getPetName(),pet.getPetWeight(), pet.getPetSpecie(), pet.getPetGender(), pet.getPetRace(),
             pet.getNeutered(), pet.getBehavior(), pet.getPetSize(), pet.getVaccinated(), pet.getPetBirthday());
    }
}