package com.petspot.model;

import com.petspot.dto.register.RegisterDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pet_owner")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
public class PetOwner {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private String id;

    private String name;

    private String lastName;

    private Date birthday;

<<<<<<< HEAD
    private String country;

    private String phone;

    private boolean newsletterCheck;

=======
>>>>>>> 0861db8e17ca107accefe0fd0ce002247b49ad84
    @OneToOne(mappedBy = "petOwner")
    private Login login;

    @ManyToMany
    @JoinTable(name = "pets_client_tutor", joinColumns = @JoinColumn(name = "pet_owner_id"), inverseJoinColumns = @JoinColumn(name = "pet_id"))
    private Set<Pet> pet = new HashSet<>();

    public PetOwner(RegisterDTO registerDTO) {
        this.name = registerDTO.nome();
        this.lastName = registerDTO.sobrenome();
        this.birthday = registerDTO.getDate();
<<<<<<< HEAD
        this.country = registerDTO.pais();
        this.phone = registerDTO.telefone();
        this.newsletterCheck = registerDTO.newsletterCheck();
    }
}
=======
    }
}
>>>>>>> 0861db8e17ca107accefe0fd0ce002247b49ad84
