package net.kemitix.naolo.entities;

import lombok.*;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.time.LocalDate;

@EntityListeners({
        JPAActivityListener.class
})
@Entity
@NamedQuery(name = Pet.FIND_ALL,
        query = "select p from Pet p order by p.name")
@With
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Pet
        implements HasId {

    public static final String FIND_ALL = "Pet.FindAll";

    @Id
    @GeneratedValue
    long id;
    String name;
    @JsonbDateFormat("yyyy-MM-dd")
    LocalDate dateOfBirth;
    PetType type;

    @ManyToOne
    Owner owner;
}

