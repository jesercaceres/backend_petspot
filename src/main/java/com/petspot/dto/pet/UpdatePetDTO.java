package com.petspot.dto.pet;

import jakarta.validation.constraints.NotBlank;

public record UpdatePetDTO(
        @NotBlank(message = "Pet name cannot be blank") String petName) {
}