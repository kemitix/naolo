package net.kemitix.naolo.entities;

import lombok.*;
import net.kemitix.naolo.storage.spi.HasId;

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
@Setter
@Getter
@ToString
@EqualsAndHashCode
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

    public Pet() {
    }

    public Pet(
            final long id,
            final String name,
            final LocalDate dateOfBirth,
            final PetType type,
            final Owner owner
    ) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
        this.owner = owner;
    }
}

