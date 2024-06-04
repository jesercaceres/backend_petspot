package com.petspot.dto.pet;

import com.petspot.model.Pet;
import java.util.Date;

public record SavedDatasPetDTO(
        String id,
        String nome,
        String especie,
        String peso,
        Integer genero,
        String raca,
        Boolean castrado,
        String comportamento,
        String porte,
        Boolean vacinado,
        Date dataDeNascimento) {
    
    public SavedDatasPetDTO(Pet pet) {
        this(pet.getId(), pet.getPetName(), pet.getPetSpecie(), pet.getPetWeight(), pet.getPetGender(), pet.getPetRace(),
             pet.getNeutered(), pet.getBehavior(), pet.getPetSize(), pet.getVaccinated(), pet.getPetBirthday());
    }
}