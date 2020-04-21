package net.kemitix.naolo.entities;

import lombok.*;
import net.kemitix.naolo.storage.spi.HasId;

import javax.persistence.*;
import java.util.List;

@EntityListeners({
        JPAActivityListener.class
})
@Entity
@NamedQuery(name = Veterinarian.FIND_ALL,
        query = "Select v From Veterinarian v Order By v.name")
@With
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Veterinarian
        implements HasId {

    public static final String FIND_ALL = "Veterinarian.FindAll";

    @Id
    @GeneratedValue
    long id;
    String name;
    @ElementCollection(
            targetClass = VetSpecialisation.class,
            fetch = FetchType.EAGER)
    @CollectionTable(
            name = "vet_specialisation",
            joinColumns = @JoinColumn(name = "vet_id"))
    @Column(name = "specialisation_id")
    List<VetSpecialisation> specialisations;

    public Veterinarian() {
    }

    public Veterinarian(
            final long id,
            final String name,
            final List<VetSpecialisation> specialisations
    ) {
        this.id = id;
        this.name = name;
        this.specialisations = specialisations;
    }
}
