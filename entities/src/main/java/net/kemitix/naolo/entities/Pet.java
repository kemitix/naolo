package net.kemitix.naolo.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.time.ZonedDateTime;

@Entity
@NamedQuery(name = Pet.FIND_ALL,
        query = "select p from Pet p order by p.name")
@With
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Pet {
    public static final String FIND_ALL = "Pet.FindAll";
    @Id
    @GeneratedValue
    Long id;
    String name;
    ZonedDateTime dateOfBirth;
    PetType type;
    Owner owner;
}
