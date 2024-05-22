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
    private String petSpecie;
    private String petRace;
    private Integer petGender;
    private Boolean neutered;
    private String behavior;
    private String petSize;
    private Boolean vaccinated;

    @JsonIgnore
    @ManyToMany(mappedBy = "pet")
    private Set<PetOwner> petOwners = new HashSet<>();

    public Pet(RegisterPetDTO petDTO) {
        this.petName = petDTO.nome();
        this.petBirthday = petDTO.getDate();
        this.petSpecie = petDTO.especie();
        this.petRace = petDTO.raca();
        this.petGender = petDTO.genero();
        this.neutered = petDTO.castrado();
        this.behavior = petDTO.comportamento();
        this.petSize = petDTO.porte();
        this.vaccinated = petDTO.vacinado();
    }

    // Adicionando manualmente os métodos get
    public String getId() {
        return id;
    }

    public String getPetName() {
        return petName;
    }

    public String getPetWeight() {
        return petWeight;
    }

    public Date getPetBirthday() {
        return petBirthday;
    }

    public String getPetSpecie() {
        return petSpecie;
    }

    public String getPetRace() {
        return petRace;
    }

    public Integer getPetGender() {
        return petGender;
    }

    public Boolean getNeutered() {
        return neutered;
    }

    public String getBehavior() {
        return behavior;
    }

    public String getPetSize() {
        return petSize;
    }

    public Boolean getVaccinated() {
        return vaccinated;
    }
}