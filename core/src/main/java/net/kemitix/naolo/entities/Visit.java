package net.kemitix.naolo.entities;


import lombok.*;
import net.kemitix.naolo.storage.HasId;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners({
        JPAActivityListener.class
})
@Entity
@NamedQuery(name = Visit.FIND_ALL,
        query = "select v from Visit v order by v.dateTime")
@With
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Visit
        implements HasId {

    public static final String FIND_ALL = "Visit.FindAll";

    @Id
    @GeneratedValue
    long id;
    @ManyToOne
    Pet pet;
    @ManyToOne
    Veterinarian veterinarian;
    @JsonbDateFormat("yyyy-MM-dd HH:mm")
    LocalDateTime dateTime;
    String description;

    public Visit() {
    }

    public Visit(
            final long id,
            final Pet pet,
            final Veterinarian veterinarian,
            final LocalDateTime dateTime,
            final String description
    ) {
        this.id = id;
        this.pet = pet;
        this.veterinarian = veterinarian;
        this.dateTime = dateTime;
        this.description = description;
    }
}
