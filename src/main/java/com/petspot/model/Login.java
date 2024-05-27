package com.petspot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import com.petspot.dto.register.RegisterDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id", "email" })
@Entity(name = "Login")
@Table(name = "login")
public class Login {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private String id;

    @Email
    private String email;

    private String passwordLogin;

    private String typeOfUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_owner_id")
    private PetOwner petOwner;

    public Login(RegisterDTO registerDTO) {
        this.email = registerDTO.email();
        this.passwordLogin = registerDTO.senha();
        this.typeOfUser = registerDTO.usuario();
    }

    public String getOwnerId() {
        return petOwner != null ? petOwner.getId() : null;
    }
    
}