package com.petspot.controller;

import com.petspot.dto.pet.RegisterPetDTO;
import com.petspot.dto.pet.SavedDatasPetDTO;
import com.petspot.dto.pet.UpdatePetDTO;
import com.petspot.dto.pet.UpdatePetWeightDTO;
import com.petspot.exceptions.DuplicatePetException;
import com.petspot.model.Pet;
import com.petspot.model.PetOwner;
import com.petspot.repository.PetOwnerRepository;
import com.petspot.repository.PetRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/petspot")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetOwnerRepository ownerRepository;
        //OwnerID é passado como parametro.
    @PostMapping("/pet-register/{id}")
    @Transactional
    public ResponseEntity<SavedDatasPetDTO> registerPet(@Valid @RequestBody RegisterPetDTO petDTO,
            @PathVariable(name = "id") String ownerId,
            UriComponentsBuilder uriBuilder) {

        // Busca o dono do pet pelo ID fornecido na URL
        PetOwner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerId));

        // Verifica se um pet duplicado já existe
        boolean petExists = petRepository
                .existsByPetNameAndPetSpecieAndPetGenderAndPetRaceAndNeuteredAndBehaviorAndPetSizeAndVaccinatedAndPetBirthday(
                        petDTO.nome(), petDTO.especie(), petDTO.genero(), petDTO.raca(), petDTO.castrado(),
                        petDTO.comportamento(), petDTO.porte(), petDTO.vacinado(), petDTO.getDate());

        if (petExists) {
            throw new DuplicatePetException("Pet com os mesmos atributos já existe.");
        }

        // Cria um novo objeto Pet a partir do DTO e salva no banco de dados
        Pet pet = new Pet(petDTO);
        petRepository.save(pet);

        // Adiciona o pet ao dono e atualiza o dono no banco de dados
        owner.getPet().add(pet);
        ownerRepository.save(owner);

        var uri = uriBuilder.path("/pet/{id}").buildAndExpand(pet.getId()).toUri();

        return ResponseEntity.created(uri).body(new SavedDatasPetDTO(pet));
    }

    @GetMapping("/meuspets/{ownerId}")
    public ResponseEntity<List<SavedDatasPetDTO>> getAllPetsByOwner(@PathVariable String ownerId) {
        PetOwner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerId));

        List<Pet> pets = new ArrayList<>(owner.getPet());
        List<SavedDatasPetDTO> petDTOs = pets.stream()
                .map(pet -> new SavedDatasPetDTO(
                        pet.getId(),
                        pet.getPetName(),
                        pet.getPetSpecie(),
                        pet.getPetGender(),
                        pet.getPetRace(),
                        pet.getNeutered(),
                        pet.getBehavior(),
                        pet.getPetSize(),
                        pet.getVaccinated(),
                        pet.getPetBirthday()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(petDTOs);
    }

    @GetMapping("/meuspets/{ownerId}/buscarpet")
    public ResponseEntity<List<SavedDatasPetDTO>> getPetsByName(@PathVariable String ownerId,
            @RequestParam String petName) {
        System.out.println("Buscando pets com o nome que contém: " + petName + " para o dono com ID: " + ownerId);

        // Busca o dono do pet pelo ID fornecido na URL
        PetOwner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerId));

        // Filtra os pets do dono pelo nome parcial
        List<Pet> pets = owner.getPet().stream()
                .filter(pet -> pet.getPetName().toLowerCase().contains(petName.toLowerCase()))
                .collect(Collectors.toList());

        if (pets.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<SavedDatasPetDTO> petDTOs = pets.stream()
                .map(pet -> new SavedDatasPetDTO(
                        pet.getId(),
                        pet.getPetName(),
                        pet.getPetSpecie(),
                        pet.getPetGender(),
                        pet.getPetRace(),
                        pet.getNeutered(),
                        pet.getBehavior(),
                        pet.getPetSize(),
                        pet.getVaccinated(),
                        pet.getPetBirthday()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(petDTOs);
    }

    @PatchMapping("/meuspets/{ownerId}/renomear/{petId}")
    public ResponseEntity<String> renamePet(@PathVariable String ownerId,
            @PathVariable String petId,
            @RequestBody UpdatePetDTO updatePetDTO) {
        try {
            Pet pet = petRepository.findById(petId)
                    .orElseThrow(() -> new RuntimeException("Pet not found with id: " + petId));

            // Verifica se o pet pertence ao PetOwner fornecido
            boolean isOwner = pet.getPetOwners().stream()
                    .anyMatch(owner -> owner.getId().equals(ownerId));

            if (!isOwner) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Você não tem permissão para renomear este pet.");
            }

            pet.setPetName(updatePetDTO.petName());
            petRepository.save(pet);

            return ResponseEntity.ok("Nome atualizado com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao atualizar o nome do pet: " + e.getMessage());
        }
    }

    @PatchMapping("/meuspets/{ownerId}/atualizarpeso/{petId}")
    public ResponseEntity<String> updatePetWeight(@PathVariable String ownerId,
            @PathVariable String petId,
            @RequestBody UpdatePetWeightDTO updatePetWeightDTO) {
        try {
            Pet pet = petRepository.findById(petId)
                    .orElseThrow(() -> new RuntimeException("Pet not found with id: " + petId));

            // Verifica se o pet pertence ao PetOwner fornecido
            boolean isOwner = pet.getPetOwners().stream()
                    .anyMatch(owner -> owner.getId().equals(ownerId));

            if (!isOwner) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Você não tem permissão para atualizar o peso deste pet.");
            }

            pet.setPetWeight(updatePetWeightDTO.peso());
            petRepository.save(pet);

            return ResponseEntity.ok("Peso atualizado com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao atualizar o peso do pet: " + e.getMessage());
        }
    }
}