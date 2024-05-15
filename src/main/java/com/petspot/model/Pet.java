package com.petspot.model;

import com.petspot.dto.pet.RegisterPetDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Pet {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private String id;

    private String petName;

    private String petWeight;

    private Date petBirthday;

    private String specie;

    private String race;

    private Integer gender;

    @JsonIgnore
    @ManyToMany(mappedBy = "pet")
    private Set<PetOwner> petOwners = new HashSet<>();

    public Pet(RegisterPetDTO petDTO) {
        this.petName = petDTO.nome();

        this.specie = petDTO.especie();

        if (petDTO.genero() != null) {
            this.gender = petDTO.genero();
        }
        if (petDTO.dataDeNascimento() != null) {
            this.petBirthday = petDTO.getDate();
        }
        if (petDTO.peso() != null) {
            this.petWeight = petDTO.peso();
        }
        if (petDTO.raca() != null) {
            this.race = petDTO.raca();
        }
    }
}
