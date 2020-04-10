package net.kemitix.naolo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = Veterinarian.FIND_ALL,
        query = "Select v From Veterinarian v Order By v.name")
@With
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Veterinarian {
    public static final String FIND_ALL = "Veterinarian.FindAll";
    @Id
    @GeneratedValue
    Long id;
    String name;
    @ElementCollection(targetClass = VetSpecialisation.class)
    @CollectionTable(
            name = "vet_specialisation",
            joinColumns = @JoinColumn(name = "vet_id"))
    @Column(name = "specialisation_id")
    List<VetSpecialisation> specialisations;
}
