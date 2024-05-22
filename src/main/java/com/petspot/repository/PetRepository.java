package com.petspot.repository;

import com.petspot.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, String> {
    List<Pet> findByPetNameContainingIgnoreCase(String petName);
    
    boolean existsByPetNameAndPetSpecieAndPetGenderAndPetRaceAndNeuteredAndBehaviorAndPetSizeAndVaccinatedAndPetBirthday(
            String petName, String petSpecie, Integer petGender, String petRace, Boolean neutered, String behavior, String petSize, Boolean vaccinated, Date petBirthday);
}