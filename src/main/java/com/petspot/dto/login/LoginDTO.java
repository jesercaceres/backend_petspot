package com.petspot.dto.login;

import jakarta.validation.constraints.Email;

public record LoginDTO(
                @Email String email,
                String senha) {
}
