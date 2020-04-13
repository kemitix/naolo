package net.kemitix.naolo.entities;


import lombok.*;

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
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(force = true)
@AllArgsConstructor
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
}
