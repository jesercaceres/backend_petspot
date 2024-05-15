package com.petspot.controller;

import com.petspot.dto.pet.RegisterPetDTO;
import com.petspot.dto.pet.SavedDatasPetDTO;
import com.petspot.dto.pet.UpdatePetDTO;
import com.petspot.dto.pet.UpdatePetWeightDTO;
import com.petspot.model.Pet;
import com.petspot.model.PetOwner;
import com.petspot.repository.PetOwnerRepository;
import com.petspot.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity registerPet(@RequestBody RegisterPetDTO petDTO, @PathVariable(name = "id") String param,
            UriComponentsBuilder uriBuilder) {
        PetOwner owner = ownerRepository.getReferenceById(param);

        Pet pet = new Pet(petDTO);
        petRepository.save(pet);

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
                .map(pet -> new SavedDatasPetDTO(pet.getId(), pet.getPetName(), pet.getGender(), pet.getPetBirthday()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(petDTOs);
    }

    @GetMapping("/meuspets/buscarpet/{petName}")
    public ResponseEntity<List<SavedDatasPetDTO>> getPetsByName(@PathVariable("petName") String petName) {
        System.out.println("Buscando pets com o nome: " + petName);

        List<Pet> pets = petRepository.findByPetNameContainingIgnoreCase(petName);

        if (pets.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<SavedDatasPetDTO> petDTOs = pets.stream()
                .map(pet -> new SavedDatasPetDTO(pet.getId(), pet.getPetName(), pet.getGender(), pet.getPetBirthday()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(petDTOs);
    }

    @PatchMapping("/meuspets/renomear/{petId}")
    public ResponseEntity<SavedDatasPetDTO> renamePet(@PathVariable String petId,
            @RequestBody UpdatePetDTO updatePetDTO) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + petId));

        pet.setPetName(updatePetDTO.petName());
        petRepository.save(pet);

        SavedDatasPetDTO responseDto = new SavedDatasPetDTO(pet);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/meuspets/atualizarpeso/{petId}")
    public ResponseEntity<SavedDatasPetDTO> updatePetWeight(@PathVariable String petId,
            @RequestBody UpdatePetWeightDTO updatePetWeightDTO) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + petId));

        pet.setPetWeight(updatePetWeightDTO.peso());
        petRepository.save(pet);

        SavedDatasPetDTO responseDto = new SavedDatasPetDTO(pet);
        return ResponseEntity.ok(responseDto);
    }
}
