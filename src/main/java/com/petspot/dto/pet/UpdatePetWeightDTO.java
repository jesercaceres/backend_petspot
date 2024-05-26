package com.petspot.dto.pet;

import jakarta.validation.constraints.NotBlank;

public record UpdatePetWeightDTO(
        @NotBlank(message = "Weight cannot be blank") String peso) {
}